package com.turnkeyafrica.bankassurance.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class RiskResponce {

    @Expose
    @SerializedName("code")
    private BigDecimal code;
    @Expose
    @SerializedName("propertyId")
    private String propertyId;
    @Expose
    @SerializedName("itemDesc")
    private String itemDesc;
    @Expose
    @SerializedName("qty")
    private String qty;
    @Expose
    @SerializedName("value")
    private BigDecimal value;
    @Expose
    @SerializedName("wef")
    private String wef;
    @Expose
    @SerializedName("wet")
    private String wet;
    @Expose
    @SerializedName("polPolicyNo")
    private String polPolicyNo;
    @Expose
    @SerializedName("polRenEndosNo")
    private String polRenEndosNo;
    @Expose
    @SerializedName("polBatchNo")
    private BigDecimal polBatchNo;
    @Expose
    @SerializedName("basicPremium")
    private BigDecimal basicPremium;
    @Expose
    @SerializedName("nettPremium")
    private BigDecimal nettPremium;
    @Expose
    @SerializedName("prrRate")
    private String prrRate;
    @Expose
    @SerializedName("polEstMaxLoss")
    private BigDecimal polEstMaxLoss;
    @Expose
    @SerializedName("availFulcBal")
    private BigDecimal availFulcBal;
    @Expose
    @SerializedName("endosDiffAmt")
    private BigDecimal endosDiffAmt;
    @Expose
    @SerializedName("premWef")
    private String premWef;
    @Expose
    @SerializedName("location")
    private String location;
    @Expose
    @SerializedName("secSectCode")
    private String secSectCode;
    @Expose
    @SerializedName("sectShtDesc")
    private String sectShtDesc;
    @Expose
    @SerializedName("secSclCode")
    private BigDecimal secSclCode;
    @Expose
    @SerializedName("ncdStatus")
    private String ncdStatus;
    @Expose
    @SerializedName("certIssued")
    private String certIssued;
    @Expose
    @SerializedName("relatedIpuCode")
    private String relatedIpuCode;
    @Expose
    @SerializedName("prorata")
    private String prorata;
    @Expose
    @SerializedName("bp")
    private BigDecimal bp;
    @Expose
    @SerializedName("gp")
    private BigDecimal gp;
    @Expose
    @SerializedName("fp")
    private String fp;
    @Expose
    @SerializedName("fap")
    private BigDecimal fap;
    @Expose
    @SerializedName("prevIpuCode")
    private String prevIpuCode;
    @Expose
    @SerializedName("cummulativeReins")
    private String cummulativeReins;
    @Expose
    @SerializedName("reinsured")
    private BigDecimal reinsured;
    @Expose
    @SerializedName("shtDesc")
    private String shtDesc;
    @Expose
    @SerializedName("nclLevel")
    private String nclLevel;
    @Expose
    @SerializedName("id")
    private BigDecimal id;
    @Expose
    @SerializedName("bindCode")
    private BigDecimal bindCode;
    @Expose
    @SerializedName("commission")
    private BigDecimal commission;
    @Expose
    @SerializedName("commEndosDiffAmt")
    private BigDecimal commEndosDiffAmt;
    @Expose
    @SerializedName("facreAmount")
    private BigDecimal facreAmount;
    @Expose
    @SerializedName("clpCode")
    private String clpCode;
    @Expose
    @SerializedName("excessRate")
    private String excessRate;
    @Expose
    @SerializedName("excessType")
    private String excessType;
    @Expose
    @SerializedName("excessRateType")
    private String excessRateType;
    @Expose
    @SerializedName("excessMin")
    private BigDecimal excessMin;
    @Expose
    @SerializedName("excessMax")
    private BigDecimal excessMax;
    @Expose
    @SerializedName("endosRemove")
    private String endosRemove;
    @Expose
    @SerializedName("commRate")
    private BigDecimal commRate;
    @Expose
    @SerializedName("prevBatchNo")
    private BigDecimal prevBatchNo;
    @Expose
    @SerializedName("curCode")
    private BigDecimal curCode;
    @Expose
    @SerializedName("reinsureAmt")
    private BigDecimal reinsureAmt;
    @Expose
    @SerializedName("prpCode")
    private BigDecimal prpCode;
    @Expose
    @SerializedName("maxExposure")
    private BigDecimal maxExposure;
    @Expose
    @SerializedName("comRetentionRate")
    private BigDecimal comRetentionRate;
    @Expose
    @SerializedName("covtCode")
    private BigDecimal covtCode;
    @Expose
    @SerializedName("covtShtDesc")
    private String covtShtDesc;
    @Expose
    @SerializedName("siDiff")
    private BigDecimal siDiff;
    @Expose
    @SerializedName("comments")
    private String comments;
    @Expose
    @SerializedName("marCertNo")
    private String marCertNo;
    @Expose
    @SerializedName("totEndosPremDif")
    private BigDecimal totEndosPremDif;
    @Expose
    @SerializedName("totGp")
    private BigDecimal totGp;
    @Expose
    @SerializedName("totValue")
    private BigDecimal totValue;
    @Expose
    @SerializedName("coverDays")
    private BigDecimal coverDays;
    @Expose
    @SerializedName("prevPrem")
    private String prevPrem;
    @Expose
    @SerializedName("currentPrrdCode")
    private String currentPrrdCode;
    @Expose
    @SerializedName("totFap")
    private BigDecimal totFap;
    @Expose
    @SerializedName("extraPremium")
    private BigDecimal extraPremium;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("uwYr")
    private BigDecimal uwYr;
    @Expose
    @SerializedName("totFirstLoss")
    private String totFirstLoss;
    @Expose
    @SerializedName("accumulationLimit")
    private String accumulationLimit;
    @Expose
    @SerializedName("computeMaxExposure")
    private String computeMaxExposure;
    @Expose
    @SerializedName("transEffWet")
    private String transEffWet;
    @Expose
    @SerializedName("premTax")
    private BigDecimal premTax;
    @Expose
    @SerializedName("paidPremium")
    private String paidPremium;
    @Expose
    @SerializedName("paidTl")
    private String paidTl;
    @Expose
    @SerializedName("inceptionUwyr")
    private BigDecimal inceptionUwyr;
    @Expose
    @SerializedName("premComment")
    private String premComment;
    @Expose
    @SerializedName("coinTl")
    private BigDecimal coinTl;
    @Expose
    @SerializedName("dcPaidPremium")
    private String dcPaidPremium;
    @Expose
    @SerializedName("dcAp")
    private BigDecimal dcAp;
    @Expose
    @SerializedName("itemDetails")
    private String itemDetails;
    @Expose
    @SerializedName("freeLimit")
    private String freeLimit;
    @Expose
    @SerializedName("declared")
    private String declared;
    @Expose
    @SerializedName("prevFap")
    private String prevFap;
    @Expose
    @SerializedName("prevTotFap")
    private String prevTotFap;
     @Expose
    @SerializedName("minPremiumUsed")
    private String minPremiumUsed;
     @Expose
    @SerializedName("cancellationDays")
    private String cancellationDays;
     @Expose
    @SerializedName("allowedCommRate")
    private BigDecimal allowedCommRate;
     @Expose
    @SerializedName("allowedCommAmt")
    private BigDecimal allowedCommAmt;
     @Expose
    @SerializedName("reinsureDiffAmt")
    private String reinsureDiffAmt = null;
     @Expose
    @SerializedName("prevReinsureAmt")
    private String prevReinsureAmt = null;
     @Expose
    @SerializedName("phfund")
    private BigDecimal phfund;
     @Expose
    @SerializedName("coinPhfund")
    private BigDecimal coinPhfund;
     @Expose
    @SerializedName("enforceCvtMinPrem")
    private String enforceCvtMinPrem;
     @Expose
    @SerializedName("instalPrem")
    private BigDecimal instalPrem;
     @Expose
    @SerializedName("prevRiSi")
    private String prevRiSi;
     @Expose
    @SerializedName("policyStatus")
    private String policyStatus;
     @Expose
    @SerializedName("riskOthIntParties")
    private String riskOthIntParties;
     @Expose
    @SerializedName("prorataSectPrem")
    private BigDecimal prorataSectPrem;
     @Expose
    @SerializedName("nonprorataSectPrem")
    private BigDecimal nonprorataSectPrem;
     @Expose
    @SerializedName("prevProrataSectPrem")
    private String prevProrataSectPrem;
     @Expose
    @SerializedName("prevNonprorataSectPrem")
    private String prevNonprorataSectPrem;
     @Expose
    @SerializedName("totProrataSectPrem")
    private BigDecimal totProrataSectPrem;
     @Expose
    @SerializedName("totNonprorataSectPrem")
    private BigDecimal totNonprorataSectPrem;
     @Expose
    @SerializedName("prevTotProrataSPrem")
    private String prevTotProrataSPrem;
     @Expose
    @SerializedName("prevTotNonprorataSPrem")
    private String prevTotNonprorataSPrem;
     @Expose
    @SerializedName("subAgnCommRate")
    private BigDecimal subAgnCommRate;
     @Expose
    @SerializedName("subAgnCommAmt")
    private BigDecimal subAgnCommAmt;
     @Expose
    @SerializedName("ltaEndosComAmt")
    private BigDecimal ltaEndosComAmt;
     @Expose
    @SerializedName("ltaCommission")
    private BigDecimal ltaCommission;
     @Expose
    @SerializedName("ltaCommRate")
    private String ltaCommRate;
     @Expose
    @SerializedName("ltaCommDiscRate")
    private String ltaCommDiscRate;
     @Expose
    @SerializedName("ltaCommDiscAmt")
    private String ltaCommDiscAmt;
     @Expose
    @SerializedName("totFamilies")
    private String totFamilies;
     @Expose
    @SerializedName("totIndividuals")
    private String totIndividuals;
     @Expose
    @SerializedName("totFemales")
    private String totFemales;
     @Expose
    @SerializedName("totMales")
    private String totMales;
     @Expose
    @SerializedName("conveyanceType")
    private String conveyanceType;
     @Expose
    @SerializedName("stampDuty")
    private String stampDuty;
     @Expose
    @SerializedName("mktrComAmt")
    private BigDecimal mktrComAmt;
     @Expose
    @SerializedName("mktrComRate")
    private BigDecimal mktrComRate;
     @Expose
    @SerializedName("vatAmt")
    private BigDecimal vatAmt;
     @Expose
    @SerializedName("vatRate")
    private String vatRate;
     @Expose
    @SerializedName("commDiscType")
    private String commDiscType;
     @Expose
    @SerializedName("commDiscRate")
    private String commDiscRate;
     @Expose
    @SerializedName("commDiscAmt")
    private BigDecimal commDiscAmt;
     @Expose
    @SerializedName("prevStatus")
    private String prevStatus;
     @Expose
    @SerializedName("rescueMem")
    private String rescueMem;
     @Expose
    @SerializedName("coverSuspended")
    private String coverSuspended;
     @Expose
    @SerializedName("suspendWef")
    private String suspendWef;
     @Expose
    @SerializedName("suspendWet")
    private String suspendWet;
     @Expose
    @SerializedName("ncdCertNo")
    private String ncdCertNo;
     @Expose
    @SerializedName("pymtInstallPcts")
    private String pymtInstallPcts ;
     @Expose
    @SerializedName("suspReinstmtType")
    private String suspReinstmtType;
     @Expose
    @SerializedName("installPeriod")
    private String installPeriod ;
     @Expose
    @SerializedName("rescueCharge")
    private String rescueCharge;
     @Expose
    @SerializedName("previousInsurer")
    private String previousInsurer;
     @Expose
    @SerializedName("nextInstPrem")
    private BigDecimal nextInstPrem;
     @Expose
    @SerializedName("wtht")
    private BigDecimal wtht;
     @Expose
    @SerializedName("drcrNo")
    private String drcrNo ;
     @Expose
    @SerializedName("postRetroWet")
    private String postRetroWet;
     @Expose
    @SerializedName("postRetroCover")
    private String postRetroCover ;
     @Expose
    @SerializedName("coPhfund")
    private BigDecimal coPhfund;
     @Expose
    @SerializedName("coverNoteRemarks")
    private String coverNoteRemarks;
     @Expose
    @SerializedName("coverNoteWet")
    private String coverNoteWet;
     @Expose
    @SerializedName("coverNoteWef")
    private String coverNoteWef;
     @Expose
    @SerializedName("coverNoteDate")
    private String coverNoteDate;
     @Expose
    @SerializedName("coverNoteBy")
    private String coverNoteBy;
     @Expose
    @SerializedName("coverNoteNo")
    private String coverNoteNo;
     @Expose
    @SerializedName("healthTax")
    private String healthTax;
     @Expose
    @SerializedName("motorTax")
    private String motorTax;
     @Expose
    @SerializedName("certchg")
    private BigDecimal certchg;
     @Expose
    @SerializedName("clientVatAmt")
    private BigDecimal clientVatAmt;
     @Expose
    @SerializedName("motorLevy")
    private BigDecimal motorLevy;
     @Expose
    @SerializedName("overriderideRetType")
    private String overriderideRetType;
     @Expose
    @SerializedName("cashbackLevel")
    private String cashbackLevel;
     @Expose
    @SerializedName("vehicleModel")
    private String vehicleModel;
     @Expose
    @SerializedName("vehicleMake")
    private String vehicleMake;
     @Expose
    @SerializedName("vehicleModelCode")
    private String vehicleModelCode;
     @Expose
    @SerializedName("vehicleMakeCode")
    private String vehicleMakeCode;
     @Expose
    @SerializedName("locTown")
    private String locTown;
     @Expose
    @SerializedName("propAddress")
    private String propAddress;
    @Expose
    @SerializedName("valuationDone")
    private String valuationDone;


    public BigDecimal getCode() {
        return code;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getQty() {
        return qty;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getWef() {
        return wef;
    }

    public String getWet() {
        return wet;
    }

    public String getValuationDone() {
        return valuationDone;
    }

    public String getPolPolicyNo() {
        return polPolicyNo;
    }

    public String getPolRenEndosNo() {
        return polRenEndosNo;
    }

    public BigDecimal getPolBatchNo() {
        return polBatchNo;
    }

    public BigDecimal getBasicPremium() {
        return basicPremium;
    }

    public BigDecimal getNettPremium() {
        return nettPremium;
    }

    public String getPrrRate() {
        return prrRate;
    }

    public BigDecimal getPolEstMaxLoss() {
        return polEstMaxLoss;
    }

    public BigDecimal getAvailFulcBal() {
        return availFulcBal;
    }

    public BigDecimal getEndosDiffAmt() {
        return endosDiffAmt;
    }

    public String getPremWef() {
        return premWef;
    }

    public String getLocation() {
        return location;
    }

    public String getSecSectCode() {
        return secSectCode;
    }

    public String getSectShtDesc() {
        return sectShtDesc;
    }

    public BigDecimal getSecSclCode() {
        return secSclCode;
    }

    public String getNcdStatus() {
        return ncdStatus;
    }

    public String getCertIssued() {
        return certIssued;
    }

    public String getRelatedIpuCode() {
        return relatedIpuCode;
    }

    public String getProrata() {
        return prorata;
    }

    public BigDecimal getBp() {
        return bp;
    }

    public BigDecimal getGp() {
        return gp;
    }

    public String getFp() {
        return fp;
    }

    public BigDecimal getFap() {
        return fap;
    }

    public String getPrevIpuCode() {
        return prevIpuCode;
    }

    public String getCummulativeReins() {
        return cummulativeReins;
    }

    public BigDecimal getReinsured() {
        return reinsured;
    }

    public String getShtDesc() {
        return shtDesc;
    }

    public String getNclLevel() {
        return nclLevel;
    }

    public BigDecimal getId() {
        return id;
    }

    public BigDecimal getBindCode() {
        return bindCode;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public BigDecimal getCommEndosDiffAmt() {
        return commEndosDiffAmt;
    }

    public BigDecimal getFacreAmount() {
        return facreAmount;
    }

    public String getClpCode() {
        return clpCode;
    }

    public String getExcessRate() {
        return excessRate;
    }

    public String getExcessType() {
        return excessType;
    }

    public String getExcessRateType() {
        return excessRateType;
    }

    public BigDecimal getExcessMin() {
        return excessMin;
    }

    public BigDecimal getExcessMax() {
        return excessMax;
    }

    public String getEndosRemove() {
        return endosRemove;
    }

    public BigDecimal getCommRate() {
        return commRate;
    }

    public BigDecimal getPrevBatchNo() {
        return prevBatchNo;
    }

    public BigDecimal getCurCode() {
        return curCode;
    }

    public BigDecimal getReinsureAmt() {
        return reinsureAmt;
    }

    public BigDecimal getPrpCode() {
        return prpCode;
    }

    public BigDecimal getMaxExposure() {
        return maxExposure;
    }

    public BigDecimal getComRetentionRate() {
        return comRetentionRate;
    }

    public BigDecimal getCovtCode() {
        return covtCode;
    }

    public String getCovtShtDesc() {
        return covtShtDesc;
    }

    public BigDecimal getSiDiff() {
        return siDiff;
    }

    public String getComments() {
        return comments;
    }

    public String getMarCertNo() {
        return marCertNo;
    }

    public BigDecimal getTotEndosPremDif() {
        return totEndosPremDif;
    }

    public BigDecimal getTotGp() {
        return totGp;
    }

    public BigDecimal getTotValue() {
        return totValue;
    }

    public BigDecimal getCoverDays() {
        return coverDays;
    }

    public String getPrevPrem() {
        return prevPrem;
    }

    public String getCurrentPrrdCode() {
        return currentPrrdCode;
    }

    public BigDecimal getTotFap() {
        return totFap;
    }

    public BigDecimal getExtraPremium() {
        return extraPremium;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getUwYr() {
        return uwYr;
    }

    public String getTotFirstLoss() {
        return totFirstLoss;
    }

    public String getAccumulationLimit() {
        return accumulationLimit;
    }

    public String getComputeMaxExposure() {
        return computeMaxExposure;
    }

    public String getTransEffWet() {
        return transEffWet;
    }

    public BigDecimal getPremTax() {
        return premTax;
    }

    public String getPaidPremium() {
        return paidPremium;
    }

    public String getPaidTl() {
        return paidTl;
    }

    public BigDecimal getInceptionUwyr() {
        return inceptionUwyr;
    }

    public String getPremComment() {
        return premComment;
    }

    public BigDecimal getCoinTl() {
        return coinTl;
    }

    public String getDcPaidPremium() {
        return dcPaidPremium;
    }

    public BigDecimal getDcAp() {
        return dcAp;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public String getFreeLimit() {
        return freeLimit;
    }

    public String getDeclared() {
        return declared;
    }

    public String getPrevFap() {
        return prevFap;
    }

    public String getPrevTotFap() {
        return prevTotFap;
    }

    public String getMinPremiumUsed() {
        return minPremiumUsed;
    }

    public String getCancellationDays() {
        return cancellationDays;
    }

    public BigDecimal getAllowedCommRate() {
        return allowedCommRate;
    }

    public BigDecimal getAllowedCommAmt() {
        return allowedCommAmt;
    }

    public String getReinsureDiffAmt() {
        return reinsureDiffAmt;
    }

    public String getPrevReinsureAmt() {
        return prevReinsureAmt;
    }

    public BigDecimal getPhfund() {
        return phfund;
    }

    public BigDecimal getCoinPhfund() {
        return coinPhfund;
    }

    public String getEnforceCvtMinPrem() {
        return enforceCvtMinPrem;
    }

    public BigDecimal getInstalPrem() {
        return instalPrem;
    }

    public String getPrevRiSi() {
        return prevRiSi;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public String getRiskOthIntParties() {
        return riskOthIntParties;
    }

    public BigDecimal getProrataSectPrem() {
        return prorataSectPrem;
    }

    public BigDecimal getNonprorataSectPrem() {
        return nonprorataSectPrem;
    }

    public String getPrevProrataSectPrem() {
        return prevProrataSectPrem;
    }

    public String getPrevNonprorataSectPrem() {
        return prevNonprorataSectPrem;
    }

    public BigDecimal getTotProrataSectPrem() {
        return totProrataSectPrem;
    }

    public BigDecimal getTotNonprorataSectPrem() {
        return totNonprorataSectPrem;
    }

    public String getPrevTotProrataSPrem() {
        return prevTotProrataSPrem;
    }

    public String getPrevTotNonprorataSPrem() {
        return prevTotNonprorataSPrem;
    }

    public BigDecimal getSubAgnCommRate() {
        return subAgnCommRate;
    }

    public BigDecimal getSubAgnCommAmt() {
        return subAgnCommAmt;
    }

    public BigDecimal getLtaEndosComAmt() {
        return ltaEndosComAmt;
    }

    public BigDecimal getLtaCommission() {
        return ltaCommission;
    }

    public String getLtaCommRate() {
        return ltaCommRate;
    }

    public String getLtaCommDiscRate() {
        return ltaCommDiscRate;
    }

    public String getLtaCommDiscAmt() {
        return ltaCommDiscAmt;
    }

    public String getTotFamilies() {
        return totFamilies;
    }

    public String getTotIndividuals() {
        return totIndividuals;
    }

    public String getTotFemales() {
        return totFemales;
    }

    public String getTotMales() {
        return totMales;
    }

    public String getConveyanceType() {
        return conveyanceType;
    }

    public String getStampDuty() {
        return stampDuty;
    }

    public BigDecimal getMktrComAmt() {
        return mktrComAmt;
    }

    public BigDecimal getMktrComRate() {
        return mktrComRate;
    }

    public BigDecimal getVatAmt() {
        return vatAmt;
    }

    public String getVatRate() {
        return vatRate;
    }

    public String getCommDiscType() {
        return commDiscType;
    }

    public String getCommDiscRate() {
        return commDiscRate;
    }

    public BigDecimal getCommDiscAmt() {
        return commDiscAmt;
    }

    public String getPrevStatus() {
        return prevStatus;
    }

    public String getRescueMem() {
        return rescueMem;
    }

    public String getCoverSuspended() {
        return coverSuspended;
    }

    public String getSuspendWef() {
        return suspendWef;
    }

    public String getSuspendWet() {
        return suspendWet;
    }

    public String getNcdCertNo() {
        return ncdCertNo;
    }

    public String getPymtInstallPcts() {
        return pymtInstallPcts;
    }

    public String getSuspReinstmtType() {
        return suspReinstmtType;
    }

    public String getInstallPeriod() {
        return installPeriod;
    }

    public String getRescueCharge() {
        return rescueCharge;
    }

    public String getPreviousInsurer() {
        return previousInsurer;
    }

    public BigDecimal getNextInstPrem() {
        return nextInstPrem;
    }

    public BigDecimal getWtht() {
        return wtht;
    }

    public String getDrcrNo() {
        return drcrNo;
    }

    public String getPostRetroWet() {
        return postRetroWet;
    }

    public String getPostRetroCover() {
        return postRetroCover;
    }

    public BigDecimal getCoPhfund() {
        return coPhfund;
    }

    public String getCoverNoteRemarks() {
        return coverNoteRemarks;
    }

    public String getCoverNoteWet() {
        return coverNoteWet;
    }

    public String getCoverNoteWef() {
        return coverNoteWef;
    }

    public String getCoverNoteDate() {
        return coverNoteDate;
    }

    public String getCoverNoteBy() {
        return coverNoteBy;
    }

    public String getCoverNoteNo() {
        return coverNoteNo;
    }

    public String getHealthTax() {
        return healthTax;
    }

    public String getMotorTax() {
        return motorTax;
    }

    public BigDecimal getCertchg() {
        return certchg;
    }

    public BigDecimal getClientVatAmt() {
        return clientVatAmt;
    }

    public BigDecimal getMotorLevy() {
        return motorLevy;
    }

    public String getOverriderideRetType() {
        return overriderideRetType;
    }

    public String getCashbackLevel() {
        return cashbackLevel;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    public String getVehicleMakeCode() {
        return vehicleMakeCode;
    }

    public String getLocTown() {
        return locTown;
    }

    public String getPropAddress() {
        return propAddress;
    }
}
