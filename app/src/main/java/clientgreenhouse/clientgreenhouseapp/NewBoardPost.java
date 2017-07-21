package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * This class represents the screen that is used to write and submit
 * a new board post. Sends data to firebase.
 */

public class NewBoardPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_board_post);

        final Button cancelButton;
        final Button submitButton;
        final EditText nameField;
        final EditText messageField;

        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.nameText);
        messageField = (EditText) findViewById(R.id.messageText);

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

                // TODO: STORE AND SEND TO FIREBASE
                startActivity(new Intent(NewBoardPost.this, MessageBoard.class));
            }
        });
    }
}
