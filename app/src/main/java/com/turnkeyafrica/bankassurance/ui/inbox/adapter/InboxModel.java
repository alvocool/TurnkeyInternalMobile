package com.turnkeyafrica.bankassurance.ui.inbox.adapter;

import com.turnkeyafrica.bankassurance.data.model.api.NotificationsResponse;

public class InboxModel {

        public static final int OLD_MESSAGE=1;
        public static final int NEW_MESSAGE=0;

        public int type;
        public boolean display;
        public NotificationsResponse mNotificationsResponse;

        public InboxModel(int type, NotificationsResponse notificationsResponse,boolean display)
        {
            this.type=type;
            this.mNotificationsResponse=notificationsResponse;
            this.display = display;
        }
    }


