package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.vehicledatedialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VehiclesDateDialogProvider {

    @ContributesAndroidInjector
    abstract VehiclesDateDialog provideVehiclesDateDialogFactory();
}
