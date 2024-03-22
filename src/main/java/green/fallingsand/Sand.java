package green.fallingsand;

import java.util.Random;

public class Sand {
    private SandGrain[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new SandGrain[height][width];
        initializeGrains(field);
    }

    //for purposes of mockitoing
    public Sand(int width, int height, Random random) {
        field = new SandGrain[height][width];
        this.random = random;
        initializeGrains(field);
    }

    private void initializeGrains(SandGrain[][] someField) {
        for (int y = 0; y < someField.length; y++) {
            for (int x = 0; x < someField[y].length; x++) {
                someField[y][x] = new SandGrain();
            }
        }
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
        field[y][x].turnVisible();
    }

    /**
     * Moves all sand down one square
     */
    public void fall() {
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {

                if (field[y][x].isVisible() && y + 1 < field.length) { //straight
                    if (!field[y + 1][x].isVisible()) {
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
                && !field[y + 1][x + direction].isVisible();
    }

    private boolean isSafeLeft(int x, int y, int direction) {
        return x - direction >= 0 && x - direction < field[y + 1].length
                && !field[y + 1][x - direction].isVisible();
    }

    private void fallDirection(int x, int y, int direction) {
        field[y + 1][x + direction].turnVisible();
        field[y][x].turnInvisible();
    }

    public void randomSand(int n) {
        int num = Math.min(n, field.length * field[0].length);

        for (int i = 0; i < num; i++) {
            int y;
            int x;
            do {
                y = random.nextInt(field.length);
                x = random.nextInt(field[0].length);
            } while (field[y][x].isVisible());

            field[y][x].turnVisible();
        }
    }

    public void resize(int width, int height) {
        SandGrain[][] newField = new SandGrain[height][width];
        initializeGrains(newField);
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
                if (field[y][x].isVisible() && !field[y + 1][x].isVisible()) {
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
            for (int x = 0; x < field[y].length; x++) {
                field[y][x].turnInvisible();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                int sandNum = (field[y][x].isVisible()) ? 1 : 0;
                builder.append(sandNum);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}
