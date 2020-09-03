package com.turnkeyafrica.turnkeybankassurance.ui.pay.autopay;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class DialogMpesaPaymentModeViewModel extends BaseViewModel<DialogMpesaPaymentModeCallback> {
    public DialogMpesaPaymentModeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void intiateMpesaSdk(){
        getNavigator().intiatePayment();
    }

    public void close(){
        getNavigator().close();
    }
}
