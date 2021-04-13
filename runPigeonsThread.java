import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class runPigeonsThread implements Runnable {

    public runPigeonsThread(){}

    public void run(){
        List<Pigeon> pigeons = Fenetre.getInstance().getListPigeons();
        float proba_dispersion = new Random().nextFloat()/10;
        float chance = new Random().nextFloat();

        for (Pigeon pigeon : pigeons) {
            if(chance < proba_dispersion){
                pigeon.setCoordinateX(new Random().nextInt(800));
                pigeon.setCoordinateY(new Random().nextInt(500));
            }
            Thread pigeonsThread = new Thread(pigeon);
            pigeonsThread.start();              
        }
        
    }
}
