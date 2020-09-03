package com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails;


import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class ValuatorDetailsViewModel extends BaseViewModel<ValuatorDetailsNavigator> {

    private  ValuersResponce valuersResponce;

    private  String batchNo;

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> location = new ObservableField<>();
    public final ObservableField<String> phoneNumber = new ObservableField<>();

    public ValuatorDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void selectValuer(){

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .emailValuer(valuersResponce.getSprCode(),batchNo)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responses -> {
                    if(responses) {
                        getNavigator().openDashboard();
                    }else {
                        getNavigator().handleError(new LocalError(0,"Failed to select valuer. \n \n Try again"));
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void setValuer(ValuersResponce valuer, String batchNo){

        valuersResponce = valuer;
        this.batchNo = batchNo;

        this.name.set(valuer.getSprName());
        this.location.set(valuer.getSprPhysicalAddress()+" ,"+valuer.getSprLocation()+" ,"+valuer.getSprPostalAddress());
        this.phoneNumber.set(valuer.getSprSmsNumber()+ "/"+ valuer.getSprPhone()+"/"+valuer.getSprMobileNo());
    }

}
