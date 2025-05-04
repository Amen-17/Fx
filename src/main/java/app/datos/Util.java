package app.datos;

import javafx.stage.Stage;

import java.io.File;

public class Util {
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
