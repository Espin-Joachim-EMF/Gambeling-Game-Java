package Roulette.Modele;

import java.util.PrimitiveIterator;
import java.util.Scanner;

public class ControleurRoulette {
    private Roulette roulette;

    private Joueur[] tableauJoueur;

    private VueRoulette vueRoulette;

    private Scanner scanner;

    private int argent;

    public ControleurRoulette(int nbrJoueur, int argent) {
        this.roulette = null;
        this.vueRoulette = null;
        this.argent = argent;

        scanner = new Scanner(System.in);
        tableauJoueur = new Joueur[nbrJoueur];
    }

    public void start() {
        remplireTableauJoueur();
        afficheTabJoueur();
        roulette.afficherToutesLesCouleurs();
    }


    public void remplireTableauJoueur() {
        for (int i = 0; i < tableauJoueur.length; i++) {
            if (tableauJoueur[i] == null) {
                System.out.println("Nom du joureur " + (i + 1));
                String prenom = scanner.nextLine();
                tableauJoueur[i] = new Joueur(prenom, argent);
            }
        }

    }

    public void afficheTabJoueur() {
        for (int i = 0; i < tableauJoueur.length; i++) {
            if (tableauJoueur[i] != null) {
                vueRoulette.afficheMessage("Voici les score de tout le monde");
                vueRoulette.afficheMessage("Joueur " + (i + 1) + " Argent : " + argent);
            }
        }
    }

    public void donnerchiffre() {

    }

    public void setRoulette(Roulette roulette) {
        this.roulette = roulette;
    }

    public void setVue(VueRoulette vueRoulette) {
        this.vueRoulette = vueRoulette;
    }


}
