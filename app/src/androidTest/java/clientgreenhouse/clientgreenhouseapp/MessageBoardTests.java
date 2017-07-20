/**
 * A test to verify that the main activity is displaying realtime data, which it retrieves from firebase.
 * Created by Sean Mahoney, Asma Alhajri, and Ben Parker on July 3, 2017
 *
 * @author Sean Mahoney, Asma Alhajri, and Ben Parker
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
package clientgreenhouse.clientgreenhouseapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MessageBoardTests {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mCurrentRef= mRootRef.child("Current_Sensor");
    private DatabaseReference mCurrTemp= mCurrentRef.child("temp");
    String temp_fireBase;

    @Rule
    public ActivityTestRule<MessageBoard> messageBoardActivity = new ActivityTestRule<>(
            MessageBoard.class);

    // a test to verify the main activity is displaying the correct temperature
    @Test
    public void Temperature_Display_Test(){
        mCurrTemp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp_fireBase = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        onView(withId(R.id.temperatureValue)).check(matches(withText(temp_fireBase)));
    }

}
