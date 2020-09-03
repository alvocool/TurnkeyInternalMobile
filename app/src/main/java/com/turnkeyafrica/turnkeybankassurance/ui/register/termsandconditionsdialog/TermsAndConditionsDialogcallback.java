package com.turnkeyafrica.turnkeybankassurance.ui.register.termsandconditionsdialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

public interface TermsAndConditionsDialogcallback {

    void close();

    void handleError(LocalError error);


}
