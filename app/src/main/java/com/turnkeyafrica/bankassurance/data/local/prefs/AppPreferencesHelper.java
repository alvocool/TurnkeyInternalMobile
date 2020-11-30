package com.turnkeyafrica.bankassurance.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.bankassurance.data.model.api.QouteResponce;
import com.turnkeyafrica.bankassurance.data.model.others.MiscData;
import com.turnkeyafrica.bankassurance.di.PreferenceInfo;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_UNIQUE_ID = "PREF_KEY_UNIQUE_ID";

    private static final String PREF_KEY_MISC_DATA = "PREF_KEY_MISC_DATA";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_MOBILE_NUMBER = "PREF_KEY_CURRENT_USER_MOBILE_NUMBER";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";

    private static final String PREF_KEY_CURRENT_COMPARISON_REQUEST = "PREF_KEY_CURRENT_COMPARISON_REQUEST";

    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_CURRENT_QUOTE = "PREF_KEY_CURRENT_QUOTE";

    private static final String PREF_KEY_CURRENT_POLICY = "PREF_KEY_CURRENT_POLICY";

    private static final String PREF_KEY_INSURANCE_QUOTE = "PREF_KEY_INSURANCE_QUOTE";

    private static final String PREF_KEY_CURRENT_CLAIMS_DETAILS_OBJECT = "PREF_KEY_CURRENT_CLAIMS_DETAILS_OBJECT";

    private static final String PREF_KEY_NOTIFICATION_TOKEN = "PREF_KEY_NOTIFICATION_TOKEN";

    private static final String PREF_KEY_CURRENT_CLIENT_ID = "PREF_KEY_CURRENT_CLIENT_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {

        SharedPreferences sharedPreferences = null;

        try {

            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
             sharedPreferences = EncryptedSharedPreferences.create(
                    prefFileName,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);


        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        mPrefs = sharedPreferences;

        //   mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getInsuranceQuote() {
        return mPrefs.getString(PREF_KEY_INSURANCE_QUOTE, null);
    }

    @Override
    public void setInsuranceQuote(String insuranceQuote) {
        mPrefs.edit().putString(PREF_KEY_INSURANCE_QUOTE, insuranceQuote).apply();
    }

    @Override
    public String getUUID() {
        return mPrefs.getString(PREF_KEY_UNIQUE_ID, null);
    }

    @Override
    public void setUUID(String UUID) {
        mPrefs.edit().putString(PREF_KEY_UNIQUE_ID, UUID).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserMobileNumber() {

        return mPrefs.getString(PREF_KEY_CURRENT_USER_MOBILE_NUMBER, null);
    }

    @Override
    public void setCurrentUserMobileNumber(String mobileNumber) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_MOBILE_NUMBER, mobileNumber).apply();
    }

    @Override
    public String getCurrentUserId() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID, null);
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public void setCurrentClientId(String clientId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_CLIENT_ID, clientId).apply();
    }

    @Override
    public String getCurrentClientId() {
        return mPrefs.getString(PREF_KEY_CURRENT_CLIENT_ID, null);
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public void setComparisonRequest(ComparisonRequest comparisonRequest) {

         String JSONString = new Gson().toJson(comparisonRequest);
         mPrefs.edit().putString(PREF_KEY_CURRENT_COMPARISON_REQUEST,JSONString).apply();
    }

    @Override
    public void clearComparisonRequest() {
        mPrefs.edit().remove(PREF_KEY_CURRENT_COMPARISON_REQUEST).apply();
    }

    @Override
    public String getComparisonRequest() {

        return mPrefs.getString(PREF_KEY_CURRENT_COMPARISON_REQUEST, null);
    }

    @Override
    public void setQuoteResponce(QouteResponce quoteResponce) {
        String JSONString = new Gson().toJson(quoteResponce);
        mPrefs.edit().putString(PREF_KEY_CURRENT_QUOTE,JSONString).apply();
    }

    @Override
    public String getQuoteResponce() {
        return mPrefs.getString(PREF_KEY_CURRENT_QUOTE, null);
    }

    @Override
    public void setPolicyResponse(PolicyResponce policyResponse) {
        String JSONString = new Gson().toJson(policyResponse);
        mPrefs.edit().putString(PREF_KEY_CURRENT_POLICY,JSONString).apply();
    }

    @Override
    public String getPolicyResponse() {
        return mPrefs.getString(PREF_KEY_CURRENT_POLICY, null);
    }

    @Override
    public String getClaimsDetailsObject() {
        return mPrefs.getString(PREF_KEY_CURRENT_CLAIMS_DETAILS_OBJECT, null);
    }

    @Override
    public void setClaimsDetailsObject(ClaimsDetailsObject claimsDetailsObject) {
        String JSONString = new Gson().toJson(claimsDetailsObject);
        mPrefs.edit().putString(PREF_KEY_CURRENT_CLAIMS_DETAILS_OBJECT,JSONString).apply();
    }

    @Override
    public String getMiscData() {
        return mPrefs.getString(PREF_KEY_MISC_DATA, null);
    }

    @Override
    public void setMiscData(MiscData miscData) {
        String JSONString = new Gson().toJson(miscData);
        mPrefs.edit().putString(PREF_KEY_MISC_DATA,JSONString).apply();
    }

    @Override
    public void setNotificationToken(boolean state) {
        mPrefs.edit().putBoolean(PREF_KEY_NOTIFICATION_TOKEN,state).apply();
    }

    @Override
    public Boolean getNotificationToken() {
        return mPrefs.getBoolean(PREF_KEY_NOTIFICATION_TOKEN, false);
    }
}
