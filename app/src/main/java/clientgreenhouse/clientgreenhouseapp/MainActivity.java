/* The main activity for ClientGreenhouseApp
 * Created by Ben Parker and Sarah Colborne
 * Modified by Sean Mahoney and Asma Alhajri
 *
 * @author Sarah Colborne, Ben Parker, Sean Mahoney and Asma Alhajri
 */
package clientgreenhouse.clientgreenhouseapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static clientgreenhouse.clientgreenhouseapp.R.id.temperatureValue;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference mCurrentRef= mRootRef.child("Current_Sensor");
    public DatabaseReference mCurrTemp= mCurrentRef.child("temp");
    public DatabaseReference mCurrHumidity =mCurrentRef.child("humid");
    public DatabaseReference mCurrLight= mCurrentRef.child("lux");
    public static String tempValGlobal = "NO_TEXT";
    final DataHolder instance1 = DataHolder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertButton = (Button) findViewById(R.id.button2);

        if (isMyServiceRunning(MessageNotificationService.class)) {
            alertButton.setTextColor(Color.RED);
        }
        else {
            alertButton.setTextColor(Color.BLACK);
        }

        //this section of code provides the bottom menu button functionality
        final Button homeNavButton;
        final Button rangesNavButton;
        final Button graphsNavButton;
        final Button messageBButton;
        final TextView currentTemp;
        final TextView currentHumidity;
        final TextView currentLight;
        homeNavButton = (Button) findViewById(R.id.homeButton);
        rangesNavButton = (Button) findViewById(R.id.rangesButton);
        graphsNavButton = (Button) findViewById(R.id.graphsButton);
        messageBButton = (Button) findViewById(R.id.messageBButton);

        //This code executes when the home button is pressed
        homeNavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                //do nothing
            }
        });
        //This code executes when the ranges button is pressed
        rangesNavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(MainActivity.this, RangesActivity.class));
            }
        });
        //This code executes when the graphs button is pressed
        graphsNavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(MainActivity.this, GraphActivity.class));
            }
        });
        //This code executes when the message board button is pressed
        messageBButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(MainActivity.this, MessageBoard.class));
            }
        });

        currentTemp =(TextView) findViewById(temperatureValue);
        currentHumidity =(TextView) findViewById(R.id.humidityValue);
        currentLight =(TextView) findViewById(R.id.lightValue);



        // Displays the real time temperature from firebase
        mCurrTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                currentTemp.setText(value.toString());
                instance1.setTempData(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Displays the real time humidity value from firebase
        mCurrHumidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                currentHumidity.setText(value.toString());
                instance1.setHumidityData(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Displays the real time light value from firebase
        mCurrLight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                currentLight.setText(value.toString());
                instance1.setLightData(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //display or annul notifications when set
    public void pushNotifications(View view){
        Button alertButton = (Button) findViewById(R.id.button2);
        if (NotificationReceiver.alarmRunning(getApplicationContext()) == false){
            NotificationReceiver.setAlarm(getApplicationContext());
            alertButton.setTextColor(Color.RED);
            startService(new Intent(this, MessageNotificationService.class));
            instance1.setSendTrue();
        }
        else{
            NotificationReceiver.deleteAlarm(getApplicationContext());
            alertButton.setTextColor(Color.BLACK);
            stopService(new Intent(this, MessageNotificationService.class));
            instance1.setSendFalse();
        }
    }
    //checks if message notification service is running
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
