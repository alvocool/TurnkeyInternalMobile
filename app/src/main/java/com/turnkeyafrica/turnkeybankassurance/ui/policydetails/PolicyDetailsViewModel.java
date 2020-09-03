package com.turnkeyafrica.turnkeybankassurance.ui.policydetails;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.MainDetailsPolicy;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableField;

public class PolicyDetailsViewModel extends BaseViewModel<PolicyDetailsNavigator> {

    public final ObservableField<String> amount = new ObservableField<>();
    public final ObservableField<String> inceptionDate = new ObservableField<>();
    public final ObservableField<String> insurer = new ObservableField<>();
    public final ObservableField<String> renewableDate = new ObservableField<>();
    public final ObservableField<String> coverType = new ObservableField<>();
    public final ObservableField<String> expiredDate = new ObservableField<>();
    private List<DetailsResponce> limits = new ArrayList<>();
    private List<DetailsResponce> excess = new ArrayList<>();

    public PolicyDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

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
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }


    private void setAdapter() {

        getNavigator().setAdapter(limits,excess);
    }

    public void getPolicyDetails(MainDetailsPolicy mainDetailsPolicy){

        if(mainDetailsPolicy != null) {

            amount.set(mainDetailsPolicy.getAmount());
            insurer.set(mainDetailsPolicy.getInsurer());
            renewableDate.set(mainDetailsPolicy.getRenewableDate());
            inceptionDate.set(mainDetailsPolicy.getStartDate());
            coverType.set(mainDetailsPolicy.getCoverType());
            expiredDate.set(mainDetailsPolicy.getExpiredDate());
            String schvType = "L";

            getLimits(mainDetailsPolicy.getBindCode(),schvType);
        }
    }
}
