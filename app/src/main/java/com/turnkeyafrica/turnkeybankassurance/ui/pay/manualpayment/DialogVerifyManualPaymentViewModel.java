package com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class DialogVerifyManualPaymentViewModel extends BaseViewModel<DialogVerifyManualPaymentCallback> {
    public DialogVerifyManualPaymentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void verifyPayment(){
        getNavigator().verifyPayment();
    }

    public void close(){
        getNavigator().close();
    }

}
