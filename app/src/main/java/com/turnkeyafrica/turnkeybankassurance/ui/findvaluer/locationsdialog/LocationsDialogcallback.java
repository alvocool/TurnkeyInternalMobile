package com.turnkeyafrica.turnkeybankassurance.ui.findvaluer.locationsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface LocationsDialogcallback {

    void close();

    void handleError(LocalError error);

}
