package com.turnkeyafrica.bankassurance.ui.certificatepickup;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.bankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface CertificatePickUpNavigator {

    void handleError(LocalError error);

    void setBranchRegions(List<BranchRegionResponce> brancheRegions);

    void setBranches(List<BranchResponce> branches);

    void loadBranches();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
