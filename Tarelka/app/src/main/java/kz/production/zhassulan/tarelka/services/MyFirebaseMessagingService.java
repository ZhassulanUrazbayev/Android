package kz.production.kuanysh.tarelka.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import kz.production.kuanysh.tarelka.R;
import kz.production.kuanysh.tarelka.app.Config;
import kz.production.kuanysh.tarelka.ui.activities.mainactivity.MainActivity;
import kz.production.kuanysh.tarelka.utils.AppConstants;
import kz.production.kuanysh.tarelka.utils.NotificationUtils;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by User on 13.07.2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "adminMessageChannel";


    private NotificationUtils notificationUtils;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("myTag", "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("myTag", "Data Payload: " + remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject(remoteMessage.getData());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e("myTag", "Exception: " + e.getMessage());
            }
        }

    }
    private void handleDataMessage(JSONObject data) {
        Log.e("myTag", "push json: " + data.toString());
        try {
            String message = data.getString("body");
            String title = data.getString("title");

            Log.e("myTag", "title: " + title);
            Log.e("myTag", "message: " + message);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra(AppConstants.PUSH_KEY, message);
                LocalBroadcastManager.getInstance(MyFirebaseMessagingService.this).sendBroadcast(pushNotification);
            } else {
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                resultIntent.putExtra(AppConstants.PUSH_KEY, message);
                resultIntent.putExtra(AppConstants.PUSH_ACTION, AppConstants.PUSH_ACTION);
                // app is in background, show the notification in notification tray
                showNotificationMessage(MyFirebaseMessagingService.this,title,message,resultIntent);

            }
        } catch (JSONException e) {
            Log.e("myTag", "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e("myTag", "Exception: " + e.getMessage());
        }
    }
    private void showNotificationMessage(Context context, String title, String message, Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        MyFirebaseMessagingService.this,
                        0,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = 0x008000;
            builder.setColor(getResources().getColor(R.color.blueNotification));
        }

        Notification notification = builder
                .setContentTitle("Tarelka - Admin")
                .setContentText("У вас непрочитанное сообщ.")
                .setTicker("У вас новое сообщение")
                .setSound(defaultSoundUri)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.mipmap.ic_noticon).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("OREO", "showNotificationMessage:background Orea channel setted");

            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("OREO", "showNotificationMessage:background created notification");

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(11, notification);

    }

}
