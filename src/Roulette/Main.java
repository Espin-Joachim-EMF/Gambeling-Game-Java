package Roulette;

import Roulette.Modele.ControleurRoulette;
import Roulette.Modele.Roulette;
import Roulette.Modele.VueRoulette;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Combien de joueur ?");
        int nbrJoueur = scanner.nextInt();

        Roulette roulette = new Roulette();
        VueRoulette vueRoulette = new VueRoulette();
        ControleurRoulette controleurRoulette = new ControleurRoulette(nbrJoueur, 100);

        controleurRoulette.setRoulette(roulette);
        controleurRoulette.setVue(vueRoulette);
        controleurRoulette.start();
    }
}
