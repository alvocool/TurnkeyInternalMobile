package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchRegionResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.BranchResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface CertificatePickUpNavigator {

    void handleError(LocalError error);

    void setBranchRegions(List<BranchRegionResponce> brancheRegions);

    void setBranches(List<BranchResponce> branches);

    void loadBranches();

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);
}
