/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package linecodesgui;


import java.lang.reflect.Method;
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
    
   
    
    @Override
    public void start(Stage primaryStage) {
     
        Pane pane = new Pane();
        pane.getChildren().addAll(w.lines);
       
        
        TextField tf = new TextField();
        tf.setPromptText("Enter digital data");
        tf.setTextFormatter(new TextFormatter <> (  change -> change.getControlNewText().matches("[0-1]*") ? change : null));
        
        ComboBox<DigitalWave.LineCode> codeTypes = new ComboBox();
        codeTypes.getItems().addAll(DigitalWave.LineCode.values());
        codeTypes.setValue(DigitalWave.LineCode.MLT3);
        
       
        
        Button submit = new Button("Submit data");
        submit.setOnAction((ActionEvent e) -> {
            w = new DigitalWave(DigitalWave.dataToSignal(codeTypes.getValue(), tf.getText()));
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