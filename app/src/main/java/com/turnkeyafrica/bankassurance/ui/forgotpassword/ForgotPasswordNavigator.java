package com.turnkeyafrica.bankassurance.ui.forgotpassword;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface ForgotPasswordNavigator {

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void proceed();

    void setQuestion(SecurityQuestions response);

    void handleError(LocalError error);
}
