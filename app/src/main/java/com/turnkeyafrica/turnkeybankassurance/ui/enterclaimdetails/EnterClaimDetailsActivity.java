package com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.RiskResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityEnterClaimDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.capturedamageimages.CaptureDamageImagesActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog.VehiclesDateDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialog;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class EnterClaimDetailsActivity extends BaseActivity<ActivityEnterClaimDetailsBinding,EnterClaimDetailsViewModel> implements EnterClaimDetailsNavigator, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    EnterClaimDetailsViewModel mEnterClaimDetailsViewModel;
    ActivityEnterClaimDetailsBinding mActivityEnterClaimDetailsBinding;

    PolicyResponce policyResponse;

    ArrayList<RiskResponce>risks;
    
    int imageOption;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    String drivingLicensePhotoPath;

    String incidentPhotoPath;

    String incidentDate;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_claim_details;
    }

    @Override
    public EnterClaimDetailsViewModel getViewModel() {
        mEnterClaimDetailsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(EnterClaimDetailsViewModel.class);
        return mEnterClaimDetailsViewModel;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, EnterClaimDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEnterClaimDetailsViewModel.setNavigator(this);
        mActivityEnterClaimDetailsBinding =getViewDataBinding();
        Toolbar toolbar = mActivityEnterClaimDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        checkCameraHardware(getApplicationContext());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setUpView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpView() {

        String polResponse = getViewModel().getDataManager().getPolicyResponse();

        if(!CommonUtils.StringIsEmpty(polResponse)){
            policyResponse = new Gson().fromJson(polResponse, PolicyResponce.class);
            if (CommonUtils.ObjectIsNotNull(policyResponse.getRisks())) {
                if (!policyResponse.getRisks().isEmpty()) {

                    if (policyResponse.getRisks().size() <= 1) {
                        mActivityEnterClaimDetailsBinding.propertyId.setEnabled(false);
                        mActivityEnterClaimDetailsBinding.propertyId.setText(policyResponse.getRisks().get(0).getPropertyId());
                    } else {
                        risks = policyResponse.getRisks();
                    }
                }
            }
        }
    }


    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    public void formatDate(int dayOfMonth, int month, int year) {

            String selectedDate = CommonUtils.formatSingleDate(String.valueOf(dayOfMonth));

            String selectedMonth = CommonUtils.formatSingleDate(String.valueOf(month + 1));

            String selectedYear = String.valueOf(year);

            String displayDate = selectedDate + "-"+ selectedMonth + "-" + selectedYear;

            incidentDate = selectedYear + "-"+ selectedMonth + "-" + selectedDate;

            TextInputEditText date = mActivityEnterClaimDetailsBinding.incidentDate;

            date.setText(displayDate);

    }

    @Override
    public void showDatePicker() {
        VehiclesDateDialog.newInstance().show(getSupportFragmentManager(),2);
    }

    @Override
    public void showVehicleRegs() {
        VehicleModelsDialog.newInstance().show(getSupportFragmentManager(),null,risks,2);
    }

    @Override
    public void pictureOptions(int option) {
        mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.VISIBLE);
        imageOption = option;
        hideKeyboard();
    }

    public void setPropertyId(RiskResponce risk) {
        mActivityEnterClaimDetailsBinding.propertyId.setText(risk.getPropertyId());
        mActivityEnterClaimDetailsBinding.propertyId.setHint(risk.getCovtShtDesc());
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void removePhoto(int option) {

        if(option==1) {
            mActivityEnterClaimDetailsBinding.incidentReportPicker.setImageResource(R.drawable.picbg);
            incidentPhotoPath = null;
                mActivityEnterClaimDetailsBinding.removePicture.setVisibility(View.GONE);
                mActivityEnterClaimDetailsBinding.incidentReportPicker.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                mActivityEnterClaimDetailsBinding.incidentReportPickerInfo.setVisibility(View.VISIBLE);
        }else{
            mActivityEnterClaimDetailsBinding.driverLicencePicker.setImageResource(R.drawable.picbg);
            drivingLicensePhotoPath = null;
                mActivityEnterClaimDetailsBinding.removePicture2.setVisibility(View.GONE);
                mActivityEnterClaimDetailsBinding.driverLicencePicker.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                mActivityEnterClaimDetailsBinding.driverLicencePickerInfo.setVisibility(View.VISIBLE);
        }
    }


    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityEnterClaimDetailsBinding.takePhoto.setEnabled(false);
            mActivityEnterClaimDetailsBinding.takePhoto.setFocusable(false);
        }
    }

    @Override
    public void takePhoto(String name) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile(name);
            } catch (IOException ex) {
                handleError(new LocalError(0,"An error occurred will opening camera"));
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.turnkeyafrica.bankassurance",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        }
    }
    private File createImageFile(String name) throws IOException {

        String imageFileName = "USERS:" + name;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".png",
                storageDir
        );

        if(imageOption == 1) {
            incidentPhotoPath = image.getAbsolutePath();
        }else {
            drivingLicensePhotoPath = image.getAbsolutePath();
        }
        return image;
    }
    
    @Override
    public void onBackPressed() {

        if(mActivityEnterClaimDetailsBinding.requestBottomNav.getVisibility() == View.VISIBLE) {
            mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                if(imageOption == 1) {
                    Glide
                            .with(this)
                            .load(incidentPhotoPath)
                            .centerCrop()
                            .into(mActivityEnterClaimDetailsBinding.incidentReportPicker);

                    mActivityEnterClaimDetailsBinding.removePicture.setVisibility(View.VISIBLE);
                    mActivityEnterClaimDetailsBinding.incidentReportPickerInfo.setVisibility(View.GONE);
                    mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.GONE);
                }else {
                    Glide
                            .with(this)
                            .load(drivingLicensePhotoPath)
                            .centerCrop()
                            .into(mActivityEnterClaimDetailsBinding.driverLicencePicker);

                    mActivityEnterClaimDetailsBinding.removePicture2.setVisibility(View.VISIBLE);
                    mActivityEnterClaimDetailsBinding.driverLicencePickerInfo.setVisibility(View.GONE);
                    mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.GONE);
                }

        }else if( requestCode == CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK
                && null != data){

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            assert selectedImage != null;
            Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

                if(imageOption == 1) {
                    Glide
                            .with(this)
                            .load(picturePath)
                            .centerCrop()
                            .into(mActivityEnterClaimDetailsBinding.incidentReportPicker);

                    incidentPhotoPath = picturePath;

                    mActivityEnterClaimDetailsBinding.removePicture.setVisibility(View.VISIBLE);
                    mActivityEnterClaimDetailsBinding.incidentReportPickerInfo.setVisibility(View.GONE);
                    mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.GONE);
                }else {
                    Glide
                            .with(this)
                            .load(picturePath)
                            .centerCrop()
                            .into(mActivityEnterClaimDetailsBinding.driverLicencePicker);

                    drivingLicensePhotoPath = picturePath;

                    mActivityEnterClaimDetailsBinding.removePicture2.setVisibility(View.VISIBLE);
                    mActivityEnterClaimDetailsBinding.driverLicencePickerInfo.setVisibility(View.GONE);
                    mActivityEnterClaimDetailsBinding.requestBottomNav.setVisibility(View.GONE);
                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void choosePhoto() {
        Intent  gallery_intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(gallery_intent, CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void verifyClaimDetails() {

        boolean state = true;

        String description = Objects.requireNonNull(mActivityEnterClaimDetailsBinding.claimShortDesc.getText()).toString();

        String incidentDate = Objects.requireNonNull(mActivityEnterClaimDetailsBinding.incidentDate.getText()).toString();

        String propertyId = Objects.requireNonNull(mActivityEnterClaimDetailsBinding.propertyId.getText()).toString();

        TextInputLayout claimShortDesc = mActivityEnterClaimDetailsBinding.claimShortDescInputLayout;

        TextInputLayout incidentDateInputLayout = mActivityEnterClaimDetailsBinding.incidentDateInputLayout;

        TextInputLayout propertyIdInputLayout = mActivityEnterClaimDetailsBinding.propertyIdInputLayout;

        claimShortDesc.setError(null);

        incidentDateInputLayout.setError(null);

        propertyIdInputLayout.setError(null);

        if(CommonUtils.StringIsEmpty(description)){
            claimShortDesc.setError(getResources().getString(R.string.error_incident_description));
            state = false;
        }

        if(CommonUtils.StringIsEmpty(incidentDate)){
            incidentDateInputLayout.setError(getResources().getString(R.string.error_incident_date));
            state = false;
        }

        if(CommonUtils.StringIsEmpty(propertyId)) {
            propertyIdInputLayout.setError(getResources().getString(R.string.error_select_vehicleRegistration));
            state = false;
        }

        if(CommonUtils.StringIsEmpty(incidentPhotoPath)){
            handleError(new LocalError(0, getResources().getString(R.string.error_incident_report)));
            state = false;
        }else if(CommonUtils.StringIsEmpty(drivingLicensePhotoPath)){
            handleError(new LocalError(0, getResources().getString(R.string.error_driving_licence)));
            state = false;
        }

        openDamageScreen(state);
    }

    private void openDamageScreen(boolean state){

        if(state){

            ClaimsDetailsObject detailsObject = new ClaimsDetailsObject();

            detailsObject.setDescription(Objects.requireNonNull(mActivityEnterClaimDetailsBinding.claimShortDesc.getText()).toString());
            detailsObject.setIncidentDate(incidentDate);
            detailsObject.setPropertyId(Objects.requireNonNull(mActivityEnterClaimDetailsBinding.propertyId.getText()).toString());
            detailsObject.setCoverType(Objects.requireNonNull(mActivityEnterClaimDetailsBinding.propertyId.getHint()).toString());

            List<String> images = new ArrayList<>();

            images.add(incidentPhotoPath);

            images.add(drivingLicensePhotoPath);

            detailsObject.setImages(images);

            Intent intent = CaptureDamageImagesActivity.newIntent(this);

            getViewModel().getDataManager().setClaimsDetailsObject(detailsObject);

            startActivity(intent);
        }
    }
}
