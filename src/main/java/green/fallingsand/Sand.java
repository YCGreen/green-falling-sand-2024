package green.fallingsand;

import java.util.Random;

public class Sand {
    private final int[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new int[height][width];
    }

    //for purposes of mockitoing
    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
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
                        fallDirection(y, x, 0);
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction = rightFirst ? 1 : -1;

                    if (isSafeRight(x, y, direction)) { //right
                        fallDirection(y, x, direction);
                    } else if (isSafeLeft(x, y, direction)) { //left
                        fallDirection(y, x, -direction);
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

    private void fallDirection(int y, int x, int direction) {
        field[y + 1][x + direction] = 1;
        field[y][x] = 0;
    }

    public void randomSand(int n) throws Exception {
        if (n > field.length * field[0].length) {
            throw new Exception("Sand to be added exceeds size of field");
        }
        for (int i = 0; i < n; i++) {
            int y;
            int x;
            do {
                y = random.nextInt(field.length);
                x = random.nextInt(field[0].length);
            } while (field[y][x] == 1);

            field[y][x] = 1;
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
