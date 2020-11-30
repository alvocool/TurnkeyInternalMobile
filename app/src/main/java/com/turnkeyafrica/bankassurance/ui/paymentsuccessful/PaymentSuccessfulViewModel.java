package com.turnkeyafrica.bankassurance.ui.paymentsuccessful;


import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class PaymentSuccessfulViewModel extends BaseViewModel<PaymentSuccessfulNavigator> {

    public PaymentSuccessfulViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void remindLater(){

        getNavigator().openDashBoard();
    }

    public void uploadValuation(){
        getNavigator().uploadValuation();
    }

    public void findValuer(){
        getNavigator().findValuer();
    }

    public void certificatePickup(){
        getNavigator().certificatePickup();
    }
}
