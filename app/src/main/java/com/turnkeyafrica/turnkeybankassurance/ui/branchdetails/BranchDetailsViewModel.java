package com.turnkeyafrica.turnkeybankassurance.ui.branchdetails;

import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class BranchDetailsViewModel extends BaseViewModel<BranchDetailsNavigator> {

    private  BranchResponce branchResponce;

    private  String batchNo;

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> location = new ObservableField<>();

    public BranchDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void selectBranch(){

        AlertDialog alertDialog = getNavigator().openLoading();

        getCompositeDisposable().add(getDataManager()
                .selectCeritificatePickupPoint(branchResponce.getCode(),batchNo)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responces -> {
                    if(responces) {
                        getNavigator().openPaymentSuccessFull();
                        getNavigator().closeLoading(alertDialog);
                    }else {
                        getNavigator().handleError(new LocalError(0,"Failed to select branch. \n \n Try again"));
                        getNavigator().closeLoading(alertDialog);
                    }
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void setBranch(BranchResponce branchResponce, String batchNo){

        this.branchResponce = branchResponce;
        this.batchNo = batchNo;

        this.name.set(branchResponce.getDesc());
        this.location.set(branchResponce.getPhysicalAddress());
    }

}
