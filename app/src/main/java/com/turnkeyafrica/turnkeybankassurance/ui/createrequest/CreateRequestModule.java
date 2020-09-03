
package com.turnkeyafrica.turnkeybankassurance.ui.createrequest;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.turnkeyafrica.turnkeybankassurance.ui.createrequest.adapter.RequestPolActiveAdapter;
import dagger.Module;
import dagger.Provides;


@Module
public class CreateRequestModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CreateRequestActivity createRequestActivity) {
        return new LinearLayoutManager(createRequestActivity);
    }

    @Provides
    RequestPolActiveAdapter providesRequestPolActiveAdapter() {
        return new RequestPolActiveAdapter();
    }

}
