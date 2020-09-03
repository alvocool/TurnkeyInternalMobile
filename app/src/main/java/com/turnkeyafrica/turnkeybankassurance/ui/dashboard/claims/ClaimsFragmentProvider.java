package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ClaimsFragmentProvider {

    @ContributesAndroidInjector(modules = ClaimsFragmentModule.class)
    abstract ClaimsFragment provideClaimsFragmentFactory();
}
