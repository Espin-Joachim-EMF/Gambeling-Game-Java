package Roulette.Modele;

public class Joueur {

    public String prenom;
    public int argent;
    public int mise;

    public Joueur(String prenom, int argent) {
        this.prenom = prenom;
        this.argent = argent;
        mise = 0;
    }
    public void miser(int mise) {
        this.mise = mise;
    }

    public void gagner() {
        argent += mise;
    }

    public void perdre() {
        argent -= mise;
    }

    public int getArgent() {
        return argent;
    }

    public String getPrenom() {
        return prenom;
    }

    public Joueur getJoueur() {
        return this;
    }
}
