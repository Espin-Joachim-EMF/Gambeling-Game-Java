package Craps.Modele;

public class Joueur {
    private int argent;
    private String prenom;

    public Joueur(int argent, String prenom) {
        this.argent = argent;
        this.prenom = prenom;
    }

    public int getArgent() {
        return argent;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean peutMiser(int mise) {
        return mise > 0 && mise <= argent;
    }

    public void ajoutArgent(int montant) {
        argent += montant;
    }

    public void enleverArgent(int montant) {
        argent -= montant;
    }
}
