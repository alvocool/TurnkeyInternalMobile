package com.turnkeyafrica.bankassurance.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ui.inbox.InboxActivity;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;

import org.jetbrains.annotations.NotNull;


public class BankAssuranceMessaging extends FirebaseMessagingService{

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {

            if(CommonUtils.ObjectIsNotNull(remoteMessage.getNotification())){
                sendNotification(remoteMessage.getNotification().getBody()
                        ,remoteMessage.getNotification().getTitle());
            }

        }

        @Override
        public void onNewToken(@NotNull String token) {


        }

        private void sendNotification(String messageBody,String title) {
            Intent intent = new Intent(this, InboxActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                    PendingIntent.FLAG_ONE_SHOT);

            String channelId = getResources().getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle(title)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        getString(R.string.default_notification_channel_id),
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(0 , notificationBuilder.build());
        }

}
