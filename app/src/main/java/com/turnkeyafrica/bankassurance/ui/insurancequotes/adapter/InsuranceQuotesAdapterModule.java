package com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.turnkeyafrica.bankassurance.ui.insurancequotes.InsuranceQuotesActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class InsuranceQuotesAdapterModule {

    @Provides
    InsuranceQuotesAdapter provideInsuranceQuotesAdapter() {
        return new InsuranceQuotesAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(InsuranceQuotesActivity myInsuranceQuotesActivity) {
        return new LinearLayoutManager(myInsuranceQuotesActivity);
    }

}
