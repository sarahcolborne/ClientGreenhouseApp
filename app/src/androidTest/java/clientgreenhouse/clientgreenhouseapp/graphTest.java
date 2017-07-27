package clientgreenhouse.clientgreenhouseapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Sean and Asma and Ben.
 */

public class graphTest {

    @Rule
    public ActivityTestRule<GraphViewActivity> graphActivityRule = new ActivityTestRule<>(
            GraphViewActivity.class);

    @Test
    public void tempGraphTest() {
        onView(withId(R.id.graphTEMPERATURE)).check(matches(isDisplayed()));

    }
    @Test
    public void humidityGraphTest() {
        onView(withId(R.id.graphHUMIDITY)).check(matches(isDisplayed()));
    }
    @Test
    public void luxGraphTest() {

        onView(withId(R.id.graphLIGHT)).perform(scrollTo()).check(matches(isDisplayed()));
    }
}