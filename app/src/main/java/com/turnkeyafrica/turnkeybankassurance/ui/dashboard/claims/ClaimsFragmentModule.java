
package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;


@Module
public class ClaimsFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ClaimsFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ClaimsAdapter provideClaimsAdapter() {
        return new ClaimsAdapter();
    }

}
