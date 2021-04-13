import java.util.ArrayList;
import java.util.List;

public class Main {
    
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();
        runPigeonsThread runPigeonsThread = new runPigeonsThread();
        // runNourritureThread runNourritureThread = new runNourritureThread();
        while(true){
            List<Nourriture> lNourritures = fenetre.getListNourritures();
            for (Nourriture nourriture : lNourritures) {
                nourriture.run();
            }
            runPigeonsThread.run();
            fenetre.run();
        }
    }
}
