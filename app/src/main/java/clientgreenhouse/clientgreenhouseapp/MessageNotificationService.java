package clientgreenhouse.clientgreenhouseapp;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hugh on 2017-07-24.
 */

public class MessageNotificationService extends Service {

    final DataHolder instance1 = DataHolder.getInstance();

    ChildEventListener childEventListener;

    FirebaseDatabase myFirebaseRef = FirebaseDatabase.getInstance();
    DatabaseReference messagesRef = myFirebaseRef.getReference("Messages");

    @Override
    public void onCreate(){

        instance1.setFirstTrue();

        childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v("NOTIFY", Boolean.toString(instance1.getNotify()));
                Log.v("FIRST", Boolean.toString(instance1.getFirst()));
                if (instance1.getNotify() == true && instance1.getSend() == true) {
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
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("SERVICE", "DESTROY!!!!!!!!!");
        messagesRef.removeEventListener(childEventListener);
        super.onDestroy();

    }
}
