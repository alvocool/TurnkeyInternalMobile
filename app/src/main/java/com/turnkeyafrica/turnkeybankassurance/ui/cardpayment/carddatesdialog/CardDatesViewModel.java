package com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;


public class CardDatesViewModel extends BaseViewModel<CardDatesCallback> {


    public CardDatesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void close() {
        getNavigator().close();
    }

}
