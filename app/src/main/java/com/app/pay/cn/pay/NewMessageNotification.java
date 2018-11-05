package com.app.pay.cn.pay;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


public class NewMessageNotification {

    private static final String NOTIFICATION_TAG = "NewMessage";


    public static void notify(final Context context,
                              final String exampleString, final int number, final Intent intent) {
        final Resources res = context.getResources();

        final String ticker = exampleString;
        final String title = res.getString(
                R.string.new_message_notification_title_template, exampleString);
        final String text = res.getString(
                R.string.new_message_notification_placeholder_text_template, exampleString);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setDefaults(Notification.DEFAULT_ALL)

                .setSmallIcon(R.drawable.ic_stat_new_message)
                .setContentTitle(title)
                .setContentText(text)

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker(ticker)
                .setNumber(number)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(false);

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        }

    }
}
