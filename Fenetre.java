import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class Fenetre implements Runnable{
    private int pigeons_number = 3;
    private int clicked = 0;
    private static Fenetre INSTANCE = null;
    private static JFrame frame;
    private static JPanel panel;
    private volatile CopyOnWriteArrayList<Pigeon> listPigeons = new CopyOnWriteArrayList<Pigeon>();
    private volatile CopyOnWriteArrayList<Nourriture> listNourritures = new CopyOnWriteArrayList<Nourriture>();
    private int nbPigeons;
    private ReentrantLock lock = new ReentrantLock();

    public Fenetre() {
        System.out.println("Start JFrame");
        frame = new JFrame();
        frame.setTitle("TP2 - Pigeons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        createPanel();
        createPigeons();
        INSTANCE = this;

        // createPanel();
    }

    public void createPanel() {
        panel = new JPanel();
        panel.setSize(800,500);
        panel.setBackground(Color.WHITE);
        frame.add(panel);
    }

    public void createPigeons(){
        //Creation of pigeons
        nbPigeons = pigeons_number;
        for(int i=0; i<nbPigeons; i++){
            Random rand = new Random();
            Pigeon pigeon = new Pigeon(rand.nextInt(800), rand.nextInt(500));
            Nourriture nourriture = new Nourriture();
            try {
                listPigeons.add(pigeon);
                //We add some food too
                nourriture = new Nourriture(rand.nextInt(800), rand.nextInt(500));
                listNourritures.add(nourriture);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }

    public void displayEntities() throws IOException {
        try{
            lock.lock();
            panel.removeAll();
            for (Pigeon pigeon : listPigeons) {
                panel.add(pigeon);
            }
            for (Nourriture nourriture : listNourritures) {
                panel.add(nourriture);
            }
            panel.revalidate();
            panel.repaint();
            Thread.sleep(50);
        }
        catch(Exception e) {
            System.out.println("displayEntities() : " + e);
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    

    public void run(){
        frame.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt) {
                try{
                    if(clicked == 0){
                        final Point pos = evt.getPoint();
                        Nourriture nourriture = new Nourriture(pos.x, pos.y);
                        listNourritures.add(nourriture);
                        System.out.println(listNourritures.size());
                        System.out.println(listPigeons.size());
                        clicked = 1;
                        displayEntities();
                    }
                    
                }
                catch(Exception e) {
                    System.out.println("mouseClicked() : " + e);
                }
            }
        });
        try {
            displayEntities();
            clicked = 0;
            Thread.sleep(0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public CopyOnWriteArrayList<Pigeon> getListPigeons() {
        return this.listPigeons;
    }
    public void setListEntities(CopyOnWriteArrayList<Pigeon> lPigeons){
        this.listPigeons = lPigeons;
    }

    public CopyOnWriteArrayList<Nourriture> getListNourritures() {
        return this.listNourritures;
    }
    public void setListNourritures(CopyOnWriteArrayList<Nourriture> lNourritures){
        this.listNourritures = lNourritures;
    }

    public static Fenetre getInstance() {
        return INSTANCE;
    }
}
