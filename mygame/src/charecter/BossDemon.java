package charecter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import mygame.Game;

public class BossDemon extends Demon {

    public int health; // สุขภาพ
    public int attackDamage; // ความเสียหาย
    public int width; // ขนาดกว้าง
    public int height; // ขนาดสูง
    private BufferedImage image;
    //private int currentLevel;  // ลบออก เพราะ currentLevel จะถูกส่งผ่านมาจาก super

    public BossDemon(int x, int y, int width, int speed, Game game) { 
        super(x, y, width, speed, game); // ส่ง currentLevel ไปยัง super
        this.health = 100; // ตั้งค่าสุขภาพเริ่มต้น
        this.attackDamage = 50; // ตั้งค่าความเสียหาย
        this.width = width; // ตั้งค่าขนาดกว้าง
        this.height = width; // ตั้งค่าขนาดสูง
        //this.currentLevel = currentLevel; // ลบออก เพราะ currentLevel จะถูกส่งผ่านมาจาก super
        try {
            this.image = ImageIO.read(new File("img\\bossdemon.png")); // เปลี่ยนภาพเป็นภาพของปีศาจบอส
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void attack(Knight knight) {
        knight.health -= this.attackDamage;
    }
}