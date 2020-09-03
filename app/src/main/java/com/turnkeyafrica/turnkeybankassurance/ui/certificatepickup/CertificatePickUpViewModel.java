package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup;


import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;

public class CertificatePickUpViewModel extends BaseViewModel<CertificatePickUpNavigator> {

    public CertificatePickUpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void getBranchesRegions() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getBranchesRegions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responce -> {
                    if (responce != null) {
                        getNavigator().setBranchRegions(responce);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getBranchesByLocation(BigDecimal regionCode) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getBranchesByRegionCode(regionCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(branchResponce -> {
                    if (branchResponce != null) {
                        getNavigator().setBranches(branchResponce);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void selectBranch(){
        getNavigator().loadBranches();
    }

}
