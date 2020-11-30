
package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.CoverTypesResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequestType;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.DialogVehicleInsuranceTypesBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.enterrequestdetails.adapter.ServiceTypesAdapter;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.adapter.InsuranceTypesAdapter;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public class VehicleInsuranceTypeDialog extends BaseDialog implements VehicleInsuranceTypecallback {

    private static final String TAG = VehicleInsuranceTypeDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private VehicleInsuranceTypeDialogViewModel mVehicleInsuranceTypeDialogViewModel;

    private DialogVehicleInsuranceTypesBinding dialogVehicleInsuranceTypeBinding;

    private  List<CoverTypesResponce> coverTypesResponceList;
    private List<ServiceRequestType> serviceRequestTypes;
    private int option;

    public static VehicleInsuranceTypeDialog newInstance() {
        VehicleInsuranceTypeDialog fragment = new VehicleInsuranceTypeDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void close() {

        dismissDialog(TAG);
    }

    @Override
    public void handleError(LocalError error) {
        if (!CommonUtils.StringIsEmpty(error.getMessage())) {

            ViewUtils.showDialog(getContext(), "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
        }
    }

    private void setTypes(List<CoverTypesResponce> coverTypesResponceList,List<ServiceRequestType> serviceRequestTypes) {

        if(option == 1) {
            InsuranceTypesAdapter insuranceTypesAdapter = new InsuranceTypesAdapter(getContext(), coverTypesResponceList);
            dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.setAdapter(insuranceTypesAdapter);

            dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.setOnItemClickListener((parent, view, position, id) -> {

                CoverTypesResponce itemAtPosition = (CoverTypesResponce) dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.getItemAtPosition(position);

                ((VehicleInsuranceActivity) Objects.requireNonNull(getActivity())).setInsuranceType(itemAtPosition.getDesc(), itemAtPosition.getCode().intValueExact());

                close();
            });
        }else {
            ServiceTypesAdapter serviceTypesAdapter = new ServiceTypesAdapter(getContext(), serviceRequestTypes);
            dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.setAdapter(serviceTypesAdapter);

            dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.setOnItemClickListener((parent, view, position, id) -> {

                ServiceRequestType serviceRequestType = (ServiceRequestType) dialogVehicleInsuranceTypeBinding.vehicleInsuranceTypes.getItemAtPosition(position);

                ((EnterServiceRequestDetailsActivity) Objects.requireNonNull(getActivity())).setServiceRequestType(serviceRequestType);

                close();
            });
        }

   }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogVehicleInsuranceTypesBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_vehicle_insurance_types, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mVehicleInsuranceTypeDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(VehicleInsuranceTypeDialogViewModel.class);
        binding.setViewModel(mVehicleInsuranceTypeDialogViewModel);

        mVehicleInsuranceTypeDialogViewModel.setNavigator(this);

        dialogVehicleInsuranceTypeBinding = binding;

        this.setTypes(coverTypesResponceList,serviceRequestTypes);

        return view;
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager, List<CoverTypesResponce> responces, List<ServiceRequestType> serviceRequestTypes, int option) {
        this.coverTypesResponceList = responces;
        this.serviceRequestTypes = serviceRequestTypes;
        this.option = option;
        super.show(fragmentManager, TAG);
    }
}
