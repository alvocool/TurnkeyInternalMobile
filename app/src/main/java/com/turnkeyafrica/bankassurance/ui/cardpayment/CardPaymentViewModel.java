package com.turnkeyafrica.bankassurance.ui.cardpayment;

import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.data.model.others.MiscData;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;

public class CardPaymentViewModel extends BaseViewModel<CardPaymentNavigator> {

   public ObservableField<String> amountDetails = new ObservableField<>();

   private AlertDialog alertDialog;

    public CardPaymentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setPaymentAmount(String amountDetails) {

        this.amountDetails.set(amountDetails);

    }

    public  void payNow(){

        if(getNavigator().verifyValues()){

            alertDialog = getNavigator().openLoading();
            Gson gson = new Gson();
            BigDecimal quoteCode =  gson.fromJson(getDataManager().getInsuranceQuote(), InsuranceQuoteResponce.class).getInsuranceQuotation().getQuotCode();

            CardDetailsRequest cardDetailsRequest = getNavigator().getCardDetails();

            getCompositeDisposable().add(getDataManager()
                    .paymentCard(cardDetailsRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(responce -> {
                        if (responce != null && responce.getTransactionStatus() != null) {
                            if(responce.getTransactionStatus().equalsIgnoreCase("ACCEPT")) {
                                ConvertQuote(quoteCode,new BigDecimal(cardDetailsRequest.getAmount()));
                            }else{
                                getNavigator().closeLoading(alertDialog);
                                getNavigator().handleError(new LocalError(0,"Payment unsuccessfully."));
                            }
                        }else{
                            getNavigator().closeLoading(alertDialog);
                        }
                    }, throwable -> {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(ErrorBase.Error(throwable));
                    }));

        }
    }

    private void ConvertQuote(BigDecimal quotCode, BigDecimal amountPaid) {
        Gson gson = new Gson();

        MiscData miscData = gson.fromJson(getDataManager().getMiscData(), MiscData.class);

        getCompositeDisposable().add(getDataManager()
                .convertToPolicy(quotCode,miscData.getIdNumber(),miscData.getKraNumber(), amountPaid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responce -> {
                    getNavigator().paymentSuccessful(responce.getPolicyNo(),responce.getBatchNo().toString());
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));

    }

    public void showDialog(int option){
        getNavigator().showDialogType(option);
    }

}
