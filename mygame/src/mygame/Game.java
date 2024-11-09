package mygame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import charecter.*;
import Element.Element;
import event.event;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private long startTime; 
    private long elapsedTime;
    private Timer spawnTimer;
    private Timer spawnBossTimer; 
    private int spawnInterval = 2000; 
    private int spawnBossInterval = 10000; 
private static int demonspeed = 100;  
  private static int knightSpeed =1000, knightSize = 300, demonSize = 300, health = 100;
    private long point = 0, lastPress = 0;

    private int knightYPosition;
    private int demonYPosition;

    private Knight knight;
    static Display display;

    ArrayList<Demon> demonSet = new ArrayList<>();
    //ArrayList<BossDemon> bossDemonSet = new ArrayList<>(); // เก็บปีศาจบอส

    private int currentLevel = 1; // เริ่มต้นที่ด่าน 1

    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.addKeyListener(this);
        this.setLayout(null);
        this.setFocusable(true);
        startTime = System.currentTimeMillis();
        knightYPosition = this.getHeight() - knightSize; 
        demonYPosition = this.getHeight() - demonSize; 
        knight = new Knight(100, knightYPosition);
        spawnTimer = new Timer(spawnInterval, e -> addDemon());
        spawnTimer.start();
        Timer timer = new Timer(1000, e -> updateElapsedTime());
        timer.start();
    }

    private void updateElapsedTime() {
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        repaint(); 
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            this.drawBackground(g2);

            g2.setFont(Element.getFont(30));
            g2.setColor(Color.black);
            g2.drawString("Point : " + point, 750, 40);
            g2.drawString("Time: " + elapsedTime + "s", 750, 70);
            g2.drawString("Level: " + currentLevel, 750, 100); 

            g2.setColor(Color.RED);
            drawKnightHealth(g2);
            g2.drawImage(knight.getImage(), knight.x, knight.y, knightSize, knightSize, null);

            for (int i = 0; i < demonSet.size(); i++) {
                Demon demon = demonSet.get(i);
                drawDemon(demon, g2);

                if (demon.x + demonSize < 0) {
                    demonSet.remove(i);
                    i--;
                }
            }

            /*for (int i = 0; i < bossDemonSet.size(); i++) {
                BossDemon bossDemon = bossDemonSet.get(i);
                drawBossDemon(bossDemon, g2);

                if (bossDemon.x + bossDemon.width < 0) {
                    bossDemonSet.remove(i);
                    i--;
                }
            }*/

            this.point += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBackground(Graphics2D g2) throws IOException {
        if (currentLevel == 1) {
            g2.drawImage(ImageIO.read(new File("img\\sky.png")), 0, 0, this.getWidth(), this.getHeight(), null);
        } else if (currentLevel == 2) {
            g2.drawImage(ImageIO.read(new File("img\\evening.png")), 0, 0, this.getWidth(), this.getHeight(), null);
        }else if(currentLevel == 3){
            g2.drawImage(ImageIO.read(new File("img\\forest.png")), 0, 0, this.getWidth(), this.getHeight(), null);
        }
        g2.drawImage(ImageIO.read(new File("img\\dir2.png")), 0, this.getHeight() - 250, this.getWidth(), 220, null);
    }

    private void drawKnightHealth(Graphics2D g2) {
        try {
            BufferedImage healthImage;

            if (knight.health >= 70) {
                healthImage = ImageIO.read(new File("img\\heart_full.png"));
            } else if (knight.health >= 30) {
                healthImage = ImageIO.read(new File("img\\heart_half.png"));
            } else {
                healthImage = ImageIO.read(new File("img\\heart_low.png"));
            }

            int healthBarWidth = 300;
            int healthBarHeight = 20;
            int currentHealthWidth = (knight.health * healthBarWidth) / 100;

            g2.setColor(new Color(241, 98, 69));
            g2.fillRect(60, 30, currentHealthWidth, healthBarHeight);

            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawRect(60, 30, healthBarWidth, healthBarHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void manageDemonDisplay() {
        int maxDemons = 5;
        int demonWidth = 50;
        int demonSpeed = 5;

        while (demonSet.size() < maxDemons) {
            int randomY = (int) (Math.random() * (getHeight() - demonSize));
            int demonStartX = this.getWidth();

            Demon newDemon = new Demon(demonStartX, randomY, demonWidth, demonSpeed, this);
            demonSet.add(newDemon);
        }
    }*/

    private void addDemon() {
        int startX = this.getWidth();
        int startY = demonYPosition;
     
        Demon demon = new Demon(startX, startY, demonSize, demonspeed, this);
        demonSet.add(demon);
    }

private void drawDemon(Demon demon, Graphics2D g2) {
 
    demon.x -= demonspeed;

    
    g2.drawImage(demon.getImage(), demon.x, demonYPosition, demonSize, demonSize, null);

    
    int collisionThreshold = 20; 

    if (Math.abs(knight.x - demon.x) < (knightSize / 2 + demonSize / 2 - collisionThreshold)) {
       
        if (event.checkHit(knight, demon, knightSize, demonSize)) {
            System.out.println("Collision Detected with Demon at position: " + demon.x + ", " + demon.y);

           
            int damage = 5;
            if (currentLevel == 1) {
                damage = 2; 
            } else if (currentLevel == 2) {
                damage = 4; 
            } else if (currentLevel == 3) {
                damage = 8; 
            }

            knight.health -= damage; 

            if (knight.health <= 0) {
                display.endGame(this.point, elapsedTime, currentLevel);
                knight.health = 100; 
                this.point = 0;
                demonSet.clear(); 
            }
        }
    }
}




   /* private void addBossDemon() {
        int startX = this.getWidth();
        int startY = demonYPosition;
        BossDemon bossDemon = new BossDemon(startX, startY, demonSize * 2, speed * 2, this);
        bossDemonSet.add(bossDemon);
    }

    /*private void drawBossDemon(BossDemon bossDemon, Graphics2D g2) {
        bossDemon.x -= bossDemon.speed;
        g2.drawImage(bossDemon.getImage(), bossDemon.x, demonYPosition, bossDemon.width, bossDemon.height, null);

        if (event.checkHit(knight, bossDemon, knightSize, bossDemon.width, bossDemon.height)) {
            g2.setColor(new Color(241, 98, 69));
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            knight.health -= 40;

            if (knight.health <= 0) {
                display.endGame(this.point, elapsedTime, currentLevel);
                knight.health = 100;
                this.point = 0;
                demonSet.clear();
                bossDemonSet.clear();
            } else {
                bossDemonSet.remove(bossDemon);
            }
        }
    }*/

    public void endGame() {
        display.endGame(this.point, elapsedTime, currentLevel);
    }

    public void nextLevel() {
        currentLevel++;
       
        knight.health = 100;
        this.point = 0;
        demonSet.clear();
        
        spawnTimer.restart();
        
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
        repaint();
    }

    @Override
  
public void keyPressed(KeyEvent e) {
    if (System.currentTimeMillis() - lastPress > 600) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
           
            knight.setJumpSpeed(300);  
            knight.jump(this);
            lastPress = System.currentTimeMillis();
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            nextLevel();
        }
    }
}

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] arg) {
        display = new Display();
    }
}