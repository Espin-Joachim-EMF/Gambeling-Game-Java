package Craps;

import Craps.Modele.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog("Nombre de joueurs ?");
            int nbJoueurs = Integer.parseInt(input);
            new Craps(nbJoueurs,100);
        });
    }
}
