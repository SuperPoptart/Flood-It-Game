import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Flood-It!");//Setting the window's Title to the game name!

        Startup.getStartButton().setOnAction(e -> {
            window.setScene(Startup.inBetween());
        });
        Startup.getExitButton().setOnAction(e -> {
            boolean exits = ConfirmBox.display("Exit?", "Are you sure you want to exit?");
            if(exits){
                window.close();
            }
        });

        window.setScene(Startup.Startup());
        window.sizeToScene();
        window.show();
        window.setX(300);
        window.setY(0);
    }//Puts functionality into the first part of the game

    public static void setWindow(Scene windows) {
        window.setScene(windows);
    }
}