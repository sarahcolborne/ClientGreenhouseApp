package clientgreenhouse.clientgreenhouseapp;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphViewActivity extends AppCompatActivity {


    static final String LIGHT = "Light";
    static final String TEMPERATURE = "Temperature";
    static final String HUMIDITY = "Humidity";

    FirebaseDatabase firebaseDatabase;

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private double graphLastXValue = 5d;
    private LineGraphSeries<DataPoint> mSeriesLIGHT;
    private LineGraphSeries<DataPoint> mSeriesTEMPERATURE;
    private LineGraphSeries<DataPoint> mSeriesHUMIDITY;

    GraphView graphViewTEMPERATURE;
    GraphView graphViewHUMIDITY;
    GraphView graphViewLIGHT;


    double lastValuesTEMPERATURE = 0;
    double lastValuesLIGHT = 0;
    double lastValuesHUMIDITY = 0;

    DataPoint[] graphTempSeries;
    DataPoint[] graphLightSeries;
    DataPoint[] graphHumiditySeries;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view_activity);
        graphViewLIGHT = (GraphView) findViewById(R.id.graphLIGHT);
        graphViewHUMIDITY = (GraphView) findViewById(R.id.graphHUMIDITY);
        graphViewTEMPERATURE = (GraphView) findViewById(R.id.graphTEMPERATURE);

        String startedYear = getIntent().getStringExtra("year");
        final String startedMonth = getIntent().getStringExtra("month");
        final String startedWeek = getIntent().getStringExtra("week");
        final String startedDay = getIntent().getStringExtra("day");

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mHistorical = firebaseDatabase.getReference().child("Historical");


        mHistorical.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SensorLogYear currYear = new SensorLogYear();
                SensorLogMonth monthToAdd = new SensorLogMonth();
                SensorLogWeek weekToAdd = new SensorLogWeek();
                SensorLogDay dayToAdd = new SensorLogDay();
                SensorLogHour hourToAdd = new SensorLogHour();
                SensorLogMinute minToAdd = new SensorLogMinute();
                int currSec = dataSnapshot.child("Indices").child("Sec").getValue(int.class);
                int currMin = dataSnapshot.child("Indices").child("Min").getValue(int.class);
                int currHour = dataSnapshot.child("Indices").child("Hour").getValue(int.class);
                int currDay;
                if (startedDay.equals("-1"))
                    currDay = dataSnapshot.child("Indices").child("Day").getValue(int.class);
                else currDay = Integer.parseInt(startedDay);
                int currWeek;
                if (startedWeek.equals("-1"))
                    currWeek = dataSnapshot.child("Indices").child("Week").getValue(int.class);
                else currWeek = Integer.parseInt(startedWeek);
                int currMonth;
                if (startedMonth.equals("-1"))
                    currMonth = dataSnapshot.child("Indices").child("Month").getValue(int.class);
                else currMonth = Integer.parseInt(startedMonth);
                int maxSec = 59;
                int maxMin = 59;
                int maxHour = 23;
                int maxDay = 6;
                int maxWeek = 3;

                //need to fill to capacity unless we are at current value for everything.
                for (int month = 0; month <= currMonth; month++) {
                    if (month == currMonth) {
                        maxWeek = currWeek;
                    }
                    for (int week = 0; week <= maxWeek; week++) {
                        if (week == currWeek && month == currMonth) {
                            maxDay = currDay;
                        }
                        for (int day = 0; day <= maxDay; day++) {
                            if (day == currDay && week == currWeek && month == currMonth) {
                                maxHour = currHour;
                            }
                            for (int hour = 0; hour <= maxHour; hour++) {
                                if (hour == currHour && day == currDay && week == currWeek && month == currMonth) {
                                    maxMin = currMin;
                                }
                                for (int min = 0; min < maxMin; min++) {
                                    if (min == currMin
                                            && hour == currHour
                                            && day == currDay
                                            && week == currWeek
                                            && month == currMonth) {
                                        maxSec = currSec;
                                    }
                                    for (int sec = 0; sec <= maxSec; sec++) {
                                        SensorEntry secToAdd = dataSnapshot
                                                .child("Year").child("1")
                                                .child("Months")
                                                .child(Integer.toString(month))
                                                .child("Weeks").child(Integer.toString(week))
                                                .child("Days").child(Integer.toString(day))
                                                .child("Hour").child(Integer.toString(hour))
                                                .child("Min").child(Integer.toString(min))
                                                .child("Second").child(Integer.toString(sec))
                                                .getValue(SensorEntry.class);
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


                if (!startedDay.equals("-1")) {
                    int count = 0;
                    for (SensorLogHour hour : dayToAdd.hours) {
                        if (hour != null) {
                            ++count;
                        }
                    }
                    if (count == 1) {
                        count = 0;
                        for (SensorLogMinute mins : hourToAdd.mins) {
                            if (mins != null) {
                                ++count;
                            }
                        }
                        graphTempSeries = new DataPoint[count];
                        graphHumiditySeries = new DataPoint[count];
                        graphLightSeries = new DataPoint[count];
                        for (int i = 0; i < count; i++) {
                            graphTempSeries[i] = new DataPoint(i, hourToAdd.mins[i].getAverageTemp());
                            graphHumiditySeries[i] = new DataPoint(i, hourToAdd.mins[i].getAverageHumid());
                            graphLightSeries[i] = new DataPoint(i, hourToAdd.mins[i].getAverageLux());
                        }

                    } else {
                        graphTempSeries = new DataPoint[count];
                        graphHumiditySeries = new DataPoint[count];
                        graphLightSeries = new DataPoint[count];
                        for (int i = 0; i < count; i++) {

                            graphTempSeries[i] = new DataPoint(i, dayToAdd.hours[i].getAverageTemp());
                            graphHumiditySeries[i] = new DataPoint(i, dayToAdd.hours[i].getAverageHumid());
                            graphLightSeries[i] = new DataPoint(i, dayToAdd.hours[i].getAverageLux());
                        }
                    }
                    initGraph();
                    return;
                }
                if (!startedWeek.equals("-1")) {
                    int count = 0;
                    for (SensorLogDay day : weekToAdd.days) {
                        if (day != null) {
                            ++count;
                        }
                    }
                    graphTempSeries = new DataPoint[count];
                    graphHumiditySeries = new DataPoint[count];
                    graphLightSeries = new DataPoint[count];
                    for (int i = 0; i < count; i++) {
                        graphTempSeries[i] = new DataPoint(i, weekToAdd.days[i].getAverageTemp());
                        graphHumiditySeries[i] = new DataPoint(i, weekToAdd.days[i].getAverageHumid());
                        graphLightSeries[i] = new DataPoint(i, weekToAdd.days[i].getAverageLux());
                    }
                    initGraph();
                    return;
                }
                if (!startedMonth.equals("-1")) {
                    int count = 0;
                    for (SensorLogWeek week : monthToAdd.weeks) {
                        if (week != null) {
                            ++count;
                        }
                    }
                    graphTempSeries = new DataPoint[count];
                    graphHumiditySeries = new DataPoint[count];
                    graphLightSeries = new DataPoint[count];
                    for (int i = 0; i < count; i++) {
                        graphTempSeries[i] = new DataPoint(i, monthToAdd.weeks[i].getAverageTemp());
                        graphHumiditySeries[i] = new DataPoint(i, monthToAdd.weeks[i].getAverageHumid());
                        graphLightSeries[i] = new DataPoint(i, monthToAdd.weeks[i].getAverageLux());
                    }
                    initGraph();
                    return;
                }
                int count = 0;
                for (SensorLogMonth month : currYear.months) {
                    if (month != null) {
                        ++count;
                    }
                }
                graphTempSeries = new DataPoint[count];
                graphHumiditySeries = new DataPoint[count];
                graphLightSeries = new DataPoint[count];
                for (int i = 0; i < currYear.months.length; i++) {
                    if (currYear.months[i] != null) {
                        graphTempSeries[i] = new DataPoint(i, currYear.months[i].getAverageTemp());
                        graphHumiditySeries[i] = new DataPoint(i, currYear.months[i].getAverageHumid());
                        graphLightSeries[i] = new DataPoint(i, currYear.months[i].getAverageLux());
                    }
                }
                initGraph();

//                double avgTemp = currYear.getAverageTemp();
//                TextView avgTempField = (TextView) findViewById(R.id.testTemp);
//                avgTempField.setText(Double.toString(avgTemp));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        Query lastTem = myRef.child(TEMPERATURE).orderByKey().limitToLast(1);
//        lastTem.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null)
//                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        lastValuesTEMPERATURE = child.getValue(Double.class);
//                    }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        Query lastLit = myRef.child(LIGHT).orderByKey().limitToLast(1);
//        lastLit.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null)
//                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        lastValuesLIGHT = child.getValue(Double.class);
//                    }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        Query lastHum = myRef.child(HUMIDITY).orderByKey().limitToLast(1);
//        lastHum.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null)
//                    if (dataSnapshot != null)
//                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                            lastValuesHUMIDITY = child.getValue(Double.class);
//                        }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        initGraph(graphViewLIGHT);
//        initGraph(graphViewHUMIDITY);
//        initGraph(graphViewTEMPERATURE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initGraph() {
        LineGraphSeries<DataPoint> tempSeries = new LineGraphSeries<>(graphTempSeries);
        tempSeries.setColor(getColor(R.color.red));
        tempSeries.setDrawDataPoints(true);
        tempSeries.setDataPointsRadius(10);
        tempSeries.setThickness(8);

        LineGraphSeries<DataPoint> humiditySeries = new LineGraphSeries<>(graphHumiditySeries);
        humiditySeries.setColor(getColor(R.color.blue));
        humiditySeries.setDrawDataPoints(true);
        humiditySeries.setDataPointsRadius(10);
        humiditySeries.setThickness(8);

        LineGraphSeries<DataPoint> lightSeries = new LineGraphSeries<>(graphLightSeries);
        lightSeries.setColor(R.color.yellow);
        lightSeries.setDrawDataPoints(true);
        lightSeries.setDataPointsRadius(10);
        lightSeries.setThickness(8);

        // set manual X bounds
        graphViewTEMPERATURE.getViewport().setYAxisBoundsManual(true);
        graphViewTEMPERATURE.getViewport().setMinY(0);
        graphViewTEMPERATURE.getViewport().setMaxY(100);

        graphViewTEMPERATURE.getViewport().setXAxisBoundsManual(true);
        graphViewTEMPERATURE.getViewport().setMinX(4);
        graphViewTEMPERATURE.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graphViewTEMPERATURE.getViewport().setScalable(true);
        graphViewTEMPERATURE.getViewport().setScalableY(true);
        graphViewTEMPERATURE.getViewport().setScrollable(true); // enables horizontal scrolling
        graphViewTEMPERATURE.getViewport().setScrollableY(true); // enables vertical scrolling
        graphViewTEMPERATURE.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graphViewTEMPERATURE.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        // set manual X bounds
        graphViewHUMIDITY.getViewport().setYAxisBoundsManual(true);
        graphViewHUMIDITY.getViewport().setMinY(0);
        graphViewHUMIDITY.getViewport().setMaxY(100);

        graphViewHUMIDITY.getViewport().setXAxisBoundsManual(true);
        graphViewHUMIDITY.getViewport().setMinX(4);
        graphViewHUMIDITY.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graphViewHUMIDITY.getViewport().setScalable(true);
        graphViewHUMIDITY.getViewport().setScalableY(true);
        graphViewHUMIDITY.getViewport().setScrollable(true); // enables horizontal scrolling
        graphViewHUMIDITY.getViewport().setScrollableY(true); // enables vertical scrolling
        graphViewHUMIDITY.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graphViewHUMIDITY.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        // set manual X bounds
        graphViewLIGHT.getViewport().setYAxisBoundsManual(true);
        graphViewLIGHT.getViewport().setMinY(0);
        graphViewLIGHT.getViewport().setMaxY(100);

        graphViewLIGHT.getViewport().setXAxisBoundsManual(true);
        graphViewLIGHT.getViewport().setMinX(4);
        graphViewLIGHT.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graphViewLIGHT.getViewport().setScalable(true);
        graphViewLIGHT.getViewport().setScalableY(true);
        graphViewLIGHT.getViewport().setScrollable(true); // enables horizontal scrolling
        graphViewLIGHT.getViewport().setScrollableY(true); // enables vertical scrolling
        graphViewLIGHT.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graphViewLIGHT.getViewport().setScalableY(true); // enables vertical zooming and scrolling


        graphViewTEMPERATURE.addSeries(tempSeries);
        graphViewHUMIDITY.addSeries(humiditySeries);
        graphViewLIGHT.addSeries(lightSeries);

    }

    @Override
    public void onResume() {
        super.onResume();
//        mTimer = new Runnable() {
//            @Override
//            public void run() {
//                graphLastXValue += 0.25d;
//                mSeriesTEMPERATURE.appendData(new DataPoint(graphLastXValue, lastValuesTEMPERATURE), true, 22);
//                mSeriesHUMIDITY.appendData(new DataPoint(graphLastXValue, lastValuesHUMIDITY), true, 22);
//                mSeriesLIGHT.appendData(new DataPoint(graphLastXValue, lastValuesLIGHT), true, 22);
//                mHandler.postDelayed(this, 330);
//            }
//        };
//        mHandler.postDelayed(mTimer, 1500);
    }

    @Override
    public void onPause() {
        super.onPause();
//        mHandler.removeCallbacks(mTimer);
    }
}
