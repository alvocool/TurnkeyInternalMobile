package com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful;


import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

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
