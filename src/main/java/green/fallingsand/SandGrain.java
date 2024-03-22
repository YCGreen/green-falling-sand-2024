package green.fallingsand;

import java.util.Random;

public class SandGrain {
    public int color;
    public boolean visible;
    Random rand = new Random();

    public SandGrain() {
        visible = false;
        color = rand.nextInt(0, 4);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void turnVisible() {
        visible = true;
    }

    public void turnInvisible() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }
}
