package com.turnkeyafrica.turnkeybankassurance.data.remote;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.CardDetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientDetailsUpdateRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientRegistrationRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClientRegistrationResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.CoverTypesResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.DetailsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.EvaluationDocRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ExtraBenefitsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.LoginRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.LogoutResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaCheckStatusResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.MpesaResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.OtpRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyCovertionResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ProductResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.QouteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.SendClaimDetailsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestType;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.StatusResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.TokenResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadAttachmentList;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.VehicleMakesResponce;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import okhttp3.OkHttpClient;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    private OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<TokenResponce> getAccessToken(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addBodyParameter(request)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(TokenResponce.class);
    }

    @Override
    public Single<ClientDetailsResponce> getClientDetails(String token) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_CLIENT_DETAILS)
                .addHeaders("Authorization", "Bearer " + token)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(ClientDetailsResponce.class);
    }

    @Override
    public Single<ClientRegistrationResponce> sendRegDetails(ClientRegistrationRequest clientRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_CLIENT_CREATION)
                .addHeaders(mApiHeader.getmProtectedLoginApiHeader())
                .addApplicationJsonBody(clientRequest)
                .addHeaders("os","android")
                .addHeaders("browser","android")
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(ClientRegistrationResponce.class);
    }

    @Override
    public Single<TokenResponce> verifyOTP(OtpRequest otpRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_VERIFY_OTP)
                .addHeaders(mApiHeader.getmProtectedLoginApiHeader())
                .addApplicationJsonBody(otpRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(TokenResponce.class);
    }

    @Override
    public Single<Boolean> requestOTP(String phoneNumber) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_REQUEST_OTP)
                .addHeaders(mApiHeader.getmProtectedLoginApiHeader())
                .addQueryParameter("phoneNumber",phoneNumber)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<Boolean> updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPDATE_CLIENT_DETAILS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(clientDetailsUpdateRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }


    @Override
    public Single<MpesaResponce> payment(MpesaRequest mpesaRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_MPESA_PAYMENT)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(mpesaRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(MpesaResponce.class);
    }

    @Override
    public Single<ComparisonRequest> editQuote(BigDecimal quoteCode) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_REQUEST_EDIT_QUOTE)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("quoteCode",String.valueOf(quoteCode))
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(ComparisonRequest.class);
    }

    @Override
    public Single<CardDetailsResponce> paymentCard(CardDetailsRequest cardDetailsRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CARD_PAYMENT)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(cardDetailsRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(CardDetailsResponce.class);
    }


    @Override
    public Single<Boolean> saveQuote(BigDecimal quoteCode) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SAVE_QUOTE)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addBodyParameter("quoteCode", String.valueOf(quoteCode.intValueExact()))
                .addBodyParameter("action", "S")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<MpesaCheckStatusResponce> verifyPayment(String CheckoutRequestID,BigDecimal agentCode) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MPESA_PAYMENT_VERIFICATION)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("CheckoutRequestID", CheckoutRequestID)
                .addQueryParameter("agentCode", String.valueOf(agentCode))
                .setTag("verify payment")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(MpesaCheckStatusResponce.class);
    }

    @Override
    public Single<PolicyCovertionResponce> convertToPolicy(BigDecimal QuotCode, String idNumber, String kraPin, BigDecimal amountPaid) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CONVERT_TO_POLICY)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("quoteCode", String.valueOf(QuotCode))
                .addQueryParameter("idNumber",idNumber)
                .addQueryParameter("kraPin",kraPin)
                .addQueryParameter("amountPaid",String.valueOf(amountPaid))
                .setTag("GET Convert Quote To Policy")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(PolicyCovertionResponce.class);
    }

    @Override
    public Single<List<ProductResponse>> getAllProducts(String option) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_PRODUCTS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("webEnabled",option)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ProductResponse.class);
    }

    @Override
    public Single<List<ClaimsResponse>> getAllClaims(Long clientId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_ALL_CLIENTS_ClAIMS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clntCode", String.valueOf(clientId))
                .setTag("GET Claims")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ClaimsResponse.class);
    }

    @Override
    public Single<List<ExtraBenefitsResponse>> getMotorExtraBenefits(BigDecimal code) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_MOTOR_EXTRA_BENEFITS +"{code}")
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addPathParameter("code", String.valueOf(code))
                .setTag("GET Extra benefits")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ExtraBenefitsResponse.class);

    }

    @Override
    public Single<List<VehicleMakesResponce>> getVehicleMakes() {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_VEHICLE_MAKES)
                .addHeaders("Authorization", "Bearer "  + mApiHeader.getProtectedApiHeader().getAccessToken())
                .setTag("GET Vehicle Makes")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(VehicleMakesResponce.class);
    }

    @Override
    public Single<List<DetailsResponce>> getDetails(BigDecimal bindCode, String schvType) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_FETCH_SCHEDULE_VALUES)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("schvType", String.valueOf(schvType))
                .addQueryParameter("bindCode", String.valueOf(bindCode))
                .setTag("Get Details")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(DetailsResponce.class);
    }

    @Override
    public Single<List<CoverTypesResponce>> getCoverTypes(BigDecimal sclCode) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_COVER_TYPES)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("sclCode", String.valueOf(sclCode))
                .setTag("Get Cover Types")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(CoverTypesResponce.class);
    }

    @Override
    public Single<List<InsuranceQuoteResponce>> getInsuranceQuotes(ComparisonRequest comparisonRequest) {

          return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_COMPUTE_PREM)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(comparisonRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(InsuranceQuoteResponce.class);
    }

    @Override
    public Single<Boolean> uploadDocuments(UploadRequest  uploadRequest) {

        UploadAttachmentList uploadAttachmentList = new UploadAttachmentList();

        List<UploadRequest> myRequest = new ArrayList<>();

        myRequest.add(uploadRequest);

        uploadAttachmentList.setAttachmentList(myRequest);

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPLOAD_DOCUMENTS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(uploadAttachmentList)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<Boolean> emailValuer(BigDecimal valuerCode,String batchNo) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EMAIL_VALUER)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("valuerCode",String.valueOf(valuerCode))
                .addQueryParameter("polBatchNo",batchNo)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<Boolean> selectCeritificatePickupPoint(BigDecimal branchCode,String polBatchNo) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SELECT_PICK_UP_POINT)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("branchCode",String.valueOf(branchCode))
                .addQueryParameter("polBatchNo",polBatchNo)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }
    @Override
    public Single<List<BranchResponce>> getBranchesByRegionCode(BigDecimal branchRegionCode) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVICE_PROVIDE_BY_CODE)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("branchRegionCode",String.valueOf(branchRegionCode))
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(BranchResponce.class);
    }

    @Override
    public Single<List<ServiceRequestType>> getAllServiceRequestTypes(String platform) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVICE_REQUEST_TYPES)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("platform",platform)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ServiceRequestType.class);
    }

    @Override
    public Single<List<ServiceRequest>> getAllServiceRequest(String clientCode) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVICE_REQUEST)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clientCode",clientCode)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ServiceRequest.class);
    }

    @Override
    public Single<List<NotificationsResponse>> getAllNotifications(String clientCode, String status) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_NOTIFICATIONS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clientCode",clientCode)
                .addQueryParameter("status",status)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(NotificationsResponse.class);
    }

    @Override
    public Single<StatusResponce> updateNotification(NotificationsResponse notification) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPDATE_MESSAGES)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(notification)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(StatusResponce.class);
    }

    @Override
    public Single<StatusResponce> saveServiceRequest(ServiceRequestResponse serviceRequestResponse) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SAVE_SERVICE_REQUEST)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(serviceRequestResponse)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(StatusResponce.class);
    }


    @Override
    public Single<Boolean> sendNotificationID(String token, String uuid, String clientCode) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER_NOTIFICATIONS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("token",token)
                .addQueryParameter("uuid",uuid)
                .addQueryParameter("clientCode",clientCode)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<Boolean> checkMpesaResultsManually(String code, String agent,
                                                     String quoteNo, String amount,
                                                     String clientName, String phoneNumber) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MANUAL_MPESA_CHECK)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("mpesaCode",code)
                .addQueryParameter("agentCode",agent)
                .addQueryParameter("quoteNo",quoteNo)
                .addQueryParameter("amount",amount)
                .addQueryParameter("clientName",clientName)
                .addQueryParameter("phoneNumber",phoneNumber)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(Boolean.class);
    }

    @Override
    public Single<List<String>> getMpesaPaymentInfo(String agent, String amount, String refNumber) {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_MPESA_DETAILS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("agentCode",agent)
                .addQueryParameter("amount", amount)
                .addQueryParameter("refNumber", refNumber)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(String.class);
    }

    @Override
    public Single<List<BranchRegionResponce>> getBranchesRegions() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_BRANCHES)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .setTag("Get all branches")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(BranchRegionResponce.class);
    }

    @Override
    public Single<List<ValuersResponce>> getValuers(String location) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_VALUERS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("location",location)
                .setTag("Get All Valuers")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(ValuersResponce.class);
    }



    @Override
    public Single<ByteArrayInputStream> getEvaluationDocument(EvaluationDocRequest evaluationDocRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_EVALUATION_REPORT)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(evaluationDocRequest)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(ByteArrayInputStream.class);
    }

    @Override
    public Single<StatusResponce> saveClaim(SendClaimDetailsResponse sendClaimDetailsResponse) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SAVE_CLAIM)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addApplicationJsonBody(sendClaimDetailsResponse)
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(StatusResponce.class);
    }

    @Override
    public Single<List<String>> getLocations() {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_LOCATIONS)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .setTag("GET Locations")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(String.class);

    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }



    @Override
    public Single<List<InsuranceQuoteResponce>> getClientsQuotes(BigDecimal clientId) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_ALL_CLIENTS_QUOTE)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clntCode", String.valueOf(clientId))
                .setTag("GET Quotes")
                .setOkHttpClient(okHttpClient)
                .build()
                  .getObjectListSingle(InsuranceQuoteResponce.class);

    }

    @Override
    public Single<List<PolicyResponce>> getClientsPolicies(BigDecimal clientId) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_ALL_CLIENTS_POLICIES +"{clntCode}")
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addPathParameter("clntCode", String.valueOf(clientId))
                .setTag("GET Client Policies")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectListSingle(PolicyResponce.class);

    }

    @Override
    public Single<QouteResponce> getLatestClientsQuotes(BigDecimal clientId) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_LATEST_CLIENTS_QUOTE)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clntCode", String.valueOf(clientId))
                .setTag("GET Latest Quotes")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(QouteResponce.class);
    }

    @Override
    public Single<QouteResponce> getQuotebyCode(BigDecimal quoteId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_QOUTE_BY_ID )
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("quoteCode", String.valueOf(quoteId))
                .setTag("GET Quotes by ID")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(QouteResponce.class);
    }

    @Override
    public Single<PolicyResponce> getPolicybyBatchNo(BigDecimal batchNo) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_POLICY_BY_BATCH_NUMBER)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("batchNo", String.valueOf(batchNo))
                .setTag("GET Policy by Batch Number")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(PolicyResponce.class);
    }


    @Override
    public Single<PolicyResponce> getLatestClientsPolicy(BigDecimal clientId) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_LATEST_CLIENTS_POLICY)
                .addHeaders("Authorization", "Bearer " + mApiHeader.getProtectedApiHeader().getAccessToken())
                .addQueryParameter("clientCode", String.valueOf(clientId))
                .setTag("GET Latest Policy")
                .setOkHttpClient(okHttpClient)
                .build()
                .getObjectSingle(PolicyResponce.class);
    }

}
