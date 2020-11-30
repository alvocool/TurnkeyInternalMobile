
package com.turnkeyafrica.bankassurance.ui.createclaim;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.turnkeyafrica.bankassurance.ui.createclaim.adapter.ClaimsPolActiveAdapter;

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
