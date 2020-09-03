package com.turnkeyafrica.turnkeybankassurance.ui.certificatepickup.branchregionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface BranchRegionDialogcallback {

    void close();

    void handleError(LocalError error);

}
