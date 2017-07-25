package clientgreenhouse.clientgreenhouseapp;
/**
 * Container for 1 minute worth of SensorEntry objects for uploading to firebase
 * has a <code>getAverage</code> method for temperature, light, and humidity
 * Created by Ben on 2017-07-03.
 */

public class SensorLogMinute {
    public SensorEntry[] secs;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;


    public SensorLogMinute(){
        secs = new SensorEntry[60];
        size = 0;
        changed = false;
    }

    /**
     * extra constructor for testing that allows efficient loading of placeholder average values
     * @param test
     * @param temp
     * @param lux
     * @param humid
     */
    public SensorLogMinute(boolean test, double temp, double lux, double humid){
        if(test) {
            avgTemp = temp;
            avgHumid = humid;
            avgLux = lux;
            changed = false;
        }
        else {
            secs = new SensorEntry[60];
            size = 0;
            changed = false;
        }
    }

    public void setSize(int i){
        size = i;
    }

    public boolean addEntry(SensorEntry entry){
        if(size==60){
            return false;
        }
        secs[size]=entry;
        size++;
        changed = true;
        return true;
    }

    public double getAverageTemp(){
        if(!changed && avgTemp!=null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=secs[i].getTemp();
        }
        avgTemp = total/size;
        return avgTemp;
    }

    public double getAverageLux(){
        if(!changed && avgLux!=null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=secs[i].getLux();
        }
        avgLux=total/size;
        return avgLux;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid!=null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=secs[i].getHumid();
        }
        avgHumid=total/size;
        return avgHumid;
    }

}
