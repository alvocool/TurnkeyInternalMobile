package com.turnkeyafrica.turnkeybankassurance.ui.pay.manualpayment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VerifyManualPaymentsDialogProvider {

    @ContributesAndroidInjector
    abstract DialogVerifyManualPayment provideDialogVerifyManualPaymentFactory();
}
