package app;

import app.enemigo.Enemigo;
import app.enemigo.GestorEnemigos;
import app.personaje.Personaje;
import app.personaje.Vida;
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
}
