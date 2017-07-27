package clientgreenhouse.clientgreenhouseapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.google.android.gms.common.ConnectionResult.TIMEOUT;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Hugh on 2017-07-06.
 */

@RunWith(AndroidJUnit4.class)
public class NotificationServiceTest {

        private final static String NOTIFICATION_TITLE_0 = "Out of Range!";
        private final static String NOTIFICATION_TEXT_0 = "Temperature | Humidity | ";
        private final static String NOTIFICATION_TITLE_1 = "NEW MESSAGE(S)";

        @Rule
        public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
                MainActivity.class);

        @Test
        public void notificationExistTest() {
                //Access Range activity
                onView(withId(R.id.rangesButton)).perform(click());
                //Insert and update values for temperature
                onView(withId(R.id.tempNewLowVal)).perform(typeText("20.0"), closeSoftKeyboard());
                onView(withId(R.id.tempNewUpperVal)).perform(typeText("22.0"), closeSoftKeyboard());
                onView(withId(R.id.updateTempButton)).perform(click());
                //Insert and update values for humidity
                onView(withId(R.id.humidityNewLowVal)).perform(typeText("35.0"), closeSoftKeyboard());
                onView(withId(R.id.humidityNewUpperVal)).perform(typeText("45.0"), closeSoftKeyboard());
                onView(withId(R.id.updateHumidityButton)).perform(click());
                //Insert and update values for light
                /*
                onView(withId(R.id.lightNewLowVal)).perform(typeText("0"), closeSoftKeyboard());
                onView(withId(R.id.lightNewUpperVal)).perform(typeText("0"), closeSoftKeyboard());
                onView(withId(R.id.updateLightButton)).perform(click());
                */
                //Navigate back to home screen
                onView(withId(R.id.homeButton2)).perform(click());
                //Turn on alerts
                onView(withId(R.id.button2)).perform(click());
                //Check for notification
                UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
                device.openNotification();
                device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE_0)), TIMEOUT);
                UiObject2 title = device.findObject(By.text(NOTIFICATION_TITLE_0));
                UiObject2 text = device.findObject(By.text(NOTIFICATION_TEXT_0));
                assertEquals(NOTIFICATION_TITLE_0, title.getText());
                assertEquals(NOTIFICATION_TEXT_0, text.getText());
                UiObject2 title1 = device.findObject(By.text(NOTIFICATION_TITLE_1));
                assertEquals(NOTIFICATION_TITLE_1, title1.getText());
        }
}
