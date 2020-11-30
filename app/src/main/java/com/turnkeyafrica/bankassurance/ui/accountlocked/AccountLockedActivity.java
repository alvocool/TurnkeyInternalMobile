package com.turnkeyafrica.bankassurance.ui.accountlocked;

import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import javax.inject.Inject;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.databinding.ActivityAccountLockedBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;

public class AccountLockedActivity extends BaseActivity<ActivityAccountLockedBinding, AccountLockedViewModel> implements AccountLockedNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private AccountLockedViewModel mAccountLockedViewModel;
    private ActivityAccountLockedBinding mActivityAccountLockedBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_locked;
    }

    @Override
    public AccountLockedViewModel getViewModel() {
        mAccountLockedViewModel = new ViewModelProvider(getViewModelStore(),factory).get(AccountLockedViewModel.class);
        return mAccountLockedViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountLockedViewModel.setNavigator(this);
        mActivityAccountLockedBinding = getViewDataBinding();
        setActions();
        mAccountLockedViewModel.getContacts();
    }

    @Override
    public void onBackPressed() {

    }

    private void setActions() {

        mAccountLockedViewModel.supportOne.set(getResources().getString(R.string.mainSupportNumber));

        TextView supportNumber = mActivityAccountLockedBinding.supportContact1;

        supportNumber.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:" + supportNumber.getText().toString()));

            startActivity(intent);

        });


    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AccountLockedActivity.class);
    }

    @Override
    public void Login() {
        Intent intent = LoginActivity.newIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}