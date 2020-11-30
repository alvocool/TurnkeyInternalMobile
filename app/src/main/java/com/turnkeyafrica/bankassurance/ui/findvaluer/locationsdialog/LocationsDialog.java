
package com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.DialogLocationsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog.adapter.locationsAdapter;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LocationsDialog extends BaseDialog implements LocationsDialogcallback {

    private static final String TAG = LocationsDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private LocationsDialogViewModel mLocationsDialogViewModel;

    private DialogLocationsBinding nDialogLocationsBinding;

    private  List<String> locations;

    public static LocationsDialog newInstance() {
        LocationsDialog fragment = new LocationsDialog();
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


    private void setTypes(List<String> locationList) {

        locationsAdapter locationsAdapter = new locationsAdapter(getContext(), locationList);
        nDialogLocationsBinding.locations.setAdapter(locationsAdapter);

        nDialogLocationsBinding.locations.setOnItemClickListener((parent, view, position, id) -> {


            String itemAtPosition = (String) nDialogLocationsBinding.locations.getItemAtPosition(position);

            ((FindValuerActivity) Objects.requireNonNull(getActivity())).setNewLocation(itemAtPosition);

            close();
        });

        }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogLocationsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_locations, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mLocationsDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(LocationsDialogViewModel.class);
        binding.setViewModel(mLocationsDialogViewModel);

        mLocationsDialogViewModel.setNavigator(this);

        nDialogLocationsBinding = binding;

        this.setTypes(locations);

        return view;
    }


    public void show(FragmentManager fragmentManager, List<String> responces) {

        this.locations = responces;
        super.show(fragmentManager, TAG);
    }
}
