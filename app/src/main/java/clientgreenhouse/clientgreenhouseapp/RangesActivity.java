package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RangesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranges);

        //this section of code provides the bottom menu button functionality
        final Button home2NavButton;
        final Button ranges2NavButton;
        final Button graphs2NavButton;

        home2NavButton = (Button) findViewById(R.id.homeButton2);
        ranges2NavButton = (Button) findViewById(R.id.rangesButton2);
        graphs2NavButton = (Button) findViewById(R.id.graphsButton2);

        //This code executes when the home button is pressed
        home2NavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(RangesActivity.this, MainActivity.class));

            }
        });
        //This code executes when the ranges button is pressed
        ranges2NavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                //do nothing
            }
        });
        //This code executes when the graphs button is pressed
        graphs2NavButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(RangesActivity.this, GraphActivity.class));
            }
        });
    }
}
