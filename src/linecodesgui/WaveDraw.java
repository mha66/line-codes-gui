/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linecodesgui;

import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author mhame
 */
public abstract class WaveDraw {
    
    static final int Y_HI = 50;
    static final int Y_0 = 100;
    static final int Y_LO = 150;
    static final int X_INTIAL = 20;
    static final int DX = 50;
    static final int DATA_VOFFSET = -10;
    static final int DATA_HOFFSET = DX/2 - 3;
   
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Text> dataText = new ArrayList<>();
    

    public WaveDraw() {
          lines.add(new Line(X_INTIAL, Y_0, X_INTIAL + 10*DX, Y_0 ));
          int x = X_INTIAL;
          for(int i = 0 ; i < 10; i++)
          {
            dataText.add(new Text(x + DATA_HOFFSET, Y_HI + DATA_VOFFSET, "0"));
            x+=DX;
          }
    }
    
    public WaveDraw(String signal, String data){
       boolean hasHalfLevels = signal.length() != data.length();
       signal += 'n'; //end char
       char levels[] = signal.toCharArray();
       
       int x = X_INTIAL;
       for(int i = 0; i < signal.length() - 1; i++)
       {
           if(levels[i] == '0')
           {
              lines.add(new Line(x, Y_0, x+DX, Y_0 ));
              if(!hasHalfLevels || i%2 == 0)
                    dataText.add(new Text(x + DATA_HOFFSET, Y_HI + DATA_VOFFSET,
                            String.valueOf(data.charAt(hasHalfLevels ? i/2 : i))));
               x += DX;
              
               if(levels[i+1] != levels[i] && levels[i+1] != 'n') 
                   lines.add(new Line(x, Y_0, x,
                           (levels[i+1]== '+') ? Y_HI : Y_LO ));
         
           }
           else if(levels[i] == '+')
           {
                lines.add(new Line(x, 
                                 Y_HI,
                                 hasHalfLevels ? x+DX/2 : x+DX,
                                 Y_HI ));
               
                if(!hasHalfLevels || i%2 == 0)
                    dataText.add(new Text(x + DATA_HOFFSET, Y_HI + DATA_VOFFSET,
                            String.valueOf(data.charAt(hasHalfLevels ? i/2 : i))));
               x += hasHalfLevels ? DX/2 : DX;
              
               if(levels[i+1] != levels[i] && levels[i+1] != 'n')
                   lines.add(new Line(x, Y_HI, x,
                           (levels[i+1]== '0') ? Y_0 : Y_LO ));
           }
           else if(levels[i] == '-')
           {
               lines.add(new Line(x, 
                                 Y_LO,
                                 hasHalfLevels ? x+DX/2 : x+DX,
                                 Y_LO ));
               
               if(!hasHalfLevels || i%2 == 0)
                    dataText.add(new Text(x + DATA_HOFFSET, Y_HI + DATA_VOFFSET,
                            String.valueOf(data.charAt(hasHalfLevels ? i/2 : i))));
               x += hasHalfLevels ? DX/2 : DX;
              
                if(levels[i+1] != levels[i] && levels[i+1] != 'n')
                   lines.add(new Line(x, Y_LO, x,
                           (levels[i+1]== '0') ? Y_0 : Y_HI ));
           }
       } 
    }
}
