package clientgreenhouse.clientgreenhouseapp;

/**
 * Created by Ben on 2017-07-03.
 */

public class SensorLogYear {
    public SensorLogMonth[] months;
    public int size;
    public boolean changed;
    public Double avgTemp;
    public Double avgLux;
    public Double avgHumid;

    public SensorLogYear(){
        months = new SensorLogMonth[12];
        int size = 0;
        addMonth(new SensorLogMonth());
        months[0].addWeek(new SensorLogWeek());
        months[0].weeks[0].addDay(new SensorLogDay());
        months[0].weeks[0].days[0].addHour(new SensorLogHour());
        months[0].weeks[0].days[0].hours[0].addMinute(new SensorLogMinute());
        changed = false;
    }
    public SensorLogYear(boolean test, double temp, double lux, double humid){
        if(test){
            avgTemp=temp;
            avgLux=lux;
            avgHumid=humid;
            changed = false;
        }
        else{
            months = new SensorLogMonth[12];
            int size = 0;
            changed = false;
        }
    }

    public boolean addMonth(SensorLogMonth month){
        if(size==12){
            return false;
        }
        months[size]= month;
        size++;
        changed = true;
        return true;
    }

    public double getAverageTemp(){
        if(!changed && avgTemp != null)
            return avgTemp;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=months[i].getAverageTemp();
        }
        avgTemp = total/size;
        return avgTemp;
    }

    public double getAverageLux(){
        if(!changed && avgLux != null)
            return avgLux;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=months[i].getAverageLux();
        }
        avgLux= total/size;
        return avgLux;
    }

    public double getAverageHumid(){
        if(!changed && avgHumid != null)
            return avgHumid;

        double total =0;
        for(int i=0; i<this.size; i++){
            total+=months[i].getAverageHumid();
        }
        avgHumid=total/size;
        return avgHumid;
    }

    public boolean addEntry(SensorEntry entry){
        if(!months[size-1].addEntry(entry)){
            if(this.addMonth(new SensorLogMonth())){
                return (months[size-1].addEntry(entry));
            }
            else
                return false;
        }
        return true;
    }
}
