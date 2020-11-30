package com.turnkeyafrica.bankassurance.ui.inbox;

import android.app.AlertDialog;

import com.turnkeyafrica.bankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;

import java.util.List;

public interface InboxNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setAdapter(List<NotificationsResponse> responses);
}
