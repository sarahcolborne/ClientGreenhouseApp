package clientgreenhouse.clientgreenhouseapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MessageBoard extends AppCompatActivity {

    private ListView messageListView;
    private FirebaseListAdapter<GreenMessage> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);

        final Button backButton;
        final Button newPostButton;

        FirebaseDatabase firebaseRootRef = FirebaseDatabase.getInstance();
        DatabaseReference messageRef = firebaseRootRef.getReference("Messages");

        backButton = (Button) findViewById(R.id.backButton);
        newPostButton = (Button) findViewById(R.id.newPostButton);

        messageListView = (ListView) findViewById(R.id.messagesList);

        firebaseAdapter = new FirebaseListAdapter<GreenMessage>(this, GreenMessage.class,
                android.R.layout.simple_list_item_2, messageRef) {
            @Override
            protected void populateView(View v, GreenMessage model, int position) {
                TextView name = (TextView) v.findViewById(android.R.id.text1);
                name.setText(model.name);
                TextView message = (TextView) v.findViewById(android.R.id.text2);
                message.setText(model.message);
            }
        };

        messageListView.setAdapter(firebaseAdapter);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GreenMessage msg = firebaseAdapter.getItem(position);
                showDetailView(msg);
            }
        });

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

    private void showDetailView(GreenMessage msg)
    {
        Intent intent = new Intent(this, MessageDetailActivity.class);
        intent.putExtra("Message", msg);
        startActivity(intent);
    }

}
