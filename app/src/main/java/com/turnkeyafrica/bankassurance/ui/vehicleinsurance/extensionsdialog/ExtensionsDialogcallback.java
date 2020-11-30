package com.turnkeyafrica.bankassurance.ui.vehicleinsurance.extensionsdialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ExtensionsDialogcallback {

    void close();

    void handleError(LocalError error);

    void setLimit();
}
