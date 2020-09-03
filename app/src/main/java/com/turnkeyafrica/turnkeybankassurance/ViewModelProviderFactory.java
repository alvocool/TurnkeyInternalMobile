package com.turnkeyafrica.turnkeybankassurance;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.ui.branchdetails.BranchDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.capturedamageimages.CaptureDamageImagesViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.CardPaymentViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog.CardDatesViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.CertificatePickUpViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog.BranchRegionDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.claimdetails.ClaimDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmandpay.ConfirmAndPayViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.confirmclaimdetails.ConfirmClaimDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.contactus.ContactUsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.CreateClaimViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.CreateRequestViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.DashboardViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.ClaimsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.PolicyViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.editprofile.EditProfileViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.enterclaimdetails.EnterClaimDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.enterrequestdetails.EnterServiceRequestDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.FindValuerViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.locationsdialog.LocationsDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.identification.IdentificationsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.inbox.InboxViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.insuranceproducts.InsuranceProductsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes.InsuranceQuotesViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.login.LoginViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.newrequest.NewRequestQuestionViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.notificationdetails.NotificationDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.otp.OtpViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.PayViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.autopay.DialogMpesaPaymentModeViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment.DialogVerifyManualPaymentViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.paymentsuccessful.PaymentSuccessfulViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.policydetails.PolicyDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.profile.ProfileViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.proofofownership.ProofOfOwnerShipViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.qoutedetails.QuoteDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.register.RegisterViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.servicerequestdetails.ServiceRequestDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.servicerequests.ServiceRequestViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.splash.SplashViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog.TermsAndConditionsDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.valuatordetails.ValuatorDetailsViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.VehicleInsuranceViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.extensionsdialog.ExtensionsDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicledatedialog.VehiclesDateDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog.VehicleInsuranceTypeDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehiclemodelsdialog.VehicleModelsDialogViewModel;
import com.turnkeyafrica.turnkeybankassurance.ui.vehiclevaluation.VehicleValuationViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            return (T) new DashboardViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PolicyViewModel.class)) {
            return (T) new PolicyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ContactUsViewModel.class)) {
            return (T) new ContactUsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ClaimsViewModel.class)) {
            return (T) new ClaimsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LocationsDialogViewModel.class)) {
            return (T) new LocationsDialogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PolicyDetailsViewModel.class)) {
            return (T) new PolicyDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CaptureDamageImagesViewModel.class)) {
            return (T) new CaptureDamageImagesViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(VehiclesDateDialogViewModel.class)) {
            return (T) new VehiclesDateDialogViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(InboxViewModel.class)) {
            return (T) new InboxViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(EnterClaimDetailsViewModel.class)) {
            return (T) new EnterClaimDetailsViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(ServiceRequestDetailsViewModel.class)) {
            return (T) new ServiceRequestDetailsViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(ServiceRequestViewModel.class)) {
            return (T) new ServiceRequestViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
            return (T) new EditProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(VehicleValuationViewModel.class)) {
            return (T) new VehicleValuationViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(InsuranceQuotesViewModel.class)) {
            return (T) new InsuranceQuotesViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(QuoteDetailsViewModel.class)) {
            return (T) new QuoteDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PaymentSuccessfulViewModel.class)) {
            return (T) new PaymentSuccessfulViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PayViewModel.class)) {
            return (T) new PayViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(InsuranceProductsViewModel.class)) {
            return (T) new InsuranceProductsViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(CardDatesViewModel.class)) {
            return (T) new CardDatesViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(IdentificationsViewModel.class)) {
            return (T) new IdentificationsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ConfirmAndPayViewModel.class)) {
            return (T) new ConfirmAndPayViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FindValuerViewModel.class)) {
            return (T) new FindValuerViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(CertificatePickUpViewModel.class)) {
            return (T) new CertificatePickUpViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(BranchRegionDialogViewModel.class)) {
            return (T) new BranchRegionDialogViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(BranchDetailsViewModel.class)) {
            return (T) new BranchDetailsViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(ValuatorDetailsViewModel.class)) {
            return (T) new ValuatorDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OtpViewModel.class)) {
            return (T) new OtpViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(VehicleInsuranceViewModel.class)) {
            return (T) new VehicleInsuranceViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TermsAndConditionsDialogViewModel.class)) {
            return (T) new TermsAndConditionsDialogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(VehicleModelsDialogViewModel.class)) {
            return (T) new VehicleModelsDialogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(VehicleInsuranceTypeDialogViewModel.class)) {
            return (T) new VehicleInsuranceTypeDialogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProofOfOwnerShipViewModel.class)) {
            return (T) new ProofOfOwnerShipViewModel(dataManager, schedulerProvider);
        }  else if (modelClass.isAssignableFrom(ExtensionsDialogViewModel.class)) {
            return (T) new ExtensionsDialogViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(CardPaymentViewModel.class)) {
            return (T) new CardPaymentViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(CreateClaimViewModel.class)) {
            return (T) new CreateClaimViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(CreateRequestViewModel.class)) {
            return (T) new CreateRequestViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(NewRequestQuestionViewModel.class)) {
            return (T) new NewRequestQuestionViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EnterServiceRequestDetailsViewModel.class)) {
            return (T) new EnterServiceRequestDetailsViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(ConfirmClaimDetailsViewModel.class)) {
            return (T) new ConfirmClaimDetailsViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(ClaimDetailsViewModel.class)) {
            return (T) new ClaimDetailsViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DialogMpesaPaymentModeViewModel.class)) {
            return (T) new DialogMpesaPaymentModeViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DialogVerifyManualPaymentViewModel.class)) {
            return (T) new DialogVerifyManualPaymentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(NotificationDetailsViewModel.class)) {
            return (T) new NotificationDetailsViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}