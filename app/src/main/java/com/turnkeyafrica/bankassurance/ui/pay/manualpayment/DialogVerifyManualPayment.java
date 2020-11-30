package com.turnkeyafrica.bankassurance.ui.pay.manualpayment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.databinding.DialogVerifyManualPaymentBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.bankassurance.ui.pay.PayActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
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

    EditText code;
    EditText cashPay;

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

        if(!CommonUtils.StringIsEmpty(this.code.getText().toString())) {

            if(!CommonUtils.StringIsEmpty(cashPay.getText().toString())) {

                String number = cashPay.getText().toString();

                String code = this.code.getText().toString();

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

        code = mDialogVerifyManualPaymentBinding.mpesaCode;

        code = getBaseActivity().disableCopyAndPaste(code);

        cashPay = mDialogVerifyManualPaymentBinding.mpesaNumber;

        cashPay = getBaseActivity().disableCopyAndPaste(cashPay);

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