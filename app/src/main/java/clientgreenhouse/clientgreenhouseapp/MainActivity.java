/* The main activity for ClientGreenhouseApp
 * Created by Ben Parker and Sarah Colborne
 * Modified by Sean Mahoney and Asma Alhajri
 *
 * @author Sarah Colborne, Ben Parker, Sean Mahoney and Asma Alhajri
 */
package clientgreenhouse.clientgreenhouseapp;

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

public class MainActivity extends AppCompatActivity {
    public DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference mCurrentRef= mRootRef.child("Current_Sensor");
//    public DatabaseReference mCurrTemp= mCurrentRef.child("Current_Temperature");
//    public DatabaseReference mCurrHumidity =mCurrentRef.child("Current_Humidity");
//    public DatabaseReference mCurrLight= mCurrentRef.child("Current_Light");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        currentTemp =(TextView) findViewById(R.id.temperatureValue);
        currentHumidity =(TextView) findViewById(R.id.humidityValue);
        currentLight =(TextView) findViewById(R.id.lightValue);

        // Displays the real time temperature from firebase
        mCurrentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SensorEntry curr = dataSnapshot.getValue(SensorEntry.class);
                currentTemp.setText(Double.toString(curr.temp));
                currentHumidity.setText(Double.toString(curr.humid));
                currentLight.setText(Double.toString(curr.lux));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        // Displays the real time humidity value from firebase
//        mCurrHumidity.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                currentHumidity.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        // Displays the real time light value from firebase
//        mCurrLight.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                currentLight.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    //display or annul notifications when set
    public void pushNotifications(View view){
        Button alertButton = (Button) findViewById(R.id.button2);
        if (NotificationReceiver.alarmRunning(getApplicationContext()) == false){
            NotificationReceiver.setAlarm(getApplicationContext());
            alertButton.setTextColor(Color.RED);

        }
        else{
            NotificationReceiver.deleteAlarm(getApplicationContext());
            alertButton.setTextColor(Color.BLACK);
        }
    }
}
