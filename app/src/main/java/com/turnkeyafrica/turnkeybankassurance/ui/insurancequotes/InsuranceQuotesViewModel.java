package com.turnkeyafrica.turnkeybankassurance.ui.insurancequotes;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.DataManager;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.turnkeybankassurance.utils.ErrorBase;
import com.turnkeyafrica.turnkeybankassurance.utils.rx.SchedulerProvider;

import java.util.List;

public class InsuranceQuotesViewModel extends BaseViewModel<InsuranceQuotesNavigator> {

    private List<InsuranceQuoteResponce> mInsuranceQuoteResponces;

        public InsuranceQuotesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
            super(dataManager, schedulerProvider);
        }

        public void getInsuranceQuotes(ComparisonRequest comparisonRequest) {

            AlertDialog alertDialog = getNavigator().openLoading();
            getCompositeDisposable().add(getDataManager()
                    .getInsuranceQuotes(comparisonRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(responces -> {
                        if (responces != null) {
                            mInsuranceQuoteResponces = responces;
                        }
                        setAdapter(mInsuranceQuoteResponces);
                        getNavigator().closeLoading(alertDialog);
                    }, throwable -> {
                        getNavigator().closeLoading(alertDialog);
                        getNavigator().handleError(ErrorBase.Error(throwable));
                    }));
        }


        private void setAdapter(List<InsuranceQuoteResponce> responces) {

            getNavigator().setAdapter(responces);
        }
}
