package clientgreenhouse.clientgreenhouseapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hugh on 2017-07-24.
 */

public class MessageNotificationService extends IntentService {

    public MessageNotificationService(){ super("nothing");}

    public MessageNotificationService(String name) {
        super(name);
    }

    final DataHolder instance1 = DataHolder.getInstance();

    @Override
    public void onHandleIntent(Intent intent){

        instance1.setFirstTrue();

        FirebaseDatabase myFirebaseRef = FirebaseDatabase.getInstance();
        DatabaseReference messagesRef = myFirebaseRef.getReference("Messages");

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v("\n", Boolean.toString(instance1.getNotify()));
                Log.v("\n", Boolean.toString(instance1.getFirst()));
                if (instance1.getNotify() == true) {
                    if (instance1.getFirst() == false) {
                        NotificationCompat.Builder mBuilder1 = new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.alert)
                                .setContentTitle("NEW MESSAGE(S)")
                                .setContentText("");
                        NotificationManager NM1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        NM1.notify(1, mBuilder1.build());
                    }
                }
                else {
                    instance1.setNotifyTrue();
                }
                instance1.setFirstFalse();
            }

            @Override
            public void onChildRemoved(DataSnapshot datasnapshot) {
                ;
            }

            @Override
            public void onChildChanged(DataSnapshot datasnapshot, String s){
                ;
            }

            @Override
            public void onChildMoved(DataSnapshot datasnapshot, String s){
                ;
            }


        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    messagesRef.addChildEventListener(childEventListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
