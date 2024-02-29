package green.fallingsand;

public class Sand {
    private int fieldX = 3;
    private int fieldY = 3;
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
        for(int y = 0; y < fieldY; y++) {
            for(int x = 0; x < fieldX; x++) {
                if(field[y][x] == 1) {
                    boolean atBottom;
                    do {
                        atBottom = fallSingular(x, y);
                    } while (!atBottom);
                }
            }
        }
    }

    public boolean fallSingular(int x, int y) {
        boolean isBottom = true;
        if(y < fieldY - 1 && field[y + 1][x] == 0){
            field[x][y+1] = 1;
            isBottom = false;
        }
        return isBottom;
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
