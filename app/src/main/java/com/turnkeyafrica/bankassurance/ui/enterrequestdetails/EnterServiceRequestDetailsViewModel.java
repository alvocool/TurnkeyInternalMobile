package com.turnkeyafrica.bankassurance.ui.enterrequestdetails;

import android.app.AlertDialog;

import androidx.databinding.ObservableBoolean;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequestResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class EnterServiceRequestDetailsViewModel extends BaseViewModel<EnterServiceRequestDetailsNavigator>{

    public ObservableBoolean hideFields = new ObservableBoolean(true);

    public EnterServiceRequestDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void showPictureOptions(){
        getNavigator().pictureOptions();
    }

    public void removePicture(){

        getNavigator().removePhoto();

    }

    public void getAllRequestCategories() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllServiceRequestTypes("M")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(()->{getNavigator().closeLoading(alertDialog);})
                .subscribe(requestTypes -> getNavigator().setRequestTypes(requestTypes), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }


    public void showPropertyId(){
        getNavigator().showPropertyId();
    }

    public void showRequestCategories(){getNavigator().showRequestCategories();}

    public void choosePhoto() {
        getNavigator().choosePhoto();
    }

    public void takePhoto() {
        getNavigator().takePhoto();
    }

    public void submitRequest(){ getNavigator().submitRequest(); }

    public void hideFields() {
        hideFields.set(false);
    }

    public void saveServiceRequest(ServiceRequestResponse serviceRequestResponse) {

        serviceRequestResponse.setClientCode(getDataManager().getCurrentClientId());

        serviceRequestResponse.setSource("MOBILE");

        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .saveServiceRequest(serviceRequestResponse)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(()->{getNavigator().closeLoading(alertDialog);})
                .subscribe(requestTypes -> getNavigator().openDashboard(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }
}
