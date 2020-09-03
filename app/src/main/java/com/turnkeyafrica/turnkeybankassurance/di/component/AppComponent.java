package com.turnkeyafrica.turnkeybankassurance.di.component;

import android.app.Application;

import com.turnkeyafrica.turnkeybankassurance.BankAssuranceApp;
import com.turnkeyafrica.turnkeybankassurance.di.builder.ActivityBuilder;
import com.turnkeyafrica.turnkeybankassurance.di.module.AppModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(BankAssuranceApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
