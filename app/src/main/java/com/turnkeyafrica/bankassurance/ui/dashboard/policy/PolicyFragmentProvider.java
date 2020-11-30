package com.turnkeyafrica.bankassurance.ui.dashboard.policy;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class PolicyFragmentProvider {

    @ContributesAndroidInjector(modules = PolicyFragmentModule.class)
    abstract PolicyFragment providePolicyFragmentFactory();
}
