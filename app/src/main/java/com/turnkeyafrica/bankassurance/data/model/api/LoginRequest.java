package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class LoginRequest {

    private LoginRequest() {
    }

    public static class ServerLoginRequest {

        @Expose
        @SerializedName("username")
        private String username;

        @Expose
        @SerializedName("password")
        private String password;

        @Expose
        @SerializedName("grant_type")
        private String grant_type;

        public ServerLoginRequest(String username, String password, String grant_type) {
            this.username = username;
            this.password = password;
            this.grant_type = grant_type;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerLoginRequest that = (ServerLoginRequest) object;

            if (username != null ? !username.equals(that.username) : that.username != null) {
                return false;
            }
            if (grant_type != null ? !grant_type.equals(that.grant_type) : that.grant_type != null) {
                return false;
            }
            return password != null ? password.equals(that.password) : that.password == null;
        }

        @Override
        public int hashCode() {
            int result = username != null ? username.hashCode() : 0;
            result = 31 * result + (password != null ? password.hashCode() : 0);
            result = 31 * result + (grant_type != null ? grant_type.hashCode() : 0);
            return result;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getGrant_type() { return grant_type; }

    }
}
