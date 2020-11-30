package com.turnkeyafrica.bankassurance.ui.otp;

import android.app.AlertDialog;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.data.model.api.LoginUserRequest;
import com.turnkeyafrica.bankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.bankassurance.data.model.api.SimpleOtpRequest;
import com.turnkeyafrica.bankassurance.data.model.api.TokenResponce;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

public class OtpViewModel extends BaseViewModel<OtpNavigator> {

    public String operationType;

    private AlertDialog alertDialog;

    public OtpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void LoginClick() {
        getNavigator().Login();
    }

    public void verifyOtp(OtpRequest request, String device, int operationSet){

        alertDialog = getNavigator().openLoading();

        if(operationSet == 1) {
            getCompositeDisposable().add(getDataManager()
                    .verifyOTP(request, device)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        getNavigator().closeLoading(alertDialog);
               /*     if(!CommonUtils.StringIsEmpty(operationType)) {

                        // disable for version 2
                        Gson gson = new Gson();
                        ClientDetailsUpdateRequest clientDetailsUpdateRequest = gson.fromJson(operationType,ClientDetailsUpdateRequest.class);
                        getDataManager().setAccessToken(response.getAccessToken());
                      //  updateClientDetails(clientDetailsUpdateRequest,response);
                    }else {*/
                        getUserDetails(response);

                    }, throwable -> {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(ErrorBase.Error(throwable));
                    }));
        }else {

            getCompositeDisposable().add(getDataManager()
                    .verifySimpleOTP(new SimpleOtpRequest(request.getPhoneNumber(),request.getResetCode()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        getNavigator().closeLoading(alertDialog);
                        if(response) {
                            if (operationSet == 2) {
                                getNavigator().openPasswords();
                            } else {
                                getNavigator().openResetPassword();
                            }
                        }
                    }, throwable -> {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(ErrorBase.Error(throwable));
                    }));
        }
    }

   /* public void updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest, TokenResponce tokenResponce){
        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .updateClientDetails(clientDetailsUpdateRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                tokenResponce.getAccessToken(),
                                clientDetailsUpdateRequest.getClntCode().toString(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                clientDetailsUpdateRequest.getSurname()+ " " + clientDetailsUpdateRequest.getFirstName(),
                                clientDetailsUpdateRequest.getPhoneNumber(),
                                clientDetailsUpdateRequest.getEmail()))
                .subscribe(res -> getNavigator().openDashBoardActivity(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }*/

    public void resendCode(LoginUserRequest request, String operationType, String email) {

        alertDialog = getNavigator().openLoading();

        if(!CommonUtils.StringIsEmpty(request.getPassword())) {
            getCompositeDisposable().add(getDataManager()
                    .requestOTP(request)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getNavigator().closeLoading(alertDialog))
                    .subscribe(response -> Analytics.trackEvent("Request Otp result for " + request + " :-" + response),
                            throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
        }else{
            getCompositeDisposable().add(getDataManager()
                    .requestSimpleOTP(new DataWrapper(request.getUsername()), operationType, email)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getNavigator().closeLoading(alertDialog))
                    .subscribe(response -> Analytics.trackEvent("Request Otp result for " + request + " :-" + response),
                            throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
        }

    }

    private void getUserDetails(TokenResponce tokenResponce) {

        DataWrapper dataWrapper = new DataWrapper(getDataManager().getUUID());

        alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getClientDetailMod(dataWrapper, tokenResponce.getAccessToken())
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                tokenResponce.getAccessToken(),
                                response.getCode(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getNames(),
                                response.getPhoneNumber(),
                                response.getClient(),
                                response.getEmail()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doFinally(() -> getNavigator().closeLoading(alertDialog))
                .subscribe(response -> getNavigator().openDashBoardActivity(), throwable -> getNavigator().handleError(ErrorBase.Error(throwable))));
    }

}
