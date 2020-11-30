package com.turnkeyafrica.bankassurance.ui.vehicleinsurance;

import android.app.AlertDialog;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.ExtraBenefitsResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public class VehicleInsuranceViewModel extends BaseViewModel<VehicleInsuranceNavigator> {

    public final ObservableField<String> vehicleMake = new ObservableField<>();
    public final ObservableField<String> vehicleRegistration = new ObservableField<>();
    public final ObservableField<String> vehicleYearOfManufacture = new ObservableField<>();
    public final ObservableField<String> vehicleValue = new ObservableField<>();
    public final ObservableField<String> insuranceType = new ObservableField<>();
    public final ObservableField<String> insuranceStartDate = new ObservableField<>();
    public final ObservableField<String> insuranceEndDate = new ObservableField<>();

    public VehicleInsuranceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        if(CommonUtils.StringIsEmpty(dataManager.getComparisonRequest())) {
            setInsuranceType(701);
        }
      }

    public void getExtraBenefits(BigDecimal code) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getMotorExtraBenefits(code)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(extraBenefitsResponses -> {
                    if (extraBenefitsResponses != null) {
                        setAdapter(extraBenefitsResponses);
                    }
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getVehicleMakes() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getVehicleMakes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(vehicleMakesResponces -> {
                      getNavigator().showModels(vehicleMakesResponces);
                      getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getCoverTypes(BigDecimal sclCode) {
        AlertDialog alertDialog = getNavigator().openLoading();

        getCompositeDisposable().add(getDataManager()
                .getCoverTypes(sclCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(coverTypesResponces -> {
                    getNavigator().showInsuranceTypes(coverTypesResponces);
                    getNavigator().closeLoading(alertDialog);
                    }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void setAdapter(List<ExtraBenefitsResponse> extraBenefitsResponses) {

        getNavigator().setAdapter(extraBenefitsResponses);
    }

    public void SubmitVehicleDetails() {

        getNavigator().submitVehicleDetails();


    }

    private void setInsuranceType(int type) {

        ComparisonRequest comparisonRequest = new ComparisonRequest();

        ComparisonRequest.ComparisonRequestQuote comparisonRequestQuote = new ComparisonRequest.ComparisonRequestQuote();

        comparisonRequestQuote.setClntType("I");

        comparisonRequestQuote.setProCode(BigDecimal.valueOf(1671));

        comparisonRequest.setQuote(comparisonRequestQuote);

        ComparisonRequest.ComparisonRequestRisk comparisonRequestRisk = new ComparisonRequest.ComparisonRequestRisk();

        comparisonRequestRisk.setSclCode(BigDecimal.valueOf(type));

        comparisonRequestRisk.setProCode(BigDecimal.valueOf(1671));

        comparisonRequest.setRisk(comparisonRequestRisk);

        getDataManager().setComparisonRequest(comparisonRequest);

    }

    public void ShowInsuranceTypes() {
        getNavigator().getSclCode();
    }

    public void ShowVehicleModels() {
          getNavigator().getVehicleMakes();
    }

    public void showDate(){

        getNavigator().showDatePicker();
    }

    public void setValues(ComparisonRequest comparisonRequest){


        if(CommonUtils.ObjectIsNotNull(comparisonRequest.getRisk())){
            if(!CommonUtils.StringIsEmpty(comparisonRequest.getRisk().getPropertyId())) {
                vehicleRegistration.set(comparisonRequest.getRisk().getPropertyId());
            }
            if(!CommonUtils.StringIsEmpty(comparisonRequest.getRisk().getPropertyDesc())) {
                vehicleMake.set(comparisonRequest.getRisk().getPropertyDesc());
            }
            if(!CommonUtils.StringIsEmpty(comparisonRequest.getRisk().getYearOfManufacture())) {
                vehicleYearOfManufacture.set(comparisonRequest.getRisk().getYearOfManufacture());
            }
        }
        if(CommonUtils.ObjectIsNotNull(comparisonRequest.getRisk().getCvtCode())){
            //changes in release two
            insuranceType.set("Comprehensive");
        }
        if(CommonUtils.ObjectIsNotNull(comparisonRequest.getSections()) && !comparisonRequest.getSections().isEmpty()){
            if(CommonUtils.ObjectIsNotNull(comparisonRequest.getSections().get(0).getLimitAmount()))
            vehicleValue.set(comparisonRequest.getSections().get(0).getLimitAmount().toString());
        }
        if(CommonUtils.ObjectIsNotNull(comparisonRequest.getQuote())){
            if(!CommonUtils.StringIsEmpty(comparisonRequest.getQuote().getWefDate())) {
                insuranceStartDate.set(comparisonRequest.getQuote().getWefDate());

                try {
                    insuranceEndDate.set(CommonUtils.generateEndDate(insuranceStartDate.get()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}