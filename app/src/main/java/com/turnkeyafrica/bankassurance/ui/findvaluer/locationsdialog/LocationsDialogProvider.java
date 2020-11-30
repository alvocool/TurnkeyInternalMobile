package com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LocationsDialogProvider {

    @ContributesAndroidInjector
    abstract LocationsDialog providelocationsDialogFactory();
}
