package com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TermsAndConditionsDialogProvider {

    @ContributesAndroidInjector
    abstract TermsAndConditionsDialog provideTermsAndConditionsDialogFactory();
}
