package green.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandFrame extends JFrame {
    private final Sand sand = new Sand(300, 400);

    public SandFrame() {
        setSize(800, 600);
        setTitle("Falling Sand");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        try {
            sand.randomSand(4000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        SandComponent sandComponent = new SandComponent(sand);
        add(sandComponent, BorderLayout.CENTER);
    }

}
