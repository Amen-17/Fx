package app.paneles;

import app.personaje.Puntuacion;
import app.personaje.Vida;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.util.ArrayList;

public class PanelInf extends HBox {

    private Puntuacion p;
    private ArrayList<Vida> vidas;
    private static PanelInf panel;

    /**
     * genera como se veria ese panel
     */
    private PanelInf(){
        p=Puntuacion.getPuntuacion();
        setStyle("-fx-background-color: GRAY");//Le ponemos un color mediante un comando de CSS
        vidas = Vida.getVidas();
        montarEscena();
    }

    /**
     * lo devuelve y si no se ha llamado antes se crea
     * @return
     */
    public static PanelInf getPanelinf(){
        if (panel == null){
            panel = new PanelInf();
        }
        return panel;
    }

    /**
     *
     */
    private void montarEscena() {
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        for (Vida act:vidas){
            getChildren().add(act);
        }
        getChildren().add(espacio);
        getChildren().add(p);
        setAlignment(Pos.CENTER);
    }

}
