package com.turnkeyafrica.bankassurance.ui.confirmandpay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.gson.Gson;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityConfirmAndPayBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.cardpayment.CardPaymentActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.pay.PayActivity;
import com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialog;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import java.math.BigDecimal;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class ConfirmAndPayActivity extends BaseActivity<ActivityConfirmAndPayBinding, ConfirmAndPayViewModel> implements ConfirmAndPayNavigator, HasAndroidInjector {

    @Inject
    ViewModelProviderFactory factory;
    private ConfirmAndPayViewModel mConfirmAndPayViewModel;

    private ActivityConfirmAndPayBinding mActivityConfirmAndPayBinding;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    private int paymentOption;

    private BigDecimal quoteCode;

    private BigDecimal agentCode;

    private String quoteNo;

    EditText payType;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_and_pay;
    }

    @Override
    public ConfirmAndPayViewModel getViewModel() {
        mConfirmAndPayViewModel = new ViewModelProvider(getViewModelStore(), factory).get(ConfirmAndPayViewModel.class);
        return mConfirmAndPayViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConfirmAndPayViewModel.setNavigator(this);
        mActivityConfirmAndPayBinding = getViewDataBinding();

        Toolbar toolbar = mActivityConfirmAndPayBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setValues();

        payType = mActivityConfirmAndPayBinding.paymentQ;

        payType = disableCopyAndPaste(payType);

    }

    private void setValues() {

        String insuranceQuote =  getViewModel().getDataManager().getInsuranceQuote();

        if (insuranceQuote != null) {
            Gson gson = new Gson();

            InsuranceQuoteResponce insuranceQuoteResponce = gson.fromJson(insuranceQuote, InsuranceQuoteResponce.class);

            if(CommonUtils.ObjectIsNotNull(insuranceQuoteResponce.getInsuranceQuotation().getFirstInstallment()) && CommonUtils.ObjectIsNotNull(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount())) {
                if(!insuranceQuoteResponce.getInsuranceQuotation().getFirstInstallment().equals(BigDecimal.ZERO) && !insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().equals(BigDecimal.ZERO)) {
                    setPaymentValues(insuranceQuoteResponce.getInsuranceQuotation().getFirstInstallment().toString(), insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().toString());
                }else {
                    if(!insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().equals(BigDecimal.ZERO)){
                        setPaymentValues("0", insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().toString());
                    }else{
                        handleError(new LocalError(0,"Your request could not be processed at this moment. \nPlease try again later."));
                        Analytics.trackEvent("Quote does not contain any premium amount. The code did not have any payment information i.e. Full or partial payment amounts.");
                    }
                }
            }else {

                if(CommonUtils.ObjectIsNotNull(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount())){
                    setPaymentValues("0", insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().toString());
                }else {
                    handleError(new LocalError(0,"Your request could not be processed at this moment. \nPlease try again later."));
                    Analytics.trackEvent("Quote does not contain any premium amount. The code did not have any payment information i.e. Full or partial payment amounts.");
                }

            }

            quoteCode = insuranceQuoteResponce.getInsuranceQuotation().getQuotCode();
            quoteNo = insuranceQuoteResponce.getInsuranceQuotation().getQuotNumber();
            agentCode = insuranceQuoteResponce.getInsuranceQuotation().getAgentCode();
            getViewModel().setHeaders(insuranceQuoteResponce.getInsuranceQuotation().getAgnName()
                    ,insuranceQuoteResponce.getInsuranceQuotation().getCoverType(),
                    getResources().getString(R.string.quoteReferenceHeader) + insuranceQuoteResponce.getInsuranceQuotation().getQuotNumber(),
                    getResources().getString(R.string.inceptionDateHeader) + insuranceQuoteResponce.getInsuranceQuotation().getCoverFrom(),
                    getResources().getString(R.string.renewalDateHeader) + insuranceQuoteResponce.getInsuranceQuotation().getCoverTo(),
                    insuranceQuoteResponce.getInsuranceQuotation().getQuotNumber()
                    );
        }
    }

    @Override
    public AlertDialog openLoading(String status) {
        return ViewUtils.isLoadingDialog(this, this,status);
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        if(alertDialog != null) {
            alertDialog.dismiss();
        }
    }



    private void setPaymentValues(String firstInstallment, String fullAmount){

        if(firstInstallment.equals("0") || CommonUtils.StringIsEmpty(firstInstallment)){
            mActivityConfirmAndPayBinding.paymentAmount1.setVisibility(View.GONE);
        }else {
            mActivityConfirmAndPayBinding.paymentAmount1.setVisibility(View.VISIBLE);
            mActivityConfirmAndPayBinding.paymentAmount1.setHint(firstInstallment);
            mActivityConfirmAndPayBinding.paymentAmount1.setText(String.format("%s. %s %s", getResources().getString(R.string.currency_kenya), CommonUtils.StringToCurrency(firstInstallment), getResources().getString(R.string.firstInstallmentDesciption)));

        }

        if(fullAmount.equals("0") || CommonUtils.StringIsEmpty(fullAmount)){
            mActivityConfirmAndPayBinding.paymentAmount2.setVisibility(View.GONE);
        }else {
            mActivityConfirmAndPayBinding.paymentAmount2.setVisibility(View.VISIBLE);
            mActivityConfirmAndPayBinding.paymentAmount2.setHint(fullAmount);
            mActivityConfirmAndPayBinding.paymentAmount2.setText(String.format("%s. %s %s", getResources().getString(R.string.currency_kenya), CommonUtils.StringToCurrency(fullAmount), getResources().getString(R.string.fullInstallmentDesciption)));

        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ConfirmAndPayActivity.class);
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
    public void showTermsAndConditions() {
        TermsAndConditionsDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void payNow() {

        if(isNetworkConnected()) {

                if (mActivityConfirmAndPayBinding.paymentQ.getText() != null && paymentOption != 0) {

                    RadioButton checked = findViewById(mActivityConfirmAndPayBinding.paymentAmountRadioGroup.getCheckedRadioButtonId());

                    if(CommonUtils.ObjectIsNotNull(checked)) {
                        switch (paymentOption) {
                            case 1:
                                validateMpesaAmount(checked.getHint().toString());
                                break;
                            case 2:
                                cardPayment(checked.getHint().toString());
                                break;
                            default:
                                handleError(new LocalError(5, getResources().getString(R.string.paymentModeError)));
                                break;
                        }
                    }else {
                        handleError(new LocalError(7, getResources().getString(R.string.payValueError)));
                    }

                } else {
                    handleError(new LocalError(5, getResources().getString(R.string.paymentModeError)));
                }
            }
        }

    private void cardPayment(String amount) {
        Intent intent = CardPaymentActivity.newIntent(this);
        intent.putExtra("carD*7&as",amount);
        startActivity(intent);
    }

    private void validateMpesaAmount(String amount){

        BigDecimal cashAmount = new BigDecimal(amount);

        if(cashAmount.compareTo(BigDecimal.valueOf(70000)) < 0) {
            if(isNetworkConnected()) {
                getViewModel().mpesaPayment(amount, quoteCode, agentCode);
            }
        }else {
            handleError(new LocalError(0,getResources().getString(R.string.mpesaError)));
        }
    }

    @Override
    public void showPaymentModes() {

        mActivityConfirmAndPayBinding.confirmPaymentbtn.setVisibility(View.GONE);
        mActivityConfirmAndPayBinding.termsAndConditionsBtn.setVisibility(View.GONE);
        mActivityConfirmAndPayBinding.policyTerms.setVisibility(View.GONE);
        mActivityConfirmAndPayBinding.payOptionBottomNav.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        if(getViewModel().getIsLoading().get()){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.paymentProcessingInfo),Toast.LENGTH_LONG).show();
        }
        else if(mActivityConfirmAndPayBinding.payOptionBottomNav.getVisibility() == View.VISIBLE) {
            mActivityConfirmAndPayBinding.confirmPaymentbtn.setVisibility(View.VISIBLE);
            mActivityConfirmAndPayBinding.termsAndConditionsBtn.setVisibility(View.VISIBLE);
            mActivityConfirmAndPayBinding.policyTerms.setVisibility(View.VISIBLE);
            mActivityConfirmAndPayBinding.payOptionBottomNav.setVisibility(View.GONE);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public void mpesaPayment(MpesaRequest mpesaRequest, BigDecimal quoteCode) {
        if(!CommonUtils.ObjectIsNotNull(mpesaRequest.getAgentCode())){
            handleError(new LocalError(0,getResources().getString(R.string.error_mpesa_agent_code)));
        }else {
            Intent intent = PayActivity.newIntent(this);
            intent.putExtra("a78as1", new Gson().toJson(mpesaRequest));
            intent.putExtra("aqEd@13%", quoteCode.intValueExact());
            intent.putExtra("ASD7^^128%", quoteNo);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void editQuoteDetails() {
        Intent intent = VehicleInsuranceActivity.newIntent(ConfirmAndPayActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPaymentOption(int option) {
        paymentOption = option;
        switch (option){
            case 1:
                mActivityConfirmAndPayBinding.paymentQ.setText(getResources().getString(R.string.payOption1));
                mActivityConfirmAndPayBinding.confirmPaymentbtn.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.termsAndConditionsBtn.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.policyTerms.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.payOptionBottomNav.setVisibility(View.GONE);
                break;
            case 2:
                mActivityConfirmAndPayBinding.paymentQ.setText(getResources().getString(R.string.payOption2));
                mActivityConfirmAndPayBinding.confirmPaymentbtn.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.termsAndConditionsBtn.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.policyTerms.setVisibility(View.VISIBLE);
                mActivityConfirmAndPayBinding.payOptionBottomNav.setVisibility(View.GONE);
                break;
                default:
                    mActivityConfirmAndPayBinding.paymentQ.setText(null);
                    mActivityConfirmAndPayBinding.confirmPaymentbtn.setVisibility(View.VISIBLE);
                    mActivityConfirmAndPayBinding.termsAndConditionsBtn.setVisibility(View.VISIBLE);
                    mActivityConfirmAndPayBinding.policyTerms.setVisibility(View.VISIBLE);
                    mActivityConfirmAndPayBinding.payOptionBottomNav.setVisibility(View.GONE);
                    break;
        }
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
