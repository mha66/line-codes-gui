
package linecodesgui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
        
        pane.getChildren().addAll(
                new Text(0, DigitalWave.Y_HI, "V+"),
                new Text(0, DigitalWave.Y_0, "0"),
                new Text(0, DigitalWave.Y_LO, "V-"),
                w.wave);
       
        
        
        TextField tf = new TextField();
        tf.setPromptText("Enter digital data");
        tf.setMaxWidth(600);
        tf.setTextFormatter(new TextFormatter <> (  change -> change.getControlNewText().matches("[0-1]*") ? change : null));
        
        ComboBox<DigitalWave.LineCode> codeTypes = new ComboBox();
        codeTypes.getItems().addAll(DigitalWave.LineCode.values());
        codeTypes.setValue(codeTypes.getItems().get(0));
        
        class WaveConverter{
            public void convert(){
                pane.getChildren().remove(w.wave);
                w = tf.getText().isEmpty() ? new DigitalWave() :
                        new DigitalWave(DigitalWave.dataToSignal(codeTypes.getValue(), tf.getText()), tf.getText());
                pane.getChildren().add(w.wave);
            }
        }
        
        WaveConverter waveConverter = new WaveConverter(); 
        
        try{
            codeTypes.setOnAction((ActionEvent e) -> waveConverter.convert());
        } catch (Exception e) {System.out.println(e);}
        
        Button submit = new Button("Convert Data");
        try{
            submit.setOnAction((ActionEvent e) -> waveConverter.convert());
        } catch(Exception e) {System.out.println(e);}
        
        
        FlowPane flow = new FlowPane(Orientation.VERTICAL, 0, 50, pane, codeTypes, tf, submit);
        flow.setAlignment(Pos.CENTER);
        flow.setColumnHalignment(HPos.CENTER);
        FlowPane.setMargin(pane, new Insets(0, 0, 0, 30));
 
        ScrollPane scrollPane = new ScrollPane(flow);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
   
        Scene scene = new Scene(scrollPane, 300, 250);
        try{
        scene.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER)
                waveConverter.convert();
        });
        }catch(Exception e){System.out.println(e);}
        
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