package Roulette.Modele;

import java.util.InputMismatchException;
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
        roulette.afficherToutesLesCouleursEtNombre();
        game();
    }


    public void remplireTableauJoueur() {
        for (int i = 0; i < tableauJoueur.length; i++) {
            if (tableauJoueur[i] == null) {
                System.out.println("Nom du joueur " + (i + 1));
                String prenom = scanner.nextLine();
                tableauJoueur[i] = new Joueur(prenom, argent);
            }
        }

    }

    public void afficheTabJoueur() {
        vueRoulette.afficheMessage("Voici les score de tout le monde");
        for (int i = 0; i < tableauJoueur.length; i++) {
            if (tableauJoueur[i] != null) {
                vueRoulette.afficheMessage("Joueur " + (i + 1) + " Argent : " + argent);
            }
        }
    }

    public void game() {
        boolean enJeu = true;
        int[] miseTab = new int[tableauJoueur.length];
        int[] nombreTab = new int[tableauJoueur.length];

        while (enJeu) {
            for (int i = 0; i < tableauJoueur.length; i++) {

                if (tableauJoueur[i].getArgent() != 0) {
                    if (tableauJoueur[i] != null) {
                        boolean miseValide = false;
                        int mise = 0;
                        while (!miseValide) {
                            try {
                                vueRoulette.afficheMessage("Bonjour " + tableauJoueur[i].getPrenom() + " quelle est votre mise ?");
                                mise = scanner.nextInt();

                                if (mise > 0 && mise <= tableauJoueur[i].getArgent()) {
                                    miseValide = true;
                                } else {
                                    vueRoulette.afficheMessage("Mise invalide, elle doit être un nombre positif et ne pas dépasser votre solde.");
                                }
                            } catch (InputMismatchException e) {
                                vueRoulette.afficheMessage("Entrée invalide. Veuillez entrer un nombre entier.");
                                scanner.nextLine();
                            }
                        }

                        boolean numeroValide = false;
                        int nombre = 0;
                        while (!numeroValide) {
                            try {
                                vueRoulette.afficheMessage("et sur quel numéro mise tu ?");
                                nombre = scanner.nextInt();

                                // Vérifier que le numéro est valide (par exemple, entre 0 et 36 pour la roulette)
                                if (nombre >= 0 && nombre <= 36) {
                                    numeroValide = true;
                                } else {
                                    vueRoulette.afficheMessage("Numéro invalide, il doit être entre 0 et 36.");
                                }
                            } catch (InputMismatchException e) {
                                vueRoulette.afficheMessage("Entrée invalide. Veuillez entrer un numéro entier.");
                                scanner.nextLine();
                            }
                        }
                    }
                }
            }

            int nombreTirer = roulette.tourneRoulette();
            vueRoulette.afficheMessage("Le chiffre choisis est...... " + nombreTirer + " !");
            vueRoulette.afficheMessage("Voici les gagnant");


        }
    }


    public void setRoulette(Roulette roulette) {
        this.roulette = roulette;
    }

    public void setVue(VueRoulette vueRoulette) {
        this.vueRoulette = vueRoulette;
    }


}
