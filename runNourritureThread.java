import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class runNourritureThread {
    public void run(){
        
        synchronized(Fenetre.getInstance().getListNourritures()){
            CopyOnWriteArrayList<Nourriture> nourritures = Fenetre.getInstance().getListNourritures();

            for (Nourriture nourriture : nourritures){
                nourriture.setLimit(nourriture.getLimit()-1);
                if(nourriture.getLimit() == 0 && nourriture.getEtat() == EtatNourriture.FRESH){
                    nourriture.setEtat(EtatNourriture.NOFRESH);
                    nourriture.setImg("./ressources/bread_nofresh.png");
                }
                else if(nourriture.getLimit() == -20){
                    nourritures.remove(nourriture);
                    Fenetre.getInstance().setListNourritures(nourritures);
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
