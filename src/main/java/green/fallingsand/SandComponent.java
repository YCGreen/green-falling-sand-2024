package green.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class SandComponent extends JComponent {
    private final Sand sand;
    private Timer timer;
    private final Color[] colors;
    private int colorIt = 0;
    private int sandSize = 1;
    private final Random rand = new Random();

    public SandComponent(Sand sand) {
        this.sand = sand;
        timer = new Timer(100, evt -> {
            sand.fall();
            repaint();
            if (sand.isDoneFalling()) {
                timer.stop();
            }
        });

        colors = new Color[]{Color.MAGENTA, Color.BLUE, Color.GREEN, Color.YELLOW};

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sand.put(e.getX() / sandSize, e.getY() / sandSize);
                colorIt = rand.nextInt(colors.length);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                sand.put(e.getX() / sandSize, e.getY() / sandSize, 5, 5, .2);
                colorIt = rand.nextInt(colors.length);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        SandGrain[][] sandField = sand.getField();

        sandSize = Math.min(getWidth() / sandField[0].length, getHeight() / sandField.length);
        sandSize = Math.max(1, sandSize);

        for (int y = 0; y < sandField.length; y++) {
            for (int x = 0; x < sandField[y].length; x++) {
                if (sandField[y][x].isVisible()) {
                    g.setColor(colors[sandField[y][x].getColor()]);
                    g.fillOval(x * sandSize, y * sandSize, sandSize, sandSize);
                }
            }
        }
        timer.start();

    }

}
