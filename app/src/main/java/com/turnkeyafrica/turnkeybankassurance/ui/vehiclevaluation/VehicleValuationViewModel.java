package com.turnkeyafrica.turnkeybankassurance.ui.vehiclevaluation;


import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class VehicleValuationViewModel extends BaseViewModel<VehicleValuationNavigator> {

    public VehicleValuationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void uploadValuation(){ getNavigator().uploadValuation();}

    public void showPictureOptions(){

        getNavigator().pictureOptions();
    }

    public void removePicture(){

        getNavigator().removePhoto();

    }

    public void uploadVehicleValuation(UploadRequest uploadRequest) {

        getCompositeDisposable().add(getDataManager()
                .uploadDocuments(uploadRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responses -> {
                    if(responses) {
                        getNavigator().dismissUploadDialog();
                    }else {
                        getNavigator().exitDialog();
                        getNavigator().handleError(new LocalError(0,"Upload failed."));
                    }
                }, throwable -> {
                    getNavigator().exitDialog();
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void choosePhoto() {
        getNavigator().choosePhoto();
    }

    public void takePhoto() {
        getNavigator().takePhoto();
    }
}
