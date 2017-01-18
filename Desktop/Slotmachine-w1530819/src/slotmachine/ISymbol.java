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
 * 
 */
public interface ISymbol {
    // interface  for symbol class
    
    void setImage(Icon Img);
    Icon getImage();
    void setValue(int value);
    int getValue();
    
    
    
}
