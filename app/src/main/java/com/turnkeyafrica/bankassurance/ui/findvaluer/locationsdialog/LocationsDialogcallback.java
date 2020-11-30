package com.turnkeyafrica.bankassurance.ui.findvaluer.locationsdialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface LocationsDialogcallback {

    void close();

    void handleError(LocalError error);

}
