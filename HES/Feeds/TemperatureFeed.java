package HES.Feeds;

import HES.Systems.Heating;

import java.util.ArrayList;

public class TemperatureFeed {
    private ArrayList<TemperatureSource> tempFeeds=new ArrayList<>();
    private Heating heating;

    public TemperatureFeed(Heating heating) {
        tempFeeds.add(new TemperatureSource(heating.getHeater(0)));
        tempFeeds.add(new TemperatureSource(heating.getHeater(1)));
        tempFeeds.add(new TemperatureSource(heating.getHeater(2)));
    }
    public float getTemperature(int wardID){
        return tempFeeds.get(wardID).getTemperature();
    }
}
