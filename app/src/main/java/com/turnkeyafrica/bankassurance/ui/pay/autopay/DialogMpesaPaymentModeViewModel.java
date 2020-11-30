package com.turnkeyafrica.bankassurance.ui.pay.autopay;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

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
