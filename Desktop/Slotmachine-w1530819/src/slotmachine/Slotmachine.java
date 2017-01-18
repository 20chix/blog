
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author hadi elmekawi
 */
public class Slotmachine extends JFrame {
    //JLabels which they will have each reel object
    private JLabel ImageLeft;
    private JLabel ImageCenter;
    private JLabel ImageRight;
    //empty labels
    private JLabel Decorative1;
    private JLabel Decorative2;
    //Instanciete Buttons
    private JButton spin;
    private JButton AddCoin;
    private JButton BetOne;
    private JButton BetMax;
    private JButton Reset;
    private JButton Statistics;
    private JButton SaveStatistics;
    private JButton SaveStatisticsPanel;
    //labels for information of users
    private JLabel YourWin;
    private JLabel YourBet;
    private JLabel YourCredit;
    //reels objects
    Reel r = new Reel();
    Reel r1 = new Reel();
    Reel r2 = new Reel();
    //3 symbol equal to reel spin method
    Symbol list1 = r.spin();
    Symbol list2 = r1.spin();
    Symbol list3 = r2.spin();
    //each thread for each symbol 
    MyThread rp1 = new MyThread(list1);
    MyThread rp2 = new MyThread(list2);
    MyThread rp3 = new MyThread(list3);
    //variables for statistic panel
    int Credit = 10;
    int Bet = 0;
    int Win = 0;
    int spinCount = 0;
    int AvBet = 0;
    // variables fo stats panel
    int CountWin = 0;
    int CountLos = 0;
    Color y = new Color(255, 255, 153);
    //instanziate panel 
    JPanel p1 = new JPanel();
    //new object of SaveFile in txt 
    SaveFile s= new SaveFile(spinCount,AvBet , CountLos, CountWin); 
   
    public Slotmachine() {
        //load gui 
        LoadGUI();
        //start action listener
        buttonsActionListener(); 
        toolTipmessageONEnable();
    }
    //when savefile class created set values of it 
    void setSaveFileValues(){
        s.setSpinCount(spinCount);    
        s.setCountLos(CountLos);
        s.setCountWin(CountWin);
    }
    //method for ActionListener
    void buttonsActionListener() {
        JButtonHandlerSpin handlerSpin = new JButtonHandlerSpin();
        spin.addActionListener(handlerSpin);

        JButtonHandlerBetOne handlerBetOne = new JButtonHandlerBetOne();
        BetOne.addActionListener(handlerBetOne);

        JButtonHandlerBetMax handlerBetMax = new JButtonHandlerBetMax();
        BetMax.addActionListener(handlerBetMax);

        JButtonHandlerAddOne handlerAddOne = new JButtonHandlerAddOne();
        AddCoin.addActionListener(handlerAddOne);

        JButtonHandlerReset handlerReset = new JButtonHandlerReset();
        Reset.addActionListener(handlerReset);

        JButtonHandlerStatistics handlerStatistics = new JButtonHandlerStatistics();
        Statistics.addActionListener(handlerStatistics);

        JButtonHandlerSaveStatistics handlerSaveStatistics = new JButtonHandlerSaveStatistics();
        SaveStatistics.addActionListener(handlerSaveStatistics);
    }
    //launch gui
    void LoadGUI() {
        //set layout
         p1.setBackground(Color.cyan.darker());
         
        p1.setLayout(new GridLayout(6, 3));
        Decorative1 = new JLabel(new ImageIcon("logo.png"));
        p1.add(Decorative1);
        //Bet labels 
        YourBet = new JLabel("Your Bet: 0");
        YourBet.setFont(new Font("Your Bet:", Font.ITALIC, 28));
        p1.add(YourBet);
        //Empty labels
        ImageIcon gif1 = new ImageIcon("Animation4.gif");
        Decorative2 = new JLabel(gif1);
        p1.add(Decorative2);
        // set the border of this component
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        ImageLeft = new JLabel(r.spin().getImage());
        ImageLeft.setBorder(border);
        p1.add(ImageLeft);
        //in the center image
        ImageCenter = new JLabel(r1.spin().getImage());
        ImageCenter.setBorder(border);
        p1.add(ImageCenter);
        //on the right image
        ImageRight = new JLabel(r2.spin().getImage());
        ImageRight.setBorder(border);
        p1.add(ImageRight);
        //empty labels
        p1.add(new JLabel(""));
        //spin button 
        spin = new JButton("Spin");
        spin.setFont(new Font("Spin", Font.BOLD, 28));
        p1.add(spin);
        //empty labels
        p1.add(new JLabel(""));
        YourCredit = new JLabel("Credit: " + Credit);
        YourCredit.setFont(new Font("Credit:", Font.ROMAN_BASELINE, 25));
        p1.add(YourCredit);
        //empty labels
        p1.add(new JLabel(""));
        YourWin = new JLabel("You win: ");
        YourWin.setFont(new Font("Your win: ", Font.ROMAN_BASELINE, 25));
        p1.add(YourWin);
        //AddCoin
        AddCoin = new JButton("Add Coin");
        p1.setLayout(new GridLayout(6, 2, 10, 10));
        p1.add(AddCoin);
        //BetOne
        BetOne = new JButton("Bet One");
        BetOne.setOpaque(true);
        BetOne.setBackground(Color.yellow);
        p1.add(BetOne);
        //BetMax
        BetMax = new JButton("BetMax");
        BetMax.setOpaque(true);
        BetMax.setBackground(Color.yellow);
        BetMax.addMouseListener(BetMaxMouse);
        p1.add(BetMax);
        //Reset
        Reset = new JButton("Reset");
        p1.add(Reset);
        //Statistic Button
        Statistics = new JButton("Statistics");
        p1.add(Statistics);
        SaveStatistics = new JButton("Save Statistics");
        p1.add(SaveStatistics);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(p1, BorderLayout.CENTER);
        //add content to the frame 
        add(p2, BorderLayout.CENTER);

    }
    //method to enable all buttons
    void EnableButtons() {
        spin.setEnabled(true);
        AddCoin.setEnabled(true);
        BetOne.setEnabled(true);
        BetMax.setEnabled(true);
        Reset.setEnabled(true);
        SaveStatistics.setEnabled(true);
        Statistics.setEnabled(true);
        toolTipmessageONEnable();
        if (Credit >= 1) {BetOne.setEnabled(true);} else {BetOne.setEnabled(false);}
        if (Credit >= 3) {BetMax.setEnabled(true);} else {BetMax.setEnabled(false);}
    }
    //method to disable all buttons
    void DisableButtons() {
        spin.setEnabled(false);
        AddCoin.setEnabled(false);
        BetOne.setEnabled(false);
        BetMax.setEnabled(false);
        Reset.setEnabled(false);
        SaveStatistics.setEnabled(false);
        Statistics.setEnabled(false);
        toolTipmessageONDIsable();
    }
    
    void toolTipmessageONDIsable(){
    BetMax.setToolTipText("Click on any reel to stop spinning!!!");
    BetOne.setToolTipText("Click on any reel to stop spinning!!!");
    AddCoin.setToolTipText("Click on any reel to stop spinning!!!");
    Reset.setToolTipText("Click on any reel to stop spinning!!!");
    Statistics.setToolTipText("Click on any reel to stop spinning!!!");
    SaveStatistics.setToolTipText("Click on any reel to stop spinning!!!");
    spin.setToolTipText("Click on any reel to stop spinning!!!");
    ImageLeft.setToolTipText("Click me to stop!!!");
    ImageCenter.setToolTipText("Click me to stop!!!");
    ImageRight.setToolTipText("Click me to stop!!!");
    }
    
    
     void toolTipmessageONEnable(){
    BetMax.setToolTipText("Bet Max");
    BetOne.setToolTipText("Bet one");
    AddCoin.setToolTipText("Add Coin to Credit");
    Reset.setToolTipText("Reset");
    Statistics.setToolTipText("Statistics Window");
    SaveStatistics.setToolTipText("Save in txt file");
    spin.setToolTipText("Spin!!!");
    ImageLeft.setToolTipText("Reel left");
    ImageCenter.setToolTipText("Reel center");
    ImageRight.setToolTipText("Reel right");
    }
    //a Win message box
    void WinMessageBox() {
        String infoMessage = "You win: " + Win + " .Bet again to start another round.";
        String titleBar = "Results";
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
        Credit = Credit + Win;
        String i = "Credit: " + Credit;
        YourCredit.setText(i);
        Win = 0;
        String s = "Your Win: " + Win;
        YourWin.setText(s);
    }
    //disable buttons
    void DisableDoubleWin() {
        Win = 0;
        String s = "Your Win: " + Win;
        YourWin.setText(s);
        Credit = Credit + 0;
        String c = "Credit: " + Credit;
        YourCredit.setText(c);
    }
    //set the bet to zero
    void setBetToZero() {
        Bet = 0;
        String s = "Your Bet: " + Bet;
        YourBet.setText(s);
    }
    //save file class
    void saveFile() {
        setSaveFileValues();
        s.saveFile();  
    }
    //method to calculate points 
    void YouWinCompare() {
        //compare reel 1 to reel2
        if (Integer.valueOf(rp1.getSymbol().getValue()).compareTo(Integer.valueOf(rp2.getSymbol().getValue())) == 0) {
            System.out.println("--> Reel One and Reel Two");
            Win = Win + ((rp1.getSymbol().getValue()) * Bet);
            if (Bet > 0) {
               
                CountWin++;
                s.setCountWin(CountWin);
            } else {
                CountLos++;
                s.setCountLos(CountLos);
            }
            String s = "Your Win: " + Win;
            YourWin.setText(s);
            Bet = 0;
            String d = "Your Bet: " + Bet;
            YourBet.setText(d);
            if (Win > 0) {
                YourWin.setForeground(Color.red);
                WinMessageBox();
            }
            DisableDoubleWin();
            setBetToZero();
            //compare reel2 to reel3
        } else if (Integer.valueOf(rp1.getSymbol().getValue()).compareTo(Integer.valueOf(rp3.getSymbol().getValue())) == 0) {
            System.out.println("--> Reel One and Reel Three");
            Win = Win + ((rp1.getSymbol().getValue()) * Bet);
            if (Bet > 0) {
                CountWin++;s.setCountWin(CountWin);
            } 
            else {
                CountLos++;s.setCountLos(CountLos);
            }
            String s = "Your Win: " + Win;
            YourWin.setText(s);
            Bet = 0;
            String d = "Your Bet: " + Bet;
            YourBet.setText(d);
            if (Win > 0) {
                YourWin.setForeground(Color.red);
                WinMessageBox();
            }
            DisableDoubleWin();
            setBetToZero();
            //compare reel 2 and reel 3
        } else if (Integer.valueOf(rp2.getSymbol().getValue()).compareTo(Integer.valueOf(rp3.getSymbol().getValue())) == 0) {
            System.out.println("--> Reel Two and Reel Three");
            Win = Win + ((rp1.getSymbol().getValue()) * Bet);
            if (Bet > 0) {
                
                CountWin++;
                s.setCountWin(CountWin);
            } else {
                CountLos++;
                 s.setCountLos(CountLos);
            }
            String s = "Your Win: " + Win;
            YourWin.setText(s);
            Bet = 0;
            String d = "Your Bet: " + Bet;
            YourBet.setText(d);
            if (Win > 0) {
                YourWin.setForeground(Color.red);
                WinMessageBox();
            }
            DisableDoubleWin();
            setBetToZero();
            //compare all three reels
        } else if (Integer.valueOf(rp1.getSymbol().getValue()).compareTo(Integer.valueOf(rp2.getSymbol().getValue())) == 0
                && Integer.valueOf(rp2.getSymbol().getValue()).compareTo(Integer.valueOf(rp3.getSymbol().getValue())) == 0) {
            System.out.println("--> Reel One and Reel Two and Three");
            Win = Win + ((rp1.getSymbol().getValue()) * Bet);
            if (Bet > 0) {
               
                CountWin++;
                s.setCountWin(CountWin);     
            } else {
                CountLos++;
                 s.setCountLos(CountLos);
            }
            String s = "Your Win: " + Win;
            YourWin.setText(s);
            Bet = 0;
            String d = "Your Bet: " + Bet;
            YourBet.setText(d);
            if (Win > 0) {
                YourWin.setForeground(Color.red);
                WinMessageBox();
            }
            DisableDoubleWin();
            setBetToZero();
            //message box saying users lost
        } else {
            Bet = 0;
            String d = "Your Bet: " + Bet;
            YourBet.setText(d);
            CountLos++;
            s.setCountLos(CountLos);
            System.out.println("--> No match found");
            String infoMessage = "You Lost";
            String titleBar = "Message";
            JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);  
        }
        //remove mouse listener 
        ImageLeft.removeMouseListener(ml);
        ImageCenter.removeMouseListener(ml);
        ImageRight.removeMouseListener(ml);
    }
    //add mouse istener 
    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent arg0) {  
            Bet = 0;
            String s = "Your Bet: " + Bet;
            YourBet.setText(s);
            if (Credit >= 1) {
                BetOne.setEnabled(true);
            }
            if (Credit >= 3) {
                BetMax.setEnabled(true);
            }  
        }
        @Override
        public void mouseEntered(MouseEvent arg0) {  
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
        }
        @Override
        public void mousePressed(MouseEvent arg0) {
            //set boolean to false therefore stop it
        rp1.stopSpin();
        rp2.stopSpin();
        rp3.stopSpin();       
        }
        @Override
        public void mouseReleased(MouseEvent arg0) {
           // call compare method
          YouWinCompare();
          //eneble all butto once stopped spinning  
          EnableButtons();
        }
    };
    MouseListener BetMaxMouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
        }
        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }
        @Override
        public void mousePressed(MouseEvent arg0) {
        }
        @Override
        public void mouseReleased(MouseEvent arg0) {
            //set bet max to false when user pressed it once
            BetMax.setEnabled(false);
        }
    };
    //add one button to credit
    private class JButtonHandlerAddOne implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            Credit = Credit + 1;
            String s = "Credit: " + Credit;
            YourCredit.setText(s);
            if (Credit >= 1) {
                BetOne.setEnabled(true);
                 BetOne.setToolTipText("Bet One!!!");
            }
            if (Credit >= 3) {
                BetMax.setEnabled(true);
                 BetMax.setToolTipText("Bet Max!!!");
            }
        }
    }
//pBet one button
    private class JButtonHandlerBetOne implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            Bet = Bet + 1;
            AvBet++;
            String s = "Your Bet: " + Bet;
            YourBet.setText(s);
            Credit = Credit - 1;
            String s1 = "Credit: " + Credit;
            YourCredit.setText(s1);
            if (Credit <= 0) {
                BetOne.setEnabled(false);
                BetMax.setEnabled(false);
                BetOne.setToolTipText("Not enogh credit!!!");
                BetMax.setToolTipText("Not enogh credit to Bet Max!!!");
           }
//            else{ BetOne.setToolTipText("Bet One!!!");
//                BetMax.setToolTipText("Bet Max!!!");}
            if (Credit < 3) {
                BetMax.setEnabled(false);
                 BetMax.setToolTipText("Not enogh credit to Bet Max!!!");
            }
        }
    }
//BetMAx button
    private class JButtonHandlerBetMax implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            Bet = Bet + 3;
            AvBet = AvBet + Bet;
            String s = "Your Bet: " + Bet;
            YourBet.setText(s);
            Credit = Credit - 3;
            String s1 = "Credit: " + Credit;
            YourCredit.setText(s1);
            if (Credit <= 0) {
                BetOne.setEnabled(false);
                BetMax.setEnabled(false);
                BetOne.setToolTipText("Not enogh credit!!!");
                BetMax.setToolTipText("Not enogh credit to Bet Max!!!");
            }
            if (Credit < 3) {
                BetMax.setEnabled(false);
                BetMax.setToolTipText("Not enogh credit to Bet Max!!!");
            }
        }
    }
//Reset button
    private class JButtonHandlerReset implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            BetMax.setEnabled(true);
            Credit = Credit + Bet;
            Bet = 0;
            Win = 0;
            String a = "Credit: " + Credit;
            YourCredit.setText(a);
            String b = "Your Bet: " + Bet;
            YourBet.setText(b);
            String c = "Your Win: " + Win;
            YourWin.setText(c);
        }
    }
//stats button, Another panel
    private class JButtonHandlerStatistics implements ActionListener {
        public void actionPerformed(ActionEvent event) {
          
            JFrame frame = new JFrame("Statistics");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1));
            //progressive bar
            JProgressBar progressBarPlayed = new JProgressBar();
            progressBarPlayed.setValue(spinCount);
            // progressBarPlayed.setStringPainted(true);
            Border borderPlayed = BorderFactory.createTitledBorder("Played: " + spinCount);
            progressBarPlayed.setBorder(borderPlayed);
            panel.add(progressBarPlayed);
            //CountLos
            JProgressBar progressBarWon = new JProgressBar();
            progressBarWon.setValue(CountWin);
            // progressBarWon.setStringPainted(true);
            Border borderWon = BorderFactory.createTitledBorder("Won: " + CountWin);
            progressBarWon.setBorder(borderWon);
            panel.add(progressBarWon);
            JProgressBar progressBarLost = new JProgressBar();
            progressBarLost.setValue(CountLos);
            // progressBarLost.setStringPainted(true);
            Border borderLost = BorderFactory.createTitledBorder("Lost: " + CountLos);
            progressBarLost.setBorder(borderLost);
            panel.add(progressBarLost);
            
             try {
                int TOT = AvBet / spinCount;
               JProgressBar progressBarAverageBet = new JProgressBar();
            progressBarAverageBet.setValue(TOT);
            Border AverageBet = BorderFactory.createTitledBorder("Average Bet: " + TOT);
            progressBarAverageBet.setBorder(AverageBet);
            panel.add(progressBarAverageBet);
            } 
             catch (ArithmeticException e) {
             JProgressBar progressBarAverageBet = new JProgressBar();
            progressBarAverageBet.setValue(0);
            Border AverageBet = BorderFactory.createTitledBorder("Average Bet not available");
            progressBarAverageBet.setBorder(AverageBet);
            panel.add(progressBarAverageBet);
            }
            SaveStatisticsPanel = new JButton("Save Statistics");
            panel.add(SaveStatisticsPanel);
            JButtonHandlerSaveStatisticsPanel handlerSaveStatisticsPanel = new JButtonHandlerSaveStatisticsPanel();
            SaveStatisticsPanel.addActionListener(handlerSaveStatisticsPanel);
            frame.add(panel);
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    //Call save file method which call save file class
    private class JButtonHandlerSaveStatistics implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            saveFile();
        }
    }
//Call save file method which call save file class
    private class JButtonHandlerSaveStatisticsPanel implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            saveFile();
        }
    }
//AddMouse listener
    public void MouseListnerADD() {
        ImageLeft.addMouseListener(ml);
        ImageCenter.addMouseListener(ml);
        ImageRight.addMouseListener(ml);

    }
//spinn button
    public class JButtonHandlerSpin implements ActionListener {
        //process jbutton event
        @Override
        public void actionPerformed(ActionEvent event) {
            s.getSpinCount();
            spinCount++;
            //show messabebox if user want to spin with out betting
            String infoMessage = "You need to Bet in order to play";
            String titleBar = "Message";
            //show mwssage box 
            if (Bet == 0) {
                JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
            } else {
                s.setAvBet(AvBet);
                DisableButtons();
                YourWin.setForeground(null);
                Win = 0;
                String u = "Your Win: " + Win;
                YourWin.setText(u);  
                //spin image left 
                rp1.spin(ImageLeft);
                //spin image center
                rp2.spin(ImageCenter);
                //spin image right
                rp3.spin(ImageRight);
                //add back mouse listener
                
               MouseListnerADD();     
            }

        }
    }

//load JFrame 
    public static void main(String[] args) throws IOException {
        //set Icon using setIcon class
        SetIcon icon = new SetIcon();
        icon.SetIcon();
        //set splash screen using class splash screen
        SetSplashScreen splash = new SetSplashScreen();
       splash.SetSplashScreen();
        Slotmachine frame = new Slotmachine();
        frame.setTitle("Slotmachine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 950);
        frame.setVisible(true);

    }
}
