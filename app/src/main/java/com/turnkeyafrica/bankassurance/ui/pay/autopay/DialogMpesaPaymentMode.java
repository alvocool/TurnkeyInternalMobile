package com.turnkeyafrica.bankassurance.ui.pay.autopay;

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
import com.turnkeyafrica.bankassurance.databinding.DialogMpesaPaymentModeBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.ui.pay.PayActivity;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DialogMpesaPaymentMode extends BaseDialog implements DialogMpesaPaymentModeCallback {

    private static final String TAG = DialogMpesaPaymentMode.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private DialogMpesaPaymentModeViewModel mDialogMpesaPaymentModeViewModel;
    private DialogMpesaPaymentModeBinding mDialogMpesaPaymentModeBinding;


    public static DialogMpesaPaymentMode newInstance() {
        DialogMpesaPaymentMode fragment = new DialogMpesaPaymentMode();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void close() {
        dismissDialog(TAG);
    }

    @Override
    public void intiatePayment() {
        ((PayActivity) Objects.requireNonNull(getActivity())).callSdkPush();
        close();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogMpesaPaymentModeBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_mpesa_payment_mode, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mDialogMpesaPaymentModeViewModel = new ViewModelProvider(getViewModelStore(),factory).get(DialogMpesaPaymentModeViewModel.class);
        binding.setViewModel(mDialogMpesaPaymentModeViewModel);

        mDialogMpesaPaymentModeViewModel.setNavigator(this);

        mDialogMpesaPaymentModeBinding = binding;

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
