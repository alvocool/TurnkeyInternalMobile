package com.turnkeyafrica.bankassurance.ui.successstep;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class SuccessStepViewModel extends BaseViewModel<SuccessStepNavigator> {

    public ObservableField<String> title = new ObservableField<>();

    public ObservableField<String> subTitle = new ObservableField<>();

    public SuccessStepViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void login(){
        getNavigator().login();
    }
}
