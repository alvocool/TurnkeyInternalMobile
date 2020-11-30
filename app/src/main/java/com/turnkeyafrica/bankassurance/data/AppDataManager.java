package com.turnkeyafrica.bankassurance.data;

import com.turnkeyafrica.bankassurance.data.local.prefs.PreferencesHelper;
import com.turnkeyafrica.bankassurance.data.model.api.AnswerVerify;
import com.turnkeyafrica.bankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.bankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ClientDetailsUpdateRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.CoverTypesResponce;
import com.turnkeyafrica.bankassurance.data.model.api.CredentialsUpdate;
import com.turnkeyafrica.bankassurance.data.model.api.DataWrapper;
import com.turnkeyafrica.bankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.bankassurance.data.model.api.EvaluationDocRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ExtraBenefitsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.data.model.api.LoginRequest;
import com.turnkeyafrica.bankassurance.data.model.api.LoginUserRequest;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaCheckStatusResponce;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.bankassurance.data.model.api.MpesaResponce;
import com.turnkeyafrica.bankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyCovertionResponce;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ProductResponse;
import com.turnkeyafrica.bankassurance.data.model.api.QouteResponce;
import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.data.model.api.SendClaimDetailsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequestResponse;
import com.turnkeyafrica.bankassurance.data.model.api.ServiceRequestType;
import com.turnkeyafrica.bankassurance.data.model.api.SimpleOtpRequest;
import com.turnkeyafrica.bankassurance.data.model.api.StatusResponce;
import com.turnkeyafrica.bankassurance.data.model.api.TokenResponce;
import com.turnkeyafrica.bankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.bankassurance.data.model.api.VehicleMakesResponce;
import com.turnkeyafrica.bankassurance.data.model.others.MiscData;
import com.turnkeyafrica.bankassurance.data.remote.ApiHeader;
import com.turnkeyafrica.bankassurance.data.remote.ApiHelper;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import okhttp3.ResponseBody;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }



    @Override
    public Single<ResponseBody> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public Single<TokenResponce> getAccessToken(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.getAccessToken(request);
    }

    @Override
    public Single<ClientDetailsResponce> getClientDetails(String token) {
        return mApiHelper.getClientDetails(token);
    }

    @Override
    public Single<ClientDetailResponce> getClientDetailMod(DataWrapper dataWrapper , String token) {
        return mApiHelper.getClientDetailMod(dataWrapper, token);
    }

    @Override
    public Single<ClientRegistrationResponce> sendRegDetails(ClientRegistrationRequest clientRequest,String device) {
        return mApiHelper.sendRegDetails(clientRequest, device);
    }

    @Override
    public Single<TokenResponce> verifyOTP(OtpRequest otpRequest, String device) {
        return mApiHelper.verifyOTP(otpRequest, device);
    }

    @Override
    public Single<Boolean> updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest) {
        return mApiHelper.updateClientDetails(clientDetailsUpdateRequest);
    }

    @Override
    public Single<DataWrapper> requestOTP(LoginUserRequest request) {
        return mApiHelper.requestOTP(request);
    }
    @Override
    public Single<List<ProductResponse>> getAllProducts(String option) {
        return mApiHelper.getAllProducts(option);
    }

    @Override
    public Single<List<ClaimsResponse>> getAllClaims() {
        return mApiHelper.getAllClaims();
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<List<InsuranceQuoteResponce>> getClientsQuotes() {
        return mApiHelper.getClientsQuotes();
    }

    @Override
    public Single<List<PolicyResponce>> getClientsPolicies(DataWrapper dataWrapper) {
        return mApiHelper.getClientsPolicies(dataWrapper);
    }

    @Override
    public Single<List<ExtraBenefitsResponse>> getMotorExtraBenefits(BigDecimal code) {
        return mApiHelper.getMotorExtraBenefits(code);
    }

    @Override
    public Single<List<VehicleMakesResponce>> getVehicleMakes() {
        return mApiHelper.getVehicleMakes();
    }

    @Override
    public Single<List<DetailsResponce>> getDetails(BigDecimal bindCode, String schvType) {
        return mApiHelper.getDetails(bindCode,schvType);
    }

    @Override
    public Single<List<CoverTypesResponce>> getCoverTypes(BigDecimal sclCode) {
        return mApiHelper.getCoverTypes(sclCode);
    }

    @Override
    public Single<List<InsuranceQuoteResponce>> getInsuranceQuotes(ComparisonRequest comparisonRequest) {
        return mApiHelper.getInsuranceQuotes(comparisonRequest);
    }
    @Override
    public Single<Boolean> uploadDocuments(UploadRequest uploadRequest) {
        return mApiHelper.uploadDocuments(uploadRequest);
    }

    @Override
    public Single<Boolean> emailValuer(BigDecimal valuerCode,String batchNo) {
        return mApiHelper.emailValuer(valuerCode,batchNo);
    }


    @Override
    public Single<List<ValuersResponce>> getValuers(String location) {
        return mApiHelper.getValuers(location);
    }

    @Override
    public Single<ByteArrayInputStream> getEvaluationDocument(EvaluationDocRequest evaluationDocRequest) {
        return mApiHelper.getEvaluationDocument(evaluationDocRequest);
    }

    @Override
    public Single<List<String>> getLocations() {
        return mApiHelper.getLocations();
    }

    @Override
    public Single<StatusResponce> saveClaim(SendClaimDetailsResponse sendClaimDetailsResponse) {
        return mApiHelper.saveClaim(sendClaimDetailsResponse);
    }

    @Override
    public Single<QouteResponce> getLatestClientsQuotes() {
        return mApiHelper.getLatestClientsQuotes();
    }

    @Override
    public Single<QouteResponce> getQuotebyCode(BigDecimal quoteId) {
        return mApiHelper.getQuotebyCode(quoteId);
    }

    @Override
    public Single<PolicyResponce> getPolicybyBatchNo(BigDecimal batchNo) {
        return mApiHelper.getPolicybyBatchNo(batchNo);
    }

    @Override
    public Single<MpesaResponce> payment(MpesaRequest mpesaRequest) {
        return mApiHelper.payment(mpesaRequest);
    }

    @Override
    public Single<ComparisonRequest> editQuote(BigDecimal quoteCode) {
        return mApiHelper.editQuote(quoteCode);
    }

    @Override
    public Single<Boolean> saveQuote(BigDecimal quoteCode) {
        return mApiHelper.saveQuote(quoteCode);
    }

    @Override
    public Single<CardDetailsResponce> paymentCard(CardDetailsRequest cardDetailsRequest) {
        return mApiHelper.paymentCard(cardDetailsRequest);
    }

    @Override
    public Single<MpesaCheckStatusResponce> verifyPayment(String CheckoutRequestID,BigDecimal agentCode) {
        return mApiHelper.verifyPayment(CheckoutRequestID,agentCode);
    }

    @Override
    public Single<PolicyCovertionResponce> convertToPolicy(BigDecimal QuotCode, String idNumber, String kraPin, BigDecimal amountPaid) {
        return mApiHelper.convertToPolicy(QuotCode, idNumber, kraPin, amountPaid);
    }

    @Override
    public Single<PolicyResponce> getLatestClientsPolicy(DataWrapper dataWrapper) {
        return mApiHelper.getLatestClientsPolicy(dataWrapper);
    }

    @Override
    public Single<Boolean> selectCeritificatePickupPoint(BigDecimal branchCode, String polBatchNo) {
        return mApiHelper.selectCeritificatePickupPoint(branchCode,polBatchNo);
    }

    @Override
    public Single<List<ServiceRequestType>> getAllServiceRequestTypes(String platform) {
        return mApiHelper.getAllServiceRequestTypes(platform);
    }

    @Override
    public Single<StatusResponce> saveServiceRequest(ServiceRequestResponse serviceRequestResponse) {
        return mApiHelper.saveServiceRequest(serviceRequestResponse);
    }

    @Override
    public Single<Boolean> sendNotificationID(String token, String uuid, String clientCode) {
        return mApiHelper.sendNotificationID(token, uuid, clientCode);
    }

    @Override
    public Single<List<BranchResponce>> getBranchesByRegionCode(BigDecimal branchRegionCode) {
        return mApiHelper.getBranchesByRegionCode(branchRegionCode);
    }

    @Override
    public Single<Boolean> checkMpesaResultsManually(String code, String agent,
                                                     String quoteNo, String amount,
                                                     String clientName, String phoneNumber) {
        return mApiHelper.checkMpesaResultsManually(code, agent, quoteNo,amount,clientName,phoneNumber);
    }

    @Override
    public Single<List<String>> getMpesaPaymentInfo(String agent, String amount, String refNumber) {
        return mApiHelper.getMpesaPaymentInfo(agent,amount,refNumber);
    }

    @Override
    public Single<List<BranchRegionResponce>> getBranchesRegions() {
        return mApiHelper.getBranchesRegions();
    }

    @Override
    public Single<List<ServiceRequest>> getAllServiceRequest(String clientCode) {
        return mApiHelper.getAllServiceRequest(clientCode);
    }

    @Override
    public Single<List<NotificationsResponse>> getAllNotifications(String clientCode,String status) {
        return mApiHelper.getAllNotifications(clientCode,status);
    }

    @Override
    public Single<Boolean> updateNotification(DataWrapper dataWrapper) {
        return mApiHelper.updateNotification(dataWrapper);
    }

    @Override
    public Single<Boolean> requestSimpleOTP(DataWrapper wrapper, String operationType, String email) {
        return mApiHelper.requestSimpleOTP(wrapper, operationType, email);
    }

    @Override
    public Single<Boolean> verifySimpleOTP(SimpleOtpRequest otpRequest) {
        return mApiHelper.verifySimpleOTP(otpRequest);
    }

    @Override
    public Single<List<SecurityQuestions>> getAllQuestions() {
        return mApiHelper.getAllQuestions();
    }

    @Override
    public Single<SecurityQuestions> getQuestionByMobile(DataWrapper wrapper) {
        return mApiHelper.getQuestionByMobile(wrapper);
    }

    @Override
    public Single<Boolean> updateCredentials(CredentialsUpdate credentialsUpdate) {
        return mApiHelper.updateCredentials(credentialsUpdate);
    }

    @Override
    public Single<Boolean> getVerifyAnswer(AnswerVerify answerVerify) {
        return mApiHelper.getVerifyAnswer(answerVerify);
    }


    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setUUID(String UUID) {
        mPreferencesHelper.setUUID(UUID);
        mApiHelper.getApiHeader().getmProtectedLoginApiHeader().setmUuid(UUID);
    }

    @Override
    public String getUUID() {
        return mPreferencesHelper.getUUID();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserMobileNumber() {
        return mPreferencesHelper.getCurrentUserMobileNumber();
    }

    @Override
    public void setInsuranceQuote(String insuranceQuote) {
        mPreferencesHelper.setInsuranceQuote(insuranceQuote);
    }

    @Override
    public String getInsuranceQuote() {
        return mPreferencesHelper.getInsuranceQuote();
    }

    @Override
    public void setCurrentUserMobileNumber(String mobileNumber) {
        mPreferencesHelper.setCurrentUserMobileNumber(mobileNumber);
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public void setCurrentClientId(String clientId) {
        mPreferencesHelper.setCurrentClientId(clientId);
    }

    @Override
    public String getCurrentClientId() {
        return mPreferencesHelper.getCurrentClientId();
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void setComparisonRequest(ComparisonRequest comparisonRequest) {
        mPreferencesHelper.setComparisonRequest(comparisonRequest);
    }

    @Override
    public String getComparisonRequest() {
       return mPreferencesHelper.getComparisonRequest();
    }

    @Override
    public void setQuoteResponce(QouteResponce quoteResponce) {
        mPreferencesHelper.setQuoteResponce(quoteResponce);
    }

    @Override
    public String getQuoteResponce() {
        return mPreferencesHelper.getQuoteResponce();
    }

    @Override
    public void setPolicyResponse(PolicyResponce policyResponse) {
        mPreferencesHelper.setPolicyResponse(policyResponse);
    }

    @Override
    public String getPolicyResponse() {
        return mPreferencesHelper.getPolicyResponse();
    }

    @Override
    public String getClaimsDetailsObject() {
        return mPreferencesHelper.getClaimsDetailsObject();
    }

    @Override
    public void setClaimsDetailsObject(ClaimsDetailsObject claimsDetailsObject) {
        mPreferencesHelper.setClaimsDetailsObject(claimsDetailsObject);
    }

    @Override
    public String getMiscData() {
        return mPreferencesHelper.getMiscData();
    }

    @Override
    public void setMiscData(MiscData miscData) {
        mPreferencesHelper.setMiscData(miscData);
    }

    @Override
    public void setNotificationToken(boolean state){
        mPreferencesHelper.setNotificationToken(state);
    }

    @Override
    public Boolean getNotificationToken() {
        return mPreferencesHelper.getNotificationToken();
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null,
                null);
    }

    @Override
    public void updateApiHeader(String userId, String accessToken, String uuid) {

        mApiHelper.getApiHeader().getProtectedApiHeader().setmUuid(uuid);
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateProtectedLoginApiHeader(String uuid) {
        mApiHelper.getApiHeader().getmProtectedLoginApiHeader().setmUuid(uuid);
    }

    @Override
    public void clearComparisonRequest() {
        mPreferencesHelper.clearComparisonRequest();
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String mobileNumber,
            String clientId,
            String email) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserMobileNumber(mobileNumber);
        setCurrentClientId(clientId);
        setCurrentUserEmail(email);

        updateApiHeader(userId, accessToken, getUUID());
    }
}
