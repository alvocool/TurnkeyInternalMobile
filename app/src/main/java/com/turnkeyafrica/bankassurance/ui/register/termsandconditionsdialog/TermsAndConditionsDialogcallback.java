package com.turnkeyafrica.bankassurance.ui.register.termsandconditionsdialog;

import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface TermsAndConditionsDialogcallback {

    void close();

    void handleError(LocalError error);


}
