import javax.swing.JPanel;

public class Entity extends JPanel{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    
	private float x;
    private float y;

    Entity() {}

    Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.setSize(32, 32);
        
    }

    public float getCoordinateX() {
        return x;
    }
    public void setCoordinateX(float x) {
        this.x = x;
    }

    public float getCoordinateY() {
        return y;
    }
    public void setCoordinateY(float y) {
        this.y = y;
    }
}
