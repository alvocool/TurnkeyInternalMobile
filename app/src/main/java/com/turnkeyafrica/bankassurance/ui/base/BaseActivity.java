package com.turnkeyafrica.bankassurance.ui.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.turnkeyafrica.bankassurance.BuildConfig;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.NetworkUtils;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.AndroidInjection;
import nya.security.androidsecurity.SecurityRuns;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
// App Center Configurations - Shikoli - 5/6/2019 Refactored By Developer
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback  {


    private AlertDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;


    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setStatusBarColor();
        }
        performDataBinding();

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

       /* if(!getClass().getSimpleName().contains("SplashActivity")) {
            if(runSecurityChecks()){
              shutDownProtocal();
            }
        }*/
    }

    public EditText disableCopyAndPaste(EditText editText){

        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });
        return  editText;
    }

    public boolean checkAndRequestPermissions(String[] permissions, int code) {
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), code);
            return false;
        }

        return true;
    }

    public void shutDownProtocal(){

        ViewUtils.showDialog(this, "", "Device not supported.", getResources().getString(R.string.ok), (dialog, which) -> {
            dialog.dismiss();
            System.exit(0);
        });

    }


    public boolean runSecurityChecks(){

        if (BuildConfig.DEBUG) {
            return SecurityRuns.isAppRoot1()
                    || !SecurityRuns.isAppRoot3(getPackageManager())
                    || SecurityRuns.isEmulator();
        }else {
            if(SecurityRuns.isDebugger(getApplicationContext())
            && SecurityRuns.isDebugRunTime()){
                return true;
            }else {
                if(SecurityRuns.isAppRoot1()
                        && !SecurityRuns.isAppRoot3(getPackageManager())
                        && SecurityRuns.isEmulator()) {
                    return false;
                }else{
                    if (SecurityRuns.isAppRootRunTime((ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE))) {
                        return false;
                    }else {
                        return SecurityRuns.isReverseEngine(getApplicationContext());
                    }

                }
            }
        }
    }


    private void setStatusBarColor(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }}

    public T getViewDataBinding() {
        return mViewDataBinding;
    }



    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = ViewUtils.isLoadingDialog(this,this,getResources().getString(R.string.loading));
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


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
    }



