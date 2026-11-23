package HES.Feeds;

import HES.Systems.Dehumidifier;

public class HumidityFeed {
    private Dehumidifier dehumudifier;
    private OutsideHumidityFeed outsideHumidity;
    public HumidityFeed(Dehumidifier dehumudifier) {
        this.dehumudifier = dehumudifier;
        outsideHumidity=new OutsideHumidityFeed(70);
    }
    public int getInsideHumidity() {
        return (int)dehumudifier.getCurrentHumidity();
    }
    public int getOutsideHumidity() {
        return outsideHumidity.getOutsideHumidity();
    }
}
