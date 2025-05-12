package app;

import app.enemigo.GestorEnemigos;
import app.personaje.Personaje;
import app.personaje.Vida;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Util {

    public static final String ARCHIVO_PARTIDAS = "partidas.xml";
    private static Stage stagePrincipal;

    public static File getArchivoPartidas() {
        return new File("./partidas.xml");
    }
    public static void setStage(Stage stage) {
        stagePrincipal = stage;
    }

    public static Stage getStage() {
        return stagePrincipal;
    }

    public static void iniciarJuego(){
        Personaje.getPers().reiniciarPJ();
        GestorEnemigos.comenzar();
        Vida.getVidas();
    }
    public static void pararJuego(){
        GestorEnemigos.detener();
        Personaje.getAni().stop();
        System.out.println("Se ha parado el juego");
    }

    public static void llenarFondoConBaldosas(Pane root, double ancho, double alto) {
        Image baldosa = new Image("file:src/main/java/app/imgs/Fondo.jpg");

        double tileWidth = baldosa.getWidth();
        double tileHeight = baldosa.getHeight();

        for (double y = 0; y < alto; y += tileHeight) {
            for (double x = 0; x < ancho; x += tileWidth) {
                ImageView tile = new ImageView(baldosa);
                tile.setLayoutX(x);
                tile.setLayoutY(y);
                root.getChildren().add(tile);
            }
        }
    }
}
