package com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CardDatesDialogProvider {

    @ContributesAndroidInjector
    abstract CardDatesDialog provideCardDatesDialogFactory();
}
