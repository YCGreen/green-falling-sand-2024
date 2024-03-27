package green.fallingsand;

import java.awt.*;
import java.util.Random;

public class SandGrain {
    public MyColor color;
    Random rand = new Random();

    public SandGrain() {
        color = MyColor.values()[rand.nextInt(0, MyColor.values().length)];
    }

    public SandGrain(MyColor color) {
        this.color = color;
    }

    public void setColor(MyColor color) {
        this.color = color;
    }

    public Color getColor() {
        return color.getColor();
    }

}
