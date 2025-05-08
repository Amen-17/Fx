package app.paneles;

import app.enemigo.GestorEnemigos;
import app.personaje.Personaje;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {

    public PanelPrincipal(){
        setCenter(new PanelJuego());
        setBottom(new PanelInf());
    }

    public static void empezarJuego(){
        GestorEnemigos.comenzar();
        Personaje.getPers().reiniciarPJ();
    }
}
