package app.paneles;

import app.Util;
import app.datos.HistorialPartidas;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Stop;
import javafx.geometry.Insets;

public class Menu extends Pane {

    private ArrayList<Boton> botones;
    private static Pane menu;
    private static Pane gameOver;
    private static PanelPrincipal panelPrin;

    /**
     * crea la primera ventana que se ve, permite dar opcionde iniciar el juego llamando al util, o salir
     * @param stage
     */
    public Menu(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #1e1e2f, #38384f);");

        //musica reproducir
        MusicaFondo.reproducir();
        botones = new ArrayList<>();
        // Título del juego
        Label titulo = new Label("SHOOTER");
        titulo.setFont(Font.font("Arial", 60));
        titulo.setTextFill(Color.WHITE);
        titulo.setStyle("-fx-effect: dropshadow(one-pass-box, black, 4, 0.5, 0, 2);");


        // Aplicar sombra real (mejor que usar setStyle)
        DropShadow sombra = new DropShadow();
        sombra.setColor(Color.BLACK);
        sombra.setRadius(4);
        sombra.setOffsetY(2);
        titulo.setEffect(sombra);



        // Botón que inicia el juego
        Boton botonIniciar = new Boton("Iniciar Juego",450, 300,() -> {
                    Escenas.getEscena().setPanelPri();
                    Util.iniciarJuego();
                    Personaje.getPers().rotacionRaton(Escenas.getEscena().getAct());
                    Tiempo.getTiempoTotal();

                }
        );

        Boton botonSalir = new Boton("Salir",450, 420,stage::close);

        botones.add(botonIniciar);
        botones.add(botonSalir);

        // Layout vertical para título y botones
        VBox menuVBox = new VBox(30); // Espacio vertical entre nodos
        menuVBox.setAlignment(Pos.CENTER);
        menuVBox.getChildren().addAll(titulo, botonIniciar, botonSalir);

        getChildren().addAll(botones);
        getChildren().add(menuVBox);
        HistorialPartidas.top3Partidas();

    }
}