package com.turnkeyafrica.bankassurance.ui.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.BuildConfig;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityDashboardBinding;
import com.turnkeyafrica.bankassurance.databinding.NavHeaderBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.contactus.ContactUsActivity;
import com.turnkeyafrica.bankassurance.ui.inbox.InboxActivity;
import com.turnkeyafrica.bankassurance.ui.insuranceproducts.InsuranceProductsActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.profile.ProfileActivity;
import com.turnkeyafrica.bankassurance.ui.servicerequests.ServiceRequestActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import javax.inject.Inject;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> implements  DashboardNavigator, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> fragmentDispatchingAndroidInjector;
    @Inject
    DashboardPagerAdapter mPagerAdapter;
    @Inject
    ViewModelProviderFactory factory;
    private ActivityDashboardBinding mDashboardBinding;
    private DashboardViewModel mDashboardViewModel;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;

    TextView mCounter;

    @Override
    protected void onRestart() {
        super.onRestart();
         mDashboardViewModel.getDataManager().clearComparisonRequest();

         if(isNetworkConnected()){
             mDashboardViewModel.getNotificationCount();
         }

        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

    }
    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public DashboardViewModel getViewModel() {
        mDashboardViewModel = new ViewModelProvider(getViewModelStore(),factory).get(DashboardViewModel.class);
        return mDashboardViewModel;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDashboardBinding = getViewDataBinding();
        mDashboardViewModel.setNavigator(this);
        mDashboardViewModel.getDataManager().clearComparisonRequest();
        AssembleTabItems();
        AssembleView();

        if(!mDashboardViewModel.getDataManager().getNotificationToken()) {
             generateNotToken();
        }
    }

    private void generateNotToken() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        handleError(new LocalError(0, getResources().getString(R.string.error_notification_account)));
                        return;
                    }

                    String token = Objects.requireNonNull(task.getResult()).getToken();

                    mDashboardViewModel.sendNotificationId(token);
                });
    }

    private void AssembleTabItems() {

        mPagerAdapter.setCount(2);

        mDashboardBinding.DashboardViewPager.setAdapter(mPagerAdapter);

        mDashboardBinding.DashtabLayout.addTab(mDashboardBinding.DashtabLayout.newTab().setText(getString(R.string.policies)));

        mDashboardBinding.DashtabLayout.addTab(mDashboardBinding.DashtabLayout.newTab().setText(getString(R.string.claims)));

        mDashboardBinding.DashboardViewPager.setOffscreenPageLimit(mDashboardBinding.DashtabLayout.getTabCount());

        mDashboardBinding.DashboardViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDashboardBinding.DashtabLayout));

        mDashboardBinding.DashtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
           public void onTabReselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mDashboardBinding.DashboardViewPager.setCurrentItem(tab.getPosition());
            }

          @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
        });
    }

    private void AssembleView() {
        mDrawer = mDashboardBinding.drawerView;
        mToolbar = mDashboardBinding.toolbar;
        mNavigationView = mDashboardBinding.navigationView;
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mDashboardViewModel.updateAppVersion(version);
        mDashboardViewModel.onNavMenuCreated();

    }

    private void setupNavMenu() {
        NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header, mDashboardBinding.navigationView, false);
        mDashboardBinding.navigationView.addHeaderView(navHeaderBinding.getRoot());
        navHeaderBinding.setViewModel(mDashboardViewModel);

        mNavigationView.setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);

                    switch (item.getItemId()) {
                        case R.id.navItemHome:
                            return true;
                        case R.id.navItemInsuranceProducts:
                            startActivity(InsuranceProductsActivity.newIntent(DashboardActivity.this));
                            return true;
                        case R.id.navItemContactUs:
                            startActivity(ContactUsActivity.newIntent(DashboardActivity.this));
                            return true;
                        case R.id.navItemServiceRequest:
                            startActivity(ServiceRequestActivity.newIntent(DashboardActivity.this));
                            return true;
                        case R.id.navItemLogout:
                            if(this.isNetworkConnected()) {
                                mDashboardViewModel.logout();
                            }
                            return true;
                        default:
                            return false;
                    }
                });
    }


    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(DashboardActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void viewProfile() {
        Intent intent = ProfileActivity.newIntent(this);
        startActivity(intent);
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
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public boolean isNetworkConnected() {
        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        RelativeLayout notifyLayout = (RelativeLayout) menu.findItem(R.id.notifications).getActionView();
        notifyLayout.findViewById(R.id.iconNotification).setOnClickListener(view -> {
            Intent intent = InboxActivity.newIntent(DashboardActivity.this);
            startActivity(intent);
        });

        mCounter = notifyLayout.findViewById(R.id.notificationCount);

        if(isNetworkConnected()) {
            mDashboardViewModel.getNotificationCount();
        }

        return true;
    }

    @Override
    public void setNotificationCount(int size) {
        if(size>0) {
            mCounter.setVisibility(View.VISIBLE);
            mCounter.setText(String.valueOf(size));
        }else{
            mCounter.setVisibility(View.GONE);
            mCounter.setText("0");
        }
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
