package com.turnkeyafrica.turnkeybankassurance.ui.otp;

import android.app.AlertDialog;
import com.google.gson.Gson;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsUpdateRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.TokenResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

public class OtpViewModel extends BaseViewModel<OtpNavigator> {

    public String operationType;

    private AlertDialog alertDialog;

    public OtpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void LoginClick() {
        getNavigator().Login();
    }

    public void verifyOtp(OtpRequest request){

        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .verifyOTP(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().closeLoading(alertDialog);
                    if(!CommonUtils.StringIsEmpty(operationType)) {
                        Gson gson = new Gson();
                        ClientDetailsUpdateRequest clientDetailsUpdateRequest = gson.fromJson(operationType,ClientDetailsUpdateRequest.class);
                        getDataManager().setAccessToken(response.getAccessToken());
                        updateClientDetails(clientDetailsUpdateRequest,response);
                    }else {
                        getUserDetails(response);
                    }
                }, throwable -> {getNavigator().closeLoading(alertDialog); getNavigator().handleError(ErrorBase.Error(throwable));}));
    }

    public void updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest, TokenResponce tokenResponce){
        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .updateClientDetails(clientDetailsUpdateRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                tokenResponce.getAccessToken(),
                                clientDetailsUpdateRequest.getClntCode(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                clientDetailsUpdateRequest.getSurname()+ " " + clientDetailsUpdateRequest.getFirstName(),
                                clientDetailsUpdateRequest.getPhoneNumber(),
                                clientDetailsUpdateRequest.getEmail()))
                .subscribe(res -> getNavigator().openDashBoardActivity(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

    public void resendCode(String phoneNumber){
        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .requestOTP(phoneNumber)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> Analytics.trackEvent("Request Otp result for "+ phoneNumber + " :-" + response),
                        throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));

    }

    private void getUserDetails(TokenResponce tokenResponce) {
        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientDetails(tokenResponce.getAccessToken())
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                tokenResponce.getAccessToken(),
                                response.getAcwaClntCode().longValue(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getAcwaUsername() + " " + response.getAcwaName(),
                                response.getAcwaMobileNumber(),
                                response.getAcwaEmailAddrs()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> getNavigator().openDashBoardActivity(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

}
