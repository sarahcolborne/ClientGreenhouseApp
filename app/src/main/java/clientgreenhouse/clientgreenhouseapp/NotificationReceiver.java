package clientgreenhouse.clientgreenhouseapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hugh on 2017-07-06.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final int NOTIFICATIONS_INTERVAL_IN_HOURS = 2;

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        Intent serviceIntent = null;
        serviceIntent = NotificationService.createIntentStartNotification(context);
        if (action.equals("START_SERVICE")) {
            context.startService(serviceIntent);
        }
        else {
            context.stopService(serviceIntent);
        }
    }
    //checks if alarm is running
    public static boolean alarmRunning(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        boolean alarmUp = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_NO_CREATE) != null;
        return alarmUp;
    }
    //setup alarm for the notifications
    public static void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                getTriggerAt(new Date(2015,3,7)),
                NOTIFICATIONS_INTERVAL_IN_HOURS * AlarmManager.INTERVAL_HOUR,
                alarmIntent);
        intent.setAction("START_SERVICE");
        context.sendBroadcast(intent);
    }
    //delete alarm
    public static void deleteAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        intent.setAction("ACTION_STOP");
        context.sendBroadcast(intent);
        alarmManager.cancel(alarmIntent);
        alarmIntent.cancel();
    }
    //set time for alarm
    private static long getTriggerAt(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        return calendar.getTimeInMillis();
    }

}
