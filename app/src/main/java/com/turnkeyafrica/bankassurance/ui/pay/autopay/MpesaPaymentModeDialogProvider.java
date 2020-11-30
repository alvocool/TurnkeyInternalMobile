package com.turnkeyafrica.bankassurance.ui.pay.autopay;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MpesaPaymentModeDialogProvider {

    @ContributesAndroidInjector
    abstract DialogMpesaPaymentMode provideDialogMpesaPaymentModeFactory();
}
