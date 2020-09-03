package com.turnkeyafrica.turnkeybankassurance.ui.identification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.MiscData;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityIdentificationsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmandpay.ConfirmAndPayActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ImageUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import javax.inject.Inject;


public class IdentificationsActivity extends BaseActivity<ActivityIdentificationsBinding, IdentificationsViewModel> implements IdentificationsNavigator {

  
    @Inject
    ViewModelProviderFactory factory;
    private IdentificationsViewModel mIdentificationsViewModel;

    private ActivityIdentificationsBinding mActivityIdentificationsBinding;

    private AlertDialog uploading;

    private EditText KRANo;

    private EditText IDNo;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    private Bitmap identificationPic;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_identifications;
    }

    @Override
    public IdentificationsViewModel getViewModel() {
        mIdentificationsViewModel = new ViewModelProvider(getViewModelStore(), factory).get(IdentificationsViewModel.class);
        return mIdentificationsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIdentificationsViewModel.setNavigator(this);
        mActivityIdentificationsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityIdentificationsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initComponents();
        checkCameraHardware(getApplicationContext());
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, IdentificationsActivity.class);
    }


    @Override
    public void handleError(LocalError error) {
        if(error.getCode() != 401) {
            if (!CommonUtils.StringIsEmpty(error.getMessage())) {

                ViewUtils.showDialog(this, "", error.getMessage(), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
                if(uploading != null){uploading.dismiss();}
            }
        }else {
            if(uploading != null){uploading.dismiss();}
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
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

    @Override
    public void proceedToPayment() {
        if(verifyInput()){
                if(CommonUtils.ObjectIsNotNull(identificationPic)) {
                    uploading = ViewUtils.uploadingDialog(this, this);
                    convertBitmap(identificationPic);
                }

             else {
                ViewUtils.showDialog(this, "", getResources().getString(R.string.error_no_id), getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
            }
        }
    }


    @Override
    public void removePhoto() {

        mActivityIdentificationsBinding.UploadIdButton.setImageResource(R.drawable.picbg);
        mActivityIdentificationsBinding.removePicture.setVisibility(View.GONE);
        identificationPic = null;
        mActivityIdentificationsBinding.UploadIdButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mActivityIdentificationsBinding.UploadIdInfo.setVisibility(View.VISIBLE);
    }

    private void initComponents() {

        KRANo = mActivityIdentificationsBinding.KraPinInput;

        IDNo = mActivityIdentificationsBinding.identificationsInput;

    }

    private boolean verifyInput() {

        TextInputLayout customerKraNumber = mActivityIdentificationsBinding.kraPinInputLayout;

        TextInputLayout customerIdNumber = mActivityIdentificationsBinding.identificationsInputLayout;

        customerIdNumber.setError(null);
        customerKraNumber.setError(null);

        if (CommonUtils.StringIsEmpty(KRANo.getText().toString())) {
            customerKraNumber.setError(getResources().getString(R.string.error_kra_pin));
            return false;
        }
        else if(CommonUtils.verifyKRAPin(KRANo.getText().toString())){
            customerKraNumber.setError(getResources().getString(R.string.error_invalid_kraNumber));
            return false;
        }
        else if (CommonUtils.StringIsEmpty(IDNo.getText().toString())) {
            customerIdNumber.setError(getResources().getString(R.string.error_id));
            return false;
        }

        return true;
    }

    private void convertBitmap(Bitmap identificationPic) {

        UploadRequest uploadRequest = new UploadRequest();

        String image  = ImageUtils.encodeTobase64(identificationPic);

        uploadRequest.setName(getResources().getString(R.string.title_activity_identifications)+ ".png");

        String insuranceQuote =  getViewModel().getDataManager().getInsuranceQuote();

        if (insuranceQuote != null) {
            Gson gson = new Gson();
            InsuranceQuoteResponce insuranceQuoteResponce = gson.fromJson(insuranceQuote, InsuranceQuoteResponce.class);

            uploadRequest.setContent(image);

            if(isNetworkConnected()) {

                uploadRequest.setClientCode(mIdentificationsViewModel.getDataManager().getCurrentUserId().toString());

                uploadRequest.setDocName(uploadRequest.getName());

                uploadRequest.setPolNo(insuranceQuoteResponce.getInsuranceQuotation().getQuotCode().toString());

                uploadRequest.setTransType("CLIENTS");

                mIdentificationsViewModel.uploadIdentification(uploadRequest);
            }
        }
    }

    @Override
    public void pictureOptions() {
        mActivityIdentificationsBinding.Q1QuoteStep1btn.setVisibility(View.GONE);
        mActivityIdentificationsBinding.identificationBottomNav.setVisibility(View.VISIBLE);
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

    private void setIdentification(Bitmap bitmap){
        if(CommonUtils.ObjectIsNotNull(bitmap)) {
            identificationPic = bitmap;
            mActivityIdentificationsBinding.removePicture.setVisibility(View.VISIBLE);
            mActivityIdentificationsBinding.UploadIdInfo.setVisibility(View.GONE);
        }
    }

    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityIdentificationsBinding.takePhoto.setEnabled(false);
            mActivityIdentificationsBinding.takePhoto.setFocusable(false);
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
        Intent intent = ConfirmAndPayActivity.newIntent(this);

        MiscData miscData = new MiscData();

        miscData.setIdNumber(IDNo.getText().toString());
        miscData.setKraNumber(KRANo.getText().toString());
        getViewModel().getDataManager().setMiscData(miscData);

        uploading.dismiss();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if(mActivityIdentificationsBinding.identificationBottomNav.getVisibility() == View.VISIBLE) {
            mActivityIdentificationsBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
            mActivityIdentificationsBinding.identificationBottomNav.setVisibility(View.GONE);
        }else {
            if(uploading != null){uploading.dismiss();}
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

                mActivityIdentificationsBinding.UploadIdButton.setImageBitmap(bitmap);
                mActivityIdentificationsBinding.UploadIdButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

                setIdentification(bitmap);
                mActivityIdentificationsBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
                mActivityIdentificationsBinding.identificationBottomNav.setVisibility(View.GONE);

            }else {

                mActivityIdentificationsBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
                mActivityIdentificationsBinding.identificationBottomNav.setVisibility(View.GONE);

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

            try{
                Glide
                        .with(this)
                        .load(picturePath)
                        .centerCrop()
                        .into(mActivityIdentificationsBinding.UploadIdButton);

            Bitmap bitmap = SiliCompressor.with(getApplicationContext()).getCompressBitmap(picturePath);

            setIdentification(bitmap);

            mActivityIdentificationsBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
            mActivityIdentificationsBinding.identificationBottomNav.setVisibility(View.GONE);
        }catch (IOException e){
                handleError(new LocalError(0,getResources().getString(R.string.error_image)));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void choosePhoto() {
       ImageUtils.chooseSinglePhoto(this,CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE);
    }
}
