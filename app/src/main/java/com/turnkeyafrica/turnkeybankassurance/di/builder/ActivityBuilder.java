package com.turnkeyafrica.turnkeybankassurance.di.builder;

import com.turnkeyafrica.turnkeybankassurance.ui.branchdetails.BranchDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.capturedamageimages.CaptureDamageImagesActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.CardPaymentActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog.CardDatesDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.CertificatePickUpActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog.BranchRegionDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.claimdetails.ClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmandpay.ConfirmAndPayActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmclaimdetails.ConfirmClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.contactus.ContactUsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.CreateClaimActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.CreateClaimModule;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.CreateRequestActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.CreateRequestModule;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardActivityModule;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.ClaimsFragmentProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.PolicyFragmentProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.editprofile.EditProfileActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.EnterClaimDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.locationsdialog.LocationsDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.identification.IdentificationsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.inbox.InboxActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.insuranceproducts.InsuranceProductsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.InsuranceQuotesActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.adapter.InsuranceQuotesAdapterModule;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.newrequest.NewRequestQuestionActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.notificationdetails.NotificationDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.PayActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.autopay.MpesaPaymentModeDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment.VerifyManualPaymentsDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful.PaymentSuccessfulActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.PolicyDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.profile.ProfileActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.proofofownership.ProofOfOwnerShipActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.qoutedetails.QuoteDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.register.RegisterActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.servicerequestdetails.ServiceRequestDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.servicerequests.ServiceRequestActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.splash.SplashActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails.ValuatorDetailsActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.extensionsdialog.ExtensionsDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog.VehiclesDateDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.VehicleInsuranceTypeDialogProvider;
import com.turnkeyafrica.turnkeybankassurance.ui.vehiclevaluation.VehicleValuationActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            TermsAndConditionsDialogProvider.class})
    abstract ConfirmAndPayActivity bindConfirmAndPayActivity();

    @ContributesAndroidInjector(modules = {
            LocationsDialogProvider.class})
    abstract FindValuerActivity bindFindValuatorActivity();

    @ContributesAndroidInjector(modules = {
            BranchRegionDialogProvider.class})
    abstract CertificatePickUpActivity bindCertificatePickUpActivity();

    @ContributesAndroidInjector()
    abstract BranchDetailsActivity bindBranchDetailsActivity();

    @ContributesAndroidInjector()
    abstract IdentificationsActivity bindIdentificationsActivity();

    @ContributesAndroidInjector()
    abstract InsuranceProductsActivity bindInsuranceProductsActivity();

    @ContributesAndroidInjector(modules = {
            MpesaPaymentModeDialogProvider.class,
            VerifyManualPaymentsDialogProvider.class
    })
    abstract PayActivity bindPayActivity();

    @ContributesAndroidInjector(modules = {
            CardDatesDialogProvider.class})
    abstract CardPaymentActivity bindCardPaymentActivity();

    @ContributesAndroidInjector()
    abstract PaymentSuccessfulActivity bindPaymentSuccessfulActivity();

    @ContributesAndroidInjector()
    abstract ValuatorDetailsActivity bindValuatorDetailsActivity();

    @ContributesAndroidInjector(modules = {InsuranceQuotesAdapterModule.class})
    abstract InsuranceQuotesActivity bindInsuranceQuotesActivity();

    @ContributesAndroidInjector()
    abstract VehicleValuationActivity bindVehicleValuationActivity();

    @ContributesAndroidInjector()
    abstract QuoteDetailsActivity bindQuoteDetailsActivity();

    @ContributesAndroidInjector()
    abstract PolicyDetailsActivity bindPolicyDetailsActivity();

    @ContributesAndroidInjector()
    abstract EditProfileActivity bindEditProfileActivity();

    @ContributesAndroidInjector()
    abstract ProfileActivity bindProfileActivity();

    @ContributesAndroidInjector(modules = {
            VehicleInsuranceTypeDialogProvider.class,
            VehicleModelsDialogProvider.class,
            VehiclesDateDialogProvider.class,
            ExtensionsDialogProvider.class})
    abstract VehicleInsuranceActivity bindVehicleInsuranceActivity();

    @ContributesAndroidInjector()
    abstract ProofOfOwnerShipActivity bindProofOfOwnerShipActivity();

    @ContributesAndroidInjector()
    abstract ContactUsActivity bindContactUsActivity();

    @ContributesAndroidInjector(modules = {
            DashboardActivityModule.class,
            PolicyFragmentProvider.class,
            ClaimsFragmentProvider.class})
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector()
    abstract OtpActivity bindOtpActivity();

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {
            TermsAndConditionsDialogProvider.class})
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector()
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector()
    abstract CaptureDamageImagesActivity bindCaptureDamageImagesActivity();

    @ContributesAndroidInjector()
    abstract InboxActivity bindInboxActivity();

    @ContributesAndroidInjector(modules = {
            VehicleModelsDialogProvider.class,
            VehiclesDateDialogProvider.class
    })
    abstract EnterClaimDetailsActivity bindEnterClaimDetailsActivity();

    @ContributesAndroidInjector()
    abstract ServiceRequestDetailsActivity bindServiceRequestDetailsActivity();

    @ContributesAndroidInjector()
    abstract ServiceRequestActivity bindServiceRequestActivity();

    @ContributesAndroidInjector(modules = CreateClaimModule.class)
    abstract CreateClaimActivity bindCreateClaimActivity();

    @ContributesAndroidInjector(modules = CreateRequestModule.class)
    abstract CreateRequestActivity bindCreateRequestActivity();

    @ContributesAndroidInjector()
    abstract ConfirmClaimDetailsActivity bindConfirmClaimDetailsActivity();

    @ContributesAndroidInjector()
    abstract ClaimDetailsActivity bindClaimDetailsActivity();

    @ContributesAndroidInjector()
    abstract NewRequestQuestionActivity bindNewRequestQuestionActivity();

    @ContributesAndroidInjector()
    abstract NotificationDetailsActivity bindNotificationDetailsActivity();

    @ContributesAndroidInjector(modules = {
            VehicleModelsDialogProvider.class,
            VehicleInsuranceTypeDialogProvider.class
    })
    abstract EnterServiceRequestDetailsActivity bindEnterServiceRequestDetailsActivity();
}

