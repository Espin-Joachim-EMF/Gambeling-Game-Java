package Roulette.Modele;

public class Roulette {
    public static int MAX = 37;
    public static int MIN = 0;

    private static final int[] rouges = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    private static final int[] noirs = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};

    public Roulette() {}

    public int tourneRoulette() {
        int chiffre = (int) (Math.random() * (MAX - MIN + 1)) + MIN;
        return chiffre;
    }

    public String determineCouleur(int nombre) {
        if (nombre == 0 || nombre == 00) {
            return "Vert";
        }

        // Recherche dans les numéros rouges
        for (int i = 0; i < rouges.length; i++) {
            if (rouges[i] == nombre) {
                return "Rouge";
            }
        }

        for (int i = 0; i < noirs.length; i++) {
            if (noirs[i] == nombre) {
                return "Noir";
            }
        }

        return "Inconnu";
    }

    public void afficherToutesLesCouleursEtNombre() {
        System.out.print("Numéros Rouges : ");
        for (int i = 0; i < rouges.length; i++) {
            System.out.print(rouges[i] + (i < rouges.length - 1 ? ", " : ""));
        }
        System.out.println();

        System.out.print("Numéros Noirs  : ");
        for (int i = 0; i < noirs.length; i++) {
            System.out.print(noirs[i] + (i < noirs.length - 1 ? ", " : ""));
        }
        System.out.println();

        System.out.println("Numéros Verts  : 0"); // Seul 0 est vert en roulette européenne
    }

    public Roulette getRoulette() {
        return this;
    }

}
