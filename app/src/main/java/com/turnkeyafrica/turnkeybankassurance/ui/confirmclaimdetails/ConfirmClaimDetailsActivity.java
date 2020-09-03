package com.turnkeyafrica.turnkeybankassurance.ui.confirmclaimdetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.turnkeyafrica.turnkeybankassurance.BR;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimDocumentsResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ClaimsDetailsObject;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.SendClaimDetailsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;
import com.turnkeyafrica.turnkeybankassurance.databinding.ActivityConfirmClaimDetailsBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.utils.CommonUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ImageUtils;
import com.turnkeyafrica.turnkeybankassurance.utils.ViewUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ConfirmClaimDetailsActivity extends BaseActivity<ActivityConfirmClaimDetailsBinding, ConfirmClaimDetailsViewModel> implements ConfirmClaimDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityConfirmClaimDetailsBinding mActivityConfirmClaimDetailsBinding;
    private ConfirmClaimDetailsViewModel mConfirmClaimDetailsViewModel;
    private PolicyResponce policyResponse;
    private ClaimsDetailsObject claimsDetail;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_claim_details;
    }

    @Override
    public ConfirmClaimDetailsViewModel getViewModel() {
        mConfirmClaimDetailsViewModel = new ViewModelProvider(getViewModelStore(), factory).get(ConfirmClaimDetailsViewModel.class);
        return mConfirmClaimDetailsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityConfirmClaimDetailsBinding = getViewDataBinding();
        mConfirmClaimDetailsViewModel.setNavigator(this);
        Toolbar toolbar = mActivityConfirmClaimDetailsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setUpView();
    }

    private void setUpView() {

        String polResponse = getViewModel().getDataManager().getPolicyResponse();

        String claimsDetailObject = getViewModel().getDataManager().getClaimsDetailsObject();

        if(!CommonUtils.StringIsEmpty(polResponse)){
            policyResponse = new Gson().fromJson(polResponse, PolicyResponce.class);
        }
        if(!CommonUtils.StringIsEmpty(claimsDetailObject)){
            claimsDetail = new Gson().fromJson(claimsDetailObject, ClaimsDetailsObject.class);
            setImages(claimsDetail);
        }

        if(!CommonUtils.StringIsEmpty(claimsDetailObject) && !CommonUtils.StringIsEmpty(polResponse)){
            getViewModel().setUpDetails(policyResponse,claimsDetail);
        }

    }

    private void setImages(ClaimsDetailsObject claimsDetail) {

        if(!claimsDetail.getImages().isEmpty()) {

            if (claimsDetail.getImages().size() == 5) {
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(2))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic1);
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(3))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic2);
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(4))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic3);
            } else if (claimsDetail.getImages().size() == 4) {
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(3))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic1);
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(2))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic2);

            } else if (claimsDetail.getImages().size() == 3) {
                Glide
                        .with(this)
                        .load(claimsDetail.getImages().get(2))
                        .centerCrop()
                        .into(mActivityConfirmClaimDetailsBinding.damagePic1);
            } else {
                handleError(new LocalError(0, getResources().getString(R.string.alert_upload_no_incident_images)));
                mActivityConfirmClaimDetailsBinding.incidentPicWrapper.setVisibility(View.GONE);
                mActivityConfirmClaimDetailsBinding.incidentPhotos.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public AlertDialog openLoading() {
        return ViewUtils.isLoadingDialog(this, this,"Loading");
    }

    @Override
    public void closeLoading(AlertDialog alertDialog) {
        if(alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void submitClaim() {
        if(mActivityConfirmClaimDetailsBinding.acceptDetails.isChecked()){

            SendClaimDetailsResponse requestResponse = new SendClaimDetailsResponse();

            requestResponse.setIncidentDesc(claimsDetail.getDescription());

            requestResponse.setLossDate(claimsDetail.getIncidentDate());

            List<ClaimDocumentsResponce> documentsResponces = new ArrayList<>();

            if(!claimsDetail.getImages().isEmpty()){

                int pictureNo = 1;

                for(String image:claimsDetail.getImages()) {

                    try {

                       Bitmap imageBitMap = SiliCompressor.with(getApplicationContext()).getCompressBitmap(image);

                       if(pictureNo == 1){

                           documentsResponces.add(
                                   new ClaimDocumentsResponce(ImageUtils.encodeTobase64(imageBitMap),
                                           "Abstract Photo"));
                       }else if(pictureNo == 2){
                           documentsResponces.add(
                                   new ClaimDocumentsResponce(ImageUtils.encodeTobase64(imageBitMap),
                                           "Drivers Licence"));
                       }else {
                           documentsResponces.add(
                                   new ClaimDocumentsResponce(ImageUtils.encodeTobase64(imageBitMap),
                                           "Incident Picture " + pictureNo));
                       }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ++pictureNo;

                }

            }

            requestResponse.setRiskId(claimsDetail.getPropertyId());

            requestResponse.setPolicyNumber(policyResponse.getPolicyNo());

            requestResponse.setPolBatchNo(policyResponse.getBatchNo());

            requestResponse.setFiles(documentsResponces);

            mConfirmClaimDetailsViewModel.submitClaimDetails(requestResponse);
        }else {
            handleError(new LocalError(0,getResources().getString(R.string.error_accept_details)));
        }
    }

    @Override
    public void openDashboard() {
        Intent intent = DashboardActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ConfirmClaimDetailsActivity.class);
    }

}
