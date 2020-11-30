package com.turnkeyafrica.bankassurance.ui.qoutedetails;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.data.model.others.MainDetailsQuote;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableField;


public class QuoteDetailsViewModel extends BaseViewModel<QuoteDetailsNavigator> {

    public final ObservableField<String> amount = new ObservableField<>();
    public final ObservableField<String> insurer = new ObservableField<>();
    public final ObservableField<String> coverType = new ObservableField<>();
    public final ObservableField<Boolean> savedStatus = new ObservableField<>();

    BigDecimal quoteCode;

    private List<DetailsResponce> limits = new ArrayList<>();
    private List<DetailsResponce> excess = new ArrayList<>();

    public QuoteDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void buyQuote(){

        getNavigator().buyQuote();
    }

    public void saveQoute(){

       getNavigator().saveQuote();

    }

    public void saveQuoteToDb(){
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .saveQuote(quoteCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responce -> {
                    if (responce) {
                        getNavigator().openDashboard();
                    }else {
                        getNavigator().handleError(new LocalError(0,"Failed to save quotation \n \n Please retry."));
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void getLimits(BigDecimal bindCode, String schvType){

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getDetails(bindCode,schvType)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(detailsResponces -> {
                    if(detailsResponces != null){
                        limits = detailsResponces;
                    }

                    getExcesses(bindCode);
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getExcesses(bindCode);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void getExcesses(BigDecimal bindCode){

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getDetails(bindCode, "E")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(detailsResponces -> {
                    if (detailsResponces != null) {
                        excess = detailsResponces;
                    }
                    setAdapter();
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    setAdapter();
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void setAdapter() {

        getNavigator().setAdapter(limits,excess);
    }

    public void getQuoteDetails(MainDetailsQuote mainDetailsQuote){

        if(mainDetailsQuote != null) {

            amount.set(mainDetailsQuote.getAmount() + " " + "per year");
            insurer.set(mainDetailsQuote.getInsurer());
            coverType.set(mainDetailsQuote.getCoverType());

            if(!CommonUtils.StringIsEmpty(mainDetailsQuote.getQuotSaved())) {
                if (mainDetailsQuote.getQuotSaved().equalsIgnoreCase("Y")){
                    savedStatus.set(false);
                }else{
                    savedStatus.set(true);
                }
            }else{
                savedStatus.set(true);
            }

            String schvType = "L";

            getLimits(mainDetailsQuote.getBindCode(),schvType);

        }
    }

    public void getQuotebyCode(BigDecimal quoteID) {

        quoteCode = quoteID;
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getQuotebyCode(quoteID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(quotesResponse -> {
                    if (quotesResponse != null) {
                        getDataManager().setQuoteResponce(quotesResponse);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));

    }
}
