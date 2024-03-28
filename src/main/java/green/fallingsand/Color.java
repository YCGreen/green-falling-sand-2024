package green.fallingsand;

public enum Color {
    PINK(java.awt.Color.MAGENTA),
    BLUE(java.awt.Color.BLUE),
    YELLOW(java.awt.Color.YELLOW),
    GREEN(java.awt.Color.GREEN);

    private final java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color getColor() {
        return color;
    }

}
