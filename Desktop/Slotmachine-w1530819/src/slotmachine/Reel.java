/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

import java.util.*;
import javax.swing.*;

public class Reel {
    private ArrayList<Symbol> images;
    
    public Reel(){
        //instanciate null symbol
        Symbol one = null;
        Symbol two = null;
        Symbol three = null;
        Symbol four = null;
        Symbol five = null;
        Symbol six = null;
        // instanciate the array list
        images = new ArrayList<Symbol>();
        
        try{
        one = new Symbol(7, new ImageIcon(getClass().getResource("Resources/Images/redseven.png")));
        }catch(NullPointerException e){
            System.out.println("The image 1 is not found");
        }
        try{
        two = new Symbol(6, new ImageIcon(getClass().getResource("Resources/Images/bell.png")));
        }catch(NullPointerException e){
            System.out.println("The image 2 is not found");
        }
        try{
        three = new Symbol(5, new ImageIcon(getClass().getResource("Resources/Images/watermelon.png")));
        }catch(NullPointerException e){
            System.out.println("The image 3 is not found");
        }
        try{
        four = new Symbol(4, new ImageIcon(getClass().getResource("Resources/Images/plum.png")));
        }catch(NullPointerException e){
            System.out.println("The image 4 is not found");
        }
        try{
        five = new Symbol(3, new ImageIcon(getClass().getResource("Resources/Images/lemon.png")));
        }catch(NullPointerException e){
            System.out.println("The image 5 is not found");
        }
        try{
        six = new Symbol(2, new ImageIcon(getClass().getResource("Resources/Images/cherry.png")));
        }catch(NullPointerException e){
            System.out.println("The image 6 is not found");
        }
        
        // add symbols to arraylist
        images.add(one);
        images.add(two);
        images.add(three);
        images.add(four);
        images.add(five);
        images.add(six);
    }
    
    public Symbol spin(){
        // retrieve a random number
        Random rand = new Random();
        int randomNum; 
        randomNum = rand.nextInt(images.size());        
        return images.get(randomNum);
    }
    
}
