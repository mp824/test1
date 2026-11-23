import HES.Feeds.HumidityFeed;
import HES.Feeds.PollutionFeed;
import HES.Feeds.TemperatureFeed;
import HES.Systems.EnvironmentalSystem;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    static EnvironmentalSystem env = new EnvironmentalSystem();
    static TemperatureFeed tempFeed = env.getTempFeed();
    static HumidityFeed humidFeed = env.getHumidityFeed();
    static PollutionFeed pollFeed = env.getPollutionFeed();
    static String[] wardNames = {"North Ward","Central Ward","South Ward"};
    static double[] targetTemp = {23,22,20};
    static JFrame frame;
    static JPanel[] panels = new JPanel[5];
    static JLabel[] labels = new JLabel[5];
    static int seconds = 0;

    public static void main(String[] args){
        buildWindow();
        updateAll();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAll();
                seconds++;
                if (seconds%30 == 0) logToConsole();
                frame.repaint();
            }
        });
        timer.start();

    }
    static void buildWindow(){
        frame = new JFrame("Children's Wing Monitor");
        frame.setSize(700,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,3));
        for  (int i =0; i < 5; i++) {
            panels[i] = new JPanel();
            labels[i] = new JLabel();
            panels[i].add(labels[i]);
            frame.add(panels[i]);
            labels[i].setText("Cell " + i);
        }
        frame.setVisible(true);
    }
    static void updateTemperature(int ward) {
        float temp = tempFeed.getTemperature(ward);
        boolean outOfRange = Math.abs(temp-targetTemp[ward]) > 0.5;
        panels[ward].setBackground(outOfRange ? Color.red : null);
        if (temp < targetTemp[ward] - 0.5) env.turnHeatingOn(ward, true);
        if (temp > targetTemp[ward] + 0.5) env.turnHeatingOn(ward, false);
        String text = "<html>" + wardNames[ward] + "<br>Current: " + String.format("%.1f",temp) + "<br>Ideal: " + targetTemp[ward] + "<br>Heating: " + (env.isHeatingOn(ward) ? "ON" : "OFF") + "</html>";
        labels[ward].setText(text);
    }
    static void updateHumidity() {
        int inside = humidFeed.getInsideHumidity();
        int outside = humidFeed.getOutsideHumidity();
        int diff = inside - outside;
        boolean outOfRange = diff > 10;
        panels[3].setBackground(outOfRange ? Color.red : null);
        env.turnDehumidifierOn(diff > 10);
        String text = "<html>" + "Humidity" + "<br>Inside: " + inside + "%" + "<br>Outside: " + outside + "%" + "<br>Difference: " + diff + "%" + "<br>Dehumidifier: " + (env.isDehumidifierOn() ? "ON" : "OFF") + "</html>";
        labels[3].setText(text);
    }
    static void updatePollution() {
        float pollution = pollFeed.getPollution();
        boolean outOfRange = pollution > 5;
        panels[4].setBackground(outOfRange ? Color.red : null);
        env.turnAirPurifierOn(pollution > 5);
        String text = "<html>" + "Air Pollution (PM2.5)" + "<br>Level: " + pollution + "<br>Purifier: " + (env.isAirPurifierOn() ? "ON" : "OFF") + "</html>";
        labels[4].setText(text);
    }
    static void updateAll() {
        for (int i=0; i < 3; i++) {
            updateTemperature(i);
        }
        updateHumidity();
        updatePollution();
    }
    static void logToConsole() {
        System.out.println("--- Log ---");
        for (int i=0; i < 5; i++) {
            String t = labels[i].getText().replace("<br>","\n").replace("<html>","").replace("</html>","");
            System.out.println(t);
            System.out.println();
        }
        System.out.println("Ultrasound: " + env.getUltrasoundScannerStatus());
        System.out.println("CT: " + env.getCTScannerStatus());
    }
}
