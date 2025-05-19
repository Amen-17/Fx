package app;

import app.enemigo.GestorEnemigos;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import app.personaje.Vida;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * inicia la partida creando al personaje y generando los enemigos
     */
    public static void iniciarJuego(){
        Personaje.getPers().reiniciarPJ();
        GestorEnemigos.comenzar();
        Vida.getVidas();
        Tiempo.iniciarCronometro();
    }

    /**
     * detiene la generacion de enemigos y al personaje
     */
    public static void pararJuego(){
        GestorEnemigos.detener();
        Personaje.getAni().stop();
        System.out.println("Se ha parado el juego");
    }

    /**
     * utiliza las baldosaspara generar el suelo
     * @param root
     * @param ancho
     * @param alto
     */
    public static void pintarSuelo(Pane root, double ancho, double alto) {
        ArrayList<Image> suelo = new ArrayList<>();
        suelo.add(new Image("file:src/main/java/app/imgs/Baldosa1.png"));
        suelo.add((new Image("file:src/main/java/app/imgs/Baldosa2.png")));
        suelo.add((new Image("file:src/main/java/app/imgs/Baldosa3.png")));
        suelo.add((new Image("file:src/main/java/app/imgs/Baldosa3.png")));

        double tileWidth = suelo.getFirst().getWidth();
        double tileHeight = suelo.getFirst().getHeight();

        for (double y = 0; y < alto; y += tileWidth ){
            for (double x = 0; x < ancho; x += tileHeight) {
                Random aleatorio = new Random();
                ImageView tile = new ImageView(suelo.get(aleatorio.nextInt(suelo.size())));
                tile.setLayoutX(x);
                tile.setLayoutY(y);
                root.getChildren().add(tile);
            }
        }
    }
}
