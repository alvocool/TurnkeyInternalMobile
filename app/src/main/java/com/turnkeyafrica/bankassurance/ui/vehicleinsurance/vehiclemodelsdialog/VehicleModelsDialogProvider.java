package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehiclemodelsdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VehicleModelsDialogProvider {

    @ContributesAndroidInjector
    abstract VehicleModelsDialog provideModelsDialogFactory();
}
