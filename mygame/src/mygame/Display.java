package mygame;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Dimension size = new Dimension(1000, 600);
    private Game currentGame; // เก็บเกมปัจจุบัน

    public Display() {
        this.setting();
        this.startNewGame();
    }

    private void setting() {
        this.setTitle("Brave Knight");
        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(280, 100);
        this.setVisible(true);
    }

    private void removeContent() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    private void startNewGame() {
        removeContent();
        currentGame = new Game();
        currentGame.demonSet.clear(); // ล้างปีศาจ
       // currentGame.bossDemonSet.clear(); // ล้างปีศาจบอส
        this.getContentPane().add(currentGame);
        currentGame.requestFocus();
    }

    public void endGame(long point, long elapsedTime, int currentLevel) {
        removeContent();
        this.getContentPane().add(new Menu(point, elapsedTime, currentLevel, this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("restart")) {
            startNewGame(); // เริ่มเกมใหม่
        } else if (e.getActionCommand().equals("nextLevel")) {
            // เรียกใช้การเพิ่มระดับผ่านเมธอด nextLevel ใน Game
            currentGame.nextLevel(); 
            // อัพเดต UI 
            removeContent(); 
            this.getContentPane().add(currentGame);
            currentGame.requestFocus(); 
        }
    }
}
