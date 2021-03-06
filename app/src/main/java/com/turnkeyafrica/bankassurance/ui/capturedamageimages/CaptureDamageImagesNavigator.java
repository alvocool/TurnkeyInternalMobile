package com.turnkeyafrica.bankassurance.ui.capturedamageimages;

public interface CaptureDamageImagesNavigator {
    
    void showPictureOptions(int option);

    void removePhoto(int option);

    void proceed();

    void takePhoto(String name);

    void choosePhoto();
}
