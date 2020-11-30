package com.turnkeyafrica.bankassurance.ui.entersecurityquestions;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.data.model.api.UserSecurityQuestions;
import com.turnkeyafrica.bankassurance.databinding.ActivityEnterSecurityQuestionsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.entersecurityquestions.data.QuestionAdapter;
import com.turnkeyafrica.bankassurance.ui.successstep.SuccessStepActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

public class EnterSecurityQuestionsActivity extends BaseActivity<ActivityEnterSecurityQuestionsBinding, EnterSecurityQuestionsViewModel> implements EnterSecurityQuestionsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    EnterSecurityQuestionsViewModel mEnterSecurityQuestionsViewModel;
    ActivityEnterSecurityQuestionsBinding mActivityEnterSecurityQuestionsBinding;

    private ClientRegistrationRequest requestRegister;

    EditText answer1;

    EditText question1;

    EditText answer2;

    EditText question2;

    EditText answer3;

    EditText question3;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_security_questions;
    }

    int opinion = 1;

    SecurityQuestions securityQuestions1 = new SecurityQuestions();

    SecurityQuestions securityQuestions2 = new SecurityQuestions();

    SecurityQuestions securityQuestions3 = new SecurityQuestions();

    @Override
    public EnterSecurityQuestionsViewModel getViewModel() {

        mEnterSecurityQuestionsViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(EnterSecurityQuestionsViewModel.class);

        return mEnterSecurityQuestionsViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, EnterSecurityQuestionsActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEnterSecurityQuestionsBinding = getViewDataBinding();
        mEnterSecurityQuestionsViewModel.setNavigator(this);
        Toolbar toolbar = mActivityEnterSecurityQuestionsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getData();
        mEnterSecurityQuestionsViewModel.getAllQuestions();
    }


    private void getData() {

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String fromRegister = extras.getString("p8*7^h@9");

        requestRegister = new Gson().fromJson(fromRegister, ClientRegistrationRequest.class);

        answer1 = mActivityEnterSecurityQuestionsBinding.answer1;

        answer1 = disableCopyAndPaste(answer1);

        answer2 = mActivityEnterSecurityQuestionsBinding.answer2;

        answer2 = disableCopyAndPaste(answer2);

        answer3 = mActivityEnterSecurityQuestionsBinding.answer3;

        answer3 = disableCopyAndPaste(answer3);

        question1 = mActivityEnterSecurityQuestionsBinding.question1;

        question1 = disableCopyAndPaste(question1);

        question2 = mActivityEnterSecurityQuestionsBinding.question2;

        question2 = disableCopyAndPaste(question2);

        question3 = mActivityEnterSecurityQuestionsBinding.question3;

        question3 = disableCopyAndPaste(question3);

        securityQuestions1.setCode(9823408);

        securityQuestions2.setCode(982408);

        securityQuestions3.setCode(123123);
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
    public void openSuccess(int step) {
        Intent intent = SuccessStepActivity.newIntent(EnterSecurityQuestionsActivity.this);
        intent.putExtra("p^7Ow@", step);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void register() {
        Register();
    }

    @Override
    public void setQuestions(List<SecurityQuestions> response) {

        QuestionAdapter adapter = new QuestionAdapter(getApplicationContext(), response);
        mActivityEnterSecurityQuestionsBinding.questionsList.setAdapter(adapter);

        mActivityEnterSecurityQuestionsBinding.questionsList.setOnItemClickListener((parent, view, position, id) -> {

            SecurityQuestions itemAtPosition = (SecurityQuestions) mActivityEnterSecurityQuestionsBinding.questionsList.getItemAtPosition(position);

            insertQuestion(itemAtPosition);

            mActivityEnterSecurityQuestionsBinding.questionsCard.setVisibility(View.GONE);

            mActivityEnterSecurityQuestionsBinding.registerBtn.setVisibility(View.VISIBLE);

        });
    }

    private void insertQuestion(SecurityQuestions questions) {

        if(!verifyQuestion(questions)) {
            if (opinion == 1) {
                mActivityEnterSecurityQuestionsBinding.question1.setText(questions.getDescription());
                securityQuestions1 = questions;
            } else if (opinion == 2) {
                mActivityEnterSecurityQuestionsBinding.question2.setText(questions.getDescription());
                securityQuestions2 = questions;
            } else if (opinion == 3) {
                mActivityEnterSecurityQuestionsBinding.question3.setText(questions.getDescription());
                securityQuestions3 = questions;
            }
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.questionExistsError), Toast.LENGTH_LONG).show();
        }
    }

    private boolean verifyQuestion(SecurityQuestions questions) {
        return  securityQuestions1.getCode().equals(questions.getCode()) || securityQuestions2.getCode().equals(questions.getCode()) ||  securityQuestions3.getCode().equals(questions.getCode());
    }

    @Override
    public void setOpinion(int option) {
        this.opinion = option;
        mActivityEnterSecurityQuestionsBinding.registerBtn.setVisibility(View.GONE);
        mActivityEnterSecurityQuestionsBinding.questionsCard.setVisibility(View.VISIBLE);
    }

    private void Register() {

        List<UserSecurityQuestions> questions = verifyDetails().second;

        if(CommonUtils.ObjectIsNotNull(questions) && verifyDetails().first){
            requestRegister.setUserSecurityQuestions(questions);
            mEnterSecurityQuestionsViewModel.sendDetails(requestRegister, Build.MODEL);
        }
    }

    @Override
    public void onBackPressed() {
        if(mActivityEnterSecurityQuestionsBinding.questionsCard.getVisibility() == View.VISIBLE){
            mActivityEnterSecurityQuestionsBinding.questionsCard.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }
    }

    private Pair<Boolean,List<UserSecurityQuestions>> verifyDetails() {

        List<UserSecurityQuestions> selection = new ArrayList<>();

        TextInputLayout question1Layout = mActivityEnterSecurityQuestionsBinding.questionInputLayout;

        question1Layout.setError(null);

        TextInputLayout question2Layout = mActivityEnterSecurityQuestionsBinding.question2InputLayout;

        question2Layout.setError(null);

        TextInputLayout question3Layout = mActivityEnterSecurityQuestionsBinding.question3InputLayout;

        question3Layout.setError(null);

        TextInputLayout answer1Layout = mActivityEnterSecurityQuestionsBinding.answer1InputLayout;

        answer1Layout.setError(null);

        TextInputLayout answer2Layout = mActivityEnterSecurityQuestionsBinding.answer2InputLayout;

        answer2Layout.setError(null);

        TextInputLayout answer3Layout = mActivityEnterSecurityQuestionsBinding.answer3InputLayout;

        answer3Layout.setError(null);

        if(CommonUtils.StringIsEmpty(question1.getText().toString()) && CommonUtils.StringIsEmpty(question2.getText().toString())
                && CommonUtils.StringIsEmpty(question3.getText().toString())){
            question1Layout.setError(getResources().getString(R.string.required));
            question2Layout.setError(getResources().getString(R.string.required));
            question3Layout.setError(getResources().getString(R.string.required));
            return new Pair<>(false,selection);
        }

        if(!CommonUtils.StringIsEmpty(question1.getText().toString())){
            if(CommonUtils.StringIsEmpty(answer1.getText().toString())) {
                answer1Layout.setError(getResources().getString(R.string.required));
                return new Pair<>(false,selection);
            }else {
                selection.add(new UserSecurityQuestions(answer1.getText().toString(),
                        securityQuestions1.getCode(),
                        securityQuestions1.getDescription()));
            }
        }

        if(!CommonUtils.StringIsEmpty(question2.getText().toString())){
            if(CommonUtils.StringIsEmpty(answer2.getText().toString())) {
                answer2Layout.setError(getResources().getString(R.string.required));
                return new Pair<>(false,selection);
            }else {
                selection.add(new UserSecurityQuestions(answer2.getText().toString(),
                        securityQuestions2.getCode(),
                        securityQuestions2.getDescription()));
            }
        }

        if(!CommonUtils.StringIsEmpty(question3.getText().toString())){
            if(CommonUtils.StringIsEmpty(answer3.getText().toString())) {
                answer3Layout.setError(getResources().getString(R.string.required));
                return new Pair<>(false,selection);
            }else {
                selection.add(new UserSecurityQuestions(answer3.getText().toString(),
                        securityQuestions3.getCode(),
                        securityQuestions3.getDescription()));
            }
        }


        return new Pair<>(true,selection);

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