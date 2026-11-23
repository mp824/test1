package HES.Feeds;

public class OutsideHumidityFeed {
    private int outsideHumidity;
    public OutsideHumidityFeed(int outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }
    public int getOutsideHumidity() {
        outsideHumidity=(int)getSinusoidalValue();
        return outsideHumidity;
    }
    private float getSinusoidalValue() {
        long now = System.currentTimeMillis();

        // 5 minutes in milliseconds
        final long period = 300_000L;

        // amplitude and midpoint for 60–80
        final float min = 60f;
        final float max = 80f;
        final float midpoint = (min + max) / 2f;
        final float amplitude = (max - min) / 2f;

        // convert to radians: 2π * t / period
        double phase = 2 * Math.PI * (now % period) / period;

        return (float)(midpoint + amplitude * Math.sin(phase));
    }
}
