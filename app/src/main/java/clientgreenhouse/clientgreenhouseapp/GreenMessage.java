package clientgreenhouse.clientgreenhouseapp;

import java.io.Serializable;

/**
 * This class is used to push message objects to firebase for the message board
 */

public class GreenMessage implements Serializable{
    public String name;
    public String message;
    public String dateTime;
    public String fbKey;

    public GreenMessage(){}

    public GreenMessage(String n, String m, String d, String k){
        name = n;
        message = m;
        dateTime = d;
        fbKey = k;
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

    public String toString(){ return name + message;}


}
