package app.paneles;

import app.enemigo.GestorEnemigos;
import app.personaje.Personaje;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {
    private static PanelInf panelinf;

    public PanelPrincipal(){
        setCenter(PanelJuego.getPanelJuego());
        setBottom(PanelInf.getPanelinf());
        panelinf = PanelInf.getPanelinf();
    }

    public static PanelInf getPanelinf(){
        return panelinf;
    }

}
