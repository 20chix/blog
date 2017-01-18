/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

import static java.awt.SystemColor.window;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 *
 * @author hadielmekawi
 */
public class SetSplashScreen {
    //set Splash Screen
    void SetSplashScreen() {
                JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("slot.gif"), SwingConstants.CENTER));
        window.setBounds(600, 250, 400, 400);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        
      
        window.dispose();

    }
}
