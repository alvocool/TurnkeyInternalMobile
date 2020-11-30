
package com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog;

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
import com.turnkeyafrica.bankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.DialogBranchRegionsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.CertificatePickUpActivity;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog.adapter.BranchRegionsAdapter;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BranchRegionDialog extends BaseDialog implements BranchRegionDialogcallback {

    private static final String TAG = BranchRegionDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private BranchRegionDialogViewModel mBranchRegionDialogViewModel;

    private DialogBranchRegionsBinding mDialogBranchRegionsBinding;

    private  List<BranchRegionResponce> branchRegions;

    public static BranchRegionDialog newInstance() {
        BranchRegionDialog fragment = new BranchRegionDialog();
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


    private void setTypes(List<BranchRegionResponce> branchRegions) {

        BranchRegionsAdapter branchRegionsAdapter = new BranchRegionsAdapter(getContext(), branchRegions);
        mDialogBranchRegionsBinding.branchRegions.setAdapter(branchRegionsAdapter);

        mDialogBranchRegionsBinding.branchRegions.setOnItemClickListener((parent, view, position, id) -> {


            BranchRegionResponce itemAtPosition = (BranchRegionResponce) mDialogBranchRegionsBinding.branchRegions.getItemAtPosition(position);

            ((CertificatePickUpActivity) Objects.requireNonNull(getActivity())).setNewBranch(itemAtPosition);

            close();
        });

        }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogBranchRegionsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_branch_regions, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mBranchRegionDialogViewModel =
                new ViewModelProvider(getViewModelStore(),factory).get(BranchRegionDialogViewModel.class);
        binding.setViewModel(mBranchRegionDialogViewModel);

        mBranchRegionDialogViewModel.setNavigator(this);

        mDialogBranchRegionsBinding = binding;

        this.setTypes(branchRegions);

        return view;
    }


    public void show(FragmentManager fragmentManager, List<BranchRegionResponce> responces) {

        this.branchRegions = responces;
        super.show(fragmentManager, TAG);
    }
}
