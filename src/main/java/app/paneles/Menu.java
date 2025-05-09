package app.paneles;

import app.Util;
import app.datos.HistorialPartidas;
import app.datos.Partida;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import app.personaje.Vida;
import com.almasb.fxgl.ui.UI;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Menu extends Pane {

    private ArrayList<Boton> botones;
    private static Pane menu;
    private static Pane gameOver;
    private static PanelPrincipal panelPrin;

    public Menu(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #1e1e2f, #38384f);");

        botones = new ArrayList<>();

        // Botón que inicia el juego
        Boton botonIniciar = new Boton("Iniciar Juego",450, 300,() -> {
                    Escenas.getEscena().setPanelPri();
                    Util.iniciarJuego();
                    Personaje.getPers().rotacionRaton(Escenas.getEscena().getAct());
                    Tiempo t = Tiempo.getTiempoTotal();
                    t.iniciarCronometro();
                }
        );

        Boton botonSalir = new Boton("Salir",450, 420,stage::close);

        botones.add(botonIniciar);
        botones.add(botonSalir);

        getChildren().addAll(botones);
    }
}