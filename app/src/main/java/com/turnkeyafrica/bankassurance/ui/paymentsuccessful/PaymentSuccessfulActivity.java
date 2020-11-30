package com.turnkeyafrica.bankassurance.ui.paymentsuccessful;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityPaymentSuccessfulBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.CertificatePickUpActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.bankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.bankassurance.ui.vehiclevaluation.VehicleValuationActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import androidx.lifecycle.ViewModelProvider;
import javax.inject.Inject;

public class PaymentSuccessfulActivity extends BaseActivity<ActivityPaymentSuccessfulBinding, PaymentSuccessfulViewModel> implements PaymentSuccessfulNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private PaymentSuccessfulViewModel mPaymentSuccessfulViewModel;

    private ActivityPaymentSuccessfulBinding mActivityPaymentSuccessfulBinding;

    private String policyNo;

    private String batchNo;

    boolean status;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_successful;
    }

    @Override
    public PaymentSuccessfulViewModel getViewModel() {
        mPaymentSuccessfulViewModel = new ViewModelProvider(getViewModelStore(),factory).get(PaymentSuccessfulViewModel.class);
        return mPaymentSuccessfulViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentSuccessfulViewModel.setNavigator(this);
        mActivityPaymentSuccessfulBinding = getViewDataBinding();
        setPolicyDetails();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PaymentSuccessfulActivity.class);
    }

    @Override
    public void onBackPressed() {
        if(!status) {
            handleError(new LocalError(0,getResources().getString(R.string.certificatePickupError)));
        }else {
            handleError(new LocalError(0,getResources().getString(R.string.policyCreationError)));
        }
    }

    @Override
    public void handleError(LocalError error) {

            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }

    }

    private void setPolicyDetails() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String PolicyNo = extras.getString("7s12a$");
        String  BatchNo= extras.getString("*aspp99|");
        boolean certificateStatus = extras.getBoolean("cer^tState#!");

        status = certificateStatus;

        if (!CommonUtils.StringIsEmpty(BatchNo)) {
            batchNo = BatchNo;
        }
        if(!CommonUtils.StringIsEmpty(PolicyNo)){
            policyNo = PolicyNo;
        }

            mActivityPaymentSuccessfulBinding.uploadEvaluationBtn.setVisibility(certificateStatus ? View.VISIBLE : View.GONE);
            mActivityPaymentSuccessfulBinding.findValuatorBtn.setVisibility(certificateStatus ? View.VISIBLE : View.GONE);
            mActivityPaymentSuccessfulBinding.remindLater.setVisibility(certificateStatus ? View.VISIBLE : View.GONE);
            mActivityPaymentSuccessfulBinding.certificatePickupBtn.setVisibility(certificateStatus ? View.GONE : View.VISIBLE);
            mActivityPaymentSuccessfulBinding.paymentSuccessful.setVisibility(certificateStatus ? View.GONE : View.VISIBLE);
            mActivityPaymentSuccessfulBinding.valuerRequiredInfo.setText(certificateStatus ? getResources().getString(R.string.valuer_required_info): getResources().getString(R.string.certificate_required_info));


    }

    @Override
    public void openDashBoard() {
        Intent intent = DashboardActivity.newIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void findValuer() {
        Intent intent = FindValuerActivity.newIntent(PaymentSuccessfulActivity.this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("*aspp99|",batchNo);
        startActivity(intent);
        finish();
    }

    @Override
    public void uploadValuation() {
        Intent intent = VehicleValuationActivity.newIntent(PaymentSuccessfulActivity.this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("7s12a$",policyNo);
        startActivity(intent);
        finish();
    }

    @Override
    public void certificatePickup() {
        finishAffinity();
        Intent intent = CertificatePickUpActivity.newIntent(PaymentSuccessfulActivity.this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("*aspp99|",batchNo);
        intent.putExtra("7s12a$",policyNo);
        startActivity(intent);
        finish();
    }
}