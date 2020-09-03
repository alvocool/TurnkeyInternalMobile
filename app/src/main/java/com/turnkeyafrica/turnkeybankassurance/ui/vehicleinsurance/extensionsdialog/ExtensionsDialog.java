
package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.extensionsdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.DialogExtensionsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import java.math.BigDecimal;
import java.util.Objects;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.AndroidSupportInjection;

public class ExtensionsDialog extends BaseDialog implements ExtensionsDialogcallback {

    private static final String TAG = ExtensionsDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private ExtensionsDialogViewModel mExtensionsDialogViewModel;
    private DialogExtensionsBinding mDialogExtensionsBinding;
    private int id;
    private String limitName;
    private String benefitsLimitDescription;

    public static ExtensionsDialog newInstance() {
        ExtensionsDialog fragment = new ExtensionsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void close() {
        String section_id = String.valueOf(id);
        CheckBox checkBox = getBaseActivity().findViewById(getResources().getIdentifier(section_id, "id", getBaseActivity().getPackageName()));
        checkBox.setChecked(false);
        dismissDialog(TAG);
    }

    public void simpleclose() {
        dismissDialog(TAG);
    }

    @Override
    public void handleError(LocalError error) {
        if (!CommonUtils.StringIsEmpty(error.getMessage())) {

            ViewUtils.showDialog(getContext(), "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void setLimit() {

      ComparisonRequest.ComparisonRequestSection section = new ComparisonRequest.ComparisonRequestSection();

      TextInputEditText extensionLimit = mDialogExtensionsBinding.extensionLimit;

      if(verifyInput(extensionLimit)){

            long limit = Long.parseLong(Objects.requireNonNull(extensionLimit.getText()).toString());

            section.setSectCode(BigDecimal.valueOf(id));
            section.setLimitAmount(BigDecimal.valueOf(limit));

          ((VehicleInsuranceActivity) Objects.requireNonNull(getActivity())).addSection(section);

          simpleclose();

      }

    }

    private boolean verifyInput(TextInputEditText editText) {

        TextInputLayout extensionLimitInputLayout = mDialogExtensionsBinding.extensionLimitInputLayout;

        extensionLimitInputLayout.setError(null);

        if (CommonUtils.StringIsEmpty(Objects.requireNonNull(editText.getText()).toString())) {
            extensionLimitInputLayout.setError(getResources().getString(R.string.benefit_limit_title));
            return false;
        }

        return true;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogExtensionsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_extensions, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mExtensionsDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(ExtensionsDialogViewModel.class);
        binding.setViewModel(mExtensionsDialogViewModel);

        mExtensionsDialogViewModel.setNavigator(this);

        mDialogExtensionsBinding = binding;

        TextView benefitsLimitHeader = mDialogExtensionsBinding.benefitsLimitHeader;

        TextView benefitsLimitDescription = mDialogExtensionsBinding.benefitsLimitDescription;

        benefitsLimitHeader.setText(limitName);

        benefitsLimitDescription.setText(this.benefitsLimitDescription);



        return view;
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager, int id, String limitName, String limitDescription) {

        this.id = id;
        this.limitName = limitName;
        this.benefitsLimitDescription = limitDescription;

        super.show(fragmentManager, TAG);
    }
}
