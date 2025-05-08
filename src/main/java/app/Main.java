package app;

import app.paneles.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

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

    public static class Util {
        public static final String ARCHIVO_PARTIDAS = "partidas.xml";
        private static Stage stagePrincipal;

        public static void setStage(Stage stage) {
            stagePrincipal = stage;
        }

        public static Stage getStage() {
            return stagePrincipal;
        }

        public static File getArchivoPartidas() {
            return new File("./partidas.xml");
        }
    }
}