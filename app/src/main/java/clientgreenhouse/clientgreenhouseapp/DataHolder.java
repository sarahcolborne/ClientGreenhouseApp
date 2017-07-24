package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Hugh on 2017-07-20.
 */

public class DataHolder {

    private static double tempData;
    private static double humidityData;
    private static double lightData;

    private static DataHolder instance;

    private DataHolder(){

    }

    static {
        instance = new DataHolder();
        tempData = 0.0;
        humidityData = 0.0;
        lightData = 0.0;
    }

    public static DataHolder getInstance() {
        return instance;
    }

    public static double getTempData() {
        return tempData;
    }

    public static double getHumidityData(){
        return humidityData;
    }

    public static double getLightData() {
        return lightData;
    }

    public static void setTempData(double tempNum) {
        tempData = tempNum;
    }

    public static void setHumidityData(double humidNum) {
        humidityData = humidNum;
    }

    public static void setLightData(double lightNum) {
        lightData = lightNum;
    }
}
