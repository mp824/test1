package HES.Systems;

public class Dehumidifier extends Actuator{
    public Dehumidifier(float initialHumidity, boolean on) {
        super(initialHumidity, on,
            0.0005f,
            -0.0005f);
    }
    public float getCurrentHumidity() {
        if (getCurrentValue()>100){
            setCurrentValue(100);
        }
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
