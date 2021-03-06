package com.turnkeyafrica.bankassurance.ui.insuranceproducts;


import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.DataManager;
import com.turnkeyafrica.bankassurance.data.model.api.ProductResponse;
import com.turnkeyafrica.bankassurance.ui.base.BaseViewModel;
import com.turnkeyafrica.bankassurance.utils.ErrorBase;
import com.turnkeyafrica.bankassurance.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class InsuranceProductsViewModel extends BaseViewModel<InsuranceProductsNavigator> {

    public InsuranceProductsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    private List<ProductResponse> webProducts = new ArrayList<>();
    private List<ProductResponse> otherProducts = new ArrayList<>();

    private void getAllWebProducts() {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllProducts("Y")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(productResponse -> {
                    webProducts = productResponse;
                    setAdapter();
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    setAdapter();
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    public void getAllProducts(String option) {
        AlertDialog alertDialog = getNavigator().openLoading();
        getCompositeDisposable().add(getDataManager()
                .getAllProducts(option)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(productResponse -> {

                    otherProducts = productResponse;
                    getAllWebProducts();
                    getNavigator().closeLoading(alertDialog);
                }, throwable -> {
                    getNavigator().closeLoading(alertDialog);
                    getNavigator().handleError(ErrorBase.Error(throwable));
                }));
    }

    private void setAdapter() {

        getNavigator().setProducts(webProducts,otherProducts);
    }

    public void openPage(){
        getNavigator().openPage();
    }
}
