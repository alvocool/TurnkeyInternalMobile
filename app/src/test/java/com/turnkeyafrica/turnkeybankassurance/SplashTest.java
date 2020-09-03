package com.turnkeyafrica.turnkeybankassurance;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.splash.SplashNavigator;
import com.turnkeyafrica.turnkeybankassurance.ui.splash.SplashViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SplashTest {

    @Mock
    private SplashNavigator mNavigator;

    @Mock
    private DataManager mMockDataManager;

    private SplashViewModel mViewModel;
    private TestScheduler mTestScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mTestScheduler = new TestScheduler();

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mViewModel = new SplashViewModel(mMockDataManager, testSchedulerProvider);

        mViewModel.setNavigator(this.mNavigator);
    }

    @Test
    public void isUnique(){
        mViewModel.isUnique();
    }

}
