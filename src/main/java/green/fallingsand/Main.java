package green.fallingsand;

import java.io.IOException;

public class Main {
    public static int ENTER = 10;

    public static void main(String[] args) {
        Sand sand = new Sand(50, 10);
        sand.randomSand(50);
        System.out.println(sand.toString());
        try {
            while (System.in.read() == ENTER) {
                onEnter(sand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void onEnter(Sand sand) {
        sand.fall();
        System.out.println(sand.toString());
    }
}
