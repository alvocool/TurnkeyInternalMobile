package com.turnkeyafrica.bankassurance.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    private ProtectedLoginApiHeader mProtectedLoginApiHeader;


    @Inject
    public ApiHeader( ProtectedApiHeader protectedApiHeader , ProtectedLoginApiHeader protectedLoginApiHeader) {
        mProtectedApiHeader = protectedApiHeader;
        mProtectedLoginApiHeader = protectedLoginApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public ProtectedLoginApiHeader getmProtectedLoginApiHeader() {
        return mProtectedLoginApiHeader;
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("access_token")
        private String mAccessToken;

        @Expose
        @SerializedName("user_id")
        private String mUserId;

        @Expose
        @SerializedName("uuid")
        private String mUuid;

        public ProtectedApiHeader(String mUserId, String mAccessToken, String mUuid) {
            this.mUserId = mUserId;
            this.mAccessToken = mAccessToken;
            this.mUuid = mUuid;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }

        public String getUserId() {
            return mUserId;
        }

        public void setUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        public String getmUuid() {
            return mUuid;
        }

        public void setmUuid(String mUuid) {
            this.mUuid = mUuid;
        }
    }

    public static final class ProtectedLoginApiHeader {

        @Expose
        @SerializedName("uuid")
        private String mUuid;

        @Expose
        @SerializedName("platform")
        private String mPlatform;

        @Expose
        @SerializedName("version")
        private String mAppVersion;

        public ProtectedLoginApiHeader(String mUuid, String mPlatform, String mAppVersion) {
            this.mUuid = mUuid;
            this.mPlatform = mPlatform;
            this.mAppVersion = mAppVersion;
        }


        public String getmAppVersion() {
            return mAppVersion;
        }

        public void setmAppVersion(String mAppVersion) {
            this.mAppVersion = mAppVersion;
        }

        public String getmUuid() {
            return mUuid;
        }

        public void setmUuid(String mUuid) {
            this.mUuid = mUuid;
        }

        public String getmPlatform() {
            return mPlatform;
        }

        public void setmPlatform(String mPlatform) {
            this.mPlatform = mPlatform;
        }
    }

    public static final class ProtectedUploadApiHeader {

        @Expose
        @SerializedName("clientCode")
        private String mclientCode;

        @Expose
        @SerializedName("docName")
        private String mdocName;

        @Expose
        @SerializedName("polNo")
        private String mpolNo;

        @Expose
        @SerializedName("transType")
        private String mtransType;

        public ProtectedUploadApiHeader(String mclientCode, String mdocName, String mpolNo, String mtransType) {
            this.mclientCode = mclientCode;
            this.mdocName = mdocName;
            this.mpolNo = mpolNo;
            this.mtransType = mtransType;
        }

        public String getMclientCode() {
            return mclientCode;
        }

        public void setMclientCode(String mclientCode) {
            this.mclientCode = mclientCode;
        }

        public String getMdocName() {
            return mdocName;
        }

        public void setMdocName(String mdocName) {
            this.mdocName = mdocName;
        }

        public String getMpolNo() {
            return mpolNo;
        }

        public void setMpolNo(String mpolNo) {
            this.mpolNo = mpolNo;
        }

        public String getMtransType() {
            return mtransType;
        }

        public void setMtransType(String mtransType) {
            this.mtransType = mtransType;
        }
    }
}
