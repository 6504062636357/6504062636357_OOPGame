package event;

import charecter.BossDemon;
import charecter.Demon;
import charecter.Knight;
import java.awt.Rectangle;


public class event {
    
    public static boolean checkHit(Knight knight, Demon demon, int knightSize, int demonSize) {
       
        int buffer = 100; 
        Rectangle knightBounds = new Rectangle(knight.x + buffer, knight.y + buffer, knightSize - 2 * buffer, knightSize - 2 * buffer);
        Rectangle demonBounds = new Rectangle(demon.x + buffer, demon.y + buffer, demonSize - 2 * buffer, demonSize - 2 * buffer);

        
        int closeDistance = 50; 

        if (Math.abs(knight.x - demon.x) < closeDistance && Math.abs(knight.y - demon.y) < closeDistance) {
            return knightBounds.intersects(demonBounds);
        }
        return false;
    }
    // ตรวจสอบการชนระหว่าง Knight และ BossDemon
    public static boolean checkHit(Knight knight, BossDemon bossDemon, int knightSize, int bossDemonWidth, int bossDemonHeight) {
        // ตรวจสอบการชนกันโดยตรงโดยไม่ใช้ BUFFER
        return (knight.x < bossDemon.x + bossDemonWidth &&
                knight.x + knightSize > bossDemon.x &&
                knight.y < bossDemon.y + bossDemonHeight &&
                knight.y + knightSize > bossDemon.y);
    }
}
