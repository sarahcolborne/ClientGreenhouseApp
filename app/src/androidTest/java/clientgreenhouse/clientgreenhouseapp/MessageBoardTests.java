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
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MessageBoardTests {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mCurrentRef= mRootRef.child("Current_Sensor");
    private DatabaseReference mCurrTemp= mCurrentRef.child("temp");
    FirebaseDatabase firebaseRootRef = FirebaseDatabase.getInstance();
    final DatabaseReference messageRef = firebaseRootRef.getReference("Messages");
    String temp_fireBase;
    private GreenMessage testMsg = new GreenMessage("Ben", "Message", "Date", "sillyID");


    @Rule
    public ActivityTestRule<MessageBoard> messageBoardActivity = new ActivityTestRule<>(
            MessageBoard.class);

    // a test to verify the messageBoardActivity is loaded
    @Test
    public void test_MessagesBoardLoaded(){
        onView(withId(R.id.messageBButton)).check(doesNotExist());
    }

    @Test
    public void test_MessageBoardFunctions() {
        onView(withId(R.id.newPostButton)).perform(click());
        onView(withId(R.id.nameText)).perform(typeText("Ben"));
        onView(withId(R.id.nameText)).perform(closeSoftKeyboard());
        onView(withId(R.id.messageText)).perform(typeText("Message"));
        onView(withId(R.id.messageText)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onData(hasToString(testMsg.toString())).inAdapterView(withId(R.id.messagesList)).check(matches(isDisplayed()));
        onData(hasToString(testMsg.toString())).inAdapterView(withId(R.id.messagesList)).perform(click());
        onView(withId(R.id.btn_Delete)).perform(click());
        onView(withId(R.id.messagesList)).check(matches(not(withAdaptedData(hasToString(testMsg.toString())))));




    }

    //method taken from google android support library - https://google.github.io/android-testing-support-library/docs/espresso/advanced/#asserting-that-a-view-is-not-present
    private static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

}
