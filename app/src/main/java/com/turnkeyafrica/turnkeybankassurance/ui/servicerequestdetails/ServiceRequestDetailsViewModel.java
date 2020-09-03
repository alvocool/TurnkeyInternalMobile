package com.turnkeyafrica.turnkeybankassurance.ui.servicerequestdetails;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class ServiceRequestDetailsViewModel extends BaseViewModel<ServiceRequestDetailsNavigator> {

    public ObservableField<String> ticketNo = new ObservableField<>();

    public ObservableField<String> polNo = new ObservableField<>();

    public ObservableField<String> dateSubmitted = new ObservableField<>();

    public ObservableField<String> requestCategory = new ObservableField<>();

    public ObservableField<String> status = new ObservableField<>();

    public ObservableField<String> vehicle = new ObservableField<>();

    public ObservableField<String> description = new ObservableField<>();

    public ObservableField<String> comments = new ObservableField<>();

    public ServiceRequestDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
