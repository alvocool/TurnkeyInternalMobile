package com.turnkeyafrica.turnkeybankassurance.ui.vehicleinsurance.extensionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface ExtensionsDialogcallback {

    void close();

    void handleError(LocalError error);

    void setLimit();
}
