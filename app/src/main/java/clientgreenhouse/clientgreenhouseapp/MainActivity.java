package clientgreenhouse.clientgreenhouseapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //this section of code provides the bottom menu button functionality
        final Button homeNavButton;
        final Button rangesNavButton;
        final Button graphsNavButton;

        homeNavButton = (Button) findViewById(R.id.homeButton);
        rangesNavButton = (Button) findViewById(R.id.rangesButton);
        graphsNavButton = (Button) findViewById(R.id.graphsButton);

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

    }
}
