package green.fallingsand;

public class Sand {
    private static final int fieldX = 3;
    private static final int fieldY = 3;
    private int[][] field = new int[fieldX][fieldY];

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
        for (int y = fieldY - 1; y >= 0; y--) {
            for (int x = fieldX - 1; x >= 0; x--)  {
                if (field[y][x] == 1 && y < fieldY - 1 && field[y + 1][x] == 0) {
                    field[y + 1][x] = 1;
                    field[y][x] = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < fieldY; y++) {
            for (int x = 0; x < fieldX; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
