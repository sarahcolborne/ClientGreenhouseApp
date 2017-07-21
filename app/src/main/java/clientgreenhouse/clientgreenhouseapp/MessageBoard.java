package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);

        final Button backButton;
        final Button newPostButton;

        backButton = (Button) findViewById(R.id.backButton);
        newPostButton = (Button) findViewById(R.id.newPostButton);

        //This code executes when the back button is pressed
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(MessageBoard.this, MainActivity.class));
            }
        });
        //This code executes when the new post button is pressed
        newPostButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                startActivity(new Intent(MessageBoard.this, NewBoardPost.class));
            }
        });



    }


}
