package com.turnkeyafrica.bankassurance.ui.dashboard;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    @Provides
    DashboardPagerAdapter provideDashboardPagerAdapter(DashboardActivity activity) {
        return new DashboardPagerAdapter(activity.getSupportFragmentManager());
    }

}
