package clientgreenhouse.clientgreenhouseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class FBOTestActivity extends AppCompatActivity {

    public DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference mHistorical= mRootRef.child("Historical");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbotest);
        mHistorical.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SensorLogYear currYear = new SensorLogYear();
                int currSec = dataSnapshot.child("Indices").child("Sec").getValue(int.class);
                int currMin = dataSnapshot.child("Indices").child("Min").getValue(int.class);
                int currHour = dataSnapshot.child("Indices").child("Hour").getValue(int.class);
                int currDay = dataSnapshot.child("Indices").child("Day").getValue(int.class);
                int currWeek = dataSnapshot.child("Indices").child("Week").getValue(int.class);
                int currMonth = dataSnapshot.child("Indices").child("Month").getValue(int.class);
                int maxSec = 59;
                int maxMin = 59;
                int maxHour = 23;
                int maxDay = 6;
                int maxWeek = 3;

                //need to fill to capacity unless we are at current value for everything.
                for(int month = 0; month<= currMonth; month++){
                    if (month == currMonth){
                        maxWeek = currWeek;
                    }
                    SensorLogMonth monthToAdd = new SensorLogMonth();
                    for(int week = 0; week<= maxWeek; week++){
                        if(week == currWeek && month == currMonth){
                            maxDay = currDay;
                        }
                        SensorLogWeek weekToAdd = new SensorLogWeek();
                        for(int day = 0; day<=maxDay; day++){
                            if(day == currDay && week == currWeek && month == currMonth){
                                maxHour = currHour;
                            }
                            SensorLogDay dayToAdd = new SensorLogDay();
                            for(int hour = 0; hour<=maxHour; hour++){
                                if(hour == currHour && day == currDay && week == currWeek && month == currMonth){
                                    maxMin = currMin;
                                }
                                SensorLogHour hourToAdd = new SensorLogHour();
                                for(int min = 0; min<maxMin; min++){
                                    SensorLogMinute minToAdd = new SensorLogMinute();
                                    if(min == currMin && hour == currHour && day == currDay && week == currWeek && month == currMonth){
                                        maxSec = currSec;
                                    }
                                    for(int sec = 0; sec<=maxSec; sec++) {
                                        SensorEntry secToAdd = dataSnapshot.child("Year").child("1").child("Months").child(Integer.toString(month)).child("Weeks").child(Integer.toString(week)).child("Days").child(Integer.toString(day)).child("Hour").child(Integer.toString(hour)).child("Min").child(Integer.toString(min)).child("Second").child(Integer.toString(sec)).getValue(SensorEntry.class);
                                        //minToAdd.addEntry(dataSnapshot.child("Year").child("1").child("Months").child(Integer.toString(month)).child("Weeks").child(Integer.toString(week)).child("Days").child(Integer.toString(day)).child("Hour").child(Integer.toString(hour)).child("Min").child(Integer.toString(min)).child("Second").child(Integer.toString(sec)).getValue(SensorEntry.class));
                                        minToAdd.addEntry(secToAdd);
                                    }
                                    hourToAdd.addMinute(minToAdd);
                                }
                                dayToAdd.addHour(hourToAdd);
                            }
                            weekToAdd.addDay(dayToAdd);
                        }
                        monthToAdd.addWeek(weekToAdd);
                    }
                    currYear.addMonth(monthToAdd);
                }
                double avgTemp = currYear.getAverageTemp();
                TextView avgTempField = (TextView) findViewById(R.id.testTemp);
                avgTempField.setText(Double.toString(avgTemp));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
