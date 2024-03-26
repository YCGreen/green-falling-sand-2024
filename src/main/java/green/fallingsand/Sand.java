package green.fallingsand;

import java.util.Arrays;
import java.util.Random;

public class Sand {
    private SandGrain[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new SandGrain[height][width];
    }

    //for purposes of mockitoing
    public Sand(int width, int height, Random random) {
        field = new SandGrain[height][width];
        this.random = random;
    }

    /**
     * @return the value in field
     */
    public SandGrain getSand(int x, int y) {
        return field[y][x];
    }

    public SandGrain[][] getField() {
        return field;
    }

    /**
     * Sets the value in field to be 1
     */
    public void put(int x, int y) {
        if (validCoords(x, y)) {
            field[y][x] = new SandGrain();
        }
    }

    public void putColor(int x, int y, int color) {
        if (validCoords(x, y)) {
            field[y][x] = new SandGrain(color);
        }
    }

    private boolean validCoords(int x, int y) {
        return y >= 0 && y < field.length
                && x >= 0 && x < field[0].length;
    }

    public boolean isSandGrain(int x, int y) {
        if (validCoords(x, y)) {
            return field[y][x] != null;
        }
        return false;
    }

    /**
     * Moves all sand down one square
     */

    private boolean isSafeRight(int x, int y, int direction) {
        return 0 <= x + direction && x + direction < field[y + 1].length
                && !isSandGrain(x + direction, y + 1);
    }

    private boolean isSafeLeft(int x, int y, int direction) {
        return x - direction >= 0 && x - direction < field[y + 1].length
                && !isSandGrain(x - direction, y + 1);
    }

    private void fallDirection(int x, int y, int direction) {
        field[y + 1][x + direction] = field[y][x];
        field[y][x] = null;
    }

    public void fall() {
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {

                if (isSandGrain(x, y) && y + 1 < field.length) { //straight
                    if (!isSandGrain(x, y + 1)) {
                        fallDirection(x, y, 0);
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction = rightFirst ? 1 : -1;

                    if (isSafeRight(x, y, direction)) { //right
                        fallDirection(x, y, direction);
                    } else if (isSafeLeft(x, y, direction)) { //left
                        fallDirection(x, y, -direction);
                    }

                }
            }
        }
    }

    public void randomSand(int n) {
        int num = Math.min(n, field.length * field[0].length);

        for (int i = 0; i < num; i++) {
            int y;
            int x;
            do {
                y = random.nextInt(field.length);
                x = random.nextInt(field[0].length);
            } while (isSandGrain(x, y));

            put(x, y);
        }
    }

    public void setColorsAcrossField(int[] colors) {
        int colorIt = 0;
        int colorChangeFreq = field.length / colors.length;

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (isSandGrain(x, y)) {
                    field[y][x].setColor(colorIt);
                }
            }
            if (y % colorChangeFreq == 0) {
                colorIt = changeColor(colorIt, colors);
            }
        }
    }

    public void resize(int width, int height) {
        SandGrain[][] newField = new SandGrain[height][width];
        int minHeight = Math.min(field.length, newField.length);
        int minWidth = Math.min(field[0].length, newField[0].length);

        for (int y = 0; y < minHeight; y++) {
            System.arraycopy(field[y], 0, newField[y], 0, minWidth);
        }

        field = newField;
    }

    public void put(int x, int y, int width, int height, double probability) {
        int minHeight = Math.min(field.length, y + height);
        int minWidth = Math.min(field[0].length, x + width);

        for (int yPos = y; yPos < minHeight; yPos++) {
            for (int xPos = x; xPos < minWidth; xPos++) {
                if (random.nextDouble() <= probability) {
                    put(xPos, yPos);
                }
            }
        }
    }

    public void putColor(int x, int y, int width, int height, double probability, int color) {
        int minHeight = Math.min(field.length, y + height);
        int minWidth = Math.min(field[0].length, x + width);

        for (int yPos = y; yPos < minHeight; yPos++) {
            for (int xPos = x; xPos < minWidth; xPos++) {
                if (random.nextDouble() <= probability) {
                    putColor(xPos, yPos, color);
                }
            }
        }
    }

    public boolean isDoneFalling() {
        for (int y = 0; y < field.length - 1; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (isSandGrain(x, y) && !isSandGrain(x, y + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void load(String sandString) {
        clearField();

        int strPos = 0;
        int strRowLength = sandString.indexOf('\n');

        int width = Math.min(strRowLength, field[0].length);
        int height = Math.min(sandString.length() / strRowLength, field.length);

        for (int y = 0; y <= height; y++) {
            for (int x = 0; x <= width; x++) {
                if (strOutOfBounds(sandString, strPos)) break;
                char currentChar = sandString.charAt(strPos);
                strPos++;

                if (currentChar == '\n') {
                    continue;
                }

                if (currentChar == '1') {
                    put(x, y);
                }


            }
        }
    }

    private static boolean strOutOfBounds(String sandString, int strPos) {
        return strPos >= sandString.length();
    }

    private void clearField() {
        for (int y = 0; y < field.length; y++) {
            Arrays.fill(field[y], null);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                int sandNum = (isSandGrain(x, y)) ? 1 : 0;
                builder.append(sandNum);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private int changeColor(int colorIt, int[] colors) {
        int nextColor = ++colorIt;

        if (nextColor >= colors.length) {
            nextColor = 0;
        }

        return nextColor;
    }

}
