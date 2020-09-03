package com.turnkeyafrica.turnkeybankassurance.ui.otp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityOtpBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class OtpActivity extends BaseActivity<ActivityOtpBinding, OtpViewModel> implements OtpNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private OtpViewModel mOtpViewModel;
    private ActivityOtpBinding mActivityOtpBinding;

    private EditText OtpCodeOne, OtpCodeTwo, OtpCodeThree, OtpCodeFour;
    private EditText[] editTexts;
    private  CountDownTimer cTimer = null;
    private SmsVerifyCatcher smsVerifyCatcher;
    private String phoneNumber;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_otp;
    }

    @Override
    public OtpViewModel getViewModel() {
        mOtpViewModel = new ViewModelProvider(getViewModelStore(), factory).get(OtpViewModel.class);
        return mOtpViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, OtpActivity.class);
    }

    @Override
    public void handleError(LocalError error) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {
                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }

    @Override
    public void Login() {
        if(isNetworkConnected()){
            if(verifyInput()) {
                OtpRequest request = new OtpRequest();
                String code  = editTexts[0].getText().toString()
                        +editTexts[1].getText().toString()
                        +editTexts[2].getText().toString()
                        +editTexts[3].getText().toString();
                request.setResetCode(code);
                request.setPhoneNumber(phoneNumber);
                mOtpViewModel.verifyOtp(request);
            }
        }
    }

    private void startTimer() {

        TextView resendCounter =mActivityOtpBinding.resendCountdown;
        TextView resendOtpButton = mActivityOtpBinding.resendOtpButton;

        resendOtpButton.setVisibility(View.GONE);
        resendCounter.setVisibility(View.VISIBLE);
        cTimer = new CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                resendCounter.setText(getResources().getString(R.string.resendTitle)+ "  " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                resendOtpButton.setVisibility(View.VISIBLE);
                resendCounter.setVisibility(View.GONE);
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if(cTimer!=null) {
            cTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    @Override
    public void openDashBoardActivity() {
        Intent intent = DashboardActivity.newIntent(OtpActivity.this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = DashboardActivity.newIntent(OtpActivity.this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public AlertDialog openLoading() {
       return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    private boolean verifyInput() {

        if ( CommonUtils.StringIsEmpty(OtpCodeOne.getText().toString())
                || CommonUtils.StringIsEmpty(OtpCodeTwo.getText().toString())
                || CommonUtils.StringIsEmpty(OtpCodeThree.getText().toString())
                || CommonUtils.StringIsEmpty(OtpCodeFour.getText().toString())) {
            handleError(new LocalError(0,getResources().getString(R.string.missing_value)));
            return false;
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOtpViewModel.setNavigator(this);
        mActivityOtpBinding = getViewDataBinding();
        Toolbar toolbar = mActivityOtpBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        isNetworkConnected();

        OtpCodeOne = mActivityOtpBinding.OtpCodeOne;
        OtpCodeTwo = mActivityOtpBinding.OtpCodeTwo;
        OtpCodeThree =  mActivityOtpBinding.OtpCodeThree;
        OtpCodeFour =  mActivityOtpBinding.OtpCodeFour;
        editTexts = new EditText[]{OtpCodeOne, OtpCodeTwo, OtpCodeThree, OtpCodeFour};

        OtpCodeOne.addTextChangedListener(new PinTextWatcher(0));
        OtpCodeTwo.addTextChangedListener(new PinTextWatcher(1));
        OtpCodeThree.addTextChangedListener(new PinTextWatcher(2));
        OtpCodeFour.addTextChangedListener(new PinTextWatcher(3));

        OtpCodeOne.setOnKeyListener(new PinOnKeyListener(0));
        OtpCodeTwo.setOnKeyListener(new PinOnKeyListener(1));
        OtpCodeThree.setOnKeyListener(new PinOnKeyListener(2));
        OtpCodeFour.setOnKeyListener(new PinOnKeyListener(3));

        phoneNumber = setPhoneNumber();

        OtpRequest request = new OtpRequest();

        smsVerifyCatcher = new SmsVerifyCatcher(this, message -> {
            String code = parseCode(message);

            if(code.length() >= 3) {
                editTexts[0].setText(code.substring(0, 1));
                editTexts[1].setText(code.substring(1, 2));
                editTexts[2].setText(code.substring(2, 3));
                editTexts[3].setText(code.substring(3, 4));
            }

                request.setResetCode(code);
                request.setPhoneNumber(phoneNumber);
                if(isNetworkConnected()) {
                    mOtpViewModel.verifyOtp(request);
                }
        });


    }

    @SuppressLint("SetTextI18n")
    private String setPhoneNumber() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String fromLogin = extras.getString("f02K@1");
        String fromRegister = extras.getString("f02K@2");
        String clientDetails = extras.getString("Cle2iat");

        if(!CommonUtils.StringIsEmpty(clientDetails)) {
            resendCode(fromLogin);
            assert fromLogin != null;
            mActivityOtpBinding.otpDescription.setText(getResources().getString(R.string.otpDescription) + " " + CommonUtils.phaseNumber(fromLogin) + ".");
            mActivityOtpBinding.resendOtpButton.setOnClickListener(v -> resendCode(phoneNumber));
            mOtpViewModel.operationType = clientDetails;
            return fromLogin;

        }else {
            if (CommonUtils.StringIsEmpty(fromRegister)) {
                resendCode(fromLogin);
                assert fromLogin != null;
                mActivityOtpBinding.otpDescription.setText(getResources().getString(R.string.otpDescription) + " " + CommonUtils.phaseNumber(fromLogin) + ".");
                mActivityOtpBinding.resendOtpButton.setOnClickListener(v -> resendCode(phoneNumber));
                return fromLogin;
            } else {
                mActivityOtpBinding.otpDescription.setText(getResources().getString(R.string.otpDescription) + " " + CommonUtils.phaseNumber(fromRegister) + ".");
                mActivityOtpBinding.resendOtpButton.setOnClickListener(v -> resendCode(phoneNumber));
                return fromRegister;
            }

        }

    }

    private void resendCode(String phoneNumber) {
        cancelTimer();
        if(isNetworkConnected()) {
            mOtpViewModel.resendCode(phoneNumber);
        }
        startTimer();
    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }


    public class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            if (text.length() > 1)
                text = String.valueOf(text.charAt(0));

                editTexts[currentIndex].removeTextChangedListener(this);
                editTexts[currentIndex].setText(text);
                editTexts[currentIndex].setSelection(text.length());
                editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) {
                editTexts[currentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts) {
                if (editText.getText().toString().trim().length() == 0) {
                    return false;
                }else {

                    editText.setOnEditorActionListener((v, actionId, event) -> {
                        if(actionId== EditorInfo.IME_ACTION_DONE){
                           hideKeyboard();
                        }
                        return false;
                    });
                }
            }
            return true;
        }

    }

    @Override
    public void hideKeyboard() {
        super.hideKeyboard();
    }

    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }

    }

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
