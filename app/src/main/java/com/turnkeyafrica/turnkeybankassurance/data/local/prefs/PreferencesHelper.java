package com.turnkeyafrica.turnkeybankassurance.data.local.prefs;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.QouteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.MiscData;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setUUID(String UUID);

    String getUUID();

    void setCurrentUserEmail(String email);

    String getCurrentUserMobileNumber();

    void setInsuranceQuote(String insuranceQuote);

    String getInsuranceQuote();

    void setCurrentUserMobileNumber(String mobileNumber);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    void setComparisonRequest(ComparisonRequest comparisonRequest);

    void clearComparisonRequest();

    String getComparisonRequest();

    void setQuoteResponce(QouteResponce quoteResponce);

    String getQuoteResponce();

    void setPolicyResponse(PolicyResponce policyResponse);

    String getPolicyResponse();

    String getClaimsDetailsObject();

    void setClaimsDetailsObject(ClaimsDetailsObject claimsDetailsObject);

    String getMiscData();

    void setMiscData(MiscData miscData);

    void setNotificationToken(boolean state);

    Boolean getNotificationToken();
}
