
package com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog;

import android.content.DialogInterface;
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
import com.turnkeyafrica.bankassurance.databinding.DialogTermsAndConditionsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;

public class TermsAndConditionsDialog extends BaseDialog implements TermsAndConditionsDialogcallback {

    private static final String TAG = TermsAndConditionsDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private TermsAndConditionsDialogViewModel mTermsAndConditionsDialogViewModel;


    public static TermsAndConditionsDialog newInstance() {
        TermsAndConditionsDialog fragment = new TermsAndConditionsDialog();
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

            ViewUtils.showDialog(getContext(), "", error.getMessage(), getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogTermsAndConditionsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_terms_and_conditions, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mTermsAndConditionsDialogViewModel = new ViewModelProvider(getViewModelStore(),factory).get(TermsAndConditionsDialogViewModel.class);
        binding.setViewModel(mTermsAndConditionsDialogViewModel);

        mTermsAndConditionsDialogViewModel.setNavigator(this);

        return view;
    }


    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }
}
