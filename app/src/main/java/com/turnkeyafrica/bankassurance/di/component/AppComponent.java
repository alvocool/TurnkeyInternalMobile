package com.turnkeyafrica.bankassurance.di.component;

import android.app.Application;

import com.turnkeyafrica.bankassurance.TurnkeyBankAssuranceApp;
import com.turnkeyafrica.bankassurance.di.builder.ActivityBuilder;
import com.turnkeyafrica.bankassurance.di.module.AppModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(TurnkeyBankAssuranceApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
