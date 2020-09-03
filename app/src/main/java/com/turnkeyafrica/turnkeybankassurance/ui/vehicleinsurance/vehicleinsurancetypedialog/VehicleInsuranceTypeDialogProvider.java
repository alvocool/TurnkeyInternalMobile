package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.vehicleinsurancetypedialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VehicleInsuranceTypeDialogProvider {

    @ContributesAndroidInjector
    abstract VehicleInsuranceTypeDialog provideVehicleInsuranceTypeDialogFactory();
}
