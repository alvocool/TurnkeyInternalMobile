package com.turnkeyafrica.bankassurance.ui.pay;


import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaCheckModel;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.data.model.others.MiscData;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;

public class PayViewModel extends BaseViewModel<PayNavigator> {

    public final ObservableField<String>  paySubtitle = new ObservableField<>();

    public final ObservableField<String>  payOptions = new ObservableField<>();

    public PayViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void sdkPush(MpesaRequest mpesaRequest, BigDecimal quoteCode){

        AlertDialog alertDialog = getNavigator().openLoading("Processing Payment");
        getCompositeDisposable().add(getDataManager()
                .payment(mpesaRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    if (response != null && response.getCheckoutRequestID() != null) {

                        MpesaCheckModel mpesaCheckModel = new MpesaCheckModel(response.getCheckoutRequestID(),
                                quoteCode,
                                mpesaRequest.getAgentCode(),
                                mpesaRequest.getAmount());
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().checkMpesaPayment(mpesaCheckModel);
                    }else{
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(new LocalError(0,"Failed to send sdk push." +
                                " Please proceed to manual payment."));
                    }
                }, throwable -> {getNavigator().handleError(ErrorBase.Error(throwable));
                    getNavigator().closeLoading(alertDialog);

                }));
    }


    public void pay() {
        Analytics.trackEvent("Payment Done Clicked");
        getNavigator().completePayment();
    }

    public void verifyPayment(String CheckOutId, BigDecimal agentCode,BigDecimal quoteCode, BigDecimal amount) {

        Analytics.trackEvent("Verify Payment Clicked");

        AlertDialog alertDialog = getNavigator().openLoading("Verifying Payment");

        getCompositeDisposable().add(getDataManager()
                .verifyPayment(CheckOutId,agentCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responses -> {
                    if (responses != null && responses.getCheckoutRequestID() != null) {

                        int responseCode = responses.getResultCode().intValueExact();

                        switch (responseCode){
                            case 0:
                                convertQuote(quoteCode,amount);
                                getNavigator().closeLoading(alertDialog);
                                return;
                            case 1031:
                            case 2001:
                                getNavigator().handleError(new LocalError(34, responses.getMessage()));
                                getNavigator().closeLoading(alertDialog);
                                return;
                            default:
                                getNavigator().handleError(new LocalError(34, "Error while processing payment."));
                                getNavigator().closeLoading(alertDialog);
                        }
                    }
                }, throwable -> {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getManualDetails(String agent, String amount, String refNo) {

        AlertDialog alertDialog = getNavigator().openLoading("Loading");
        getCompositeDisposable().add(getDataManager()
                .getMpesaPaymentInfo(agent, amount, refNo)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() ->   getNavigator().closeLoading(alertDialog))
                .subscribe(response -> {
                    int itemNo = 1;
                    StringBuilder payDetails = new StringBuilder("\n");
                    for(String item: response) {

                        String value = itemNo + ". " + item + " \n \n";

                        payDetails.append(value);

                        ++itemNo;

                    }
                    this.payOptions.set(payDetails.toString());

                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));

    }

    public void verifyManualPayment(String mpesaCode, String agentNo,
                                    String quoteNo, String amount,String phoneNumber) {

        Analytics.trackEvent("Verify Manual Payment Clicked");

        AlertDialog alertDialog = getNavigator().openLoading("Verifying Payment");

        getCompositeDisposable().add(getDataManager()
                .checkMpesaResultsManually(mpesaCode, agentNo,quoteNo,amount,
                        getDataManager().getCurrentUserName(),phoneNumber
                        )
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responses -> {

                    getNavigator().closeLoading(alertDialog);
                    if (responses) {
                        getNavigator().convertQuote();
                    }else
                    {
                        getNavigator().handleError(new LocalError(34, "Error while processing payment."));
                    }

                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));

    }

    public void convertQuote(BigDecimal quotCode, BigDecimal amount) {
        Gson gson = new Gson();

        MiscData miscData = gson.fromJson(getDataManager().getMiscData(), MiscData.class);

        AlertDialog alertDialog = getNavigator().openLoading("Loading");
        getCompositeDisposable().add(getDataManager()
                .convertToPolicy(quotCode,miscData.getIdNumber(),miscData.getKraNumber(), amount)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() ->   getNavigator().closeLoading(alertDialog))
                .subscribe(response -> getNavigator().paymentSuccessful(response.getPolicyNo(),response.getBatchNo().toString()),
                        throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));

    }

    void setMpesaAmount(String paySubtitle){
        this.paySubtitle.set(paySubtitle);
    }



}
