import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Just a regular alert

public class Alertbox2 {

    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);//We are gonna block interaction with other window until this i taken care of
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button button = new Button("Close");
        button.setOnAction(e -> window.close());

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();//Displays the window but waits for it to close

    }

}









