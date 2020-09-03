package com.turnkeyafrica.turnkeybankassurance.ui.profile;

import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {

    public final ObservableField<String> clientFirstName = new ObservableField<>();
    public final ObservableField<String> clientSurname = new ObservableField<>();
    public final ObservableField<String> clientNumber = new ObservableField<>();
    public final ObservableField<String> clientEmail = new ObservableField<>();
    public final ObservableField<String> clientRegistrationDate = new ObservableField<>();
    public final ObservableField<String> clientStatus = new ObservableField<>();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void editProfile() {
        getNavigator().editProfile();
    }

    public void getClientDetails() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientDetails(getDataManager().getAccessToken())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().closeLoading(alertDialog);
                    setProfileDetails(response);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void setProfileDetails(ClientDetailsResponce profileDetails) {

        if(CommonUtils.ObjectIsNotNull(profileDetails)) {

            getNavigator().setClientDetails(profileDetails);
            if (profileDetails.getAcwaName() != null) {
                clientFirstName.set(profileDetails.getAcwaName());
            }if(profileDetails.getAcwaUsername() != null){
                clientSurname.set(profileDetails.getAcwaUsername());
            }if(profileDetails.getAcwaMobileNumber() != null){
                clientNumber.set(profileDetails.getAcwaMobileNumber());
            } if(profileDetails.getAcwaEmailAddrs() != null){
                clientEmail.set(profileDetails.getAcwaEmailAddrs());
            } if(profileDetails.getAcwaDtCreated() != null){
                clientRegistrationDate.set(profileDetails.getAcwaDtCreated());
            } if(profileDetails.getAcwaStatus() != null){
                if(profileDetails.getAcwaStatus().equalsIgnoreCase("A")) {
                    clientStatus.set("Verified");
                }else {
                    clientStatus.set("Unverified");
                }
            }
        }
    }

}
