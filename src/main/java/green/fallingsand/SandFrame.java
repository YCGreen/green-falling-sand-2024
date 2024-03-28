package green.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandFrame extends JFrame {

    public SandFrame() {
        setSize(800, 600);
        setTitle("Falling Sand");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel sandPanel = new JPanel();
        sandPanel.setLayout(new BorderLayout());
        setContentPane(sandPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        sandPanel.add(buttonPanel, BorderLayout.EAST);

        Sand sand = new Sand(90, 90);
        sand.randomSand(600);

        SandComponent sandComponent = new SandComponent(sand);
        sandPanel.add(sandComponent, BorderLayout.CENTER);

        JButton pinkButton = new JButton("Pink");
        buttonPanel.add(pinkButton);

        JButton blueButton = new JButton("Blue");
        buttonPanel.add(blueButton);

        JButton yellowButton = new JButton("Yellow");
        buttonPanel.add(yellowButton);

        JButton greenButton = new JButton("Green");
        buttonPanel.add(greenButton);

        JButton resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);

        JButton repopulateButton = new JButton("Repopulate");
        buttonPanel.add(repopulateButton);

        pinkButton.addActionListener(evt -> sandComponent.setColor(Color.PINK));

        blueButton.addActionListener(evt -> sandComponent.setColor(Color.BLUE));

        yellowButton.addActionListener(evt -> sandComponent.setColor(Color.YELLOW));

        greenButton.addActionListener(evt -> sandComponent.setColor(Color.GREEN));

        resetButton.addActionListener(evt -> sand.clearField());

        repopulateButton.addActionListener(evt -> sand.randomSand(500));
    }

}
