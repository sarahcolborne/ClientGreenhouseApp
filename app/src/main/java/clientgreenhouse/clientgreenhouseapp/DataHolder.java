package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Hugh on 2017-07-20.
 */

public class DataHolder {

    private static double tempData;
    private static double humidityData;
    private static double lightData;
    private static boolean notify;
    private static boolean first;
    private static boolean justClicked;

    private static DataHolder instance;

    private DataHolder(){

    }

    static {
        instance = new DataHolder();
        tempData = 0.0;
        humidityData = 0.0;
        lightData = 0.0;
        notify = true;
        first = true;
        justClicked = true;
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

    public static boolean getNotify() { return notify; }

    public static boolean getFirst() { return first; }

    public static boolean getJustClicked() { return justClicked; }

    public static void setTempData(double tempNum) {
        tempData = tempNum;
    }

    public static void setHumidityData(double humidNum) {
        humidityData = humidNum;
    }

    public static void setLightData(double lightNum) {
        lightData = lightNum;
    }

    public static void setNotifyTrue() {
        notify = true;
    }

    public static void setNotifyFalse() {
        notify = false;
    }

    public static void setFirstTrue(){
        first = true;
    }

    public static void setFirstFalse(){
        first = false;
    }

    public static void setJustClickedTrue() {
        justClicked = true;
    }

    public static void setJustClickedFalse(){
        justClicked = false;
    }
}
