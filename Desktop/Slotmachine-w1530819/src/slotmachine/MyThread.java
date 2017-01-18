/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;
import javax.swing.JLabel;

/**
 *
 * @author hadielmekawi
 */
public class MyThread extends Thread {
    Reel r = new Reel();
    Thread t = new Thread();
    Symbol reel1;
    JLabel o;
    boolean running = true;
    //construct Symbol
    public MyThread(Symbol reel1) {
        this.reel1 = reel1;
    }
    //stop thread
    public void stopSpin() {
        running = false;
    }

    public void spin(JLabel o) {
        running = true;
        Thread y = new Thread() {
            @Override
            public void run() {
                //run while running is true
                while (running) {
                    try {
                        Thread.sleep(40);
                        reel1 = r.spin();
                        o.setIcon(reel1.getImage());
                  
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }

        };
        //start thtread
        y.start();

    }
    //return symbol object 
    public Symbol getSymbol() {
        return reel1;
    }



}
