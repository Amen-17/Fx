package app;

import app.paneles.Escenas;
import app.paneles.Menu;
import app.personaje.Personaje;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Personaje pj = Personaje.getPers();
        Util.setStage(stage);
        Escenas e = Escenas.getEscena();
        stage.setTitle("Menú Principal");
        e.setMenu();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}