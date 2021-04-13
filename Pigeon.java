import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;


public class Pigeon extends Entity implements Runnable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

    private EtatPigeon etat;
    public static BufferedImage img;
    private ReentrantLock lock;

	public Pigeon() {}

    public Pigeon(int x, int y) {
        try {
            etat = EtatPigeon.AWAKE;
            this.setCoordinateX(x);
            this.setCoordinateY(y);
            img = ImageIO.read(getClass().getResource("./ressources/pigeon.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public EtatPigeon getEtat() {
        return etat;
    }

    public void setEtat(EtatPigeon etat) {
        this.etat = etat;
    }

    public void run(){
        try{
            CopyOnWriteArrayList<Nourriture> listNourriture = Fenetre.getInstance().getListNourritures();
            ArrayList<Nourriture> fresh_nourriture = new ArrayList<Nourriture>();
            synchronized(Fenetre.getInstance().getListNourritures()){
                for (Iterator<Nourriture> it = listNourriture.iterator(); it.hasNext();) {
                    Nourriture nourriture = it.next();
                    if(!nourriture.getEtat().equals(EtatNourriture.NOFRESH)){
                        fresh_nourriture.add(nourriture);
                    }
                    
                }

                if(fresh_nourriture.isEmpty()) {
                    setEtat(EtatPigeon.SLEEP);
                }
                else {
                    Nourriture nourriture_choisie = new Nourriture(0,0);
                    setEtat(EtatPigeon.AWAKE);
                    float x_choisi = 0;
                    float y_choisi = 0;
                    double dist = -1;
                    nourriture_choisie = new Nourriture();

                    for (Nourriture nourriture : fresh_nourriture) {
                        float x1 = getCoordinateX();
                        float x2 = nourriture.getCoordinateX();
                        float y1 = getCoordinateY();
                        float y2 = nourriture.getCoordinateY();
                        
                        double dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
                        if(dist == -1 || dis < dist){
                            dist = dis;
                            nourriture_choisie = nourriture;
                            x_choisi = nourriture_choisie.getCoordinateX();
                            y_choisi = nourriture_choisie.getCoordinateY();
                        }
                    }


                    if(x_choisi < getCoordinateX()-5){
                        setCoordinateX(getCoordinateX() - 10);
                    }
                    else if(x_choisi > getCoordinateX()+5){
                        setCoordinateX(getCoordinateX() + 10);
                    }

                    if(y_choisi < getCoordinateY()-5){
                        setCoordinateY(getCoordinateY() - 10);
                    }
                    else if(y_choisi > getCoordinateY()+5){
                        setCoordinateY(getCoordinateY() + 10);
                    }

                    if((x_choisi >= getCoordinateX()-5 && x_choisi <= getCoordinateX()+5) && (y_choisi >= getCoordinateY()-5 && y_choisi <= getCoordinateY()+5)){
                        listNourriture.remove(nourriture_choisie);
                        // System.out.println("mange");
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Erreur dans le thread pigeon : "+ e);
            e.printStackTrace();
        }


    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        setBounds((int)this.getCoordinateX(), (int)this.getCoordinateY(), img.getWidth(),img.getHeight());
        g.drawImage(img, 0, 0, this);
    }
}
