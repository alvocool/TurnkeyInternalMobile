package com.turnkeyafrica.turnkeybankassurance.di.module;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.turnkeyafrica.turnkeybankassurance.BuildConfig;
import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.data.AppDataManager;
import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.local.prefs.AppPreferencesHelper;
import com.turnkeyafrica.turnkeybankassurance.data.local.prefs.PreferencesHelper;
import com.turnkeyafrica.turnkeybankassurance.data.remote.ApiHeader;
import com.turnkeyafrica.turnkeybankassurance.data.remote.ApiHelper;
import com.turnkeyafrica.turnkeybankassurance.data.remote.AppApiHelper;
import com.turnkeyafrica.turnkeybankassurance.di.PreferenceInfo;
import com.turnkeyafrica.turnkeybankassurance.utils.AppConstants;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.AppSchedulerProvider;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedLoginApiHeader provideProtectedLoginApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedLoginApiHeader(
                preferencesHelper.getUUID(),
                "MOBILE",
                BuildConfig.VERSION_NAME);
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedUploadApiHeader provideProtectedUploadApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedUploadApiHeader(
        "",
                "",
                "",
                ""
        );

    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
