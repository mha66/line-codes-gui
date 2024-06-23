/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linecodesgui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;




/**
 *
 * @author mhame
 */
public class DigitalWave extends WaveDraw{
    
      public enum LineCode{
        AMI, PSEUDOTERNARY, MLT3, B8ZS, HDB3;
    } 
    
    Pane wave = new Pane();
      
    public DigitalWave() {
      super();
      wave.getChildren().addAll(lines);
      wave.getChildren().addAll(dataText);
    }
    
    
    public DigitalWave(String signal, String data) {
      super(signal, data);
      wave.getChildren().addAll(lines);
      wave.getChildren().addAll(dataText);
    }
    
   
    public static String dataToSignal(LineCode selection, String data){
        try{
            //m is the method with the same name as the selected line code
            Method m = DigitalWave.class.getDeclaredMethod(selection.toString().toLowerCase(), String.class);
            return (String)m.invoke(DigitalWave.class, data);
        }
       catch(Exception e){
             System.out.println(e.toString());
             return "0000000000";
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
                    signal += (lastPolarity > 0) ? '-' : '+';
                    lastPolarity *= -1;
                    break;
                
                case 'v':
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
                data = (numOnes%2 == 0) ? data.replaceFirst("0000", "b00v") : data.replaceFirst("0000", "000v");
                firstSub = true;
                numOnes = 0;
            }
            switch (data.charAt(i)) {
                case '1':
                case 'b':
                    
                    signal += (lastPolarity > 0) ? '-' : '+';
                    if(data.charAt(i)== '1' && firstSub)
                        numOnes++;
                    
                    lastPolarity *= -1;
                    break;
                
                case 'v':
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
                    
                    if(currentPolarity == -1 || currentPolarity == 1)
                        dirUp = !dirUp;
                    
                    if(dirUp)
                        currentPolarity++;
                    else
                        currentPolarity--;
                    
                    break;
                
                case '0':
                    signal += (i==0) ? '0' : signal.charAt(i-1);
                    break;
                default:
                    break;
                }
        }
        return signal;
      }
     
      public static String pseudoternary(String data){
        String signal = "";
        int lastPolarity = -1;
        
        for(int i= 0; i < data.length(); i++)
        {
            switch (data.charAt(i)) {
                
                case '1':
                   signal += '0';
                   break;
                    
                case '0':
                    signal += (lastPolarity > 0) ? '-' : '+';
                    lastPolarity *= -1;
                    break;
                    
                default:
                    break;
                }
        }
        return signal;
      }
     
      public static String ami(String data){
        String signal = "";
        int lastPolarity = -1;
        
        for(int i= 0; i < data.length(); i++)
        {
            switch (data.charAt(i)) {
                case '1':
                    signal += (lastPolarity > 0) ? '-' : '+';
                    lastPolarity *= -1;
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
     
    }

    
    
      
    
    

