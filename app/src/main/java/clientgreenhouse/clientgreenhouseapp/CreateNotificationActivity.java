package clientgreenhouse.clientgreenhouseapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hugh on 2017-07-07.
 */

public class CreateNotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String message = "";
        if (tempNum < tempRangeLowNum ||  tempNum > tempRangeHighNum) {
            message += "Temperature OFR | ";
        }
        if (humidityNum < humidityRangeLowNum || humidityNum > humidityRangeHighNum) {
            message += "Humidity OFR | ";
        }
        if (lightNum < lightRangeLowNum || lightNum > lightRangeHighNum) {
            message += "Light OFR | ";
        }

        if (!message.equals("")) {
            NotificationCompat.Builder mBuilder0 = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.alert)
                    .setContentTitle("Alert!")
                    .setContentText(message);
            NotificationManager NM0 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NM0.notify(0, mBuilder0.build());
        }

        finish();
    }
}
