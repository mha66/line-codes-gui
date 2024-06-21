/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linecodesgui;

import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;



/**
 *
 * @author mhame
 */
public class DigitalWave{

    static final int Y_HI = 50;
    static final int Y_0 = 100;
    static final int Y_LO = 150;
    static final int X_INTIAL = 10;
    static final int DX = 50;
   
    ArrayList<Line> lines = new ArrayList<>();

    public DigitalWave() {
        lines.add(new Line(X_INTIAL, Y_0, X_INTIAL + 10*DX, Y_0 ));
    }
    

    public DigitalWave(String signal) {
       signal += 'n'; //end char
       char levels[] = signal.toCharArray();
       
       int x = X_INTIAL;
       for(int i = 0; i < signal.length() - 1; i++)
       {
           if(levels[i] == '0')
           {
               lines.add(new Line(x, Y_0, x+DX, Y_0 ));
               x+=DX;
               if(levels[i+1] == '+')
                   lines.add(new Line(x, Y_0, x, Y_HI ));
               else if(levels[i+1] == '-')
                   lines.add(new Line(x, Y_0, x, Y_LO ));
           }
           else if(levels[i] == '+')
           {
               lines.add(new Line(x, Y_HI, x+DX, Y_HI ));
               x+=DX;
               if(levels[i+1] == '0')
                   lines.add(new Line(x, Y_HI, x, Y_0 ));
               else if(levels[i+1] == '-')
                   lines.add(new Line(x, Y_HI, x, Y_LO ));
           }
           else if(levels[i] == '-')
           {
               lines.add(new Line(x, Y_LO, x+DX, Y_LO ));
               x+=DX;
               if(levels[i+1] == '0')
                   lines.add(new Line(x, Y_LO, x, Y_0 ));
               else if(levels[i+1] == '+')
                   lines.add(new Line(x, Y_LO, x, Y_HI ));
           }
       } 
    }
    
    public static String b8zs(String data){
        String signal = "";
        int lastPolarity = -1;
        
        data = data.replaceAll("00000000", "000vb0vb");
        
        for(int i= 0; i<data.length(); i++)
        {
            switch (data.charAt(i)) {
                case '1':
                case 'b':
                    /*if(lastPolarity > 0)
                        signal += '-';
                    else
                        signal += '+';*/
                    
                    signal += (lastPolarity > 0) ? '-' : '+';
                    lastPolarity *= -1;
                    break;
                
                case 'v':
                    /*if(lastPolarity < 0)
                        signal += '-';
                    else
                        signal += '+';*/
                    signal += (lastPolarity < 0) ? '-' : '+';
                    break;
                case '0':
                    signal += '0';
                    break;
                default:
                    break;
                }
        }
        return signal;
      }
      
     public static String hdb3(String data){
        String signal = "";
        int lastPolarity = -1;
        int numOnes = 0;
        boolean firstSub = false;
        
        for(int i= 0; i < data.length(); i++)
        {
            if(data.regionMatches(i, "0000", 0, 4))
            {
                /*if(numOnes%2 == 0)
                    data = data.replaceFirst("0000", "b00v");
                else
                    data = data.replaceFirst("0000", "000v");*/
                
                data = (numOnes%2 == 0) ? data.replaceFirst("0000", "b00v") : data.replaceFirst("0000", "000v");
                
                firstSub = true;
                numOnes = 0;
            }
            switch (data.charAt(i)) {
                case '1':
                case 'b':
                    
                    signal += (lastPolarity > 0) ? '-' : '+';
                    
                    /*if(lastPolarity > 0)
                        signal += '-';
                    else
                        signal += '+';*/
                    
                    if(data.charAt(i)== '1' && firstSub)
                        numOnes++;
                    
                    lastPolarity *= -1;
                    break;
                
                case 'v':
                    
                     signal += (lastPolarity < 0) ? '-' : '+';
                    /*if(lastPolarity < 0)
                        signal += '-';
                    else
                        signal += '+';*/
                    break;
                    
                case '0':
                    signal += '0';
                    break;
                default:
                    break;
                }
        }
        return signal;
      }
    
     public static String mlt3(String data){
        String signal = "";
        int currentPolarity = 1;
        boolean dirUp = true ;
           
        for(int i= 0; i < data.length(); i++)
        {
            switch (data.charAt(i)) {
                case '1':
                    
                    signal += (currentPolarity == 1) ? '+' : 
                              (currentPolarity == 0) ? '0' :
                              '-';  
                   /* if(currentPolarity == 1)
                        signal += '+';
                    else if(currentPolarity == 0)
                        signal += '0';
                    else
                        signal += '-';
                    */
                    if(currentPolarity == -1 || currentPolarity == 1)
                        dirUp = !dirUp;
                    
                    if(dirUp)
                        currentPolarity++;
                    else
                        currentPolarity--;
                    
                    break;
                
                case '0':
                   /* if(i == 0)
                        signal += '0';
                    else
                        signal += signal.charAt(i-1);*/
                    signal += (i==0) ? '0' : signal.charAt(i-1);
                    break;
                default:
                    break;
                }
        }
        return signal;
      }
     
    }

    
    
      
    
    

