package Roulette.Modele;

    public class VueRoulette {
        
        private ControleurRoulette controleurRoulette;

        public VueRoulette() {
            this.controleurRoulette = null;
        }

        public void afficheMessage(String msg) {
            System.out.println(msg);
        }

        public void afficheResultat(int numero, String couleur) {
            System.out.println("Voici le nombre tirer " + numero + " et la couleur " + couleur);
        }
        
        public void setControleurRoulette(ControleurRoulette controleurRoulette) {
            this.controleurRoulette = controleurRoulette;
        }

        public ControleurRoulette getControleurRoulette() {
            return controleurRoulette;
        }
    }
