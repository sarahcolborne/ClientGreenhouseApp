package clientgreenhouse.clientgreenhouseapp;
/**
 * Stores an array of 60 SensorLogMinute objects
 * has methods to obtain average values for temp, light, humid
 * Created by Ben on 2017-07-03.
 */

public class SensorLogHour {
    public SensorLogMinute[] mins;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;

    public SensorLogHour(){
        mins = new SensorLogMinute[60];
        int size = 0;
        changed = false;
    }
    public SensorLogHour(boolean test, double temp, double lux, double humid){
        if(test){
            avgTemp=temp;
            avgLux=lux;
            avgHumid=humid;
            changed = false;
        }
        else{
            mins = new SensorLogMinute[60];
            int size = 0;
            changed = false;
        }
    }

    public boolean addMinute(SensorLogMinute minute){
        if(size==60){
            return false;
        }
        mins[size]= minute;
        size++;
        changed = true;
        return true;
    }

    public double getAverageTemp(){
        if(!changed && avgTemp != null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=mins[i].getAverageTemp();
        }
        return total/size;
    }

    public double getAverageLux(){
        if(!changed && avgLux != null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=mins[i].getAverageLux();
        }
        return total/size;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid != null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=mins[i].getAverageHumid();
        }
        return total/size;
    }

    public boolean addEntry(SensorEntry entry){
        if(!mins[size-1].addEntry(entry)){
            if(this.addMinute(new SensorLogMinute())){
                return (mins[size-1].addEntry(entry));
            }
            else
                return false;
        }
        return true;
    }
}
