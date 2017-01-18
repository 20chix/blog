/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;
import javax.swing.Icon;
/**
 *
 * @author hadielmekawi
 */
public class Symbol implements ISymbol{
    private int value;
    private Icon Img;
    
    
//  Seven:	7
// Bell:        6
// Watermelon:	5
// Plum:    	4
// Lemon:	3
// Cherry:	2
    //constructor
    Symbol(int value, Icon Img){
        
        this.value= value;
        this.Img = Img;
    }
    //set image of symbol
     @Override
     public  void setImage(Icon Img){
         this.Img = Img;    
     }
     //get image of symbol
    @Override
    public Icon getImage(){
        return Img;
    }
    //set value of symbol
    @Override
    public void setValue(int value){
        this.value = value;
    }
    //get value of symbol
    @Override
    public int getValue(){
         return value;
    }
    //compare method to get 0 -1 or 1 
    public static int compareTo(int x, int y) {
      return (x < y) ? -1 : ((x == y) ? 0 : 1);
  }
    

} 

