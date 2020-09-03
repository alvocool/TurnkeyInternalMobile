
package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.RiskResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.VehicleMakesResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.DialogVehiclesModelBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.EnterClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.adapter.RisksModelsAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog.adapter.modelsAdapter;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public class VehicleModelsDialog extends BaseDialog implements VehicleModelsDialogcallback {

    private static final String TAG = VehicleModelsDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private VehicleModelsDialogViewModel mVehicleModelsDialogViewModel;

    private DialogVehiclesModelBinding dialogVehiclesModelBinding;

    private  List<VehicleMakesResponce> vehicleMakesResponces;
    private List<RiskResponce> riskResponses;
    private int category;

    public static VehicleModelsDialog newInstance() {
        VehicleModelsDialog fragment = new VehicleModelsDialog();
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

    private void setTypes(List<VehicleMakesResponce> vehicleMakesResponceList, List<RiskResponce> riskResponses) {

        if(category == 1) {
            modelsAdapter modelsAdapter = new modelsAdapter(getContext(), vehicleMakesResponceList);
            dialogVehiclesModelBinding.vehicleModelsTypes.setAdapter(modelsAdapter);

            dialogVehiclesModelBinding.vehicleModelsTypes.setOnItemClickListener((parent, view, position, id) -> {


                VehicleMakesResponce itemAtPosition = (VehicleMakesResponce) dialogVehiclesModelBinding.vehicleModelsTypes.getItemAtPosition(position);

                ((VehicleInsuranceActivity) Objects.requireNonNull(getActivity())).setMakeName(itemAtPosition.getVmMake());

                close();
            });


        }else if(category == 2){
            RisksModelsAdapter modelsAdapter = new RisksModelsAdapter(getContext(), riskResponses);
            dialogVehiclesModelBinding.vehicleModelsTypes.setAdapter(modelsAdapter);

            dialogVehiclesModelBinding.vehicleModelsTypes.setOnItemClickListener((parent, view, position, id) -> {


                RiskResponce itemAtPosition = (RiskResponce) dialogVehiclesModelBinding.vehicleModelsTypes.getItemAtPosition(position);

                ((EnterClaimDetailsActivity) Objects.requireNonNull(getActivity())).setPropertyId(itemAtPosition);

                close();
            });
        }else if(category ==3){

            RisksModelsAdapter modelsAdapter = new RisksModelsAdapter(getContext(), riskResponses);
            dialogVehiclesModelBinding.vehicleModelsTypes.setAdapter(modelsAdapter);

            dialogVehiclesModelBinding.vehicleModelsTypes.setOnItemClickListener((parent, view, position, id) -> {


                RiskResponce itemAtPosition = (RiskResponce) dialogVehiclesModelBinding.vehicleModelsTypes.getItemAtPosition(position);

                ((EnterServiceRequestDetailsActivity) Objects.requireNonNull(getActivity())).setPropertyId(itemAtPosition);

                close();
            });
        }

        }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogVehiclesModelBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_vehicles_model, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mVehicleModelsDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(VehicleModelsDialogViewModel.class);
        binding.setViewModel(mVehicleModelsDialogViewModel);

        mVehicleModelsDialogViewModel.setNavigator(this);

        dialogVehiclesModelBinding = binding;

        this.setTypes(vehicleMakesResponces,riskResponses);

        //filterModels(binding);

        return view;
    }

/*
    private void filterModels(DialogModelBinding binding) {

       TextInputEditText inputEditText = binding.modelSearch;
       inputEditText.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {

          //filter values
               return false;
           }
       });
    }
*/

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager, List<VehicleMakesResponce> vehicleMakesResponses, List<RiskResponce> riskResponses, int option) {

        this.vehicleMakesResponces = vehicleMakesResponses;

        this.riskResponses = riskResponses;

        this.category = option;

        super.show(fragmentManager, TAG);
    }
}
