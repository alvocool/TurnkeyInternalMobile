package com.turnkeyafrica.turnkeybankassurance.ui.capturedamageimages;


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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityCaptureDamageImagesBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmclaimdetails.ConfirmClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CaptureDamageImagesActivity extends BaseActivity<ActivityCaptureDamageImagesBinding, CaptureDamageImagesViewModel> implements CaptureDamageImagesNavigator{
 
    @Inject
    ViewModelProviderFactory factory;
    private CaptureDamageImagesViewModel mCaptureDamageImagesViewModel;
    ActivityCaptureDamageImagesBinding mActivityCaptureDamageImagesBinding;

    int imageOption;

    private final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1997;

    private final int CAPTURE_IMAGE_GALLERY_ACTIVITY_REQUEST_CODE = 1845;

    private String damagePic1;

    private String damagePic2;

    private String damagePic3;

    private ClaimsDetailsObject claimsDetail;

    @Override
        public int getBindingVariable() {
            return BR.viewModel;
        }

        @Override
        public int getLayoutId() {
           return R.layout.activity_capture_damage_images;
        }

        @Override
        public CaptureDamageImagesViewModel getViewModel() {
             mCaptureDamageImagesViewModel = new ViewModelProvider(getViewModelStore(),factory).get(CaptureDamageImagesViewModel.class);
             return mCaptureDamageImagesViewModel;
        }

    public static Intent newIntent(Context context) {
        return new Intent(context, CaptureDamageImagesActivity.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
    public void onBackPressed() {

        if(mActivityCaptureDamageImagesBinding.requestBottomNav.getVisibility() == View.VISIBLE) {
            mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
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
        protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
            mCaptureDamageImagesViewModel.setNavigator(this);
            mActivityCaptureDamageImagesBinding =getViewDataBinding();
            Toolbar toolbar = mActivityCaptureDamageImagesBinding.toolbar;
            setSupportActionBar(toolbar);
            checkCameraHardware(getApplicationContext());
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }


            setUpView();

        }

    private void setUpView() {

        String claimsDetailObject = getViewModel().getDataManager().getClaimsDetailsObject();

        if(!CommonUtils.StringIsEmpty(claimsDetailObject)){
            claimsDetail = new Gson().fromJson(claimsDetailObject, ClaimsDetailsObject.class);
        }
    }

    @Override
    public void showPictureOptions(int option) {
        mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.VISIBLE);
        imageOption = option;
        hideKeyboard();
    }

    @Override
    public void removePhoto(int option) {

        if (option == 1) {
            mActivityCaptureDamageImagesBinding.damagePic1.setImageResource(R.drawable.picbg);
                damagePic1 =  null;
                mActivityCaptureDamageImagesBinding.removePicture1.setVisibility(View.GONE);
                mActivityCaptureDamageImagesBinding.damagePic1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                mActivityCaptureDamageImagesBinding.damagePicInfo1.setVisibility(View.VISIBLE);
        } else if (option == 2) {
            mActivityCaptureDamageImagesBinding.damagePic2.setImageResource(R.drawable.picbg);
                damagePic2 = null;
                mActivityCaptureDamageImagesBinding.removePicture2.setVisibility(View.GONE);
                mActivityCaptureDamageImagesBinding.damagePic2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                mActivityCaptureDamageImagesBinding.damagePicInfo2.setVisibility(View.VISIBLE);
            } else {
                mActivityCaptureDamageImagesBinding.damagePic3.setImageResource(R.drawable.picbg);
                   damagePic3 = null;
                    mActivityCaptureDamageImagesBinding.removePicture3.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.damagePic3.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo3.setVisibility(View.VISIBLE);
            }
        }

    private void checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            mActivityCaptureDamageImagesBinding.takePhoto.setEnabled(false);
            mActivityCaptureDamageImagesBinding.takePhoto.setFocusable(false);
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
            damagePic1 = image.getAbsolutePath();
        }else if(imageOption == 2){
            damagePic2 = image.getAbsolutePath();
        }else{
            damagePic3 = image.getAbsolutePath();
        }
        return image;
    }

/*    @Override
    public void onBackPressed() {

        if(mActivityCaptureDamageImagesBinding.requestBottomNav.getVisibility() == View.VISIBLE) {
            mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }

    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                if(imageOption == 1) {
                    Glide
                            .with(this)
                            .load(damagePic1)
                            .centerCrop()
                            .into(mActivityCaptureDamageImagesBinding.damagePic1);

                    mActivityCaptureDamageImagesBinding.removePicture1.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo1.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);

                }else if(imageOption == 2){
                    Glide
                            .with(this)
                            .load(damagePic2)
                            .centerCrop()
                            .into(mActivityCaptureDamageImagesBinding.damagePic2);

                    mActivityCaptureDamageImagesBinding.removePicture2.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo2.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
                }
                else if(imageOption == 3) {
                    Glide
                            .with(this)
                            .load(damagePic3)
                            .centerCrop()
                            .into(mActivityCaptureDamageImagesBinding.damagePic3);

                    mActivityCaptureDamageImagesBinding.removePicture3.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo3.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
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
                            .into(mActivityCaptureDamageImagesBinding.damagePic1);

                    damagePic1 = picturePath;

                    mActivityCaptureDamageImagesBinding.removePicture1.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo1.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
                }else if(imageOption == 2){
                    Glide
                            .with(this)
                            .load(picturePath)
                            .centerCrop()
                            .into(mActivityCaptureDamageImagesBinding.damagePic2);

                    damagePic2 = picturePath;

                    mActivityCaptureDamageImagesBinding.removePicture2.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo2.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
                }else {
                    Glide
                            .with(this)
                            .load(picturePath)
                            .centerCrop()
                            .into(mActivityCaptureDamageImagesBinding.damagePic3);

                    damagePic3 = picturePath;


                    mActivityCaptureDamageImagesBinding.removePicture3.setVisibility(View.VISIBLE);
                    mActivityCaptureDamageImagesBinding.damagePicInfo3.setVisibility(View.GONE);
                    mActivityCaptureDamageImagesBinding.requestBottomNav.setVisibility(View.GONE);
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

    private void openClaimsVerificationScreen(boolean state){

        if(state){
            Intent intent = ConfirmClaimDetailsActivity.newIntent(this);
            getViewModel().getDataManager().setClaimsDetailsObject(claimsDetail);
            startActivity(intent);
        }
    }
    @Override
    public void proceed() {

        boolean state = true;

        if(CommonUtils.StringIsEmpty(damagePic1) && CommonUtils.StringIsEmpty(damagePic2) && CommonUtils.StringIsEmpty(damagePic3)){
            handleError(new LocalError(0, getResources().getString(R.string.error_damage_photos)));
            state = false;
        }else {

            if(claimsDetail.getImages().size() > 2){
                List<String> refresh = new ArrayList<>();
                refresh.add(claimsDetail.getImages().get(0));
                refresh.add(claimsDetail.getImages().get(1));
                claimsDetail.getImages().clear();
                claimsDetail.setImages(refresh);
            }

            if(!CommonUtils.StringIsEmpty(damagePic1)) {
                claimsDetail.getImages().add(damagePic1);
            }
            if(!CommonUtils.StringIsEmpty(damagePic2)){
                claimsDetail.getImages().add(damagePic2);
            }
            if(!CommonUtils.StringIsEmpty(damagePic3)){
                claimsDetail.getImages().add(damagePic3);
            }

        }

        openClaimsVerificationScreen(state);
    }
}
