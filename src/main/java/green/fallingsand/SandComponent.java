package green.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandComponent extends JComponent {
    private final Sand sand;

    public SandComponent(Sand sand) {
        this.sand = sand;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the sand
        //   g.setColor(Color.YELLOW); -- can't see particles

        int[][] sandField = sand.getField();

        int spacing = 2;
        //   int squareSize = 5;//
        int squareSize = Math.min(getWidth() / sandField.length, getHeight() / sandField[0].length);
        int dimensionSand = sandField[0].length < sandField.length ? sandField[0].length : sandField.length;
        int dimensionBox = getWidth() < getHeight() ? getWidth() : getHeight();
        //  int squareSize = dimensionSand < dimensionBox ? dimensionSand : dimensionBox;


        int heightRatio = getHeight() / (sandField[0].length * 3); //one element, one padding on each side
        int widthRatio = getWidth() / (sandField.length * 3);
        int circleSize = Math.min(heightRatio, widthRatio);
        System.out.println(circleSize);
        System.out.println(heightRatio);
        System.out.println(widthRatio);

        for (int y = 0; y < sandField.length; y++) {
            for (int x = 0; x < sandField[y].length; x++) {
          /*      if (sandField[y][x] == 1) {
                    int squareX = x * (squareSize + spacing);
                    int squareY = y * (squareSize + spacing);
                    g.fillOval(squareX, squareY, squareSize, squareSize);
                }
*/
                //  version without spacing
                if (sandField[y][x] == 1) {
                    g.fillOval(x, y, circleSize, circleSize);
                }

            }
        }


    }
}
