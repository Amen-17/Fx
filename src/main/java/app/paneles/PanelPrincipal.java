package app.paneles;

import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {
    private static PanelInf panelinf;

    /**
     * crea el panel en el que se juega llamando a los metodos que conforman tambien este panel
     */
    public PanelPrincipal(){
        setCenter(PanelJuego.getPanelJuego());
        setBottom(PanelInf.getPanelinf());
        panelinf = PanelInf.getPanelinf();
    }

    /**
     * llama al panelinf
     * @return el panelinf
     */
    public static PanelInf getPanelinf(){
        return panelinf;
    }

}
