package green.fallingsand;

import java.util.Random;

public class SandGrain {
    public int color;
    Random rand = new Random();

    public SandGrain() {
        color = rand.nextInt(0, 4);
    }

    public SandGrain(int color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

}
