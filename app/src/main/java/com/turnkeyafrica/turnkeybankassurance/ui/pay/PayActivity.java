package com.turnkeyafrica.turnkeybankassurance.ui.pay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaCheckModel;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityPayBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmandpay.ConfirmAndPayActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.autopay.DialogMpesaPaymentMode;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment.DialogVerifyManualPayment;
import com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful.PaymentSuccessfulActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import java.math.BigDecimal;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class PayActivity extends BaseActivity<ActivityPayBinding, PayViewModel> implements PayNavigator, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private PayViewModel mPayViewModel;

    private MpesaRequest mpesaRequest;
    private BigDecimal quoteCode;
    private MpesaCheckModel mpesaCheckModel;
    private String quoteNo;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public PayViewModel getViewModel() {
        mPayViewModel = new ViewModelProvider(getViewModelStore(),factory).get(PayViewModel.class);
        return mPayViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PayActivity.class);
    }

    @Override
    public AlertDialog openLoading(String status) {
        return ViewUtils.isLoadingDialog(this, this,status);
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void checkMpesaPayment(MpesaCheckModel mpesaCheckModel) {
        this.mpesaCheckModel = mpesaCheckModel;
    }

    @Override
    public void completePayment() {
        if(CommonUtils.ObjectIsNotNull(mpesaCheckModel)) {
            if(CommonUtils.StringIsEmpty(mpesaCheckModel.getCheckoutRequestID())) {
                DialogVerifyManualPayment.newInstance().show(getSupportFragmentManager(),mpesaRequest.getAgentCode(),mpesaRequest.getAmount());
            }else{
                mPayViewModel.verifyPayment(mpesaCheckModel.getCheckoutRequestID(),mpesaCheckModel.getAgentCode(),mpesaCheckModel.getQuoteCode(),mpesaCheckModel.getAmount());
            }
        }else{
            DialogVerifyManualPayment.newInstance().show(getSupportFragmentManager(),mpesaRequest.getAgentCode(),mpesaRequest.getAmount());
        }
    }

    @Override
    public void convertQuote() {
        mPayViewModel.convertQuote(quoteCode,mpesaRequest.getAmount());
    }

    @Override
    public void onBackPressed() {
        Intent intent = ConfirmAndPayActivity.newIntent(PayActivity.this);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPayViewModel.setNavigator(this);

        getMpesaValues();

    }

    private void getMpesaValues() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String mpesaRequestString = extras.getString("a78as1");
        int quoteCode = extras.getInt("aqEd@13%");
        String quoteNo = extras.getString("ASD7^^128%");


        mpesaRequest = new Gson().fromJson(mpesaRequestString,MpesaRequest.class);
        this.quoteCode = BigDecimal.valueOf(quoteCode);

        this.quoteNo = quoteNo;

        String paySubtitle = getResources().getString(R.string.payment_subtitle_info_part3) + getResources().getString(R.string.payment_subtitle_info_part2);

        getViewModel().setMpesaAmount(paySubtitle);

        DialogMpesaPaymentMode.newInstance().show(getSupportFragmentManager());

        mPayViewModel.getManualDetails(mpesaRequest.getAgentCode().toString(), mpesaRequest.getAmount().toString(), quoteNo);

    }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }else {
            SessionExpired();
        }
    }

    private void SessionExpired(){

        Toast.makeText(getApplicationContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void paymentSuccessful(String policyNo, String batchNo) {
        finishAffinity();
        Intent intent = PaymentSuccessfulActivity.newIntent(PayActivity.this);
        intent.putExtra("7s12a$",policyNo);
        intent.putExtra("*aspp99|",batchNo);
        intent.putExtra("cer^tState#!",false);
        startActivity(intent);
        finish();
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    public void callSdkPush() {

        String paySubtitle = getResources().getString(R.string.payment_subtitle_info_part1) +" "+
                getViewModel().getDataManager().getCurrentUserMobileNumber() + "."+ getResources().getString(R.string.payment_subtitle_info_part2);

        getViewModel().setMpesaAmount(paySubtitle);

        mPayViewModel.sdkPush(mpesaRequest,quoteCode);
    }

    public void initiateManualPayment(String mpesaCode, String agent,String phoneNumber) {
        mPayViewModel.verifyManualPayment(mpesaCode,agent,quoteNo,mpesaRequest.getAmount().toString(),phoneNumber);
    }
}