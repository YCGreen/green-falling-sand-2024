package green.fallingsand;

import java.io.IOException;

import static java.awt.event.KeyEvent.VK_ENTER;

public class Main {
    public static void main(String[] args) {
        Sand sand = new Sand(50, 10);
        sand.randomSand(50);
        System.out.println(sand.toString());
        try {
            while (System.in.read() == VK_ENTER) {
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
