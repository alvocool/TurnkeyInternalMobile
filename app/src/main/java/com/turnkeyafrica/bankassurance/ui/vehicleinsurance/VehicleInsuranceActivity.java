package com.turnkeyafrica.bankassurance.ui.vehicleinsurance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.microsoft.appcenter.Flags;
import com.microsoft.appcenter.analytics.Analytics;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.CoverTypesResponce;
import com.turnkeyafrica.bankassurance.data.model.api.ExtraBenefitsResponse;
import com.turnkeyafrica.bankassurance.data.model.api.VehicleMakesResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityVehicleInsuranceBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.InsuranceQuotesActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.extensionsdialog.ExtensionsDialog;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicledatedialog.VehiclesDateDialog;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialog;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.VehicleInsuranceTypeDialog;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import org.apache.commons.lang3.StringUtils;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class VehicleInsuranceActivity extends BaseActivity<ActivityVehicleInsuranceBinding, VehicleInsuranceViewModel> implements VehicleInsuranceNavigator , HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private VehicleInsuranceViewModel mVehicleInsuranceViewModel;
    private ActivityVehicleInsuranceBinding mActivityVehicleInsuranceBinding;
    private EditText vehicleValueEditText;
    private EditText vehicleRegEditText;
    private EditText vehicleTypeEditText;
    private EditText vehicleYearManufactureEditText;
    private EditText vehicleStartDate;
    private EditText vehicleEndDate;
    private EditText InsuranceType;
    private BigDecimal cvtCode;

    private List<ComparisonRequest.ComparisonRequestSection> comparisonRequestSections = new ArrayList<>();

    public static Intent newIntent(Context context) {
        return new Intent(context, VehicleInsuranceActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_insurance;
    }

    @Override
    public VehicleInsuranceViewModel getViewModel() {
        mVehicleInsuranceViewModel = new ViewModelProvider(getViewModelStore(),factory).get(VehicleInsuranceViewModel.class);
        return mVehicleInsuranceViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVehicleInsuranceViewModel.setNavigator(this);
        mActivityVehicleInsuranceBinding = getViewDataBinding();
        Toolbar toolbar = mActivityVehicleInsuranceBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        vehicleValueEditText = mActivityVehicleInsuranceBinding.vehicleValue;

        vehicleValueEditText = disableCopyAndPaste(vehicleValueEditText);

        vehicleRegEditText = mActivityVehicleInsuranceBinding.vehicleReg;

        vehicleRegEditText = disableCopyAndPaste(vehicleRegEditText);

        vehicleTypeEditText = mActivityVehicleInsuranceBinding.vehicleType;

        vehicleTypeEditText = disableCopyAndPaste(vehicleTypeEditText);

        vehicleYearManufactureEditText = mActivityVehicleInsuranceBinding.vehicleYearManufacture;

        vehicleYearManufactureEditText = disableCopyAndPaste(vehicleYearManufactureEditText);

        vehicleStartDate = mActivityVehicleInsuranceBinding.vehicleDateStart;

        vehicleStartDate = disableCopyAndPaste(vehicleStartDate);

        vehicleEndDate = mActivityVehicleInsuranceBinding.vehicleDateEnd;

        vehicleEndDate = disableCopyAndPaste(vehicleEndDate);

        InsuranceType = mActivityVehicleInsuranceBinding.vehicleInsuranceType;

        InsuranceType = disableCopyAndPaste(InsuranceType);

        vehicleValueEditText.addTextChangedListener(new NumberTextWatcher(vehicleValueEditText, "#,###"));

        String stringComparisonRequest =  mVehicleInsuranceViewModel.getDataManager().getComparisonRequest();

        if (!CommonUtils.StringIsEmpty(stringComparisonRequest)) {
            Gson gson = new Gson();

            ComparisonRequest comparisonRequest = gson.fromJson(stringComparisonRequest, ComparisonRequest.class);

            setValues(comparisonRequest);
        }

    }

    @Override
    public void submitVehicleDetails() {

        TextInputLayout vehicleValueInputLayout = mActivityVehicleInsuranceBinding.vehicleValueInputLayout;

        vehicleValueInputLayout.setError(null);

        Gson  gson = new Gson();

        ComparisonRequest comparisonRequest = gson.fromJson(mVehicleInsuranceViewModel.getDataManager().getComparisonRequest(),ComparisonRequest.class);

        comparisonRequest.setQuote(CommonUtils.setDefaultValue(comparisonRequest.getQuote()));

        ComparisonRequest.ComparisonRequestRisk comparisonRequestRisk =  comparisonRequest.getRisk();

        ComparisonRequest.ComparisonRequestQuote comparisonRequestQuote = comparisonRequest.getQuote();

        comparisonRequestRisk.setPropertyId(vehicleRegEditText.getText().toString());

        comparisonRequestRisk.setPropertyDesc(vehicleTypeEditText.getText().toString());

        comparisonRequestRisk.setYearOfManufacture(vehicleYearManufactureEditText.getText().toString());

        if(CommonUtils.ObjectIsNotNull(cvtCode)) {
            comparisonRequestRisk.setCvtCode(cvtCode);
        }else {
            comparisonRequestRisk.setCvtCode(comparisonRequestRisk.getCvtCode());
        }

        comparisonRequestQuote.setWefDate(Objects.requireNonNull(mActivityVehicleInsuranceBinding.vehicleDateStart.getText()).toString());

        ComparisonRequest.ComparisonRequestSection vehicleValue = new ComparisonRequest.ComparisonRequestSection();

        vehicleValue.setSectCode(BigDecimal.valueOf(2054));

        List<ComparisonRequest.ComparisonRequestSection> sections = new ArrayList<>();

        Map<String, String> amountInput= new HashMap<>();

        if(!CommonUtils.StringIsEmpty(vehicleValueEditText.getText().toString())&& vehicleValueEditText.getText().toString().length() > 3){

            amountInput.put("Vehicle Amount Before Convertion", vehicleValueEditText.getText().toString());

            String val = formatToSend(vehicleValueEditText.getText().toString());

            amountInput.put("Vehicle Amount After Convertion", val);

            Analytics.trackEvent("Vehicle Details Submitted: ", amountInput, Flags.PERSISTENCE_CRITICAL);

            vehicleValue.setLimitAmount(new BigDecimal(val));
        }

        sections.add(vehicleValue);

        //Analytics
        Map<String, String> properties = new HashMap<>();
        properties.put("Vehicle Type", vehicleTypeEditText.getText().toString());
        properties.put("Vehicle Value", vehicleValueEditText.getText().toString());
        Analytics.trackEvent("Vehicle Details Submitted: ", properties, Flags.PERSISTENCE_CRITICAL);


        if(!this.comparisonRequestSections.isEmpty()){
            this.comparisonRequestSections.add(vehicleValue);
            sections = this.comparisonRequestSections;
            comparisonRequest.setSections(sections);

        }else {

            comparisonRequest.setSections(sections);
        }

        comparisonRequest.setRisk(comparisonRequestRisk);
        if(validateComparisonRequest(comparisonRequest)) {
            if(!CommonUtils.isValidLimit(comparisonRequest.getSections().get(0).getLimitAmount(), new BigDecimal(100))) {
                mVehicleInsuranceViewModel.getDataManager().setComparisonRequest(comparisonRequest);
                Intent intent = InsuranceQuotesActivity.newIntent(VehicleInsuranceActivity.this);
                startActivity(intent);
            }else {
                vehicleValueInputLayout.setError(getResources().getString(R.string.warning_invalid_vehicleValue));
            }
        }
    }

    private String formatToSend(String amountFormatted) {

        if(vehicleValueEditText.getText().toString().endsWith(".00")) {
            amountFormatted = amountFormatted.substring(0, amountFormatted.length() - 3);
        }else if(vehicleValueEditText.getText().toString().endsWith(".0")){

            amountFormatted = amountFormatted.substring(0, amountFormatted.length() - 2);
        }else if(vehicleValueEditText.getText().toString().endsWith(".")){
            amountFormatted = amountFormatted.substring(0, amountFormatted.length() - 1);
        }else {
            amountFormatted = StringUtils.substringBefore(amountFormatted, ".");
        }

        amountFormatted = amountFormatted.replaceAll(",","");

        return amountFormatted;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = DashboardActivity.newIntent(VehicleInsuranceActivity.this);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private boolean validateComparisonRequest(ComparisonRequest comparisonRequest){

        boolean status = true;

        ComparisonRequest.ComparisonRequestRisk comparisonRequestRisk = comparisonRequest.getRisk();

        ComparisonRequest.ComparisonRequestQuote comparisonRequestQuote = comparisonRequest.getQuote();

        TextInputLayout vehicleInsuranceTypeInputLayout = mActivityVehicleInsuranceBinding.vehicleInsuranceTypeInputLayout;

        TextInputLayout vehicleTypeInputLayout = mActivityVehicleInsuranceBinding.vehicleTypeInputLayout;

        TextInputLayout vehicleRegInputLayout = mActivityVehicleInsuranceBinding.vehicleRegInputLayout;

        TextInputLayout vehicleDateStartInputLayout = mActivityVehicleInsuranceBinding.vehicleDateStartInputLayout;

        TextInputLayout vehicleYearManufactureInputLayout = mActivityVehicleInsuranceBinding.vehicleYearManufactureInputLayout;

        TextInputLayout vehicleValueInputLayout = mActivityVehicleInsuranceBinding.vehicleValueInputLayout;

        vehicleInsuranceTypeInputLayout.setError(null);

        vehicleTypeInputLayout.setError(null);

        vehicleRegInputLayout.setError(null);

        vehicleDateStartInputLayout.setError(null);

        vehicleDateStartInputLayout.setError(null);

        vehicleYearManufactureInputLayout.setError(null);

        if(CommonUtils.ObjectIsNotNull(comparisonRequestRisk)) {

            String cvtCode = String.valueOf(comparisonRequestRisk.getCvtCode());

            if (CommonUtils.StringIsEmpty(cvtCode) || CommonUtils.StringIsEqual(cvtCode,"0") ) {
                vehicleInsuranceTypeInputLayout.setError(getResources().getString(R.string.error_insuranceType));
                status=false;
            }

            if (CommonUtils.StringIsEmpty(comparisonRequestRisk.getPropertyDesc())) {
                vehicleTypeInputLayout.setError(getResources().getString(R.string.error_vehicleModel));
                status=false;
            } if (CommonUtils.StringIsEmpty(comparisonRequestRisk.getPropertyId())) {
                vehicleRegInputLayout.setError(getResources().getString(R.string.error_vehicleRegistration));
                status=false;
            } else if (CommonUtils.isValidRegistrationNumber(comparisonRequestRisk.getPropertyId())) {
                vehicleRegInputLayout.setError(getResources().getString(R.string.error_invalid_vehicleRegistration));
                status=false;
            }
            if (CommonUtils.StringIsEmpty(comparisonRequestRisk.getYearOfManufacture())) {
                vehicleYearManufactureInputLayout.setError(getResources().getString(R.string.error_vehicleYearOfManufacture));
                status=false;
            } else if (comparisonRequestRisk.getYearOfManufacture().length() < 4) {
                vehicleYearManufactureInputLayout.setError(getResources().getString(R.string.error_invalid_vehicleYearOfManufacture));
                status=false;
            }
            if (CommonUtils.StringIsEmpty(vehicleValueEditText.getText().toString())
                   || vehicleValueEditText.getText().toString().length() <= 3 ) {
                vehicleValueInputLayout.setError(getResources().getString(R.string.error_vehicleValue));
                status=false;
            } else if( !vehicleValueEditText.getText().toString().endsWith(".00")){
                   vehicleValueInputLayout.setError(getResources().getString(R.string.error_invalid_vehicleValue));
                 status=false;
            }

            TextInputEditText insuranceType = mActivityVehicleInsuranceBinding.vehicleInsuranceType;
            if (CommonUtils.StringIsEmpty(Objects.requireNonNull(insuranceType.getText()).toString()) ) {
                vehicleInsuranceTypeInputLayout.setError(getResources().getString(R.string.error_insuranceType));
                status=false;
            }

            if(CommonUtils.ObjectIsNotNull(comparisonRequestQuote)){
                if (CommonUtils.StringIsEmpty(comparisonRequestQuote.getWefDate())) {
                    vehicleDateStartInputLayout.setError(getResources().getString(R.string.error_startDate));
                    status=false;
                }
            }
            }else {
                status=false;
                Toast.makeText(getApplicationContext(), "Enter missing value !!!", Toast.LENGTH_LONG).show();
            }
        return status;
    }



    @Override
    public void setAdapter(List<ExtraBenefitsResponse> extraBenefitsResponses) {
        LinearLayout extensionList = mActivityVehicleInsuranceBinding.extensions;

        extensionList.removeAllViews();

        if(!extraBenefitsResponses.isEmpty()) {
            int pad = Math.round(getResources().getDimension(R.dimen.scale_10));
            TextView title_vehicleExtensions = new TextView(this);
            title_vehicleExtensions.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            title_vehicleExtensions.setText(getResources().getString(R.string.optional_extras));
            title_vehicleExtensions.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
            title_vehicleExtensions.setTextSize(getResources().getDimension(R.dimen.scale_6));
            title_vehicleExtensions.setPadding(pad, pad, pad, pad);
            extensionList.addView(title_vehicleExtensions);

            CheckBox checkBox;
            for (ExtraBenefitsResponse extraBenefitsResponse : extraBenefitsResponses) {

                checkBox = new CheckBox(this);
                checkBox.setText(extraBenefitsResponse.getDesc());
                checkBox.setId(extraBenefitsResponse.getCode().intValue());
                checkBox.setTextSize(getResources().getDimension(R.dimen.scale_4));
                checkBox.setHint(extraBenefitsResponse.getExcessDetails());
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) {
                        buttonView.getId();

                        String limitname = null;
                        String details = null;

                        if(buttonView.getHint() != null){

                            details = buttonView.getHint().toString();
                        }

                        if(buttonView.getText() != null){

                            limitname = buttonView.getText().toString();
                        }

                        ExtensionsDialog.newInstance().show(getSupportFragmentManager(), buttonView.getId(),limitname,details);
                    } else {
                        removeSection(buttonView.getId());
                    }

                });
                extensionList.addView(checkBox);
            }
        }

    }

    private void removeSection(int id) {
        Iterator<ComparisonRequest.ComparisonRequestSection> iterator = this.comparisonRequestSections.iterator();
        this.comparisonRequestSections = new ArrayList<>();
        while(iterator.hasNext()){
            ComparisonRequest.ComparisonRequestSection requestSection = iterator.next();
            if(requestSection.getSectCode().intValueExact() == id){
                iterator.remove();
            }
        }
    }

    @Override
    public void showInsuranceTypes(List<CoverTypesResponce> coverTypesResponceList) {

        if(!coverTypesResponceList.isEmpty()) {

            if(coverTypesResponceList.size() == 1){
                setInsuranceType(coverTypesResponceList.get(0).getDesc(),coverTypesResponceList.get(0).getCode().intValueExact());

            }else {

                VehicleInsuranceTypeDialog.newInstance().show(getSupportFragmentManager(), coverTypesResponceList,null,1);
            }

        }else {
            VehicleInsuranceTypeDialog.newInstance().close();
            handleError(new LocalError(0,getResources().getString(R.string.missing_insurance_types_value)));
        }

    }

    @Override
    public void showModels(List<VehicleMakesResponce> vehicleMakesResponcesList) {
        if(!vehicleMakesResponcesList.isEmpty()) {

            VehicleModelsDialog.newInstance().show(getSupportFragmentManager(),vehicleMakesResponcesList,null,1);

        }else {
            VehicleModelsDialog.newInstance().close();
            handleError(new LocalError(0,getResources().getString(R.string.missing_vehicle_models_value)));
        }

    }

    @Override
    public void showDatePicker() {
        VehiclesDateDialog.newInstance().show(getSupportFragmentManager(),1);
    }

    @Override
    public void getVehicleMakes() {
        if(isNetworkConnected()){
            mVehicleInsuranceViewModel.getVehicleMakes();
        }
    }

    @Override
    public void getSclCode() {
        if(isNetworkConnected()) {
            mVehicleInsuranceViewModel.getCoverTypes(BigDecimal.valueOf(701));
        }
    }

    public void setMakeName(String make){
        EditText button = mActivityVehicleInsuranceBinding.vehicleType;
        button.setText(make);

    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    public void setInsuranceType(String type,int code){
        EditText button = mActivityVehicleInsuranceBinding.vehicleInsuranceType;
        button.setText(type);
        //release two

      /*  if(isNetworkConnected())
        {
            mVehicleInsuranceViewModel.getExtraBenefits(BigDecimal.valueOf(code));

        }*/


        this.cvtCode = BigDecimal.valueOf(code);

    }

    public void formatDate(int dayOfMonth, int month ,int year){

        String selecteddate = CommonUtils.formatSingleDate(String.valueOf(dayOfMonth));

        String selectedmonth = CommonUtils.formatSingleDate(String.valueOf(month + 1));

        String selectedyear = String.valueOf(year);


        String reformattedStr = selectedyear + "-" + selectedmonth + "-" + selecteddate;

        EditText button = mActivityVehicleInsuranceBinding.vehicleDateStart;
       if(!reformattedStr.isEmpty()) {
           String displayDate = selecteddate + "-"+ selectedmonth + "-" + selectedyear;
           try {
               mActivityVehicleInsuranceBinding.vehicleDateEnd.setText(CommonUtils.generateEndDate(displayDate));
           } catch (ParseException e) {
               e.printStackTrace();
           }
           button.setText(displayDate);
       }
    }

    public void addSection(ComparisonRequest.ComparisonRequestSection comparisonRequestSection){

        this.comparisonRequestSections.add(comparisonRequestSection);
    }

    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }else {
            SessionExpired();
        }
    }

   private void SessionExpired(){

        Toast.makeText(getApplicationContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onRestart() {

        String comparisonRequest1 =  getViewModel().getDataManager().getComparisonRequest();

        if (!CommonUtils.StringIsEmpty(comparisonRequest1)) {
            Gson gson = new Gson();

            ComparisonRequest comparisonRequest = gson.fromJson(comparisonRequest1, ComparisonRequest.class);

            setValues(comparisonRequest);
        }
        super.onRestart();
    }

    private void setValues(ComparisonRequest comparisonRequest) {

        mVehicleInsuranceViewModel.setValues(comparisonRequest);
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}

    class NumberTextWatcher implements TextWatcher {

    private final DecimalFormat decimalFormat;
    private final DecimalFormat decimalFormatting;
    private final EditText editText;
    private boolean hasFractionalPart;
    private int trailingZeroCount;

    public NumberTextWatcher(EditText editText, String pattern) {
        decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormatting = new DecimalFormat("#,###.00",new DecimalFormatSymbols(Locale.US));
        this.editText = editText;
        hasFractionalPart = false;
    }

    @Override
    public void afterTextChanged(Editable s) {
        editText.removeTextChangedListener(this);

        if (s != null && !s.toString().isEmpty()) {
            try {
                int inilen, endlen;
                inilen = editText.getText().length();
                String v = s.toString().replace(String.valueOf(decimalFormat.getDecimalFormatSymbols().getGroupingSeparator()), "").replace("$","");
                Number n = decimalFormat.parse(v);
                int cp = editText.getSelectionStart();
                if (hasFractionalPart) {
                    StringBuilder trailingZeros = new StringBuilder();
                    while (trailingZeroCount-- > 0)
                        trailingZeros.append('0');
                    editText.setText(String.format("%s%s", decimalFormat.format(n), trailingZeros.toString()));
                } else {
                    editText.setText(decimalFormatting.format(n));
                }
                editText.setText(editText.getText().toString());
                endlen = editText.getText().length();
                int sel = (cp + (endlen - inilen));
                if (sel > 0 && sel < editText.getText().length()) {
                    editText.setSelection(sel);
                } else if (trailingZeroCount > -1) {
                    editText.setSelection(editText.getText().length() - 3);
                } else {
                    editText.setSelection(editText.getText().length());
                }
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
            }
        }

        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int index = s.toString().indexOf(String.valueOf(decimalFormat.getDecimalFormatSymbols().getDecimalSeparator()));
        trailingZeroCount = 0;
        if (index > -1) {
            for (index++; index < s.length(); index++) {
                if (s.charAt(index) == '0')
                    trailingZeroCount++;
                else {
                    trailingZeroCount = 0;
                }
            }
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }
}