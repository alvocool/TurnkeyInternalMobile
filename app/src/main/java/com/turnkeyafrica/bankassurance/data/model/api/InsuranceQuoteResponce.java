package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;

public class InsuranceQuoteResponce {

    @Expose
    @SerializedName("quotation")
    InsuranceQuotation insuranceQuotation;
    @Expose
    @SerializedName("sections")
    ArrayList<InsuranceQuotationSections>  sections = new ArrayList <> ();

    public static class InsuranceQuotation {

        @Expose
        @SerializedName("code")
        private BigDecimal code;
        @Expose
        @SerializedName("clntCode")
        private BigDecimal clntCode;
        @Expose
        @SerializedName("gpwDate")
        private String gpwDate;
        @Expose
        @SerializedName("premiumAmount")
        private BigDecimal premiumAmount;
        @Expose
        @SerializedName("quotCode")
        private BigDecimal quotCode;
        @Expose
        @SerializedName("quotNumber")
        private String quotNumber;
        @Expose
        @SerializedName("bindCode")
        private BigDecimal bindCode;
        @Expose
        @SerializedName("agnName")
        private String agnName;
        @Expose
        @SerializedName("bindName")
        private String bindName;
        @Expose
        @SerializedName("consCode")
        private String consCode;
        @Expose
        @SerializedName("quotSaved")
        private String quotSaved;
        @Expose
        @SerializedName("firstInstallment")
        private BigDecimal firstInstallment;
        @Expose
        @SerializedName("coverFrom")
        private String coverFrom;
        @Expose
        @SerializedName("coverTo")
        private String coverTo;
        @Expose
        @SerializedName("quotExpiryDate")
        private String quotExpiryDate;
        @Expose
        @SerializedName("coverType")
        private String coverType;
        @Expose
        @SerializedName("agentCode")
        private BigDecimal agentCode;
        @Expose
        @SerializedName("risks")
        ArrayList<InsuranceQuotationRisks> risks = new ArrayList<InsuranceQuotationRisks>();

        public static class InsuranceQuotationRisks {

            @Expose
            @SerializedName("code")
            private BigDecimal code;
            @Expose
            @SerializedName("propertyId")
            private String propertyId;
            @Expose
            @SerializedName("desc")
            private String desc;
            @Expose
            @SerializedName("coverType")
            private String coverType;

            public String getCoverType() {
                return coverType;
            }

            public BigDecimal getCode() {
                return code;
            }

            public String getPropertyId() {
                return propertyId;
            }

            public String getDesc() {
                return desc;
            }
        }

        public BigDecimal getCode() {
            return code;
        }

        public BigDecimal getClntCode() {
            return clntCode;
        }

        public String getGpwDate() {
            return gpwDate;
        }

        public BigDecimal getPremiumAmount() {
            return premiumAmount;
        }

        public BigDecimal getQuotCode() {
            return quotCode;
        }

        public String getQuotNumber() {
            return quotNumber;
        }

        public BigDecimal getBindCode() {
            return bindCode;
        }

        public String getAgnName() {
            return agnName;
        }

        public String getBindName() {
            return bindName;
        }

        public String getConsCode() {
            return consCode;
        }

        public String getQuotSaved() {
            return quotSaved;
        }

        public ArrayList<InsuranceQuotationRisks> getRisks() {
            return risks;
        }

        public BigDecimal getFirstInstallment() {
            return firstInstallment;
        }

        public String getCoverFrom() {
            return coverFrom;
        }

        public String getCoverTo() {
            return coverTo;
        }

        public String getQuotExpiryDate() {
            return quotExpiryDate;
        }

        public String getCoverType() {
            return coverType;
        }

        public BigDecimal getAgentCode() {
            return agentCode;
        }
    }

    public static class InsuranceQuotationSections {
        @Expose
        @SerializedName("code")
        private BigDecimal code;
        @Expose
        @SerializedName("sectCode")
        private BigDecimal sectCode;
        @Expose
        @SerializedName("sectShtDesc")
        private String sectShtDesc;
        @Expose
        @SerializedName("limitAmount")
        private BigDecimal limitAmount;
        @Expose
        @SerializedName("premiumRate")
        private BigDecimal premiumRate;
        @Expose
        @SerializedName("premiumAmount")
        private BigDecimal premiumAmount;
        @Expose
        @SerializedName("sectionType")
        private String sectionType;
        @Expose
        @SerializedName("minimumPremium")
        private BigDecimal minimumPremium;

        public BigDecimal getCode() {
            return code;
        }

        public BigDecimal getSectCode() {
            return sectCode;
        }

        public String getSectShtDesc() {
            return sectShtDesc;
        }

        public BigDecimal getLimitAmount() {
            return limitAmount;
        }

        public BigDecimal getPremiumRate() {
            return premiumRate;
        }

        public BigDecimal getPremiumAmount() {
            return premiumAmount;
        }

        public String getSectionType() {
            return sectionType;
        }

        public BigDecimal getMinimumPremium() {
            return minimumPremium;
        }
    }

    public InsuranceQuotation getInsuranceQuotation() {
        return insuranceQuotation;
    }

    public ArrayList<InsuranceQuotationSections> getSections() {
        return sections;
    }
}


