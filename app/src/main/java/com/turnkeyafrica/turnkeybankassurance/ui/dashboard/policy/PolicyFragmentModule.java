
package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;


@Module
public class PolicyFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PolicyFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    PolicyAdapter providePolicyAdapter() {
        return new PolicyAdapter();
    }

}
