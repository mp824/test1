package HES.Systems;

import java.util.Random;

public class AirPurifier extends Actuator{
    public AirPurifier(float initialPollution,boolean on) {
        super(initialPollution,on,
            0.0000f,
            -0.0002f);
    }
    public float getCurrentPollution() {
        Random rand = new Random();
        int value = rand.nextInt(40);   // 1 to 20
        if (value==5) setCurrentValue(getCurrentValue()+15);
        return getCurrentValue();
    }
    @Override
    public boolean isOn() {
        return !super.isOn();
    }
    public void setOn(boolean on) {
        super.setOn(!on);
    }
}
