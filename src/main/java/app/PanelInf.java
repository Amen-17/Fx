package app;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.util.ArrayList;

public class PanelInf extends HBox {

    private Puntuacion p;
    private ArrayList<Vida> vidas;
    private static PanelInf panel;

    public PanelInf(){
        p=Puntuacion.getPuntuacion();
        setStyle("-fx-background-color: GRAY");//Le ponemos un color mediante un comando de CSS
        panel = this;
        vidas = Vida.getVidas();
        montarEscena(); //Añado la puntuación al panel y la centro
    }

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

    public static PanelInf getPanel(){
        return panel;
    }

}
