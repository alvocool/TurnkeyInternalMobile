package com.turnkeyafrica.bankassurance.ui.findvaluer;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.ValuersResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface FindValuatorNavigator {

    void handleError(LocalError error);

    void setLocations(List<String> locations);

    void setValuers(List<ValuersResponce> valuersResponces);

    void loadLocations();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
