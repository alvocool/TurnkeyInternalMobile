package com.turnkeyafrica.bankassurance.data.remote;


import com.turnkeyafrica.bankassurance.BuildConfig;

@SuppressWarnings("WeakerAccess")
public final class ApiEndPoint {

    public static final String ENDPOINT_LOGOUT = BuildConfig.BASE_URL + "auth-service/oauth/revoke-token";

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "barclays-uaa/oauth/token";

    public static final String ENDPOINT_SERVER_CLIENT_DETAILS = BuildConfig.BASE_URL + "auth-service/api/me";

    public static final String ENDPOINT_SERVER_CLIENT_CREATION = BuildConfig.BASE_URL + "auth-service/api/auth/register-new-prospect";

    public static final String ENDPOINT_SERVER_VERIFY_OTP = BuildConfig.BASE_URL + "auth-service/api/auth/validateLoginOTP";

    public static final String ENDPOINT_SERVER_REQUEST_OTP = BuildConfig.BASE_URL + "auth-service/api/auth/sendOtp";

    public static final String ENDPOINT_GET_ALL_CLIENTS_POLICIES = BuildConfig.BASE_URL + "motor-service/api/getClientPolicies";

    public static final String ENDPOINT_GET_VEHICLE_MAKES = BuildConfig.BASE_URL + "motor-service/api/getVehicleMakes";

    public static final String ENDPOINT_GET_ALL_CLIENTS_QUOTE = BuildConfig.BASE_URL + "brokerage-service/api/quotation/getClientQuotationsWrap";

    public static final String ENDPOINT_GET_LATEST_CLIENTS_QUOTE = BuildConfig.BASE_URL + "brokerage-service/api/quotation/getClientQuotationLatest";

    public static final String ENDPOINT_GET_LATEST_CLIENTS_POLICY = BuildConfig.BASE_URL + "brokerage-service/api/getClientPoliciesLatest";

    public static final String ENDPOINT_GET_PRODUCTS = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchProducts";

    public static final String ENDPOINT_GET_LOCATIONS = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchLocations";

    public static final String ENDPOINT_GET_ALL_CLIENTS_ClAIMS = BuildConfig.BASE_URL + "brokerage-service/api/claim/clientClaims";

    public static final String ENDPOINT_UPLOAD_DOCUMENTS= BuildConfig.BASE_URL + "brokerage-service/api/docs/uploadDocuments";

    public static final String ENDPOINT_GET_COVER_TYPES = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchCoverTypes";

    public static final String ENDPOINT_GET_FETCH_SCHEDULE_VALUES = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchScheduleValues";

    public static final String ENDPOINT_GET_MOTOR_EXTRA_BENEFITS = BuildConfig.BASE_URL + "motor-service/api/getMotorExtraBenefits/";

    public static final String ENDPOINT_COMPUTE_PREM = BuildConfig.BASE_URL + "brokerage-service/api/quotation/computePrem";

    public static final String ENDPOINT_CONVERT_TO_POLICY = BuildConfig.BASE_URL + "brokerage-service/api/quotation/convert-to-policy";

    public static final String ENDPOINT_GET_VALUERS =  BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchServiceProviders";

    public static final String ENDPOINT_GET_QOUTE_BY_ID =  BuildConfig.BASE_URL + "brokerage-service/api/quotation/getClientQuoteByQuoteCode";

    public static final String ENDPOINT_GET_POLICY_BY_BATCH_NUMBER =  BuildConfig.BASE_URL + "brokerage-service/api/getPolicyByBatchNo";

    public  static final String ENDPOINT_GET_EVALUATION_REPORT = BuildConfig.BASE_URL + "barclays-agent-api/api/generateReport";

    public  static final String ENDPOINT_MPESA_PAYMENT = BuildConfig.BASE_URL + "payments-service/api/payment/mpesa/process-stk-push";

    public  static final String ENDPOINT_MPESA_PAYMENT_VERIFICATION = BuildConfig.BASE_URL + "payments-service/api/payment/mpesa/stkCheckStatus";

    public  static final String ENDPOINT_REQUEST_EDIT_QUOTE = BuildConfig.BASE_URL + "brokerage-service/api/quotation/editQuotation";

    public  static final String ENDPOINT_SAVE_QUOTE = BuildConfig.BASE_URL + "brokerage-service/api/quotation/saveClientWebQuote";

    public static final String ENDPOINT_EMAIL_VALUER = BuildConfig.BASE_URL + "brokerage-service/api/quotation/emailValuer";

    public static final String ENDPOINT_UPDATE_CLIENT_DETAILS = BuildConfig.BASE_URL + "auth-service/api/auth/updateClient";

    public static final String ENDPOINT_CARD_PAYMENT = BuildConfig.BASE_URL + "payments-service/api/payment/cybersource/processCardPayment";

    public static final String ENDPOINT_SELECT_PICK_UP_POINT = BuildConfig.BASE_URL + "brokerage-service/api/quotation/updateCertificatePickup";

    public static final String ENDPOINT_GET_BRANCHES = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchBranchRegions";

    public static final String ENDPOINT_SERVICE_PROVIDE_BY_CODE = BuildConfig.BASE_URL + "brokerage-service/api/setups/fetchBranches";

    public static final String ENDPOINT_SAVE_SERVICE_REQUEST = BuildConfig.BASE_URL + "commons-service/api/saveServiceRequest";

    public static final String ENDPOINT_SERVICE_REQUEST_TYPES = BuildConfig.BASE_URL + "commons-service/api/getAllServiceRequestTypes";

    public static final String ENDPOINT_SERVICE_REQUEST = BuildConfig.BASE_URL + "commons-service/api/getAllServiceRequests";

    public static final String ENDPOINT_NOTIFICATIONS = BuildConfig.BASE_URL + "commons-service/api/getInboxMessages";

    public static final String ENDPOINT_UPDATE_MESSAGES = BuildConfig.BASE_URL + "commons-service/api/updateNotificationStatus";

    public static final String ENDPOINT_REGISTER_NOTIFICATIONS = BuildConfig.BASE_URL + "commons-service/api/registerWebClient";

    public static final String ENDPOINT_MANUAL_MPESA_CHECK = BuildConfig.BASE_URL + "payments-service/api/payment/mpesa/checkMpesaStatus";

    public static final String ENDPOINT_GET_MPESA_DETAILS = BuildConfig.BASE_URL +  "payments-service/api/payment/mpesa/mpesaDetails";

    public static final String ENDPOINT_SAVE_CLAIM = BuildConfig.BASE_URL + "commons-service/api/saveClaimServiceRequest";

    public static final String ENDPOINT_SERVER_CLIENT_INFO =  BuildConfig.BASE_URL + "auth-service/api/auth/getDetailsByCode";

    public static final String ENDPOINT_SIMPLE_OTP_VALIDATION = BuildConfig.BASE_URL + "auth-service/api/auth/validateOneTimePin";

    public static final String ENDPOINT_SIMPLE_OTP_REQUEST = BuildConfig.BASE_URL + "auth-service/api/auth/sendOneTimePinSms";

    public static final String ENDPOINT_GET_QUESTIONS = BuildConfig.BASE_URL + "auth-service/api/auth/getAllQuestions";

    public static final String ENDPOINT_GET_MY_QUESTIONS = BuildConfig.BASE_URL + "auth-service/api/auth/getClientQuestions";

    public static final String ENDPOINT_UPDATE_CREDENTIALS = BuildConfig.BASE_URL + "auth-service/api/auth/updateClientPassword";

    public static final String ENDPOINT_VERIFY_ANSWER = BuildConfig.BASE_URL + "auth-service/api/auth/validateSecurityQuestion";

    private ApiEndPoint() {
    }
}
