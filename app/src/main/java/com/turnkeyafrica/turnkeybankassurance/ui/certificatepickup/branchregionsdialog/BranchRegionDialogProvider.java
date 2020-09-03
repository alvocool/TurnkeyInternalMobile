package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BranchRegionDialogProvider {

    @ContributesAndroidInjector
    abstract BranchRegionDialog providelocationsDialogFactory();
}
