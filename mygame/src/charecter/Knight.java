package charecter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Knight {
    public int x, y, knightSize, health;
    private int jumpHeight = 100;
    private int jumpSpeed = 500;  
    private boolean jumping = false;

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
        this.knightSize = 60;
        this.health = 100;
    }

   
    public void setJumpSpeed(int speed) {
        this.jumpSpeed = speed;
    }

    public void jump(JPanel page) {
		this.y -= jumpSpeed ;
		page.repaint();
		
		Timer timer =new Timer(450,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					y += jumpSpeed ;
					page.repaint();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	

    
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img\\knight.png"));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

  
}