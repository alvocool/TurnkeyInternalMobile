package com.turnkeyafrica.turnkeybankassurance.data.remote;


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
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.VehicleMakesResponce;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Single;


public interface ApiHelper {

    Single<LogoutResponse> doLogoutApiCall();

    Single<TokenResponce> getAccessToken(LoginRequest.ServerLoginRequest request);

    Single<ClientDetailsResponce> getClientDetails(String token);

    Single<ClientRegistrationResponce> sendRegDetails(ClientRegistrationRequest clientRequest);

    Single<TokenResponce> verifyOTP(OtpRequest otpRequest);

    Single<Boolean> updateClientDetails(ClientDetailsUpdateRequest clientDetailsUpdateRequest) ;

    Single<Boolean> requestOTP(String phoneNumber);

    Single<List<ProductResponse>> getAllProducts(String option);

    Single<List<ClaimsResponse>> getAllClaims(Long clientId);

    ApiHeader getApiHeader();

    Single<List<InsuranceQuoteResponce>> getClientsQuotes(BigDecimal clientId);

    Single<List<PolicyResponce>> getClientsPolicies(BigDecimal clientId);

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

    Single <QouteResponce> getLatestClientsQuotes(BigDecimal clientId);

    Single <QouteResponce> getQuotebyCode(BigDecimal quoteId);

    Single <PolicyResponce> getPolicybyBatchNo(BigDecimal batchNo);

    Single<MpesaResponce> payment(MpesaRequest mpesaRequest);

    Single<ComparisonRequest> editQuote(BigDecimal quoteCode);

    Single<Boolean> saveQuote(BigDecimal quoteCode);

    Single<CardDetailsResponce> paymentCard(CardDetailsRequest cardDetailsRequest);

    Single<MpesaCheckStatusResponce> verifyPayment(String CheckoutRequestID,BigDecimal agentCode);

    Single<PolicyCovertionResponce> convertToPolicy(BigDecimal QuotCode,String idNumber, String kraPin, BigDecimal amount);

    //todo incase of errors in the getlatest policy the path will have change from clntId to clientid
    Single <PolicyResponce> getLatestClientsPolicy(BigDecimal clientId);

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

    Single<StatusResponce> updateNotification(NotificationsResponse notification);
}
