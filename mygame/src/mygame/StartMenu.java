package mygame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Element.EleButton;
import Element.EleLabel;

public class StartMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private Display display;

    public StartMenu(Display display) { 
        try {
            this.display = display; 
            String imagePath = "img/start.png"; 
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource(imagePath));
            JLabel background = new JLabel(backgroundIcon);
            background.setBounds(0, 0, 1000, 600); 

            this.setLayout(null);
            this.add(background);
            this.setBounds(0, 0, 1000, 600);
            this.setFocusable(true);

            EleLabel title = new EleLabel("Dark Knight", 50, 400, 100, 150, 100);
            title.setForeground(Color.white);

            EleButton startButton = new EleButton("Start", 30, 400, 300, 200, 50); 
            startButton.setActionCommand("start"); 
            startButton.addActionListener(display);

            background.add(title);
            background.add(startButton); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}