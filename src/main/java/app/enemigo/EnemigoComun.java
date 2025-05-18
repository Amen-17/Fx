package app.enemigo;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class EnemigoComun extends Enemigo {
    private Bounds pocEneCom;

    /**
     * Constructor básico de la clase EnemigoComun.
     */
    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, (Math.random()*2)*dificultad);
        setFill(Color.RED);
        atacar();
    }
    /**
     * Constructor de la clase EnemigoComun que nos permite que aparezca de forma aleatoria por los 4 lados de la pantalla.
     *
     * @param poX Posición aleatoria X
     * @param poY Posición aleatoria Y
     */
    public EnemigoComun(double poX, double poY){
        super(poX, poY, 50, 50, 1, (Math.random()*2)+dificultad);
        setFill(Color.RED);
        atacar();
        imagen = new Image("file:src/main/java/app/imgs/EnemiCom.png");
        setFill(new ImagePattern(imagen));
    }

    /**
     * Método sobreescrito de la clase madre Enemigo que llama al método madre, al método de rotar madre y almacena su posición.
     */
    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                    EnemigoComun.super.atacar();
                EnemigoComun.super.rotar();
                pocEneCom = EnemigoComun.this.localToScene(EnemigoComun.this.getBoundsInLocal());
            }
        }; t.start();
    }

    @Override
    public Bounds getPosc() {
        return pocEneCom;
    }
}
