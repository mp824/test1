package HES.Feeds;

import HES.Systems.AirPurifier;

public class PollutionFeed {
    private AirPurifier airPurifier;
    public PollutionFeed(AirPurifier airPurifier) {
        this.airPurifier = airPurifier;
    }
    public float getPollution() {
        return airPurifier.getCurrentPollution();
    }
}
