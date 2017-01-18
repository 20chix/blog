/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hadielmekawi
 */
public class SaveFile {
    //get some variables
    int spinCount;
    int AvBet;
    int CountLos;
    int CountWin;
    //construct all this variables
    public SaveFile(int spinCount,int AvBet , int CountLos, int CountWin){
    this.spinCount=spinCount;
    this.AvBet= AvBet;
    this.CountLos=CountLos;
    this.CountWin=CountWin;
    
    }
    //setters
    public void setSpinCount(int spinCount){
        this.spinCount = spinCount;
    }
    public void setAvBet(int AvBet){
        this.AvBet = AvBet;
    }
   public void setCountLos(int CountLos){
        this.CountLos = CountLos;
    }
   public void setCountWin(int CountWin){
        this.CountWin = CountWin;
    }
   //getters
   public int getSpinCount(){
         return spinCount;
    }
   public int getAvBet(){
         return AvBet;
    }
   public int getCountLos(){
         return CountLos;
    }
   public int getCountWin(){
         return CountWin;
    }
   //savefile method 
   public  void saveFile() {
        try {
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
            //BufferedWriter out = new BufferedWriter(new FileWriter("src\\slotmachine\\Resources\\Statistics\\"+timeStamp+".txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("src/slotmachine/Resources/Statistics/" + timeStamp + ".txt"));

            out.write("Statistics: \n");

            out.write("Played:     " + spinCount + " time(s).\n");
            out.write("Won:        " + CountWin + " time(s).\n");
            out.write("Lost:       " + CountLos + " time(s).\n");
            out.write("Betted:     " + AvBet + " credit(s)\n");
            try {
                double TOT = AvBet / spinCount;
                out.write("Bet average :" + TOT + " .\n");
            } catch (ArithmeticException e) {
                out.write("Bet average not available. \n");

            }
            System.out.println("succesfully added on text file");
            JOptionPane.showMessageDialog(null, "Succesfully saved statistics", "SlotMachine",
                    JOptionPane.WARNING_MESSAGE);

            out.close();
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Cannot find txt file", "SlotMachine",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
    
}
