package app;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Vida extends Rectangle {

    private static ArrayList<Vida> vidas;
    private Image img = new Image("file:src/main/java/app/imgs/vida.png");
    private static int indiceAct = 2;

    private Vida() {
        super(50, 50);
        setFill(new ImagePattern(img));
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
        if (indiceAct >= 0) {
            Vida v = vidas.get(indiceAct);
            PanelInf.getPanel().getChildren().remove(v);
            indiceAct--;
        }
        if (indiceAct ==0){
            Tiempo t = Tiempo.getTiempoTotal();
            t.detenerCronometro();
        }
    }
}
