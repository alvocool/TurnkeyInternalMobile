package com.turnkeyafrica.turnkeybankassurance.ui.cardpayment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityCardPaymentBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog.CardDatesDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful.PaymentSuccessfulActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CardPaymentActivity extends BaseActivity<ActivityCardPaymentBinding, CardPaymentViewModel> implements CardPaymentNavigator, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private CardPaymentViewModel mCardPaymentViewModel;

    private EditText creditCardNameTextInput;

    private EditText creditCardNumberTextInput;

    private EditText creditCardMonthInput;

    private EditText creditCardYearInput;

    private EditText cvvNumberTextInput;

    String payAmount;


    public static Intent newIntent(Context context) {
        return new Intent(context, CardPaymentActivity.class);
    }

    private ActivityCardPaymentBinding mActivityCardPaymentBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_payment;
    }

    @Override
    public CardPaymentViewModel getViewModel() {
        mCardPaymentViewModel = new ViewModelProvider(getViewModelStore(),factory).get(CardPaymentViewModel.class);
        return mCardPaymentViewModel;
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardPaymentViewModel.setNavigator(this);
        mActivityCardPaymentBinding = getViewDataBinding();
        Toolbar toolbar = mActivityCardPaymentBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setAmount();
        initComponents();
    }

    private void setAmount() {

            Bundle extras = getIntent().getExtras();

            assert extras != null;
            String amount = extras.getString("carD*7&as");


            if(!CommonUtils.StringIsEmpty(amount)){

                String amountDetails = getResources().getString(R.string.currency_kenya) +" " + CommonUtils.StringToCurrency(amount);

                payAmount = amount;

                mCardPaymentViewModel.setPaymentAmount(amountDetails);
            }


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

    @Override
    public boolean verifyValues() {

        TextInputLayout creditCardNameTextInputLayout = mActivityCardPaymentBinding.creditCardNameTextInputLayout;

        TextInputLayout creditCardNumberTextInputLayout = mActivityCardPaymentBinding.creditCardNumberTextInputLayout;

        TextInputLayout creditCardMonthInputLayout = mActivityCardPaymentBinding.creditCardMonthInputLayout;

        TextInputLayout creditCardYearInputLayout = mActivityCardPaymentBinding.creditCardYearInputLayout;

        TextInputLayout cvvNumberTextInputLayout = mActivityCardPaymentBinding.cvvNumberTextInputLayout;

        creditCardNameTextInputLayout.setError(null);
        creditCardNumberTextInputLayout.setError(null);
        creditCardMonthInputLayout.setError(null);
        creditCardYearInputLayout.setError(null);
        cvvNumberTextInputLayout.setError(null);

        if (CommonUtils.StringIsEmpty(creditCardNameTextInput.getText().toString())) {
            creditCardNameTextInputLayout.setError(getResources().getString(R.string.error_card_name));
            return false;
        }else if (CommonUtils.verifyCardName(creditCardNameTextInput.getText().toString())) {
            creditCardNameTextInputLayout.setError(getResources().getString(R.string.error_invalid_card_name));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(creditCardNumberTextInput.getText().toString())) {
            creditCardNumberTextInputLayout.setError(getResources().getString(R.string.error_card_number));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(creditCardMonthInput.getText().toString())) {
            creditCardMonthInputLayout.setError(getResources().getString(R.string.input_error_simple));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(creditCardYearInput.getText().toString())) {
            creditCardYearInputLayout.setError(getResources().getString(R.string.input_error_simple));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(cvvNumberTextInput.getText().toString())) {
            cvvNumberTextInputLayout.setError(getResources().getString(R.string.input_error_simple));
            return false;
        }

        return true;

    }

    @Override
    public void showDialogType(int dialogOption) {
        CardDatesDialog.newInstance().show(getSupportFragmentManager(), dialogOption);
    }

    private void SessionExpired(){

        Toast.makeText(getApplicationContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    private void initComponents() {

          creditCardNameTextInput = mActivityCardPaymentBinding.creditCardName;

          creditCardNumberTextInput = mActivityCardPaymentBinding.creditCardNumber;

          creditCardMonthInput = mActivityCardPaymentBinding.creditCardMonth;

          creditCardYearInput = mActivityCardPaymentBinding.creditCardYear;

          cvvNumberTextInput = mActivityCardPaymentBinding.cvvNumberInput;

    }

    public void setMonth(String month){

        mActivityCardPaymentBinding.creditCardMonth.setText(month);
    }

    public void setYear(String year){

        mActivityCardPaymentBinding.creditCardYear.setText(year);
    }

    @Override
    public void paymentSuccessful(String policyNo, String batchNo) {
        finishAffinity();
        Intent intent = PaymentSuccessfulActivity.newIntent(CardPaymentActivity.this);
        intent.putExtra("7s12a$",policyNo);
        intent.putExtra("*aspp99|",batchNo);
        intent.putExtra("cer^tState#!",false);
        startActivity(intent);
        finish();
    }

    @Override
    public CardDetailsRequest getCardDetails() {

        Gson gson = new Gson();

        String quoteCode =  String.valueOf(gson.fromJson(getViewModel().getDataManager().getInsuranceQuote(), InsuranceQuoteResponce.class).getInsuranceQuotation().getQuotNumber());
        BigDecimal userId = BigDecimal.valueOf(getViewModel().getDataManager().getCurrentUserId());
        String cardNumber = Objects.requireNonNull(mActivityCardPaymentBinding.creditCardNumber.getText()).toString();
        String cvv =  Objects.requireNonNull(mActivityCardPaymentBinding.cvvNumberInput.getText()).toString();
        String creditCardMonth = Objects.requireNonNull(mActivityCardPaymentBinding.creditCardMonth.getText()).toString();
        String creditCardYear = Objects.requireNonNull(mActivityCardPaymentBinding.creditCardYear.getText()).toString();
        String cardName = Objects.requireNonNull(mActivityCardPaymentBinding.creditCardName.getText()).toString();

        return new CardDetailsRequest(
                payAmount,
                userId,
                "254",
                cardNumber,
                "KES",
                cvv,
                creditCardMonth,
                creditCardYear,
                cardName,
                quoteCode,
                "NB"
                );
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
