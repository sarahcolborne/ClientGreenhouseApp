package clientgreenhouse.clientgreenhouseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                int maxSec;
                int maxMin;
                int maxHour;
                int maxDay;
                int maxWeek;
                int maxMonth;

                //need to fill to capacity unless we are at current value for everything.
                for(int month = 0; month<= currSec; month++){
                    SensorLogMonth monthToAdd = new SensorLogMonth();
                    for(int week = 0; week<= currWeek; week++){
                        SensorLogWeek weekToAdd = new SensorLogWeek();
                        for(int day = 0; day<=currDay; day++){
                            SensorLogDay dayToAdd = new SensorLogDay();
                            for(int hour = 0; hour<=currHour; hour++){
                                SensorLogHour hourToAdd = new SensorLogHour();
                                if(hour<currHour)
                                    maxMin = 59;
                                else maxMin = currMin;
                                for(int min = 0; min<maxMin; min++){
                                    SensorLogMinute minToAdd = new SensorLogMinute();
                                    if(min<currMin) {
                                        maxSec = 59;
                                    }
                                    else maxSec = currSec;
                                    for(int sec = 0; sec<=maxSec; sec++) {
                                            minToAdd.addEntry((SensorEntry)dataSnapshot.child("Year").child("1").child("Months").child(Integer.toString(month)).child("Weeks").child(Integer.toString(week)).child("Days").child(Integer.toString(day)).child("Hour").ch0ild(Integer.toString(hour)).child("Min").child(Integer.toString(min)).child("Second").child(Integer.toString(sec)).getValue()));
                                    }
                                    hourToAdd.addMinute(minToAdd);
                                }

                            }
                        }
                    }
                }
                double avgTemp = currYear.getAverageTemp();
                temp.setText(Double.toString(avgTemp));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
