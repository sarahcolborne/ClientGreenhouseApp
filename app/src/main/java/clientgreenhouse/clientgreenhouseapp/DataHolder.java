package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Hugh on 2017-07-20.
 */

public class DataHolder {

    private static String tempData;
    private static String humidityData;
    private static String lightData;

    public static String getTempData() {
        return tempData;
    }

    public static String getHumidityData(){
        return humidityData;
    }

    public static String getLightData() {
        return lightData;
    }

    public static void setTempData(String s) {
        tempData = s;
    }

    public static void setHumidityData(String s) {
        humidityData = s;
    }

    public static void setLightData(String s) {
        lightData = s;
    }
}
