package com.turnkeyafrica.turnkeybankassurance.ui.dashboard;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    @Provides
    DashboardPagerAdapter provideDashboardPagerAdapter(DashboardActivity activity) {
        return new DashboardPagerAdapter(activity.getSupportFragmentManager());
    }

}
