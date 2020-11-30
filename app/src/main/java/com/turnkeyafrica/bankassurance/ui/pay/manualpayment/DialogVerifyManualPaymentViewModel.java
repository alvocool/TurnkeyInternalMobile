package com.turnkeyafrica.bankassurance.ui.pay.manualpayment;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

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
