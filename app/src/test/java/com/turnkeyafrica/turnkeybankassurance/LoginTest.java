package com.turnkeyafrica.turnkeybankassurance;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginNavigator;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    private LoginNavigator mNavigator;

    @Mock
    private DataManager mMockDataManager;

    private LoginViewModel mViewModel;

    private TestScheduler mTestScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mTestScheduler = new TestScheduler();

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mViewModel = new LoginViewModel(mMockDataManager, testSchedulerProvider);

        mViewModel.setNavigator(mNavigator);
    }

    @Test
    public void registerNew(){

        mViewModel.registerNew();
      Mockito.verify(mNavigator, Mockito.times(1)).registerNew();
    }

    @Test
    public void LoginClick(){

        mViewModel.LoginClick();
        Mockito.verify(mNavigator, Mockito.times(1)).launchOtp();
    }

}
