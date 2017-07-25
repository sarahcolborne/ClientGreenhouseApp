package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class GraphActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    Spinner spYear;
    Spinner spMonth;
    Spinner spWeek;
    Spinner spDay;

    String selectedYear = "-1";
    String selectedMonth = "-1";
    String selectedWeek = "-1";
    String selectedDay = "-1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        //this section of code provides the bottom menu button functionality
        final Button homeNavButton;
        final Button rangesNavButton;
        final Button graphsNavButton;
        final Button testsNavButton;
        final Button showGraph;

        showGraph = (Button) findViewById(R.id.showGraph);
        showGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraphActivity.this, GraphViewActivity.class);
                intent.putExtra("year", selectedYear);
                intent.putExtra("month", selectedMonth);
                intent.putExtra("week", selectedWeek);
                intent.putExtra("day", selectedDay);
                startActivity(intent);
            }
        });
        homeNavButton = (Button) findViewById(R.id.homeButton);
        rangesNavButton = (Button) findViewById(R.id.rangesButton);
        graphsNavButton = (Button) findViewById(R.id.graphsButton);
        testsNavButton = (Button) findViewById(R.id.goTest);

        spYear = (Spinner) findViewById(R.id.spYear);
        spMonth = (Spinner) findViewById(R.id.spMonth);
        spWeek = (Spinner) findViewById(R.id.spWeek);
        spDay = (Spinner) findViewById(R.id.spDay);
        try {
            firebaseDatabase.getReference().child("Historical")
                    .child("Year").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {
                    final ArrayList<String> years = new ArrayList<String>();
                    years.add("Select Year");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        years.add(snapshot.getKey());
                    }
                    final ArrayAdapter<String> spinnerAdapter =
                            new ArrayAdapter<String>(GraphActivity.this,
                                    R.layout.spinner_item_layout, R.id.listItem, years);
                    spYear.setAdapter(spinnerAdapter);
                    spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                getMonths(years.get(position));
                                selectedYear = years.get(position);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            Log.d("fireBaseerror", e.getMessage());
        }


        //This code executes when the home button is pressed
        homeNavButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                startActivity(new Intent(GraphActivity.this, MainActivity.class));
            }
        });
        //This code executes when the ranges button is pressed
        rangesNavButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                startActivity(new Intent(GraphActivity.this, RangesActivity.class));
            }
        });
        testsNavButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                startActivity(new Intent(GraphActivity.this, FBOTestActivity.class));
            }
        });

        //This code executes when the graphs button is pressed
        graphsNavButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                //do nothing
            }
        });
    }

    private void getMonths(final String s) {
        final ArrayList<String> months = new ArrayList<String>();
        months.add("Select Month");
        firebaseDatabase.getReference()
                .child("Historical")
                .child("Year").child(s)
                .child("Months").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    months.add(snapshot.getKey());
                final ArrayAdapter<String> spinnerAdapterM =
                        new ArrayAdapter<String>(GraphActivity.this,
                                R.layout.spinner_item_layout, R.id.listItem, months);
                spMonth.setAdapter(spinnerAdapterM);
                spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                            getWeeks(s, months.get(position));

                            selectedMonth = months.get(position);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getWeeks(final String year, final String month) {
        final ArrayList<String> weeks = new ArrayList<String>();
        weeks.add("Select week");
        firebaseDatabase.getReference()
                .child("Historical")
                .child("Year").child(year)
                .child("Months").child(month)
                .child("Weeks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    weeks.add(snapshot.getKey());
                }
                final ArrayAdapter<String> spinnerAdapterM =
                        new ArrayAdapter<String>(GraphActivity.this,
                                R.layout.spinner_item_layout, R.id.listItem, weeks);
                spWeek.setAdapter(spinnerAdapterM);
                spWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                            getDays(year, month, weeks.get(position));
                            selectedWeek = weeks.get(position);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDays(String year, String month, String week) {
        final ArrayList<String> days = new ArrayList<>();
        days.add("Select Day");
        firebaseDatabase.getReference().child("Historical")
                .child("Year").child(year)
                .child("Months").child(month)
                .child("Weeks").child(week)
                .child("Days").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    days.add(snapshot.getKey());
                final ArrayAdapter<String> spinnerAdapterM =
                        new ArrayAdapter<>(GraphActivity.this,
                                R.layout.spinner_item_layout, R.id.listItem, days);
                spDay.setAdapter(spinnerAdapterM);
                spDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                            selectedDay = days.get(position);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
