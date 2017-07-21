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

        String tempNumString = DataHolder.getTempData();
        String lightNumString = DataHolder.getLightData();
        String humidityNumString = DataHolder.getHumidityData();
        double tempNum = Double.parseDouble(tempNumString);
        double lightNum = Double.parseDouble(lightNumString);
        double humidityNum = Double.parseDouble(humidityNumString);

        String tempRangeLowString = "20";
        String tempRangeHighString = "30";
        String lightRangeLowString = "20";
        String lightRangeHighString = "30";
        String humidityRangeLowString = "20";
        String humidityRangeHighString = "30";
        double tempRangeLowNum = Double.parseDouble(tempRangeLowString);
        double tempRangeHighNum = Double.parseDouble(tempRangeHighString);
        double lightRangeLowNum = Double.parseDouble(lightRangeLowString);
        double lightRangeHighNum = Double.parseDouble(tempRangeHighString);
        double humidityRangeLowNum = Double.parseDouble(humidityRangeLowString);
        double humidityRangeHighNum = Double.parseDouble(tempRangeHighString);

        String message = "";
        if (tempNum < tempRangeLowNum ||  tempNum > tempRangeHighNum) {
            message += "Temperature OFR | ";
        }
        if (humidityNum < humidityRangeLowNum || humidityNum > humidityRangeLowNum) {
            message += "Humidity OFR | ";
        }
        if (lightNum < lightRangeLowNum || lightNum> lightRangeHighNum) {
            message += "Light OFR | ";
        }
        NotificationCompat.Builder mBuilder0 = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("Alert!")
                .setContentText(message);
        NotificationManager NM0 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NM0.notify(0, mBuilder0.build());

        finish();
    }
}
