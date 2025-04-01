package Craps.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Craps {
    private Joueur[] joueurs;
    private Dés de1, de2;
    private int joueurActuel;
    private int point;
    private boolean enJeu;
    private int miseActuelle;

    private JFrame frame;
    private JLabel labelArgent, labelMessage, labelResultatDes, labelPoint;
    private JTextField champMise;
    private JButton boutonMiser, boutonLancer;

    public Craps(int nbJoueurs, int argentInitial) {
        joueurActuel = 0;
        enJeu = false;
        de1 = new Dés(6);
        de2 = new Dés(6);
        miseActuelle = 0;

        joueurs = new Joueur[nbJoueurs];
        for (int i = 0; i < joueurs.length; i++) {
            String prenom = JOptionPane.showInputDialog("Entrez le prénom du joueur " + (i + 1));
            joueurs[i] = new Joueur(argentInitial, prenom);
        }

        // Configuration de la fenêtre Swing
        frame = new JFrame("Jeu de Craps 🎲");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Zone d'affichage des dés et points
        labelResultatDes = new JLabel("Résultat des dés : -");
        labelResultatDes.setFont(new Font("Arial", Font.BOLD, 30));
        labelResultatDes.setHorizontalAlignment(SwingConstants.CENTER);

        labelPoint = new JLabel("Point : -");
        labelPoint.setFont(new Font("Arial", Font.BOLD, 30));
        labelPoint.setHorizontalAlignment(SwingConstants.CENTER);

        labelMessage = new JLabel("C'est au tour de " + joueurs[joueurActuel].getPrenom() + " !");
        labelMessage.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(labelMessage, BorderLayout.NORTH);
        frame.add(labelResultatDes, BorderLayout.CENTER);
        frame.add(labelPoint, BorderLayout.SOUTH);

        // Zone de mise et actions 💰
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        labelArgent = new JLabel("Argent : " + joueurs[joueurActuel].getArgent() + "€");
        champMise = new JTextField(10);
        boutonMiser = new JButton("Miser");
        boutonLancer = new JButton("Lancer les dés");
        boutonLancer.setEnabled(false); // Désactivé tant que la mise n'est pas faite

        panel.add(labelArgent);
        panel.add(new JLabel("Mise : "));
        panel.add(champMise);
        panel.add(boutonMiser);
        panel.add(boutonLancer);

        frame.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);

        // Ajout des écouteurs d'événements 🎮
        boutonMiser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placerMise();
                mettreAJourAffichage();
            }
        });

        boutonLancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lancerDes();
                mettreAJourAffichage();
            }
        });
    }

    private void placerMise() {
        try {
            int mise = Integer.parseInt(champMise.getText());
            Joueur joueur = joueurs[joueurActuel];

            if (mise <= 0 || !joueur.peutMiser(mise)) {
                JOptionPane.showMessageDialog(frame, "❌ Mise invalide !");
                return;
            }

            miseActuelle = mise;
            joueur.enleverArgent(mise);
            labelMessage.setText("🎲 Mise acceptée ! Lancez les dés !");
            boutonLancer.setEnabled(true); // Activer le bouton pour lancer les dés
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "❌ Entrez une mise valide !");
        }
    }

    private void lancerDes() {
        int resultat1 = de1.tireDes();
        int resultat2 = de2.tireDes();
        int lancer = resultat1 + resultat2;

        labelResultatDes.setText("Résultat des dés : " + resultat1 + " et " + resultat2 + " (Total: " + lancer + ")");
        Joueur joueur = joueurs[joueurActuel];

        if (!enJeu) {
            if (lancer == 7 || lancer == 11) {
                JOptionPane.showMessageDialog(frame, "🎉 Vous avez gagné !");
                joueur.ajoutArgent(miseActuelle * 2);  // Gain = mise * 2
                enJeu = false;
            } else if (lancer == 2 || lancer == 3 || lancer == 12) {
                JOptionPane.showMessageDialog(frame, "💀 Vous avez perdu !");
                enJeu = false;
            } else {
                point = lancer;
                enJeu = true;
                labelPoint.setText("Point : " + point);
                labelMessage.setText("🔄 Votre point est " + point + ". Continuez à jouer !");
            }
        } else {
            if (lancer == point) {
                JOptionPane.showMessageDialog(frame, "🎉 Vous avez refait votre point et gagné !");
                joueur.ajoutArgent(miseActuelle * 2);  // Gain = mise * 2
                enJeu = false;
            } else if (lancer == 7) {
                JOptionPane.showMessageDialog(frame, "💀 Vous avez fait 7, perdu !");
                enJeu = false;
            } else {
                labelMessage.setText("🔄 Continuez à lancer (point : " + point + ").");
            }
        }

        boutonLancer.setEnabled(false); // Désactiver le bouton jusqu'à la prochaine mise
        passerAuJoueurSuivant();
    }

    private void passerAuJoueurSuivant() {
        joueurActuel = (joueurActuel + 1) % joueurs.length;

        // S'assurer que le joueur a encore de l'argent
        if (joueurs[joueurActuel].getArgent() == 0) {
            passerAuJoueurSuivant();
        }

        miseActuelle = 0; // Réinitialiser la mise pour le joueur suivant
        mettreAJourAffichage();
    }

    private void mettreAJourAffichage() {
        labelArgent.setText("Argent : " + joueurs[joueurActuel].getArgent() + "€");
        labelMessage.setText("C'est au tour de " + joueurs[joueurActuel].getPrenom() + " !");
        labelPoint.setText(enJeu ? "Point : " + point : "Point : -");
        frame.revalidate();
        frame.repaint();
    }
}
