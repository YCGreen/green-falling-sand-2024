package green.fallingsand;

public class Sand {
    private int[][] field;

    public void setField(int x, int y) {
        field = new int[y][x];
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
                if (field[y][x] == 1 && field[y + 1][x] == 0) {
                    field[y + 1][x] = 1;
                    field[y][x] = 0;
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
