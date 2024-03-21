package green.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandComponent extends JComponent {
    private final Sand sand;
    private Timer timer;

    public SandComponent(Sand sand) {
        this.sand = sand;
        timer = new Timer(10, evt -> {
            sand.fall();
            repaint();
            if (sand.isDoneFalling()) {
                timer.stop();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] sandField = sand.getField();

        // int sandSize = 2;
        int sandSize = Math.min(getWidth() / sandField[0].length, getHeight() / sandField.length);
        sandSize = Math.max(1, sandSize) * 2;

        g.setColor(new Color(0xCCAA00));

        for (int y = 0; y < sandField.length; y++) {
            for (int x = 0; x < sandField[y].length; x++) {
                if (sandField[y][x] == 1) {
                    g.fillOval(x * sandSize, y * sandSize, sandSize, sandSize);
                }
            }
        }


    }
}
