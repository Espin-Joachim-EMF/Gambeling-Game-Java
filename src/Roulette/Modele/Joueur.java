package Roulette.Modele;

public class Joueur {

    private final String prenom;
    private int argent;

    public Joueur(String prenom, int argent) {
        this.prenom = prenom;
        this.argent = argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
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
