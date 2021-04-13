import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Nourriture extends Entity implements Runnable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    
	private EtatNourriture etat;
    public BufferedImage img;
    private int limit = 30;

    public Nourriture() {}

    public Nourriture(int x, int y) throws IOException {
        super();
        try {
            etat = EtatNourriture.FRESH;
            this.setCoordinateX(x);
            this.setCoordinateY(y);
            this.setImg("./ressources/bread.png");
        }
        catch(Exception e) {
            System.out.println("Nourriture constructor : " + e);
        }
    }

    public EtatNourriture getEtat() {
        return etat;
    }

    public void setEtat(EtatNourriture etat) {
        this.etat = etat;
    }

    public int getLimit(){
        return limit;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public void setImg(String url){
        try {
            img = ImageIO.read(getClass().getResource(url));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void run(){
        synchronized(Fenetre.getInstance().getListNourritures()){
            setLimit(getLimit()-1);
            if(getLimit() <= 0 && getEtat() == EtatNourriture.FRESH){
                this.setEtat(EtatNourriture.NOFRESH);
                this.setImg("./ressources/bread_nofresh.png");
            }
            else if(this.getLimit() == -20){
                Fenetre.getInstance().getListNourritures().remove(this);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        setBounds((int)this.getCoordinateX(), (int)this.getCoordinateY(), this.img.getWidth(),this.img.getHeight());
        g.drawImage(this.img, 0, 0, this);
    }

}
