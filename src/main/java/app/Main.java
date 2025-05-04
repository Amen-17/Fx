package app;

import app.paneles.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu(stage);
        Scene escena = new Scene(menu, 1200, 800);
        stage.setTitle("Menú Principal");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}