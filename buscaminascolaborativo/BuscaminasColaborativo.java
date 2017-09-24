package buscaminascolaborativo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BuscaminasColaborativo /*extends Application*/ {
    
    /*
    Al ser esta la clase principal, no carga la ventana que te pone javaFX por default, yo entiendo, que para crear una 
    ventana, se hará otra clase digamos "VentanaInicio", y esa será la que extienda de "Application" y sobreescriba el 
    método "start"
    */
    
    /*@Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        //launch(args);
    }
    
}
