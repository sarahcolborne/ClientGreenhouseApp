package clientgreenhouse.clientgreenhouseapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Sean and Asma and Ben.
 */

public class graphTest {

    @Rule
    public ActivityTestRule<GraphActivity> graphActivityRule = new ActivityTestRule<>(
            GraphActivity.class);

    @Test
    public void tempTest() {
        onView(withId(R.id.graphTEMPERATURE)).check(matches(isDisplayed()));

    }
    @Test
    public void humidityTest() {
        onView(withId(R.id.graphHUMIDITY)).check(matches(isDisplayed()));
    }
    @Test
    public void luxTest() {
        onView(withId(R.id.graphLIGHT)).check(matches(isDisplayed()));
    }
}