package com.turnkeyafrica.bankassurance.ui.vehiclevaluation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.iceteck.silicompressorr.SiliCompressor;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityVehicleValuationBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;
import com.turnkeyafrica.bankassurance.utils.ImageUtils;
import com.turnkeyafrica.bankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import javax.inject.Inject;

public class VehicleValuationActivity extends BaseActivity<ActivityVehicleValuationBinding, VehicleValuationViewModel> implements VehicleValuationNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private VehicleValuationViewModel mVehicleValuationViewModel;

    private ActivityVehicleValuationBinding mActivityVehicleValuationBinding;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    private AlertDialog uploading;

    private Bitmap valuationDocumentPicture;

    String policyNo;
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_valuation;
    }

    @Override
    public VehicleValuationViewModel getViewModel() {
        mVehicleValuationViewModel = new ViewModelProvider(getViewModelStore(),factory).get(VehicleValuationViewModel.class);
        return mVehicleValuationViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVehicleValuationViewModel.setNavigator(this);
        mActivityVehicleValuationBinding = getViewDataBinding();
        Toolbar toolbar = mActivityVehicleValuationBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getPolicyResponce();
        checkCameraHardware(getApplicationContext());
    }

    private void getPolicyResponce() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            policyNo = extras.getString("7s12a$");

        }

    }


    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
            if(uploading != null){uploading.dismiss();}
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
    }@Override
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void uploadValuation() {
  
            if(CommonUtils.ObjectIsNotNull(valuationDocumentPicture)) {
                    uploading = ViewUtils.uploadingDialog(this, this);
                    convertBitmap(valuationDocumentPicture);
                }else {
                ViewUtils.showDialog(this, "", getResources().getString(R.string.error_no_id), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }
    


    @Override
    public void removePhoto() {

        mActivityVehicleValuationBinding.vehicleValuationBtn.setImageResource(R.drawable.picbg);
        mActivityVehicleValuationBinding.removePicture.setVisibility(View.GONE);
        valuationDocumentPicture = null;
        mActivityVehicleValuationBinding.vehicleValuationBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mActivityVehicleValuationBinding.vehicleValuationQ1.setVisibility(View.VISIBLE);
    }
    

    public static Intent newIntent(Context context) {
        return new Intent(context, VehicleValuationActivity.class);
    }

    private void convertBitmap(Bitmap valuationReport) {

        UploadRequest uploadRequest = new UploadRequest();

        String image  = ImageUtils.encodeTobase64(valuationReport);

        uploadRequest.setName("VehicleValuation.png");

            uploadRequest.setContent(image);

            if(isNetworkConnected()) {

                uploadRequest.setClientCode("0");

                uploadRequest.setDocName(uploadRequest.getName());

                uploadRequest.setPolNo(policyNo);

                uploadRequest.setTransType("UW");

                mVehicleValuationViewModel.uploadVehicleValuation(uploadRequest);
            }

    }

    @Override
    public void pictureOptions() {
        mActivityVehicleValuationBinding.nextStepValuationBtn.setVisibility(View.GONE);
        mActivityVehicleValuationBinding.vehicleValuationBottomNav.setVisibility(View.VISIBLE);
        hideKeyboard();
    }

    @Override
    public void hideKeyboard() {
        super.hideKeyboard();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    private void setValuationDocument(Bitmap bitmap){
        if(CommonUtils.ObjectIsNotNull(bitmap)) {
            valuationDocumentPicture = bitmap;
            mActivityVehicleValuationBinding.removePicture.setVisibility(View.VISIBLE);
            mActivityVehicleValuationBinding.vehicleValuationQ1.setVisibility(View.GONE);
        }
    }

    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityVehicleValuationBinding.takePhoto.setEnabled(false);
            mActivityVehicleValuationBinding.takePhoto.setFocusable(false);
        }
    }

    @Override
    public void takePhoto() {
        Intent camera_intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(camera_intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void exitDialog() {
        uploading.dismiss();
    }

    @Override
    public void dismissUploadDialog() {
        Intent intent = DashboardActivity.newIntent(this);
        uploading.dismiss();
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if( mActivityVehicleValuationBinding.vehicleValuationBottomNav.getVisibility() == View.VISIBLE) {
            mActivityVehicleValuationBinding.nextStepValuationBtn.setVisibility(View.VISIBLE);
            mActivityVehicleValuationBinding.vehicleValuationBottomNav.setVisibility(View.GONE);
        }else {
            if(uploading != null){uploading.dismiss();}
            super.onBackPressed();
            Intent intent = DashboardActivity.newIntent(this);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK
                && null != data) {

            if(CommonUtils.ObjectIsNotNull(data.getExtras())) {

                Bitmap bitmap = (Bitmap) data.getExtras()
                        .get("data");

                mActivityVehicleValuationBinding.vehicleValuationBtn.setImageBitmap(bitmap);
                mActivityVehicleValuationBinding.vehicleValuationBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);

                setValuationDocument(bitmap);

                mActivityVehicleValuationBinding.nextStepValuationBtn.setVisibility(View.VISIBLE);
                mActivityVehicleValuationBinding.vehicleValuationBottomNav.setVisibility(View.GONE);

            }else {

                mActivityVehicleValuationBinding.nextStepValuationBtn.setVisibility(View.VISIBLE);
                mActivityVehicleValuationBinding.vehicleValuationBottomNav.setVisibility(View.GONE);

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
                    .into(mActivityVehicleValuationBinding.vehicleValuationBtn);

                Bitmap bitmap = SiliCompressor.with(getApplicationContext()).getCompressBitmap(picturePath);
            setValuationDocument(bitmap);

            mActivityVehicleValuationBinding.nextStepValuationBtn.setVisibility(View.VISIBLE);
            mActivityVehicleValuationBinding.vehicleValuationBottomNav.setVisibility(View.GONE);
        }catch (IOException e){
                handleError(new LocalError(0,getResources().getString(R.string.error_image)));
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
}