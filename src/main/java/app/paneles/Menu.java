package app.paneles;

import app.Util;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import javafx.scene.layout.Pane;
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

//                    Scene escenaJuego = new Scene(PanelPrincipal.getPanelPrincipal(), 1200, 800);
//                    stage.setScene(escenaJuego);
//                    Personaje pj = Personaje.getPers();
//                    pj.rotacionRaton(escenaJuego);
//                    GestorEnemigos.comenzar();

//                    Tiempo t = Tiempo.getTiempoTotal(); //si sustituyo esto por Tiempo.detenercronometro me peta
//                    t.iniciarCronometro();

                        Tiempo.detenerCronometro();
                        Tiempo.getTiempoTotal();
                }
        );

        Boton botonSalir = new Boton("Salir",450, 420,stage::close);

        botones.add(botonIniciar);
        botones.add(botonSalir);

        getChildren().addAll(botones);
    }
}