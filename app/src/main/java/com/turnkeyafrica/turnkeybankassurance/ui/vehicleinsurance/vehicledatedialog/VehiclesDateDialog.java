
package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.DialogVehiclesDateBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.EnterClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.Calendar;
import java.util.Objects;
import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;


public class VehiclesDateDialog extends BaseDialog implements VehiclesDateDialogcallback {

    private static final String TAG = VehiclesDateDialog.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private DatePicker datePicker;

    private VehiclesDateDialogViewModel mVehiclesDateDialogViewModel;

    private int stage;

    public static VehiclesDateDialog newInstance() {
        VehiclesDateDialog fragment = new VehiclesDateDialog();
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

    @Override
    public void setDate() {
        if(stage == 1) {
            ((VehicleInsuranceActivity) Objects.requireNonNull(getActivity())).formatDate(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
        }else if (stage == 2){
            ((EnterClaimDetailsActivity) Objects.requireNonNull(getActivity())).formatDate(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
        }

        close();
    }

    private void setTypes(DialogVehiclesDateBinding binding) {

        datePicker = binding.carDatePicker;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if(stage == 1) {
            datePicker.setMinDate(System.currentTimeMillis() - 1000);
        }else if (stage ==2){
            datePicker.setMinDate(System.currentTimeMillis() - 15*24*60*60*1000);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), (datePicker1, year, month, dayOfMonth) -> {

            if(stage == 1) {
                ((VehicleInsuranceActivity) Objects.requireNonNull(getActivity())).formatDate(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
            }else if (stage == 2){
                ((EnterClaimDetailsActivity) Objects.requireNonNull(getActivity())).formatDate(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
            }
            close();
        });

        }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogVehiclesDateBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_vehicles_date, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mVehiclesDateDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(VehiclesDateDialogViewModel.class);
        binding.setViewModel(mVehiclesDateDialogViewModel);

        mVehiclesDateDialogViewModel.setNavigator(this);

        setTypes(binding);

        return view;
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager, int stage) {

        this.stage = stage;
        super.show(fragmentManager, TAG);
    }
}
