package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RangesActivity extends AppCompatActivity {

    //global range variables
    public Double tempLowRange;
    public Double tempHighRange;
    public Double humidityLowRange;
    public Double humidityHighRange;
    public Double lightLowRange;
    public Double lightHighRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranges);

        //this section of code provides the bottom menu button functionality
        final Button home2NavButton = (Button) findViewById(R.id.homeButton2);
        final Button ranges2NavButton = (Button) findViewById(R.id.rangesButton2);
        final Button graphs2NavButton = (Button) findViewById(R.id.graphsButton2);

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

        // END OF NAVIGATION BUTTON FUNCTIONALITY CODE

        //Temperature functionality
        final Button updateTempButton = (Button) findViewById(R.id.updateTempButton);
        final TextView tempCurrLowRange = (TextView) findViewById(R.id.tempCurrLowVal);
        final TextView tempCurrHighRange = (TextView) findViewById(R.id.tempCurrUpperVal);
        final TextView tempUpdateMessage = (TextView) findViewById(R.id.tempUpdateMessage);
        final EditText tempNewLowRange = (EditText) findViewById(R.id.tempNewLowVal);
        final EditText tempNewHighRange = (EditText) findViewById(R.id.tempNewUpperVal);

        updateTempButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                String strNewTempLowVal = tempNewLowRange.getText().toString();
                String strNewTempHighVal = tempNewHighRange.getText().toString();

                if (strNewTempLowVal.equals("") || strNewTempHighVal.equals("")){
                    //need both values
                    tempUpdateMessage.setText("Input both range values");
                    tempUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                }
                else {
                    Double newTempLowVal = Double.parseDouble(tempNewLowRange.getText().toString());
                    Double newTempHighVal = Double.parseDouble(tempNewHighRange.getText().toString());

                    if (newTempLowVal >= newTempHighVal) {
                        //invalid range
                        tempUpdateMessage.setText("Invalid range");
                        tempUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                    } else {
                        //store the values, update views, clear input areas
                        Double tempLowVal = Double.parseDouble(tempNewLowRange.getText().toString());
                        Double tempHighVal = Double.parseDouble(tempNewHighRange.getText().toString());
                        tempCurrLowRange.setText(newTempLowVal.toString());
                        tempCurrHighRange.setText(newTempHighVal.toString());
                        tempNewLowRange.setText("");
                        tempNewHighRange.setText("");
                        tempUpdateMessage.setText("Successfully updated");
                        tempUpdateMessage.setTextColor(Color.parseColor("#00FF55"));
                    }
                }
            }

        });

        //Humidity functionality
        final Button updateHumidityButton = (Button) findViewById(R.id.updateHumidityButton);
        final TextView humidityCurrLowRange = (TextView) findViewById(R.id.humidityCurrLowVal);
        final TextView humidityCurrHighRange = (TextView) findViewById(R.id.humidityCurrUpperVal);
        final TextView humidityUpdateMessage = (TextView) findViewById(R.id.humidityUpdateMessage);
        final EditText humidityNewLowRange = (EditText) findViewById(R.id.humidityNewLowVal);
        final EditText humidityNewHighRange = (EditText) findViewById(R.id.humidityNewUpperVal);

        updateHumidityButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                String strNewHumidityLowVal = humidityNewLowRange.getText().toString();
                String strNewHumidityHighVal = humidityNewHighRange.getText().toString();

                if (strNewHumidityLowVal.equals("") || strNewHumidityHighVal.equals("")){
                    //need both values
                    humidityUpdateMessage.setText("Input both range values");
                    humidityUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                }
                else {
                    Double newHumidityLowVal = Double.parseDouble(humidityNewLowRange.getText().toString());
                    Double newHumidityHighVal = Double.parseDouble(humidityNewHighRange.getText().toString());

                    if (newHumidityLowVal >= newHumidityHighVal) {
                        //invalid range
                        humidityUpdateMessage.setText("Invalid range");
                        humidityUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                    } else {
                        //store the values, update views, clear input areas
                        Double humidityLowVal = Double.parseDouble(humidityNewLowRange.getText().toString());
                        Double humidityHighVal = Double.parseDouble(humidityNewHighRange.getText().toString());
                        humidityCurrLowRange.setText(newHumidityLowVal.toString());
                        humidityCurrHighRange.setText(newHumidityHighVal.toString());
                        humidityNewLowRange.setText("");
                        humidityNewHighRange.setText("");
                        humidityUpdateMessage.setText("Successfully updated");
                        humidityUpdateMessage.setTextColor(Color.parseColor("#00FF55"));
                    }
                }
            }

        });


        //Light functionality
        final Button updateLightButton = (Button) findViewById(R.id.updateLightButton);
        final TextView lightCurrLowRange = (TextView) findViewById(R.id.lightCurrLowVal);
        final TextView lightCurrHighRange = (TextView) findViewById(R.id.lightCurrUpperVal);
        final TextView lightUpdateMessage = (TextView) findViewById(R.id.lightUpdateMessage);
        final EditText lightNewLowRange = (EditText) findViewById(R.id.lightNewLowVal);
        final EditText lightNewHighRange = (EditText) findViewById(R.id.lightNewUpperVal);

        updateLightButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                String strNewLightLowVal = lightNewLowRange.getText().toString();
                String strNewLightHighVal = lightNewHighRange.getText().toString();

                if (strNewLightLowVal.equals("") || strNewLightHighVal.equals("")){
                    //need both values
                    lightUpdateMessage.setText("Input both range values");
                    lightUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                }
                else {
                    Double newLightLowVal = Double.parseDouble(lightNewLowRange.getText().toString());
                    Double newLightHighVal = Double.parseDouble(lightNewHighRange.getText().toString());

                    if (newLightLowVal >= newLightHighVal) {
                        //invalid range
                        lightUpdateMessage.setText("Invalid range");
                        lightUpdateMessage.setTextColor(Color.parseColor("#FF0055"));

                    } else {
                        //store the values, update views, clear input areas
                        Double lightLowVal = Double.parseDouble(lightNewLowRange.getText().toString());
                        Double lightHighVal = Double.parseDouble(lightNewHighRange.getText().toString());
                        lightCurrLowRange.setText(newLightLowVal.toString());
                        lightCurrHighRange.setText(newLightHighVal.toString());
                        lightNewLowRange.setText("");
                        lightNewHighRange.setText("");
                        lightUpdateMessage.setText("Successfully updated");
                        lightUpdateMessage.setTextColor(Color.parseColor("#00FF55"));
                    }
                }
            }

        });



    }
}
