package app;

import app.datos.Util;
import app.paneles.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Util.setStage(stage);
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