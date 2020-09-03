package com.turnkeyafrica.turnkeybankassurance.ui.capturedamageimages;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class CaptureDamageImagesViewModel extends BaseViewModel<CaptureDamageImagesNavigator>{

    private int imageOption;

    public CaptureDamageImagesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void showPictureOptions(int option){
        imageOption = option;
        getNavigator().showPictureOptions(option);
    }

    public void removePicture(int option){
        getNavigator().removePhoto(option);
    }

    public void next(){
        getNavigator().proceed();
    }

    public void choosePhoto(){
        getNavigator().choosePhoto();
    }

    public void takePhoto(){
        if(imageOption == 1) {
            getNavigator().takePhoto("IncidentPhotos1");
        }else if(imageOption == 2){
            getNavigator().takePhoto("IncidentPhotos2");
        }else if(imageOption == 3){
            getNavigator().takePhoto("IncidentPhotos3");
        }
    }
}
