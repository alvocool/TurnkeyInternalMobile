package com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.databinding.DialogVerifyManualPaymentBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.PayActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DialogVerifyManualPayment extends BaseDialog implements DialogVerifyManualPaymentCallback {

    private static final String TAG = DialogVerifyManualPayment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private DialogVerifyManualPaymentViewModel mDialogVerifyManualPaymentViewModel;
    private DialogVerifyManualPaymentBinding mDialogVerifyManualPaymentBinding;
    private BigDecimal agentCode;
    private BigDecimal amount;


    public static DialogVerifyManualPayment newInstance() {
        DialogVerifyManualPayment fragment = new DialogVerifyManualPayment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void close() {
        dismissDialog(TAG);
    }

    @Override
    public void verifyPayment() {

        mDialogVerifyManualPaymentBinding.mpesaCodeInputLayout.setError(null);

        mDialogVerifyManualPaymentBinding.mpesaNumberInputLayout.setError(null);

        hideKeyboard();

        if(!CommonUtils.StringIsEmpty(Objects.requireNonNull(mDialogVerifyManualPaymentBinding.mpesaCode.getText()).toString())) {

            if(!CommonUtils.StringIsEmpty(Objects.requireNonNull(mDialogVerifyManualPaymentBinding.mpesaNumber.getText()).toString())) {

                String number = mDialogVerifyManualPaymentBinding.mpesaNumber.getText().toString();

                String code = mDialogVerifyManualPaymentBinding.mpesaCode.getText().toString();

                ((PayActivity) Objects.requireNonNull(getActivity())).initiateManualPayment(code, agentCode.toString(),number);

                close();
            }else {
                mDialogVerifyManualPaymentBinding.mpesaNumberInputLayout.setError(getResources().getString(R.string.error_mobile_no));
            }
        }else {
            mDialogVerifyManualPaymentBinding.mpesaCodeInputLayout.setError(getResources().getString(R.string.error_mpesa_code));
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogVerifyManualPaymentBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_verify_manual_payment, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mDialogVerifyManualPaymentViewModel = new ViewModelProvider(getViewModelStore(),factory).get(DialogVerifyManualPaymentViewModel.class);
        binding.setViewModel(mDialogVerifyManualPaymentViewModel);

        mDialogVerifyManualPaymentViewModel.setNavigator(this);

        mDialogVerifyManualPaymentBinding = binding;

        return view;
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void show(FragmentManager fragmentManager, BigDecimal agentCode,BigDecimal amount) {

        this.amount = amount;
        this.agentCode = agentCode;
        super.show(fragmentManager, TAG);
    }
}