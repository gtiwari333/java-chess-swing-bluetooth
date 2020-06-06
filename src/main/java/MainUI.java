/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainMenu.java
 *
 * Created on Jan 7, 2010, 9:02:27 PM
 */
/**
 * @author MADHAV
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUI extends JFrame {

    /** Creates new form MainMenu */
    public MainUI() {
        super("Main Menu");
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new JPanel();
        newgame = new JLabel();
        options = new JLabel();
        help = new JLabel();
        about = new JLabel();
        exit = new JLabel();
        logo = new JLabel();
        createdby = new JLabel();
        exitbutton = new JButton();
        subpanel = new JPanel();
        single = new JLabel();
        host = new JLabel();
        join = new JLabel();
        back = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main MENU");

        newgame.setText("jLabel3");

        options.setText("jLabel4");

        help.setText("jLabel5");

        about.setText("jLabel6");

        exit.setText("jLabel7");

        GroupLayout mainpanelLayout = new GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
                mainpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(about, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exit, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addGroup(mainpanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(help, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(options, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(newgame, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                                .addGap(229, 229, 229))
        );
        mainpanelLayout.setVerticalGroup(
                mainpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(newgame)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(options)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(help)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(about)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exit)
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        logo.setText("BlueChess logo");

        createdby.setFont(new Font("Tahoma", Font.BOLD, 14));
        createdby.setText("Created by Biraj, Ganesh & Madhav");

        exitbutton.setFont(new Font("Tahoma", Font.BOLD, 18));
        exitbutton.setForeground(new Color(255, 0, 0));
        exitbutton.setText("EXIT");
        exitbutton.addActionListener(this::exitbuttonActionPerformed);

        single.setText("jLabel8");

        host.setText("jLabel9");

        join.setText("jLabel10");

        back.setText("jLabel1");

        GroupLayout subpanelLayout = new GroupLayout(subpanel);
        subpanel.setLayout(subpanelLayout);
        subpanelLayout.setHorizontalGroup(
                subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(subpanelLayout.createSequentialGroup()
                                .addGroup(subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(single)
                                        .addComponent(host)
                                        .addComponent(join)
                                        .addComponent(back))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subpanelLayout.setVerticalGroup(
                subpanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(subpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(single)
                                .addGap(18, 18, 18)
                                .addComponent(host)
                                .addGap(18, 18, 18)
                                .addComponent(join)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(back)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(logo))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(subpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(mainpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(174, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(createdby, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(subpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mainpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(createdby)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitbutton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbuttonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        EventQueue.invokeLater(() -> new MainMenu().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel about;
    private JLabel back;
    private JLabel createdby;
    private JLabel exit;
    private JButton exitbutton;
    private JLabel help;
    private JLabel host;
    private JLabel join;
    private JLabel logo;
    private JPanel mainpanel;
    private JLabel newgame;
    private JLabel options;
    private JLabel single;
    private JPanel subpanel;
    // End of variables declaration//GEN-END:variables
    protected Icon bluechess;
    protected Icon Newgame; // 수행전 : newgame1
    protected Icon OnMouseNewgame; // 수행전 : newgame2
    protected Icon Options; // 수행전 : options1
    protected Icon OnMouseOptions; // 수행전 : options2
    protected Icon Help; // 수행전 : help1
    protected Icon OnMouseHelp; // 수행전 : help2
    protected Icon About; // 수행전 : about1
    protected Icon OnMouseAbout; // 수행전 : about2
    protected Icon Exit; // 수행전 : exit1
    protected Icon OnMouseExit; // 수행전 : exit2
    protected Icon Single; // 수행전 : single1
    protected Icon OnMouseSingle; // 수행전 : single2
    protected Icon Host; // 수행전 : host1
    protected Icon OnMouseHost; // 수행전 : host2
    protected Icon Join; // 수행전 : join1
    protected Icon OnMouseJoin; // 수행전 : join2
    protected Icon Back; // 수행전 : back1
    protected Icon OnMouseBack; // 수행전 : back2

    private class MouseHandler implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            //switch-case문으로 바꾸려고 하였으나 변수타입이 JLabel이라서 switch-case문에 적용되지 않아 if문으로 했습니다.

            JLabel MouseReleasedMenu = (JLabel)e.getSource();

            //수행전 :
//            if(e.getSource() == newgame){
//                mainpanel.setVisible(false);
//                subpanel.setVisible(true);
//            }

            if (MouseReleasedMenu == newgame) {
                mainpanel.setVisible(false);
                subpanel.setVisible(true);

            }

            if (MouseReleasedMenu == options) {
            }

            if (MouseReleasedMenu == help) {
            }

            if (MouseReleasedMenu == about) {
            }

            if (MouseReleasedMenu == exit) {
                System.exit(0);
            }

            if (MouseReleasedMenu == single) {
                JFrame main = new JFrame("Blue Chess Game Window"); //Title
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                GUISingle chessWindow = new GUISingle();
                main.add(chessWindow);//creates game board
                chessWindow.createGUI(main);
                main.setSize(680, 580);
                main.setResizable(false);
                main.setVisible(true);
                dispose();
            }

            if (MouseReleasedMenu == join) {
                try {
                    JFrame main = new JFrame("Blue Chess Game Window"); //Title
                    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    GUIBT chessWindow = new GUIBT(false);
                    main.add(chessWindow); //creates game board
                    chessWindow.createGUI(main);
                    main.setSize(680, 580);
                    main.setResizable(false);
                    main.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (MouseReleasedMenu == host) {
                try {
                    JFrame main = new JFrame("Blue Chess Game Window"); //Title
                    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    GUIBT chessWindow = new GUIBT(true);
                    main.add(chessWindow); //creates game board
                    chessWindow.createGUI(main);
                    main.setSize(680, 580);
                    main.setResizable(false);
                    main.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (MouseReleasedMenu == back) {

                subpanel.setVisible(false);
                mainpanel.setVisible(true);
            }
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");

            JLabel MouseEnteredMenu = (JLabel)e.getSource();

            //수행전 :
            //if(MouseEnteredMenu.getSource() == newgame){
            //      newgame.setIcon(OnMouseNewgame);
            //}

            //switch-case문으로 바꾸려고 하였으나 변수타입이 JLabel이라서 switch-case문에 적용되지 않아 if문으로 했습니다.

            if (MouseEnteredMenu == newgame) {
                newgame.setIcon(OnMouseNewgame);
            }

            if (MouseEnteredMenu == options) {
                options.setIcon(OnMouseOptions);
            }

            if (MouseEnteredMenu == help) {
                help.setIcon(OnMouseHelp);
            }

            if (MouseEnteredMenu == about) {
                about.setIcon(OnMouseAbout);
            }

            if (MouseEnteredMenu == exit) {
                exit.setIcon(OnMouseExit);
            }

            if (MouseEnteredMenu == single) {
                single.setIcon(OnMouseSingle);
                subpanel.setVisible(true);
            }

            if (MouseEnteredMenu == host) {
                host.setIcon(OnMouseHost);
                subpanel.setVisible(true);
            }

            if (MouseEnteredMenu == join) {
                join.setIcon(OnMouseJoin);
                subpanel.setVisible(true);
            }

            if (MouseEnteredMenu == back) {
                back.setIcon(OnMouseBack);
                subpanel.setVisible(true);
            }

        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            //switch-case문으로 바꾸려고 하였으나 변수타입이 JLabel이라서 switch-case문에 적용되지 않아 if문으로 했습니다.

            JLabel MouseExitedMenu = (JLabel)e.getSource();

//          if (e.getSource() == newgame) {
//              newgame.setIcon(Newgame);
//          }

            if (MouseExitedMenu == newgame) {
                   newgame.setIcon(Newgame);
            }

            if (MouseExitedMenu == options) {
                options.setIcon(Options);
            }

            if (MouseExitedMenu == help) {
                help.setIcon(Help);
            }

            if (MouseExitedMenu == about) {
                about.setIcon(About);
            }

            if (MouseExitedMenu == exit) {
                exit.setIcon(Exit);
            }

            if (e.getSource() == subpanel) {
                subpanel.setVisible(false);
            }

            if (MouseExitedMenu == single) {
                single.setIcon(Single);
            }

            if (MouseExitedMenu == host) {
                host.setIcon(Host);
            }

            if (MouseExitedMenu == join) {
                join.setIcon(Join);
            }

            if (MouseExitedMenu == back) {
                back.setIcon(Back);
            }
        }
    }

    public void showMenu() {
//      메소드를 추출하여 private 함수로 만들어서 수행합니다.

//      bluechess = new ImageIcon(getClass().getResource("images2/bluechess.png"));
//
//      Newgame = new ImageIcon(getClass().getResource("images2/newgame1.png")); //수행전 : newgame1
//      OnMouseNewgame = new ImageIcon(getClass().getResource("images2/newgame2.png")); //수행전 : newgame2
//      Options = new ImageIcon(getClass().getResource("images2/options1.png")); // 수행전 : options1
//      OnMouseOptions = new ImageIcon(getClass().getResource("images2/options2.png")); // 수행전 : options2
//      Help = new ImageIcon(getClass().getResource("images2/help1.png")); // 수행전 : help1
//      OnMouseHelp = new ImageIcon(getClass().getResource("images2/help2.png")); // 수행전 : help2
//      About = new ImageIcon(getClass().getResource("images2/about1.png")); // 수행전 : about1
//      OnMouseAbout = new ImageIcon(getClass().getResource("images2/about2.png")); // 수행전 : about2
//      Exit = new ImageIcon(getClass().getResource("images2/exit1.png")); // 수행전 : exit1
//      OnMouseExit = new ImageIcon(getClass().getResource("images2/exit2.png")); // 수행전 : exit2
//
//      Single = new ImageIcon(getClass().getResource("images2/single1.png")); // 수행전 : single1
//      OnMouseSingle = new ImageIcon(getClass().getResource("images2/single2.png")); // 수행전 : single2
//      Host = new ImageIcon(getClass().getResource("images2/host1.png")); // 수행전 : host1
//      OnMouseHost = new ImageIcon(getClass().getResource("images2/host2.png")); // 수행전 : host2
//      Join = new ImageIcon(getClass().getResource("images2/join1.png")); // 수행전 : join1
//      OnMouseJoin = new ImageIcon(getClass().getResource("images2/join2.png")); // 수행전 : join2
//      Back = new ImageIcon(getClass().getResource("images2/back1.png")); // 수행전 : back1
//      OnMouseBack = new ImageIcon(getClass().getResource("images2/back2.png")); // 수행전 : back2


        ShowBlueChessIcon();

        ShowMenuPage();

        ShowModePage();


        MouseHandler handler = new MouseHandler();

//      생성 함수 또한 메소드로 추출하여 코드를 간단하게 해줍니다.

//        수행전 :
//        mainpanel.setBounds(20, 100, 255, 250);
//        add(mainpanel);
//
//        subpanel.setBounds(20, 100, 255, 250);
//        add(subpanel);

        SetPanelBound();

//        수행전 :
//        logo.setIcon(bluechess);
//        logo.setText(null);

        SetLogo();

        SetNewgameEnvironment(handler);

        SetOptionsEnvironment(handler);

        SetHelpEnvironment(handler);

        SetAboutEnvironment(handler);

        SetExitEnvironment(handler);

        SetSingleEnvironment(handler);

        SetJoinEnvironment(handler);

        SetHostEnvironment(handler);

        SetBackEnvironment(handler);

        Point menuCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        //adjust the bound of frame by a simple logic
        //to center the frame window

        //frame window를 만드는 함수를 추출합니다.
        SetFrameWindow(menuCenter);

    }

    private void SetFrameWindow(Point menuCenter) {
        int menuWindowWidth = 300;
        int menuWindowHeight = 450;
        setBounds(menuCenter.x - menuWindowWidth / 2, menuCenter.y - menuWindowHeight / 2, menuWindowWidth, menuWindowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        mainpanel.setVisible(true);
        subpanel.setVisible(false);
        setVisible(true);
    }

    private void SetBackEnvironment(MouseHandler handler) {
        back.setIcon(Back);
        back.setText(null);
        back.setToolTipText("Back to MainMenu");

        back.addMouseListener(handler);
    }

    private void SetHostEnvironment(MouseHandler handler) {
        host.setIcon(Host);
        host.setText(null);
        host.setToolTipText("Host to play through BLUETOOTH");

        host.addMouseListener(handler);
    }

    private void SetJoinEnvironment(MouseHandler handler) {
        join.setIcon(Join);
        join.setText(null);
        join.setToolTipText("Join to play through BLUETOOTH");

        join.addMouseListener(handler);
    }

    private void SetSingleEnvironment(MouseHandler handler) {
        single.setIcon(Single);
        single.setText(null);
        single.setToolTipText("Play in same device");

        single.addMouseListener(handler);
    }

    private void SetExitEnvironment(MouseHandler handler) {
        exit.setIcon(Exit);
        exit.setText(null);
        exit.setToolTipText("Exit From Game");

        exit.addMouseListener(handler);
    }

    private void SetAboutEnvironment(MouseHandler handler) {
        about.setIcon(About);
        about.setText(null);
        about.setToolTipText("About Game");

        about.addMouseListener(handler);
    }

    private void SetHelpEnvironment(MouseHandler handler) {
        help.setIcon(Help);
        help.setText(null);
        help.setToolTipText("Help");

        help.addMouseListener(handler);
    }

    private void SetOptionsEnvironment(MouseHandler handler) {
        options.setIcon(Options);
        options.setText(null);
        options.setToolTipText("Options");

        options.addMouseListener(handler);
    }

    private void SetNewgameEnvironment(MouseHandler handler) {
        newgame.setIcon(Newgame);
        newgame.setText(null);
        newgame.setToolTipText("New Game");


        newgame.addMouseListener(handler);
    }

    private void SetLogo() {
        logo.setIcon(bluechess);
        logo.setText(null);
    }

    private void SetPanelBound() {
        mainpanel.setBounds(20, 100, 255, 250);
        add(mainpanel);

        subpanel.setBounds(20, 100, 255, 250);
        add(subpanel);
    }

    private void ShowModePage() {
        Single = new ImageIcon(getClass().getResource("images2/single1.png")); // 수행전 : single1
        OnMouseSingle = new ImageIcon(getClass().getResource("images2/single2.png")); // 수행전 : single2
        Host = new ImageIcon(getClass().getResource("images2/host1.png")); // 수행전 : host1
        OnMouseHost = new ImageIcon(getClass().getResource("images2/host2.png")); // 수행전 : host2
        Join = new ImageIcon(getClass().getResource("images2/join1.png")); // 수행전 : join1
        OnMouseJoin = new ImageIcon(getClass().getResource("images2/join2.png")); // 수행전 : join2
        Back = new ImageIcon(getClass().getResource("images2/back1.png")); // 수행전 : back1
        OnMouseBack = new ImageIcon(getClass().getResource("images2/back2.png")); // 수행전 : back2
    }

    private void ShowMenuPage() {
        Newgame = new ImageIcon(getClass().getResource("images2/newgame1.png")); //수행전 : newgame1
        OnMouseNewgame = new ImageIcon(getClass().getResource("images2/newgame2.png")); //수행전 : newgame2
        Options = new ImageIcon(getClass().getResource("images2/options1.png")); // 수행전 : options1
        OnMouseOptions = new ImageIcon(getClass().getResource("images2/options2.png")); // 수행전 : options2
        Help = new ImageIcon(getClass().getResource("images2/help1.png")); // 수행전 : help1
        OnMouseHelp = new ImageIcon(getClass().getResource("images2/help2.png")); // 수행전 : help2
        About = new ImageIcon(getClass().getResource("images2/about1.png")); // 수행전 : about1
        OnMouseAbout = new ImageIcon(getClass().getResource("images2/about2.png")); // 수행전 : about2
        Exit = new ImageIcon(getClass().getResource("images2/exit1.png")); // 수행전 : exit1
        OnMouseExit = new ImageIcon(getClass().getResource("images2/exit2.png")); // 수행전 : exit2
    }

    private void ShowBlueChessIcon() {
        bluechess = new ImageIcon(getClass().getResource("images2/bluechess.png"));
    }
}
