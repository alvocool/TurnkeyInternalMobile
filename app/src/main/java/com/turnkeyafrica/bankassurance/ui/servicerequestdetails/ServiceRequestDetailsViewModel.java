package com.turnkeyafrica.bankassurance.ui.servicerequestdetails;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

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
