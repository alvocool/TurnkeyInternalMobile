
package com.turnkeyafrica.turnkeybankassurance.ui.createclaim;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.turnkeyafrica.turnkeybankassurance.ui.createclaim.adapter.ClaimsPolActiveAdapter;

import dagger.Module;
import dagger.Provides;


@Module
public class CreateClaimModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CreateClaimActivity createClaimActivity) {
        return new LinearLayoutManager(createClaimActivity);
    }

    @Provides
    ClaimsPolActiveAdapter provideClaimsPolActiveAdapter() {
        return new ClaimsPolActiveAdapter();
    }

}
