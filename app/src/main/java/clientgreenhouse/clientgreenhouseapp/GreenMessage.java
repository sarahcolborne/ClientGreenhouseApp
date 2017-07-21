package clientgreenhouse.clientgreenhouseapp;

/**
 * This class is used to push message objects to firebase for the message board
 */

public class GreenMessage {
    private String name;
    private String message;
    private String dateTime;

    public GreenMessage(String n, String m, String d){
        name = n;
        message = m;
        dateTime = d;
    }

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    public String getDateTime(){
        return dateTime;
    }

}
