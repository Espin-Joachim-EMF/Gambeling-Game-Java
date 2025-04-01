package Craps.Modele;

import java.util.Random;

public class Dés {
    private int faces;
    private Random random;

    public Dés(int faces) {
        this.faces = faces;
        this.random = new Random();
    }

    public int tireDes() {
        return random.nextInt(faces) + 1;
    }
}
