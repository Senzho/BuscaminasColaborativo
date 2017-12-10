package InterfazGrafica;

import javafx.scene.control.Alert;

public class MessageFactory {
    private MessageFactory(){
        
    }
    /**
     * Muestra una ventana de mensaje Alert.
     * @param title, el título de la ventana de mensaje.
     * @param header, la cabecera del mensje.
     * @param content, el mensaje a mostrar.
     * @param type, el tipo de alerta del mensaje.
     */
    public static void showMessage(String title, String header, String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
