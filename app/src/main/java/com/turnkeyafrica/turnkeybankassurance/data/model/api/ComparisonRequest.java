package com.turnkeyafrica.turnkeybankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.List;

public class ComparisonRequest {

    @Expose
    @SerializedName("risk")
    private ComparisonRequestRisk risk;
    @Expose
    @SerializedName("sections")
    private List<ComparisonRequestSection> sections;
    @Expose
    @SerializedName("quote")
    private ComparisonRequestQuote quote;


    public ComparisonRequestRisk getRisk() {
        return risk;
    }

    public void setRisk(ComparisonRequestRisk risk) {
        this.risk = risk;
    }

    public List<ComparisonRequestSection> getSections() {
        return sections;
    }

    public void setSections(List<ComparisonRequestSection> sections) {
        this.sections = sections;
    }

    public ComparisonRequestQuote getQuote() {
        return quote;
    }

    public void setQuote(ComparisonRequestQuote quote) {
        this.quote = quote;
    }

    public static class ComparisonRequestQuote {
        @Expose
        @SerializedName("agnCode")
        private BigDecimal agnCode;
        @Expose
        @SerializedName("agnShtDesc")
        private String agnShtDesc;
        @Expose
        @SerializedName("curCode")
        private BigDecimal curCode;
        @Expose
        @SerializedName("curSymbol")
        private String curSymbol;
        @Expose
        @SerializedName("clntCode")
        private Long clntCode;
        @Expose
        @SerializedName("proCode")
        private BigDecimal proCode;
        @Expose
        @SerializedName("source")
        private String source;
        @Expose
        @SerializedName("coinLeaderCombined")
        private String coinLeaderCombined;
        @Expose
        @SerializedName("freqOfPayment")
        private String freqOfPayment;
        @Expose
        @SerializedName("consCode")
        private String consCode;
        @Expose
        @SerializedName("clntType")
        private String clntType;
        @Expose
        @SerializedName("wefDate")
        private String wefDate;

        public String getWefDate() {
            return wefDate;
        }

        public void setWefDate(String wefDate) {
            this.wefDate = wefDate;
        }

        public BigDecimal getAgnCode() {
            return agnCode;
        }

        public void setAgnCode(BigDecimal agnCode) {
            this.agnCode = agnCode;
        }

        public void setAgnShtDesc(String agnShtDesc) {
            this.agnShtDesc = agnShtDesc;
        }

        public void setCurCode(BigDecimal curCode) {
            this.curCode = curCode;
        }

        public void setCurSymbol(String curSymbol) {
            this.curSymbol = curSymbol;
        }

        public void setClntCode(Long clntCode) {
            this.clntCode = clntCode;
        }

        public void setProCode(BigDecimal proCode) {
            this.proCode = proCode;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setCoinLeaderCombined(String coinLeaderCombined) {
            this.coinLeaderCombined = coinLeaderCombined;
        }

        public String getConsCode() {
            return consCode;
        }

        public void setConsCode(String consCode) {
            this.consCode = consCode;
        }

        public void setFreqOfPayment(String freqOfPayment) {
            this.freqOfPayment = freqOfPayment;
        }

        public void setClntType(String clntType) {
            this.clntType = clntType;
        }

        public String getAgnShtDesc() {
            return agnShtDesc;
        }

        public BigDecimal getCurCode() {
            return curCode;
        }

        public String getCurSymbol() {
            return curSymbol;
        }

        public Long getClntCode() {
            return clntCode;
        }

        public BigDecimal getProCode() {
            return proCode;
        }

        public String getSource() {
            return source;
        }

        public String getCoinLeaderCombined() {
            return coinLeaderCombined;
        }

        public String getFreqOfPayment() {
            return freqOfPayment;
        }

        public String getClntType() {
            return clntType;
        }
    }

    public static class ComparisonRequestRisk {
        @Expose
        @SerializedName("propertyId")
        private String propertyId;
        @Expose
        @SerializedName("propertyDesc")
        private String propertyDesc;
        @Expose
        @SerializedName("sclCode")
        private BigDecimal sclCode;
        @Expose
        @SerializedName("yrOfManufacture")
        private String yearOfManufacture;
        @Expose
        @SerializedName("cvtCode")
        private BigDecimal cvtCode;
        @Expose
        @SerializedName("proCode")
        private BigDecimal proCode;

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public void setPropertyDesc(String propertyDesc) {
            this.propertyDesc = propertyDesc;
        }

        public void setSclCode(BigDecimal sclCode) {
            this.sclCode = sclCode;
        }

        public void setCvtCode(BigDecimal cvtCode) {
            this.cvtCode = cvtCode;
        }

        public void setProCode(BigDecimal proCode) {
            this.proCode = proCode;
        }

        public String getPropertyDesc() {
            return propertyDesc;
        }

        public BigDecimal getSclCode() {
            return sclCode;
        }

        public BigDecimal getCvtCode() {
            return cvtCode;
        }

        public BigDecimal getProCode() {
            return proCode;
        }

        public String getYearOfManufacture() {
            return yearOfManufacture;
        }

        public void setYearOfManufacture(String yearOfManufacture) {
            this.yearOfManufacture = yearOfManufacture;
        }
    }
    
    public static class ComparisonRequestSection{
        @Expose
        @SerializedName("sectCode")
        private BigDecimal sectCode;

        public BigDecimal getSectCode() {
            return sectCode;
        }

        public void setSectCode(BigDecimal sectCode) {
            this.sectCode = sectCode;
        }

        public void setLimitAmount(BigDecimal limitAmount) {
            this.limitAmount = limitAmount;
        }

        public BigDecimal getLimitAmount() {
            return limitAmount;
        }

        @Expose
        @SerializedName("limitAmount")
        private BigDecimal limitAmount;
    }
}