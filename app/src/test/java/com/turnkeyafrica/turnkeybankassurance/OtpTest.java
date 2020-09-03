package com.turnkeyafrica.turnkeybankassurance;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsUpdateRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.TokenResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpNavigator;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class OtpTest {

    @Mock
    private OtpNavigator mNavigator;

    @Mock
    private DataManager mMockDataManager;

    @Mock
    private AlertDialog alertDialog;

    private OtpViewModel mViewModel;

    private TestScheduler mTestScheduler;

    private OtpRequest otpRequest;

    private String number = "0123123";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mTestScheduler = new TestScheduler();

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mViewModel = new OtpViewModel(mMockDataManager, testSchedulerProvider);

        mViewModel.setNavigator(this.mNavigator);
    }


    @Test
    public void LoginClick(){
        mViewModel.LoginClick();
        Mockito.verify(mNavigator, Mockito.times(1)).Login();
    }

    @Test
    public void resendCode(){

        Mockito.doReturn(Single.just(true))
                .when(mMockDataManager)
                .requestOTP(number);
        //trigger
        mViewModel.resendCode(number);
    }

    @Test
    public void verifyOtp(){
        OtpRequest otpRequest = new OtpRequest();
        Mockito.doReturn(Single.just(true))
                .when(mMockDataManager)
                .verifyOTP(otpRequest);
        mViewModel.verifyOtp(otpRequest);
    }

    @Test
    public void updateClientDetails(){

        ClientDetailsUpdateRequest clientDetailsUpdateRequest = new ClientDetailsUpdateRequest(
                100L,
                "test@test.com",
                "079898988",
                "Test",
                "TestSurname"
        );
        TokenResponce tokenResponce = new TokenResponce();
        Mockito.doReturn(Single.just(true))
                .when(mMockDataManager)
                .updateClientDetails(clientDetailsUpdateRequest);
        mViewModel.updateClientDetails(clientDetailsUpdateRequest, tokenResponce);
    }

}
