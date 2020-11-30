package com.turnkeyafrica.bankassurance.ui.entersecurityquestions;

import android.app.AlertDialog;

import java.util.List;

import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

public interface EnterSecurityQuestionsNavigator {

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void handleError(LocalError error);

    void openSuccess(int i);

    void register();

    void setQuestions(List<SecurityQuestions> response);

    void setOpinion(int option);
}
