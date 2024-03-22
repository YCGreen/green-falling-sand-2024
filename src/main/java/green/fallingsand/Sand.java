package green.fallingsand;

import java.util.Arrays;
import java.util.Random;

public class Sand {
    private int[][] field;
    private int[][] origField; //for repainting sandComponent
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new int[height][width];
        origField = new int[height][width];
    }

    //for purposes of mockitoing
    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }

    public int[][] getOrigField() {
        return origField;
    }

    public void resetField() {
        field = origField;
    }

    /**
     * @return the value in field
     */
    public int getSand(int x, int y) {
        return field[y][x];
    }

    public int[][] getField() {
        return field;
    }

    /**
     * Sets the value in field to be 1
     */
    public void put(int x, int y) {
        field[y][x] = 1;
    }

    /**
     * Moves all sand down one square
     */
    public void fall() {
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {

                if (field[y][x] == 1 && y + 1 < field.length) { //straight
                    if (field[y + 1][x] == 0) {
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

    private boolean isSafeRight(int x, int y, int direction) {
        return 0 <= x + direction && x + direction < field[y + 1].length
                && field[y + 1][x + direction] == 0;
    }

    private boolean isSafeLeft(int x, int y, int direction) {
        return x - direction >= 0 && x - direction < field[y + 1].length
                && field[y + 1][x - direction] == 0;
    }

    private void fallDirection(int x, int y, int direction) {
        field[y + 1][x + direction] = 1;
        field[y][x] = 0;
    }

    public void randomSand(int n) {
        int num = Math.min(n, field.length * field[0].length);

        for (int i = 0; i < num; i++) {
            int y;
            int x;
            do {
                y = random.nextInt(field.length);
                x = random.nextInt(field[0].length);
            } while (field[y][x] == 1);

            field[y][x] = 1;
            origField[y][x] = 1;
        }
    }

    public void resize(int width, int height) {
        int[][] newField = new int[height][width];
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

    public boolean isDoneFalling() {
        for (int y = 0; y < field.length - 1; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1 && field[y + 1][x] == 0) {
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
            Arrays.fill(field[y], 0);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}
