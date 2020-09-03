package com.turnkeyafrica.turnkeybankassurance.ui.proofofownership;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.UploadRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityProofOfOwnerShipBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.identification.IdentificationsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ImageUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import javax.inject.Inject;


public class ProofOfOwnerShipActivity extends BaseActivity<ActivityProofOfOwnerShipBinding, ProofOfOwnerShipViewModel> implements ProofOfOwnerShipNavigator{

    @Inject
    ViewModelProviderFactory factory;
    private ProofOfOwnerShipViewModel mProofOfOwnerShipViewModel;

    private ActivityProofOfOwnerShipBinding mActivityProofOfOwnerShipBinding;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private AlertDialog uploading;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    private Bitmap proofOfOwnerShipPic;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_proof_of_owner_ship;
    }

    @Override
    public ProofOfOwnerShipViewModel getViewModel() {
        mProofOfOwnerShipViewModel = new ViewModelProvider(getViewModelStore(),factory).get(ProofOfOwnerShipViewModel.class);
        return mProofOfOwnerShipViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProofOfOwnerShipViewModel.setNavigator(this);
        mActivityProofOfOwnerShipBinding = getViewDataBinding();
        Toolbar toolbar = mActivityProofOfOwnerShipBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        checkCameraHardware(getApplicationContext());

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ProofOfOwnerShipActivity.class);
    }

    @Override
    public void exitDialog() {
        uploading.dismiss();
    }

    @Override
    public void nextStep() {

        if(CommonUtils.ObjectIsNotNull(proofOfOwnerShipPic)) {
                uploading = ViewUtils.uploadingDialog(this, this);
                convertBitmap(proofOfOwnerShipPic);
        }else {
            ViewUtils.showDialog(this,"",getResources().getString(R.string.error_no_proof_of_ownership),getResources().getString(R.string.ok), (dialog, which) -> dialog.dismiss());
        }
    }


    private void convertBitmap(Bitmap proofOfOwnerShipPic) {

        UploadRequest uploadRequest = new UploadRequest();

        String image  = ImageUtils.encodeTobase64(proofOfOwnerShipPic);

        uploadRequest.setName("ProofOfOwnerShip.png");

        String insuranceQuote =  getViewModel().getDataManager().getInsuranceQuote();

        if (insuranceQuote != null) {
            Gson gson = new Gson();
            InsuranceQuoteResponce insuranceQuoteResponce = gson.fromJson(insuranceQuote, InsuranceQuoteResponce.class);

            uploadRequest.setContent(image);

            if(isNetworkConnected()) {

                uploadRequest.setClientCode(mProofOfOwnerShipViewModel.getDataManager().getCurrentUserId().toString());

                uploadRequest.setDocName(uploadRequest.getName());

                uploadRequest.setPolNo(insuranceQuoteResponce.getInsuranceQuotation().getQuotCode().toString());

                uploadRequest.setTransType("UW");

                mProofOfOwnerShipViewModel.uploadProofOfOwnerShip(uploadRequest);
            }
        }
    }

    @Override
    public void pictureOptions() {
        mActivityProofOfOwnerShipBinding.Q1QuoteStep1btn.setVisibility(View.GONE);
        mActivityProofOfOwnerShipBinding.proofBottomNav.setVisibility(View.VISIBLE);
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

    @Override
    public void removePhoto() {

        mActivityProofOfOwnerShipBinding.ProofOfOwnerShip.setImageResource(R.drawable.picbg);
        proofOfOwnerShipPic = null;
        mActivityProofOfOwnerShipBinding.removePicture.setVisibility(View.GONE);
        mActivityProofOfOwnerShipBinding.ProofOfOwnerShip.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mActivityProofOfOwnerShipBinding.Q3QuoteStep1.setVisibility(View.VISIBLE);
    }

    private void setProofOfOwnerShip(Bitmap bitmap){
        if(CommonUtils.ObjectIsNotNull(bitmap)) {
            proofOfOwnerShipPic = bitmap;
            mActivityProofOfOwnerShipBinding.removePicture.setVisibility(View.VISIBLE);
            mActivityProofOfOwnerShipBinding.Q3QuoteStep1.setVisibility(View.GONE);
        }
    }

    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityProofOfOwnerShipBinding.takePhoto.setEnabled(false);
            mActivityProofOfOwnerShipBinding.takePhoto.setFocusable(false);
        }
    }

    @Override
    public void takePhoto() {
        Intent camera_intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(camera_intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void dismissUploadDialog() {
        Intent intent = IdentificationsActivity.newIntent(this);
        uploading.dismiss();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if(mActivityProofOfOwnerShipBinding.proofBottomNav.getVisibility() == View.VISIBLE) {
            mActivityProofOfOwnerShipBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
            mActivityProofOfOwnerShipBinding.proofBottomNav.setVisibility(View.GONE);
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

                mActivityProofOfOwnerShipBinding.ProofOfOwnerShip.setImageBitmap(bitmap);
                mActivityProofOfOwnerShipBinding.ProofOfOwnerShip.setScaleType(ImageView.ScaleType.FIT_CENTER);
                setProofOfOwnerShip(bitmap);

                mActivityProofOfOwnerShipBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
                mActivityProofOfOwnerShipBinding.proofBottomNav.setVisibility(View.GONE);

            }else {

                mActivityProofOfOwnerShipBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
                mActivityProofOfOwnerShipBinding.proofBottomNav.setVisibility(View.GONE);

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
                        .into(mActivityProofOfOwnerShipBinding.ProofOfOwnerShip);

                Bitmap bitmap = SiliCompressor.with(getApplicationContext()).getCompressBitmap(picturePath);

                setProofOfOwnerShip(bitmap);

                mActivityProofOfOwnerShipBinding.Q1QuoteStep1btn.setVisibility(View.VISIBLE);
                mActivityProofOfOwnerShipBinding.proofBottomNav.setVisibility(View.GONE);
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
    public boolean isNetworkConnected() {

        if (!super.isNetworkConnected()) {
            handleError(new LocalError(500,getResources().getString(R.string.no_connection)));
        }
        return super.isNetworkConnected();
    }

}
