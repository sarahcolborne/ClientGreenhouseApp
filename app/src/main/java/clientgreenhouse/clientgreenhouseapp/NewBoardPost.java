package clientgreenhouse.clientgreenhouseapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
 * This class represents the screen that is used to write and submit
 * a new board post. Sends data to firebase to be displayed in the
 * MessageBoard class.
 */

public class NewBoardPost extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_board_post);

        final DataHolder instance1 = DataHolder.getInstance();

        FirebaseDatabase firebaseRootRef = FirebaseDatabase.getInstance();
        final DatabaseReference messageRef = firebaseRootRef.getReference("Messages");

        final Button cancelButton;
        final Button submitButton;
        final EditText nameField;
        final EditText messageField;
        final TextView errorMessage;
        //Calendar help from: https://stackoverflow.com/questions/2271131/display-the-current-time-and-date-in-an-android-application
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.nameText);
        messageField = (EditText) findViewById(R.id.messageText);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        //This code executes when the cancel button is pressed
        cancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(NewBoardPost.this, MessageBoard.class));
            }
        });
        //This code executes when the submit button is pressed
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){

                String name = nameField.getText().toString();
                String message = messageField.getText().toString();
                String formattedDate = df.format(c.getTime());
                String keyID = messageRef.push().getKey();
                GreenMessage messageToPost = new GreenMessage(name, message, formattedDate, keyID);


                //Verifying the entered values
                if (name.equals("")){
                    errorMessage.setText("Please enter your name");
                }
                else if (message.equals("")){
                    errorMessage.setText("Please enter a message");
                }
                else {
                    messageRef.child(keyID).setValue(messageToPost);
                    finish();
                    instance1.setNotifyFalse();
                    //startActivity(new Intent(NewBoardPost.this, MessageBoard.class));
                }
            }
        });
    }
}
