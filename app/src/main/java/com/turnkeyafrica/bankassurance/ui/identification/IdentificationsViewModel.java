package com.turnkeyafrica.bankassurance.ui.identification;


import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class IdentificationsViewModel extends BaseViewModel<IdentificationsNavigator> {

    public IdentificationsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void nextStep(){

        getNavigator().proceedToPayment();
    }

    public void showPictureOptions(){

        getNavigator().pictureOptions();
    }

    public void removePicture(){

        getNavigator().removePhoto();

    }

    public void uploadIdentification(UploadRequest uploadRequest) {

        getCompositeDisposable().add(getDataManager()
                .uploadDocuments(uploadRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responces -> {
                    if(responces) {
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

