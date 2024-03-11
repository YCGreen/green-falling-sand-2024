package green.fallingsand;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Sand sand = new Sand(50, 10);
        sand.randomSand(50);
        System.out.println(sand.toString());
        try {
            while (System.in.read() == 10) {
                onClick(sand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void onClick(Sand sand) {
        sand.fall();
        System.out.println(sand.toString());
    }
}
