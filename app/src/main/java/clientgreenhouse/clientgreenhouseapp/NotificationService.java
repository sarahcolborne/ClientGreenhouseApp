package clientgreenhouse.clientgreenhouseapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;

import static clientgreenhouse.clientgreenhouseapp.R.id.humidityCurrLowVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.humidityCurrUpperVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.humidityValue;
import static clientgreenhouse.clientgreenhouseapp.R.id.lightCurrLowVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.lightCurrUpperVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.lightValue;
import static clientgreenhouse.clientgreenhouseapp.R.id.tempCurrLowVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.tempCurrUpperVal;
import static clientgreenhouse.clientgreenhouseapp.R.id.temperatureValue;

/**
 * Created by Hugh on 2017-07-06.
 */

public class NotificationService extends IntentService {

    public static final String ACTION_START = "ACTION_START";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(){ super("nothing");}

    public NotificationService(String name) {
        super(name);
    }

    @Override
    public void onHandleIntent(Intent intent){
        String action = intent.getAction();
        if (ACTION_START.equals(action)) {
            startNotificationProcess();
        }
    }

    public static Intent createIntentStartNotification(Context context) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public void startNotificationProcess() {
        String message = "";
        if (temperatureValue < tempCurrLowVal || temperatureValue > tempCurrUpperVal) {
            message += "Temperature OFR | ";
        }
        if (humidityValue < humidityCurrLowVal || humidityValue > humidityCurrUpperVal) {
            message += "Humidity OFR | ";
        }
        if (lightValue < lightCurrLowVal || lightValue > lightCurrUpperVal) {
            message += "Light OFR | ";
        }
        NotificationCompat.Builder mBuilder0 = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("Alert!")
                .setContentText(message);
        NotificationManager NM0 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NM0.notify(0, mBuilder0.build());
    }
}
