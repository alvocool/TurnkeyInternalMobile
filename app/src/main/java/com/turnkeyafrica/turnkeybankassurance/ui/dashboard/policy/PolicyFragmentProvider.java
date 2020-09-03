package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class PolicyFragmentProvider {

    @ContributesAndroidInjector(modules = PolicyFragmentModule.class)
    abstract PolicyFragment providePolicyFragmentFactory();
}
