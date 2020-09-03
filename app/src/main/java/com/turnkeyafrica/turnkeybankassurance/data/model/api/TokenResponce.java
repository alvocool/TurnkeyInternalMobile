package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class TokenResponce {

    @Expose
    @SerializedName("access_token")
    private String accessToken;
    @Expose
    @SerializedName("token_type")
    private String tokenType;
    @Expose
    @SerializedName("refresh_token")
    private String refreshToken;
    @Expose
    @SerializedName("expires_in")
    private BigDecimal expiresIn;
    @Expose
    @SerializedName("scope")
    private String scope;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        TokenResponce that = (TokenResponce) object;

        if (tokenType != null ? !tokenType.equals(that.tokenType) : that.tokenType != null) {
            return false;
        }
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) {
            return false;
        }
        if (refreshToken != null ? !refreshToken.equals(that.refreshToken) : that.refreshToken != null) {
            return false;
        }
        if (expiresIn != null ? !expiresIn.equals(that.expiresIn) : that.expiresIn != null) {
            return false;
        }

        return scope != null ? scope.equals(that.scope) : that.scope == null;

    }

    @Override
    public int hashCode() {
        int result = tokenType != null ? tokenType.hashCode() : 0;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        result = 31 * result + (expiresIn != null ? expiresIn.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public BigDecimal getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }
}
