package com.turnkeyafrica.turnkeybankassurance.ui.pay.autopay;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MpesaPaymentModeDialogProvider {

    @ContributesAndroidInjector
    abstract DialogMpesaPaymentMode provideDialogMpesaPaymentModeFactory();
}
