package com.turnkeyafrica.bankassurance.ui.editprofile;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;


public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator> {

    public final ObservableField<String> clientFirstName = new ObservableField<>();
    public final ObservableField<String> clientSurname = new ObservableField<>();
    public final ObservableField<String> clientNumber = new ObservableField<>();
    public final ObservableField<String> clientEmail = new ObservableField<>();


    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void updateProfile() {
        getNavigator().updateProfile();
    }

    public void setProfileDetails(ClientDetailsResponce profileDetails) {

        if(CommonUtils.ObjectIsNotNull(profileDetails)) {

            if (profileDetails.getAcwaName() != null) {
                clientFirstName.set(profileDetails.getAcwaName());
            }if(profileDetails.getAcwaUsername() != null){
                clientSurname.set(profileDetails.getAcwaUsername());
            }if(profileDetails.getAcwaMobileNumber() != null){
                clientNumber.set(profileDetails.getAcwaMobileNumber());
            } if(profileDetails.getAcwaEmailAddrs() != null){
                clientEmail.set(profileDetails.getAcwaEmailAddrs());
            }
        }
    }

}
