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
        p=Puntuacion.getPuntuacion(); // Obtiene instancia única de puntuación
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
     * Monta la escena del panel inferior, añadiendo las vidas, un espacio flexible y la puntuación.
     */
    private void montarEscena() {
        Region espacio = new Region(); // Región vacía para separar elementos
        HBox.setHgrow(espacio, Priority.ALWAYS);  // El espacio crecerá y empujará elementos

        // Añade todas las imágenes de vida al panel
        for (Vida act:vidas){
            getChildren().add(act);
        }
        getChildren().add(espacio);  // Añade espacio flexible entre vidas y puntuación
        getChildren().add(p);         // Añade el objeto puntuación (texto)
        setAlignment(Pos.CENTER);     // Centra verticalmente los elementos dentro del HBox
    }

}
