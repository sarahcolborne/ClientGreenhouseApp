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

    //creates notification and sends it
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataHolder instance1 = DataHolder.getInstance();

        double tempNum = instance1.getTempData();
        double lightNum = instance1.getLightData();
        double humidityNum = instance1.getHumidityData();

        RangesStorage instance2 = RangesStorage.getInstance();

        double tempRangeLowNum = instance2.getTempLowRange();
        double tempRangeHighNum = instance2.getTempHighRange();
        double lightRangeLowNum = instance2.getLightLowRange();
        double lightRangeHighNum = instance2.getLightHighRange();
        double humidityRangeLowNum = instance2.getHumidLowRange();
        double humidityRangeHighNum = instance2.getHumidHighRange();

        String message = "";
        if (tempNum < tempRangeLowNum ||  tempNum > tempRangeHighNum) {
            message += "Temperature | ";
        }
        if (humidityNum < humidityRangeLowNum || humidityNum > humidityRangeHighNum) {
            message += "Humidity | ";
        }
        if (lightNum < lightRangeLowNum || lightNum > lightRangeHighNum) {
            message += "Light | ";
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
