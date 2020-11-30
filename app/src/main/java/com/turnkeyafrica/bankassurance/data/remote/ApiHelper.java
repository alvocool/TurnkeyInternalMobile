package com.turnkeyafrica.bankassurance.data.remote;


import com.turnkeyafrica.bankassurance.data.model.api.AnswerVerify;
import com.turnkeyafrica.bankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.bankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsRequest;
import com.turnkeyafrica.bankassurance.data.model.api.CardDetailsResponce;
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
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;


public interface ApiHelper {

    Single<ResponseBody> doLogoutApiCall();

    Single<TokenResponce> getAccessToken(LoginRequest.ServerLoginRequest request);

    Single<ClientDetailsResponce> getClientDetails(String token);

    Single<ClientDetailResponce> getClientDetailMod(DataWrapper dataWrapper, String token);

    Single<ClientRegistrationResponce> sendRegDetails(ClientRegistrationRequest clientRequest,
                                                      String device);

    Single<TokenResponce> verifyOTP(OtpRequest otpRequest, String device);

    Single<Boolean> updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest) ;

    Single<DataWrapper> requestOTP(LoginUserRequest request);

    Single<List<ProductResponse>> getAllProducts(String option);

    Single<List<ClaimsResponse>> getAllClaims();

    ApiHeader getApiHeader();

    Single<List<InsuranceQuoteResponce>> getClientsQuotes();

    Single<List<PolicyResponce>> getClientsPolicies(DataWrapper dataWrapper);

    Single<List<ExtraBenefitsResponse>> getMotorExtraBenefits(BigDecimal code);

    Single<List<VehicleMakesResponce>> getVehicleMakes();

    Single<List<DetailsResponce>> getDetails(BigDecimal bindCode, String schvType);

    Single<List<CoverTypesResponce>> getCoverTypes(BigDecimal sclCode);

    Single<List<InsuranceQuoteResponce>> getInsuranceQuotes(ComparisonRequest comparisonRequest);

    Single<Boolean> uploadDocuments(UploadRequest  uploadRequest);

    Single<Boolean> emailValuer(BigDecimal valuerCode, String batchNo);

    Single<List<ValuersResponce>> getValuers(String location);

    Single<ByteArrayInputStream> getEvaluationDocument(EvaluationDocRequest evaluationDocRequest);

    Single<List<String>> getLocations();

    Single<StatusResponce> saveClaim(SendClaimDetailsResponse sendClaimDetailsResponse);

    Single <QouteResponce> getLatestClientsQuotes();

    Single <QouteResponce> getQuotebyCode(BigDecimal quoteId);

    Single <PolicyResponce> getPolicybyBatchNo(BigDecimal batchNo);

    Single<MpesaResponce> payment(MpesaRequest mpesaRequest);

    Single<ComparisonRequest> editQuote(BigDecimal quoteCode);

    Single<Boolean> saveQuote(BigDecimal quoteCode);

    Single<CardDetailsResponce> paymentCard(CardDetailsRequest cardDetailsRequest);

    Single<MpesaCheckStatusResponce> verifyPayment(String CheckoutRequestID,BigDecimal agentCode);

    Single<PolicyCovertionResponce> convertToPolicy(BigDecimal QuotCode,String idNumber, String kraPin, BigDecimal amount);

    Single <PolicyResponce> getLatestClientsPolicy(DataWrapper dataWrapper);

    Single<Boolean> selectCeritificatePickupPoint(BigDecimal branchCode,String polBatchNo);

    Single<List<ServiceRequestType>> getAllServiceRequestTypes(String platform);

    Single<StatusResponce> saveServiceRequest(ServiceRequestResponse serviceRequestResponse);

    Single<Boolean> sendNotificationID(String token, String uuid, String clientCode);

    Single<List<BranchResponce>> getBranchesByRegionCode(BigDecimal branchRegionCode);

    Single<Boolean> checkMpesaResultsManually(String code, String agent,
                                              String quoteNo, String amount,
                                              String clientName, String phoneNumber);

    Single<List<String>> getMpesaPaymentInfo(String agent, String amount, String refNumber);

    Single<List<BranchRegionResponce>> getBranchesRegions();

    Single<List<ServiceRequest>> getAllServiceRequest(String clientCode);

    Single<List<NotificationsResponse>> getAllNotifications(String clientCode,String status);

    Single<Boolean> updateNotification(DataWrapper dataWrapper);

    Single<Boolean> requestSimpleOTP(DataWrapper wrapper, String operationType, String email) ;

    Single<Boolean> verifySimpleOTP(SimpleOtpRequest otpRequest);

    Single<List<SecurityQuestions>> getAllQuestions();

    Single<SecurityQuestions> getQuestionByMobile(DataWrapper wrapper);

    Single<Boolean> updateCredentials(CredentialsUpdate credentialsUpdate);

    Single<Boolean> getVerifyAnswer(AnswerVerify answerVerify);
}
