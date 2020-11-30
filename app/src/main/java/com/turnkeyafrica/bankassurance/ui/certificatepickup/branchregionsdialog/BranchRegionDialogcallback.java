package com.turnkeyafrica.bankassurance.ui.certificatepickup.branchregionsdialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface BranchRegionDialogcallback {

    void close();

    void handleError(LocalError error);

}
