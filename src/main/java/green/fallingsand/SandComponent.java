package green.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class SandComponent extends JComponent {
    private final Sand sand;
    private SandGrain[][] sandField;
    private Timer timer;
    private int sandSize = 1;
    private final Random rand = new Random();
    MyColor currColor = MyColor.values()[0];

    public SandComponent(Sand sand) {
        this.sand = sand;
        sand.setColorsAcrossField();
        SandGrain[][] sandField = sand.getField();

        timer = new Timer(100, evt -> {
            sand.fall();
            repaint();
            if (sand.isDoneFalling()) {
                timer.stop();
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / sandSize;
                int y = e.getY() / sandSize;
                sand.putColor(x, y, currColor);
                repaint();
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
                int x = e.getX() / sandSize;
                int y = e.getY() / sandSize;
                sand.putColor(x, y, 5, 5, .2, currColor);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        SandGrain[][] sandField = sand.getField();
        super.paintComponent(g);

        sandSize = Math.min(getWidth() / sandField[0].length, getHeight() / sandField.length);
        sandSize = Math.max(1, sandSize);

        for (int y = 0; y < sandField.length; y++) {
            for (int x = 0; x < sandField[y].length; x++) {
                if (sand.isSandGrain(x, y)) {
                    g.setColor(sandField[y][x].getColor());
                    g.fillOval(x * sandSize, y * sandSize, sandSize, sandSize);
                }
            }
        }
        timer.start();

    }

    public void setColor(MyColor color) {
        currColor = color;
    }

}
