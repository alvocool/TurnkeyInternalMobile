package com.turnkeyafrica.bankassurance.di.builder;

import com.turnkeyafrica.bankassurance.ui.accountlocked.AccountLockedActivity;
import com.turnkeyafrica.bankassurance.ui.branchdetails.BranchDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.capturedamageimages.CaptureDamageImagesActivity;
import com.turnkeyafrica.bankassurance.ui.cardpayment.CardPaymentActivity;
import com.turnkeyafrica.bankassurance.ui.cardpayment.carddatesdialog.CardDatesDialogProvider;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.CertificatePickUpActivity;
import com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog.BranchRegionDialogProvider;
import com.turnkeyafrica.bankassurance.ui.claimdetails.ClaimDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.confirmandpay.ConfirmAndPayActivity;
import com.turnkeyafrica.bankassurance.ui.confirmclaimdetails.ConfirmClaimDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.contactus.ContactUsActivity;
import com.turnkeyafrica.bankassurance.ui.createclaim.CreateClaimActivity;
import com.turnkeyafrica.bankassurance.ui.createclaim.CreateClaimModule;
import com.turnkeyafrica.bankassurance.ui.createrequest.CreateRequestActivity;
import com.turnkeyafrica.bankassurance.ui.createrequest.CreateRequestModule;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivity;
import com.turnkeyafrica.bankassurance.ui.dashboard.DashboardActivityModule;
import com.turnkeyafrica.bankassurance.ui.dashboard.claims.ClaimsFragmentProvider;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.PolicyFragmentProvider;
import com.turnkeyafrica.bankassurance.ui.editprofile.EditProfileActivity;
import com.turnkeyafrica.bankassurance.ui.enterclaimdetails.EnterClaimDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.entersecurityquestions.EnterSecurityQuestionsActivity;
import com.turnkeyafrica.bankassurance.ui.findvaluer.FindValuerActivity;
import com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog.LocationsDialogProvider;
import com.turnkeyafrica.bankassurance.ui.forgotpassword.ForgotPasswordActivity;
import com.turnkeyafrica.bankassurance.ui.identification.IdentificationsActivity;
import com.turnkeyafrica.bankassurance.ui.inbox.InboxActivity;
import com.turnkeyafrica.bankassurance.ui.insuranceproducts.InsuranceProductsActivity;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.InsuranceQuotesActivity;
import com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter.InsuranceQuotesAdapterModule;
import com.turnkeyafrica.bankassurance.ui.login.LoginActivity;
import com.turnkeyafrica.bankassurance.ui.newrequest.NewRequestQuestionActivity;
import com.turnkeyafrica.bankassurance.ui.notificationdetails.NotificationDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.otp.OtpActivity;
import com.turnkeyafrica.bankassurance.ui.pay.PayActivity;
import com.turnkeyafrica.bankassurance.ui.pay.autopay.MpesaPaymentModeDialogProvider;
import com.turnkeyafrica.bankassurance.ui.pay.manualpayment.VerifyManualPaymentsDialogProvider;
import com.turnkeyafrica.bankassurance.ui.paymentsuccessful.PaymentSuccessfulActivity;
import com.turnkeyafrica.bankassurance.ui.policydetails.PolicyDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.profile.ProfileActivity;
import com.turnkeyafrica.bankassurance.ui.proofofownership.ProofOfOwnerShipActivity;
import com.turnkeyafrica.bankassurance.ui.qoutedetails.QuoteDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.register.RegisterActivity;
import com.turnkeyafrica.bankassurance.ui.resetpassword.ResetPasswordActivity;
import com.turnkeyafrica.bankassurance.ui.securityquestion.SecurityQuestionActivity;
import com.turnkeyafrica.bankassurance.ui.servicerequestdetails.ServiceRequestDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.servicerequests.ServiceRequestActivity;
import com.turnkeyafrica.bankassurance.ui.splash.SplashActivity;
import com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialogProvider;
import com.turnkeyafrica.bankassurance.ui.successstep.SuccessStepActivity;
import com.turnkeyafrica.bankassurance.ui.valuatordetails.ValuatorDetailsActivity;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.VehicleInsuranceActivity;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.extensionsdialog.ExtensionsDialogProvider;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicledatedialog.VehiclesDateDialogProvider;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialogProvider;
import com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.VehicleInsuranceTypeDialogProvider;
import com.turnkeyafrica.bankassurance.ui.vehiclevaluation.VehicleValuationActivity;
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
    abstract AccountLockedActivity bindAccountLockedActivity();

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

    @ContributesAndroidInjector()
    abstract SecurityQuestionActivity bindSecurityQuestionActivity();

    @ContributesAndroidInjector()
    abstract SuccessStepActivity bindSuccessStepActivity();

    @ContributesAndroidInjector()
    abstract ForgotPasswordActivity bindForgotPasswordActivity();

    @ContributesAndroidInjector()
    abstract ResetPasswordActivity bindResetPasswordActivity();

    @ContributesAndroidInjector()
    abstract EnterSecurityQuestionsActivity bindEnterSecurityQuestionsActivity();

    @ContributesAndroidInjector(modules = {
            VehicleModelsDialogProvider.class,
            VehicleInsuranceTypeDialogProvider.class
    })
    abstract EnterServiceRequestDetailsActivity bindEnterServiceRequestDetailsActivity();
}

