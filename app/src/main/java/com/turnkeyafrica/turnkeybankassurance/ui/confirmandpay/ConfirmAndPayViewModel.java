package com.turnkeyafrica.turnkeybankassurance.ui.confirmandpay;


import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;
import java.math.BigDecimal;

public class ConfirmAndPayViewModel extends BaseViewModel<ConfirmAndPayNavigator> {

    public final ObservableField<String> insurer = new ObservableField<>();
    public final ObservableField<String> coverType = new ObservableField<>();
    public final ObservableField<String> quoteRef = new ObservableField<>();
    public final ObservableField<String> inceptionDate = new ObservableField<>();
    public final ObservableField<String> renewalsDate = new ObservableField<>();


    private String quoteRefNo;

    public AlertDialog alertDialog;


    public ConfirmAndPayViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void payNow(){
        getNavigator().payNow();
    }

    public void showTermsAndConditions(){
        getNavigator().showTermsAndConditions();
    }

    public void mpesaPayment(String amount, BigDecimal quoteCode, BigDecimal agentCode) {

        String mobile = getDataManager().getCurrentUserMobileNumber();

        MpesaRequest mpesaRequest = new MpesaRequest();
        mpesaRequest.setAccountReference(quoteRefNo);
        mpesaRequest.setTransactionDesc("PREMIUM");
        mpesaRequest.setTransactionType("NB");
        mpesaRequest.setAmount(new BigDecimal(amount));
        mpesaRequest.setAgentCode(agentCode);
        mpesaRequest.setSenderName(getDataManager().getCurrentUserName());
        mpesaRequest.setMobileNumber(mobile);

        getNavigator().mpesaPayment(mpesaRequest,quoteCode);
    }






    public void payOption(int option){
        getNavigator().setPaymentOption(option);
    }

    public void showPaymentModes(){
        getNavigator().showPaymentModes();
    }

    public void setHeaders(String insurer,String coverType,String quoteRef,String inceptionDate,String renewalsDate, String quotationRef){
        this.insurer.set(insurer);
        this.coverType.set(coverType);
        this.quoteRef.set(quoteRef);
        this.inceptionDate.set(inceptionDate);
        this.renewalsDate.set(renewalsDate);
        this.quoteRefNo = quotationRef;
    }

    public void editQuote(){

        alertDialog = getNavigator().openLoading("Loading");

        Gson gson = new Gson();

        BigDecimal quoteCode = gson.fromJson(getDataManager().getInsuranceQuote(), InsuranceQuoteResponce.class).getInsuranceQuotation().getQuotCode();

        getCompositeDisposable().add(getDataManager()
                .editQuote(quoteCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() ->  getNavigator().closeLoading(alertDialog))
                .subscribe(response -> {
                    getDataManager().clearComparisonRequest();
                    getDataManager().setComparisonRequest(response);
                    getNavigator().editQuoteDetails();
                }, throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));

    }
}
