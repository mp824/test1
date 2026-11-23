package HES.Systems;

import java.util.ArrayList;
import java.util.Random;


public class Heating {
    private ArrayList<Heater> heaters=new ArrayList<>();
    public Heating() {
        Random rand = new Random();
        int value = 18 + rand.nextInt(6);
        heaters.add(new Heater(value,true));
        value = 18 + rand.nextInt(6);
        heaters.add(new Heater(value,true));
        value = 18 + rand.nextInt(6);
        heaters.add(new Heater(value,true));
    }
    public Heater getHeater(int heaterID) {
        return heaters.get(heaterID);
    }
}
