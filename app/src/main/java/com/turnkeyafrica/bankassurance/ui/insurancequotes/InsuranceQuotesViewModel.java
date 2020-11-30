package com.turnkeyafrica.bankassurance.ui.insurancequotes;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ComparisonRequest;
import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.math.BigDecimal;
import java.util.List;

public class InsuranceQuotesViewModel extends BaseViewModel<InsuranceQuotesNavigator> {

    private List<InsuranceQuoteResponce> mInsuranceQuoteResponces;

        public InsuranceQuotesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
            super(dataManager, schedulerProvider);
        }

        public void getInsuranceQuotes(ComparisonRequest comparisonRequest) {

            comparisonRequest.getQuote().setClntCode(BigDecimal.ZERO);

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
