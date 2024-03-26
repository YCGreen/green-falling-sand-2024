package green.fallingsand;

import java.awt.*;

public enum MyColor {
    PINK(Color.MAGENTA),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN);

    private final Color color;

    MyColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
