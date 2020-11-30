package com.turnkeyafrica.bankassurance.ui.securityquestion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import javax.inject.Inject;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.AnswerVerify;
import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.databinding.ActivitySecurityQuestionBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.contactus.ContactUsActivity;
import com.turnkeyafrica.bankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

public class SecurityQuestionActivity extends BaseActivity<ActivitySecurityQuestionBinding, SecurityQuestionViewModel> implements SecurityQuestionNavigator {

    @Inject
    ViewModelProviderFactory factory;
    SecurityQuestionViewModel mSecurityQuestionViewModel;
    ActivitySecurityQuestionBinding mActivitySecurityQuestionBinding;
    
    SecurityQuestions questions;

    String phone;

    EditText answer;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_question;
    }

    @Override
    public SecurityQuestionViewModel getViewModel() {

        mSecurityQuestionViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(SecurityQuestionViewModel.class);

        return mSecurityQuestionViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, SecurityQuestionActivity.class);
    }

  
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySecurityQuestionBinding = getViewDataBinding();
        mSecurityQuestionViewModel.setNavigator(this);
        Toolbar toolbar = mActivitySecurityQuestionBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getData();
    }

    private void getData() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String question = extras.getString("Mon@45@1");

        phone = extras.getString("q3;AS!");

        questions = new Gson().fromJson(question, SecurityQuestions.class);

        mActivitySecurityQuestionBinding.questionInputLayout.setHint(questions.getDescription());

        answer = mActivitySecurityQuestionBinding.answer;

        answer = disableCopyAndPaste(answer);

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
    public void verify() {

        TextInputLayout question = mActivitySecurityQuestionBinding.questionInputLayout;

        question.setError(null);

        if(CommonUtils.StringIsEmpty(answer.getText().toString())){
            question.setError(getResources().getString(R.string.errorSecurityQuestion));
        }else {
            mSecurityQuestionViewModel.verifyAnswer(new AnswerVerify(questions.getCode(),
                    phone,
                    answer.getText().toString()));
        }
    }

    @Override
    public void Continue(Boolean response) {
        if(response){
            Intent intent = OtpActivity.newIntent(SecurityQuestionActivity.this);
            intent.putExtra("phht@@00", phone);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void showContacts() {
        Intent intent = ContactUsActivity.newIntent(SecurityQuestionActivity.this);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


}