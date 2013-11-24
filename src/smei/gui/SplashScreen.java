/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui;

/**
 *
 * @author Ernesto
 */
// SplashScreen.java
// A simple application to show a title screen in the center of the screen
// for the amount of time given in the constructor.  This class includes
// a sample main() method to test the splash screen, but it's meant for use
// with other applications.
//
import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {

    private int duration;

    public SplashScreen(int d) {
        duration = (1000 * d);
    }

    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        //961, 503
        int width = 970;
        int height = 510;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("resources/images/boceto_reunion.jpg"));
        JLabel copyrt = new JLabel("Sistema de Manejo de Espacios Individuales. "
                + "Ernesto Mancebo 2010-1823 - Wilkys Rodriguez 1997-1095", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
//        Color oraRed = new Color(156, 20, 20, 255);
        Color leBlue = new Color(51, 153, 255);
        content.setBorder(BorderFactory.createLineBorder(leBlue, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }


    }

    public void showSplashAndExit() {
        showSplash();
        IniciarSesion.getInstance().setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Throw a nice little title page up on the screen first
        SplashScreen splash = new SplashScreen(6);
        // Normally, we'd call splash.showSplash() and get on with the program.
        // But, since this is only a test...
        splash.showSplashAndExit();
    }
}