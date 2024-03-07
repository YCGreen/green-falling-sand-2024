package green.fallingsand;

import java.util.Random;

public class Sand {
    private final int[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new int[height][width];
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
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
            for (int x = field[y].length - 1; x >= 0; x--) {

                if (field[y][x] == 1) {
                    //check if sand should fall straight down
                    if (field[y + 1][x] == 0) {
                        field[y + 1][x] = 1;
                        field[y][x] = 0;
                        continue;
                    }
                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? 1 : -1;
                    int direction2 = rightFirst ? -1 : 1;

                    if (field[y + 1][x + direction1] == 0) { //right
                        field[y + 1][x + direction1] = 1;
                        field[y][x] = 0;
                    } else if (field[y + 1][x + direction2] == 0) { //left
                        field[y + 1][x + direction2] = 1;
                        field[y][x] = 0;
                    }

                }
            }
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
