package HES.Systems;

import HES.Feeds.HumidityFeed;
import HES.Feeds.PollutionFeed;
import HES.Feeds.TemperatureFeed;
import HES.Scanners.CTScanner;
import HES.Scanners.UltrasoundScanner;

import java.util.Random;

public class EnvironmentalSystem {
    private TemperatureFeed tempFeed;
    private HumidityFeed humidityFeed;
    private PollutionFeed pollutionFeed;
    private Heating heating;
    private Dehumidifier dehumidifier;
    private AirPurifier airPurifier;
    private UltrasoundScanner ultrasoundScanner=new UltrasoundScanner();
    private CTScanner ctScanner=new CTScanner();
    public EnvironmentalSystem() {
        Random rand = new Random();
        int value = 60 + rand.nextInt(21);

        dehumidifier = new Dehumidifier(60 + rand.nextInt(21),true);
        airPurifier = new AirPurifier(10,true);

        heating = new Heating();
        tempFeed = new TemperatureFeed(heating);
        humidityFeed = new HumidityFeed(dehumidifier);
        pollutionFeed = new PollutionFeed(airPurifier);
    }
    public TemperatureFeed getTempFeed() {
        return tempFeed;
    }
    public HumidityFeed getHumidityFeed() {
        return humidityFeed;
    }
    public PollutionFeed getPollutionFeed() {
        return pollutionFeed;
    }
    public void turnHeatingOn(int wardID, boolean on) {
        heating.getHeater(wardID).setOn(on);
    }
    public void turnDehumidifierOn(boolean on) {
        dehumidifier.setOn(on);
    }
    public void turnAirPurifierOn(boolean on) {
        airPurifier.setOn(on);
    }
    public boolean isHeatingOn(int wardID) {
        return heating.getHeater(wardID).isOn();
    }
    public boolean isDehumidifierOn() {
        return dehumidifier.isOn();
    }
    public boolean isAirPurifierOn() {
        return airPurifier.isOn();
    }
    public String getUltrasoundScannerStatus(){
        return ultrasoundScanner.getStatus();
    }
    public String getCTScannerStatus(){
        return ctScanner.getStatus();
    }
}
