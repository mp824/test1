package HES.Systems;

import java.util.Random;

public class Heater extends Actuator{

    public Heater(float initialTemperature,boolean on) {
        super(initialTemperature,    on,
            0.0001f,   // heating rate (°C per ms)
            -0.0002f   // cooling rate (°C per ms)
            );
    }

    public float getTemperature() {
        Random rand = new Random();
        int value = rand.nextInt(25);   // 1 to 20
        if (value==5) setCurrentValue(getCurrentValue()-1);
        return getCurrentValue();
    }
}
