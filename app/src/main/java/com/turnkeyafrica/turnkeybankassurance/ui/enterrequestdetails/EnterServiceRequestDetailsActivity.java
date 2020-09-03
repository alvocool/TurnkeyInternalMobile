package com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.RiskResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestDocuments;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequestType;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityEnterServiceRequestDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.VehicleInsuranceTypeDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialog;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ImageUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


public class EnterServiceRequestDetailsActivity extends BaseActivity<ActivityEnterServiceRequestDetailsBinding,EnterServiceRequestDetailsViewModel> implements EnterServiceRequestDetailsNavigator, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    PolicyResponce policyResponce;
    @Inject
    ViewModelProviderFactory factory;
    EnterServiceRequestDetailsViewModel mEnterServiceRequestDetailsViewModel;

    ActivityEnterServiceRequestDetailsBinding mActivityEnterServiceRequestDetailsBinding;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    private Bitmap requestPic;

    private ServiceRequestResponse serviceRequestResponse;

    private List<ServiceRequestType> serviceRequestCategory;
    private List<RiskResponce> risks;
    private ServiceRequestType serviceRequestType;

    public static Intent newIntent(Context context) {
        return new Intent(context, EnterServiceRequestDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_service_request_details;
    }

    @Override
    public EnterServiceRequestDetailsViewModel getViewModel() {

        mEnterServiceRequestDetailsViewModel = new ViewModelProvider(
                getViewModelStore(),
                factory
        ).get(EnterServiceRequestDetailsViewModel.class);

        return mEnterServiceRequestDetailsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEnterServiceRequestDetailsViewModel.setNavigator(this);
        mActivityEnterServiceRequestDetailsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityEnterServiceRequestDetailsBinding.toolbar;
        serviceRequestResponse = new ServiceRequestResponse();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setUpView();
        getViewModel().getAllRequestCategories();
        checkCameraHardware(getApplicationContext());
    }

    private void setUpView() {

        Bundle extras = getIntent().getExtras();

        if(extras == null){
            mEnterServiceRequestDetailsViewModel.hideFields();
        }else {
            String response = extras.getString("polResp@1");
            policyResponce = new Gson().fromJson(response, PolicyResponce.class);
            mActivityEnterServiceRequestDetailsBinding.policyNameTxt.setText(policyResponce.getPolicyNo());

            serviceRequestResponse.setRefNumber(policyResponce.getPolicyNo());

            if (CommonUtils.ObjectIsNotNull(policyResponce.getRisks())) {

                if (!policyResponce.getRisks().isEmpty()) {

                    if (policyResponce.getRisks().size() <= 1) {
                        mActivityEnterServiceRequestDetailsBinding.vehicleSelectTxt.setText(policyResponce.getRisks().get(0).getPropertyId());
                        mActivityEnterServiceRequestDetailsBinding.vehicleSelectTxt.setEnabled(false);
                    } else {
                        risks = policyResponce.getRisks();
                    }
                }
            }
        }

    }

    @Override
    public void pictureOptions() {
        mActivityEnterServiceRequestDetailsBinding.submitRequest.setVisibility(View.GONE);
        mActivityEnterServiceRequestDetailsBinding.requestBottomNav.setVisibility(View.VISIBLE);
        hideKeyboard();
    }



    @Override
    public void onBackPressed() {

        if(mActivityEnterServiceRequestDetailsBinding.requestBottomNav.getVisibility() == View.VISIBLE) {
            mActivityEnterServiceRequestDetailsBinding.submitRequest.setVisibility(View.VISIBLE);
            mActivityEnterServiceRequestDetailsBinding.requestBottomNav.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK
                && null != data) {

            if(CommonUtils.ObjectIsNotNull(data.getExtras())) {

                Bitmap bitmap = (Bitmap) data.getExtras()
                        .get("data");

                mActivityEnterServiceRequestDetailsBinding.requestImagePicker.setImageBitmap(bitmap);
                mActivityEnterServiceRequestDetailsBinding.requestImagePicker.setScaleType(ImageView.ScaleType.FIT_CENTER);
                setRequestImagePicker(bitmap);

                mActivityEnterServiceRequestDetailsBinding.submitRequest.setVisibility(View.VISIBLE);
                mActivityEnterServiceRequestDetailsBinding.requestBottomNav.setVisibility(View.GONE);

            }else {

                mActivityEnterServiceRequestDetailsBinding.submitRequest.setVisibility(View.VISIBLE);
                mActivityEnterServiceRequestDetailsBinding.requestBottomNav.setVisibility(View.GONE);

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

            try {

                Glide
                        .with(this)
                        .load(picturePath)
                        .centerCrop()
                        .into(mActivityEnterServiceRequestDetailsBinding.requestImagePicker);

                Bitmap bitmap = SiliCompressor.with(getApplicationContext()).getCompressBitmap(picturePath);

                setRequestImagePicker(bitmap);

                mActivityEnterServiceRequestDetailsBinding.submitRequest.setVisibility(View.VISIBLE);
                mActivityEnterServiceRequestDetailsBinding.requestBottomNav.setVisibility(View.GONE);
            }catch (IOException e){
                handleError(new LocalError(0,getResources().getString(R.string.error_image)));

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void removePhoto() {

        mActivityEnterServiceRequestDetailsBinding.requestImagePicker.setImageResource(R.drawable.picbg);
        requestPic = null;
        mActivityEnterServiceRequestDetailsBinding.removePicture.setVisibility(View.GONE);
        mActivityEnterServiceRequestDetailsBinding.requestImagePicker.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mActivityEnterServiceRequestDetailsBinding.requestPhoto.setVisibility(View.VISIBLE);
    }

    private void setRequestImagePicker(Bitmap bitmap){
        if(CommonUtils.ObjectIsNotNull(bitmap)) {
            requestPic = bitmap;
            mActivityEnterServiceRequestDetailsBinding.removePicture.setVisibility(View.VISIBLE);
            mActivityEnterServiceRequestDetailsBinding.requestPhoto.setVisibility(View.GONE);
        }
    }

    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityEnterServiceRequestDetailsBinding.takePhoto.setEnabled(false);
            mActivityEnterServiceRequestDetailsBinding.takePhoto.setFocusable(false);
        }
    }

    @Override
    public void takePhoto() {

        Intent camera_intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(camera_intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void submitRequest() {

        if(verifyDetails()) {

            serviceRequestResponse= new ServiceRequestResponse();

            serviceRequestResponse.setDescription(Objects.requireNonNull(mActivityEnterServiceRequestDetailsBinding.requestDescription.getText()).toString());

            serviceRequestResponse.setRequestCategory(serviceRequestType.getCode());

            if(!CommonUtils.StringIsEmpty(Objects.requireNonNull(mActivityEnterServiceRequestDetailsBinding.vehicleSelectTxt.getText()).toString())){
                serviceRequestResponse.setSubRefNumber(mActivityEnterServiceRequestDetailsBinding.vehicleSelectTxt.getText().toString());
            }

            if(!CommonUtils.StringIsEmpty(Objects.requireNonNull(mActivityEnterServiceRequestDetailsBinding.policyNameTxt.getText()).toString())){
                serviceRequestResponse.setRefNumber(mActivityEnterServiceRequestDetailsBinding.policyNameTxt.getText().toString());
            }

            if(requestPic != null){

                ServiceRequestDocuments requestDocument = new ServiceRequestDocuments();

                requestDocument.setName("ServiceRequestDocument");

                String image  = ImageUtils.encodeTobase64(requestPic);

                requestDocument.setContent(image);

                List<ServiceRequestDocuments> documents = new ArrayList<>();

                documents.add(requestDocument);

                serviceRequestResponse.setDocuments(documents);
            }

            getViewModel().saveServiceRequest(serviceRequestResponse);
        }
    }

    private boolean verifyDetails() {

        boolean state = true;

        String description = Objects.requireNonNull(mActivityEnterServiceRequestDetailsBinding.requestDescription.getText()).toString();

        String category = Objects.requireNonNull(mActivityEnterServiceRequestDetailsBinding.requestCategoryTxt.getText()).toString();

        TextInputLayout requestInputLayout = mActivityEnterServiceRequestDetailsBinding.requestDescriptionInputLayout;

        TextInputLayout categoryInputLayout = mActivityEnterServiceRequestDetailsBinding.requestCategory;

        requestInputLayout.setError(null);

        categoryInputLayout.setError(null);


        if(CommonUtils.StringIsEmpty(description)){
            requestInputLayout.setError(getResources().getString(R.string.error_request_description));
            state = false;
        }

        if(CommonUtils.StringIsEmpty(category)){
            categoryInputLayout.setError(getResources().getString(R.string.error_request_category));
            state = false;
        }

        return state;
    }

    @Override
    public void showRequestCategories() {
        VehicleInsuranceTypeDialog.newInstance().show(getSupportFragmentManager(), null,serviceRequestCategory,2);
    }

    @Override
    public void choosePhoto() {
        Intent  gallery_intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(gallery_intent, CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE);
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

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,getResources().getString(R.string.loading));
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void setRequestTypes(List<ServiceRequestType> requestTypes) {
        if(!requestTypes.isEmpty()){
           this.serviceRequestCategory = requestTypes;
           mActivityEnterServiceRequestDetailsBinding.requestCategory.setEnabled(true);
        }
    }

    @Override
    public void showPropertyId() {
        VehicleModelsDialog.newInstance().show(getSupportFragmentManager(),null,risks,3);
    }

    @Override
    public void openDashboard() {
        Intent intent = DashboardActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    private void SessionExpired(){

        Toast.makeText(getApplicationContext(),getResources().getString(R.string.sessionExpired),Toast.LENGTH_LONG).show();
        getViewModel().getDataManager().setUserAsLoggedOut();
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    public void setPropertyId(RiskResponce risk) {
        mActivityEnterServiceRequestDetailsBinding.vehicleSelectTxt.setText(risk.getPropertyId());
        serviceRequestResponse.setSubRefNumber(risk.getPropertyId());
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    public void setServiceRequestType(ServiceRequestType serviceRequestType) {
        mActivityEnterServiceRequestDetailsBinding.requestCategoryTxt.setText(serviceRequestType.getName());
        this.serviceRequestType = serviceRequestType;
    }
}
