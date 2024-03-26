package green.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SandFrame extends JFrame {
    private final Sand sand = new Sand(90, 90);

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

        sand.randomSand(400);

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

        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                sandComponent.setColor(MyColor.PINK);
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                sandComponent.setColor(MyColor.BLUE);
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                sandComponent.setColor(MyColor.YELLOW);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                sandComponent.setColor(MyColor.GREEN);
            }
        });


    }

}
