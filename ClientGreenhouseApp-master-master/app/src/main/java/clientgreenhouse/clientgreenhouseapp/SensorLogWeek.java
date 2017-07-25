package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Ben on 2017-07-03.
 */

public class SensorLogWeek {

    public SensorLogDay[] days;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;

    public SensorLogWeek(){
        days = new SensorLogDay[7];
        int size = 0;
        changed = false;
    }
    public SensorLogWeek(boolean test, double temp, double lux, double humid){
        if(test){
            avgTemp=temp;
            avgLux=lux;
            avgHumid=humid;
            changed = false;
        }
        else{
            days = new SensorLogDay[7];
            int size = 0;
            changed = false;
        }
    }

    public boolean addDay(SensorLogDay day){
        if(size==7){
            return false;
        }
        days[size]= day;
        size++;
        changed = true;
        return true;

    }

    public double getAverageTemp(){
        if(!changed && avgTemp != null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=days[i].getAverageTemp();
        }
        avgTemp = total/size;
        return avgTemp;
    }

    public double getAverageLux(){
        if(!changed && avgLux != null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=days[i].getAverageLux();
        }
        avgLux= total/size;
        return avgLux;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid != null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=days[i].getAverageHumid();
        }
        avgHumid=total/size;
        return avgHumid;
    }

    public boolean addEntry(SensorEntry entry){
        if(!days[size-1].addEntry(entry)){
            if(this.addDay(new SensorLogDay())){
                return (days[size-1].addEntry(entry));
            }
            else
                return false;
        }
        return true;
    }
}

