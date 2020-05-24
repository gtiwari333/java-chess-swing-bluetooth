import javax.swing.*;

//main class
public class Main extends JFrame {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // SplashScreen at the beginning
        SplashScreen splash = new SplashScreen(210);
        splash.showSplash();


        // Game Menu
        MainUI menu = new MainUI();
        menu.showMenu();

    }
}
