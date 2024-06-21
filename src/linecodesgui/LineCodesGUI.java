/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package linecodesgui;

import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author mhame
 */
public class LineCodesGUI extends Application {
    
    
    private static DigitalWave w = new DigitalWave();
    
    public enum LineCode{
        MLT3, B8ZS, HDB3;
    } 
    
    private static void setDigitalWave(LineCode selection, String data){
        
        switch(selection){
            case MLT3:
                w = new DigitalWave(DigitalWave.mlt3(data));
                break;
            case B8ZS:
                w = new DigitalWave(DigitalWave.b8zs(data));
                break;
            case HDB3:
                w = new DigitalWave(DigitalWave.hdb3(data));
                break;
        }
    } 
    
    @Override
    public void start(Stage primaryStage) {
     
        //DigitalWave w = new DigitalWave(DigitalWave.mlt3("0110010110100100001"));
        //DigitalWave w = new DigitalWave(DigitalWave.hdb3("01000000001011000001"));
        //DigitalWave w = new DigitalWave(DigitalWave.b8zs("0100100000000101"));
        
        Pane pane = new Pane();
        pane.getChildren().addAll(w.lines);
       
        
        TextField tf = new TextField();
        tf.setPromptText("Enter digital data");
        tf.setTextFormatter(new TextFormatter <> (  change -> change.getControlNewText().matches("[0-1]*") ? change : null));
        
        ComboBox<LineCode> codeTypes = new ComboBox();
        codeTypes.getItems().addAll(LineCode.values());
        codeTypes.setValue(LineCode.MLT3);
        
       
        
        Button submit = new Button("Submit data");
        submit.setOnAction((ActionEvent e) ->{
            setDigitalWave(codeTypes.getValue(), tf.getText());
            pane.getChildren().setAll(w.lines);
        });
        
        FlowPane flow = new FlowPane(Orientation.VERTICAL, 0, 50, pane, codeTypes, tf, submit);
        flow.setAlignment(Pos.CENTER);
        flow.setColumnHalignment(HPos.CENTER);
        Scene scene = new Scene(flow, 300, 250);
           
        primaryStage.setTitle("Line Codes");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


  /* 
        
        
        
        Circle c1 = new Circle(60,60,80);
        c1.setFill(null);
        c1.setStroke(Color.RED);
        
        Circle c2 = new Circle(60,60,80);
        c2.setFill(null);
        c2.setStroke(Color.BLUE);
        
        Circle c3 = new Circle(60,60,80);
        c3.setFill(null);
        c3.setStroke(Color.PURPLE);
        
        Button grow = new Button("Grow");
       grow.setOnAction((ActionEvent event) -> {
            c1.setRadius(c1.getRadius() + 5 );
       });
      
        
        
        class ShrinkHandler implements EventHandler<ActionEvent>
        {

            @Override
            public void handle(ActionEvent event) {
                if(c1.getRadius() > 5)
                 c1.setRadius(c1.getRadius() - 5);
        }}
        
         Button shrink = new Button("Shrink");
         ShrinkHandler shrinker = new ShrinkHandler();
         shrink.setOnAction(shrinker);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        //grid.setRotate(5);
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        //root.getChildren().add(btn);
         grid.add(c1, 0, 0);
         grid.add(grow, 0, 1);
         grid.add(c2, 1, 0);
         grid.add(shrink, 1, 1);
         
        // StackPane pane = new StackPane(grid);
         grid.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.PRIMARY)
                c1.setRadius(c1.getRadius() + 5 );
            else if(e.getButton() == MouseButton.SECONDARY && c1.getRadius() > 5 )
                c1.setRadius(c1.getRadius() - 5);
            else if(e.getButton() == MouseButton.MIDDLE)
            {
                if(c1.getFill() == null)
                    c1.setFill(Color.YELLOW);
                else
                    c1.setFill(null);
            }
            
        });
         
//         grid.setOnKeyPressed(e ->{
//             if(e.getCode() == KeyCode.W)
//                 grid.setLayoutY(grid.getLayoutY() - 10);
//             else if(e.getCode() == KeyCode.S)
//                 grid.setLayoutY(grid.getLayoutY() + 10);
//         });
        
         
          
        StackPane sp = new StackPane(grid, c3);
        
        
        sp.setOnKeyPressed(e -> {
             if(e.getCode() == KeyCode.W)
                c3.setCenterY(c3.getCenterY() - 10);
             else if(e.getCode() == KeyCode.S)
                 c3.setCenterY(c3.getCenterY() + 10);
             else if(e.getCode() == KeyCode.D)
                 c3.setCenterX(c3.getCenterX() + 10);
             else if(e.getCode() == KeyCode.A)
                 c3.setCenterX(c3.getCenterX() - 10);
             
         });
         sp.requestFocus();
        
        Scene scene = new Scene(sp, 300, 250);
        
        primaryStage.setTitle("S");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
     */