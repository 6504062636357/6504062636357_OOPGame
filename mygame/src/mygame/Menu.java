package mygame;

import java.awt.Color;
import javax.swing.JPanel;
import Element.EleButton;
import Element.EleLabel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;
    public long point;
    public long timeSpent;
    private Display display; // ประกาศ Display

    public Menu(long point, long timeSpent, int currentLevel, Display display) { // รับ Display เป็นอาร์กิวเมนต์
        try {
            this.point = point;
            this.timeSpent = timeSpent;
            this.display = display; // เก็บ Display

            ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("img\\21.png").getImage().getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH));
            JLabel background = new JLabel(backgroundIcon);
            background.setBounds(0, 0, 1000, 600);
            this.setLayout(null);
            this.add(background);
            this.setBounds(0, 0, 1000, 600);
            this.setFocusable(true);

            EleLabel status = new EleLabel("You Died!", 40, 400, 100, 200, 100);
            status.setForeground(Color.white);

            EleLabel showPoint = new EleLabel("Total : " + this.point, 30, 400, 200, 200, 100);
            showPoint.setForeground(Color.white);

            EleLabel showTime = new EleLabel("Time: " + this.timeSpent + "s", 30, 400, 280, 200, 100);
            showTime.setForeground(Color.white);

            // ใช้ EleButton แทน JButton และตั้งค่า ActionCommand
            EleButton restartButton = new EleButton("Restart", 30, 400, 360, 200, 50); // กำหนดตำแหน่งและขนาด
            restartButton.setActionCommand("restart"); // กำหนด ActionCommand เป็น "restart"
            restartButton.addActionListener(display); // เพิ่ม ActionListener ที่ชี้ไปที่ Display
            //  เพิ่มปุ่ม Next Level
            EleButton nextLevelButton = new EleButton("Next Level", 30, 400, 440, 200, 50);
            nextLevelButton.setActionCommand("nextLevel"); // กำหนด ActionCommand เป็น "nextLevel"
            nextLevelButton.addActionListener(display); // เพิ่ม ActionListener ที่ชี้ไปที่ Display
            
            background.add(showPoint);
            background.add(status);
            background.add(showTime);
            background.add(restartButton); // เพิ่มปุ่ม restart ลงใน background
            background.add(nextLevelButton); // เพิ่มปุ่ม nextLevel ลงใน background
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
