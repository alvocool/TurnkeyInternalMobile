package com.turnkeyafrica.bankassurance.ui.enterclaimdetails;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class EnterClaimDetailsViewModel extends BaseViewModel<EnterClaimDetailsNavigator> {

    private int pictureOption;

    public EnterClaimDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void verifyClaimDetails(){
        getNavigator().verifyClaimDetails();
    }

    public void removePicture(int option){
        getNavigator().removePhoto(option);
    }

    public void takePhoto(){
        if(pictureOption == 1) {
            getNavigator().takePhoto("incidentReport");
        }else if(pictureOption == 2){
            getNavigator().takePhoto( "myDl");
        }
    }

    public void choosePhoto(){
        getNavigator().choosePhoto();
    }

    public void showPictureOptions(int option){
        pictureOption = option;
        getNavigator().pictureOptions(option);
    }

    public void showPropertyId(){
        getNavigator().showVehicleRegs();
    }

    public void showDate(){
        getNavigator().showDatePicker();
    }
}
