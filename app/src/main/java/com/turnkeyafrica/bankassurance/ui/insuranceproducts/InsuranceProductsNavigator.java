package com.turnkeyafrica.bankassurance.ui.insuranceproducts;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.ProductResponse;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface InsuranceProductsNavigator {

    void handleError(LocalError error);

    void setProducts(List<ProductResponse> webProductResponse,List<ProductResponse> allProductResponse);

    void openPage();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
