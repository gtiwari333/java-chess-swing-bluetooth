import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private final int duration;
    protected final ImageIcon[] images;

    public SplashScreen(int d) {
        duration = d;
        images = new ImageIcon[10];

        for (int count = 0; count < images.length; count++) {
            images[count] = new ImageIcon(getClass().getResource("images1/bc" + (count + 1) + ".png"));
        }

    }

    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash() {

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.BLACK);

        SetTheWindowBound();
        BuildTheSplashScreen(content);
        Display(content);
        dispose();

    }

    private void Display(JPanel content) {
        JLabel label = new JLabel("", JLabel.CENTER);
        content.add(label);
        setVisible(true);

        for (int i = 0; i < 10; i++) {

            label.setIcon(images[i]);
            // Display it
            label.setVisible(true);

            // Wait a little while, maybe while loading resources
            try {
                Thread.sleep(duration);
            } catch (Exception e) {
            }
            if (i == 9) {
                try {
                    Thread.sleep(5 * duration);
                } catch (Exception e) {
                }
            }
            label.setVisible(false);
        }
    }

    private void BuildTheSplashScreen(JPanel content) {
        JLabel copyrt = new JLabel("***Created by Biraj, Ganesh & Madhav***", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        copyrt.setForeground(Color.WHITE);
        content.add(copyrt, BorderLayout.SOUTH);
        Color clr = new Color(255, 100, 100);
        content.setBorder(BorderFactory.createLineBorder(clr, 5));
    }

    private void SetTheWindowBound() {
        int width = 500;
        int height = 250;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
    }
}