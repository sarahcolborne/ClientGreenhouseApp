package clientgreenhouse.clientgreenhouseapp;


/**
 * Created by Ben on 2017-07-03.
 */

public class SensorLogDay {
    public SensorLogHour[] hours;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;

    public SensorLogDay(){
        hours = new SensorLogHour[24];
        int size = 0;
        changed = false;
    }
    public SensorLogDay(boolean test, double temp, double lux, double humid){
        if(test){
            avgTemp=temp;
            avgLux=lux;
            avgHumid=humid;
            changed = false;
        }
        else{
            hours = new SensorLogHour[24];
            int size = 0;
            changed = false;
        }
    }

    public boolean addHour(SensorLogHour hour){
        if(size==24){
            return false;
        }
        hours[size]= hour;
        size++;
        changed = true;
        return true;
    }

    public double getAverageTemp(){
        if(!changed && avgTemp != null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=hours[i].getAverageTemp();
        }
        avgTemp = total/size;
        return avgTemp;
    }

    public double getAverageLux(){
        if(!changed && avgLux != null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=hours[i].getAverageLux();
        }
        avgLux= total/size;
        return avgLux;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid != null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=hours[i].getAverageHumid();
        }
        avgHumid=total/size;
        return avgHumid;
    }

    public boolean addEntry(SensorEntry entry){
        if(!hours[size-1].addEntry(entry)){
            if(this.addHour(new SensorLogHour())){
                return (hours[size-1].addEntry(entry));
            }
            else
                return false;
        }
        return true;
    }
}
