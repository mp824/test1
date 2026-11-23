package HES.Systems;

public abstract class Actuator {
    private boolean on;

    // Rates at which the quantity changes depending on ON/OFF state
    protected float increaseRate;
    protected float decreaseRate;

    // Current change rate (depends on ON/OFF)
    private float currentRate;

    // State used to compute the value at any point
    private float lastValueChanged;
    private long lastChangedTime;

    /**
     * @param initialValue      Starting value of the quantity (e.g., temperature, humidity)
     * @param isInitiallyOn     Whether the actuator starts in ON state
     * @param increaseRate      Rate of change per millisecond when ON   (positive)
     * @param decreaseRate      Rate of change per millisecond when OFF  (negative)
     */
    public Actuator(float initialValue,
                                  boolean isInitiallyOn,
                                  float increaseRate,
                                  float decreaseRate) {

        this.increaseRate = increaseRate;
        this.decreaseRate = decreaseRate;

        this.on = isInitiallyOn;
        this.currentRate = (isInitiallyOn ? increaseRate : decreaseRate);

        this.lastValueChanged = initialValue;
        this.lastChangedTime = System.currentTimeMillis();
    }

    /**
     * Turn on/off the actuator. Automatically updates the stored value
     * so that transitions between states are smooth.
     */
    public void setOn(boolean on) {
        if (this.on != on) {
            // Update current value before switching rate
            long now = System.currentTimeMillis();
            lastValueChanged = getCurrentValue();
            lastChangedTime = now;
            currentRate = (on ? increaseRate : decreaseRate);
        }
        this.on = on;
        System.out.println(on);
    }

    public boolean isOn() {
        return on;
    }

    /**
     * Calculate the current value based on elapsed time
     * since the last change in state.
     */
    public float getCurrentValue() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastChangedTime;
        return lastValueChanged + currentRate * elapsed;
    }
    public void setCurrentValue(float currentValue) {
        lastValueChanged=currentValue;
        lastChangedTime=System.currentTimeMillis();
    }
}