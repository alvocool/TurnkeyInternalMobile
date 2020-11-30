package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.extensionsdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ExtensionsDialogProvider {

    @ContributesAndroidInjector
    abstract ExtensionsDialog provideExtensionsDialogFactory();
}
