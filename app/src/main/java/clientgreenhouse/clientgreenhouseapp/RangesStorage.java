package clientgreenhouse.clientgreenhouseapp;

/**
 * This class uses the singleton pattern to ensure that only one copy exists.
 * Help from here: https://stackoverflow.com/questions/27782769/how-to-make-sure-that-there-is-just-one-instance-of-class-in-jvm
 */

public class RangesStorage {
    public static Double tempLowRange, tempHighRange;
    public static Double humidLowRange, humidHighRange;
    public static Double lightLowRange, lightHighRange;

    private static RangesStorage instance;

    private RangesStorage() {

    }

    //creates the single instance
    static {
        instance = new RangesStorage();
        tempLowRange = 0.0;
        tempHighRange = 0.0;
        humidLowRange = 0.0;
        humidHighRange = 0.0;
        lightLowRange = 0.0;
        lightHighRange = 0.0;
    }

    //use this method to access the instance
    public static RangesStorage getInstance() {
        return instance;
    }


    //Get methods
    public Double getTempLowRange() {
        return tempLowRange;
    }

    public Double getTempHighRange() {
        return tempHighRange;
    }

    public Double getHumidLowRange() {
        return humidLowRange;
    }

    public Double getHumidHighRange() {
        return humidHighRange;
    }

    public Double getLightLowRange() {
        return lightLowRange;
    }

    public Double getLightHighRange() {
        return lightHighRange;
    }


    //Set methods
    public void setTempLowRange(Double newVal) {
        tempLowRange = newVal;
    }

    public void setTempHighRange(Double newVal) {
        tempHighRange = newVal;;
    }

    public void setHumidLowRange(Double newVal) {
        humidLowRange = newVal;;
    }

    public void setHumidHighRange(Double newVal) {
        humidHighRange = newVal;;
    }

    public void setLightLowRange(Double newVal) {
        lightLowRange = newVal;;
    }

    public void setLightHighRange(Double newVal) {
        lightHighRange = newVal;;
    }

}
