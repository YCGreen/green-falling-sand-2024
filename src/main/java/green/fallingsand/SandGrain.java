package green.fallingsand;

import java.util.Random;

public class SandGrain {
    public Color color;
    private static final Random rand = new Random();

    public SandGrain() {
        color = Color.values()[rand.nextInt(0, Color.values().length)];
    }

    public SandGrain(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public java.awt.Color getColor() {
        return color.getColor();
    }

}
