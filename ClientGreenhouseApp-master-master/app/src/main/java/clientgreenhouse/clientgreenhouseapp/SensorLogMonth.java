package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Ben on 2017-07-03.
 */

public class SensorLogMonth {

    public SensorLogWeek[] weeks;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;

    public SensorLogMonth(){
        weeks = new SensorLogWeek[4];
        int size = 0;
        changed = false;
    }
    public SensorLogMonth(boolean test, double temp, double lux, double humid){
        if(test){
            avgTemp=temp;
            avgLux=lux;
            avgHumid=humid;
            changed = false;
        }
        else{
            weeks = new SensorLogWeek[4];
            int size = 0;
            changed = false;
        }
    }

    public boolean addWeek(SensorLogWeek week){
        if(size==4){
            return false;
        }
        weeks[size]= week;
        size++;
        changed=true;
        return true;
    }

    public double getAverageTemp(){
        if(!changed && avgTemp != null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=weeks[i].getAverageTemp();
        }
        avgTemp = total/size;
        return avgTemp;
    }

    public double getAverageLux(){
        if(!changed && avgLux != null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=weeks[i].getAverageLux();
        }
        avgLux= total/size;
        return avgLux;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid != null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=weeks[i].getAverageHumid();
        }
        avgHumid=total/size;
        return avgHumid;
    }

    public boolean addEntry(SensorEntry entry){
        if(!weeks[size-1].addEntry(entry)){
            if(this.addWeek(new SensorLogWeek())){
                return (weeks[size-1].addEntry(entry));
            }
            else
                return false;
        }
        return true;
    }
}
