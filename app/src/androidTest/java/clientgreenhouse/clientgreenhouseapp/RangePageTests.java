package clientgreenhouse.clientgreenhouseapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RangePageTests {

    @Rule
    public ActivityTestRule<RangesActivity> rangeActivityRule = new ActivityTestRule<>(
            RangesActivity.class);

    @Test
    public void attemptTempTests() {
        String emptyRangePhrase = "Input both range values";
        String invalidRangePhrase = "Invalid range";
        String acceptedRangePhrase = "Successfully updated";

        //Attempt to submit empty ranges in each button, check resulting messages
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Attempt to submit value in only lower bound
        onView(withId(R.id.tempNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Erase values in lower bound
        onView(withId(R.id.tempNewLowVal)).perform(clearText());

        //Attempt to submit value in only lower bound
        onView(withId(R.id.tempNewUpperVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Fill in equal values in lower bound to upper
        onView(withId(R.id.tempNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(invalidRangePhrase)));

        //Fill in higher values in lower bound than upper
        onView(withId(R.id.tempNewLowVal)).perform(clearText(), typeText("25.2"), closeSoftKeyboard());
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(invalidRangePhrase)));

        //Fill in proper values in both bounds
        onView(withId(R.id.tempNewLowVal)).perform(clearText(), typeText("15.2"), closeSoftKeyboard());
        onView(withId(R.id.updateTempButton)).perform(click());
        onView(withId(R.id.tempUpdateMessage)).check(matches(withText(acceptedRangePhrase)));

    }

    @Test
    public void attemptHumidityTests() {
        String emptyRangePhrase = "Input both range values";
        String invalidRangePhrase = "Invalid range";
        String acceptedRangePhrase = "Successfully updated";

        //Attempt to submit empty ranges in each button, check resulting messages
        onView(withId(R.id.updateHumidityButton)).perform(ViewActions.scrollTo()).perform(click());

        onView(withId(R.id.humidityUpdateMessage)).perform(ViewActions.scrollTo()).check(matches(withText(emptyRangePhrase)));

        //Attempt to submit value in only lower bound
        onView(withId(R.id.humidityNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateHumidityButton)).perform(click());
        onView(withId(R.id.humidityUpdateMessage)).perform(ViewActions.scrollTo()).check(matches(withText(emptyRangePhrase)));

        //Erase values in lower bound
        onView(withId(R.id.humidityNewLowVal)).perform(clearText());

        //Attempt to submit value in only lower bound
        onView(withId(R.id.humidityNewUpperVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateHumidityButton)).perform(click());
        onView(withId(R.id.humidityUpdateMessage)).perform(ViewActions.scrollTo()).check(matches(withText(emptyRangePhrase)));

        //Fill in equal values in lower bound to upper
        onView(withId(R.id.humidityNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateHumidityButton)).perform(click());
        onView(withId(R.id.humidityUpdateMessage)).perform(ViewActions.scrollTo()).check(matches(withText(invalidRangePhrase)));

        //Fill in higher values in lower bound than upper
        onView(withId(R.id.humidityNewLowVal)).perform(clearText(), typeText("25.2"), closeSoftKeyboard());
        onView(withId(R.id.updateHumidityButton)).perform(click());
        onView(withId(R.id.humidityUpdateMessage)).check(matches(withText(invalidRangePhrase)));

        //Fill in proper values in both bounds
        onView(withId(R.id.humidityNewLowVal)).perform(clearText(), typeText("15.2"), closeSoftKeyboard());
        onView(withId(R.id.updateHumidityButton)).perform(click());
        onView(withId(R.id.humidityUpdateMessage)).perform(ViewActions.scrollTo()).check(matches(withText(acceptedRangePhrase)));

    }

    @Test
    public void attemptLightTests() {
        String emptyRangePhrase = "Input both range values";
        String invalidRangePhrase = "Invalid range";
        String acceptedRangePhrase = "Successfully updated";

        //Attempt to submit empty ranges in each button, check resulting messages
        onView(withId(R.id.updateLightButton)).perform(ViewActions.scrollTo()).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Attempt to submit value in only lower bound
        onView(withId(R.id.lightNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateLightButton)).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Erase values in lower bound
        onView(withId(R.id.lightNewLowVal)).perform(clearText());

        //Attempt to submit value in only lower bound
        onView(withId(R.id.lightNewUpperVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateLightButton)).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(emptyRangePhrase)));

        //Fill in equal values in lower bound to upper
        onView(withId(R.id.lightNewLowVal)).perform(typeText("20.2"), closeSoftKeyboard());
        onView(withId(R.id.updateLightButton)).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(invalidRangePhrase)));

        //Fill in higher values in lower bound than upper
        onView(withId(R.id.lightNewLowVal)).perform(clearText(), typeText("25.2"), closeSoftKeyboard());
        onView(withId(R.id.updateLightButton)).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(invalidRangePhrase)));

        //Fill in proper values in both bounds
        onView(withId(R.id.lightNewLowVal)).perform(clearText(), typeText("15.2"), closeSoftKeyboard());
        onView(withId(R.id.updateLightButton)).perform(click());
        onView(withId(R.id.lightUpdateMessage)).check(matches(withText(acceptedRangePhrase)));

    }
}
