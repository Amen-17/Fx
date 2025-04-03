package app;

import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {
    public PanelPrincipal(){
        setCenter(new PanelJuego());
        setBottom(new PanelInf());
    }
}
