package app.personaje;

import app.Main;
import app.Util;
import app.datos.XML;
import app.paneles.Escenas;
import app.paneles.Menu;
import app.paneles.PanelInf;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Vida extends Rectangle {

    private static ArrayList<Vida> vidas;
    private Image img = new Image("file:src/main/java/app/imgs/vida.png");
    private static int indiceAct = 3;
    public static boolean muerto;

    private Vida() {
        super(50, 50);
        setFill(new ImagePattern(img));
        muerto = false;
    }

    public static ArrayList<Vida> getVidas() {
        if (vidas == null) {
            vidas = new ArrayList<>();
            for (int cont = 0; cont < 3; cont++) {
                vidas.add(new Vida());
            }
        }
        return vidas;
    }

    public static void reducirVida() {
        if (indiceAct > 0) {
            System.out.println("Mi indice es"+indiceAct);
            indiceAct--;
            Vida v = vidas.get(indiceAct);
            PanelInf.getPanel().getChildren().remove(v);
        }

        if (indiceAct == 0) {
            Tiempo tiempo = Tiempo.getTiempoTotal();
            Puntuacion puntuacion = Puntuacion.getPuntuacion();
            tiempo.detenerCronometro();
            muerto = true;
            try {
                XML xml = new XML(puntuacion, tiempo);
                xml.guardarPartida();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Escenas.getEscena().setGameOver();
        }
    }

    public static void reiniciarVidas(){
        indiceAct = 3;
        vidas = null;
    }
}
