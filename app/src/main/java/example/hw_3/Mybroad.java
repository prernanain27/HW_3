package example.hw_3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by prernaa on 3/12/2017.
 */

public class Mybroad extends BroadcastReceiver{
    public void onReceive(Context context, Intent intent) {

             /*Sent when the user is present after
         * device wakes up (e.g when the keyguard is gone)
         * */
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
           // Toast.makeText(context,"Broadcast Receiver !!!",Toast.LENGTH_SHORT).show();
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setContentIntent(contentIntent)
                    .setSmallIcon(android.R.drawable.stat_notify_error)
                    .setContentTitle("Notify user")
                    .setContentText("Do you want to Draw");
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

        }
        /*Device is shutting down. This is broadcast when the device
         * is being shut down (completely turned off, not sleeping)
         * */
        else if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {

        }

    }
}
