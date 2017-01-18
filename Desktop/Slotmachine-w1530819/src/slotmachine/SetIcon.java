/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

import com.apple.eawt.Application;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author hadielmekawi
 */
public class SetIcon {
    
    //set icon of applications for MAC OSX only
     public  void SetIcon() {
        Application application = Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage("logo2.png");
        application.setDockIconImage(image);    

    }
    
}
