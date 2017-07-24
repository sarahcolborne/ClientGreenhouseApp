package clientgreenhouse.clientgreenhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView message;
    private TextView date;
    GreenMessage receivedMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        receivedMsg = (GreenMessage) getIntent().getSerializableExtra("Message");
        name = (TextView) findViewById(R.id.field_Name);
        message = (TextView) findViewById(R.id.field_Message);
        date = (TextView) findViewById(R.id.field_Date);

        if(receivedMsg != null){
            name.setText(receivedMsg.name);
            date.setText(receivedMsg.dateTime);
            message.setText(receivedMsg.message);
        }


    }

    public void eraseMessage(View v){
        FirebaseDatabase firebaseRootRef = FirebaseDatabase.getInstance();
        DatabaseReference messageRef = firebaseRootRef.getReference("Messages");
        messageRef.child(receivedMsg.fbKey).removeValue();
        finish();
    }

    public void returnToBoard(View v){
        finish();
        //startActivity(new Intent(this, MessageBoard.class));
    }
}
