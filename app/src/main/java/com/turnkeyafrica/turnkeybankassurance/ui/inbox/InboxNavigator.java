package com.turnkeyafrica.turnkeybankassurance.ui.inbox;

import android.app.AlertDialog;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.NotificationsResponse;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.util.List;

public interface InboxNavigator {

    void handleError(LocalError error);

    AlertDialog openLoading();

    void closeLoading(AlertDialog alertDialog);

    void setAdapter(List<NotificationsResponse> responses);
}
