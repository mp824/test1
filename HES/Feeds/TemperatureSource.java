package HES.Feeds;

import HES.Systems.Heater;

public class TemperatureSource {
    Heater heater;
    public TemperatureSource(Heater heater) {
        this.heater=heater;
    }
    public float getTemperature() {
        return heater.getTemperature();
    }
}
