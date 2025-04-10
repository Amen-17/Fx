package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemigoComun extends Enemigo{

    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, (Math.random()*2)*dificultad);
        setFill(Color.RED);
        atacar();
    }

    public EnemigoComun(double poX, double poY){
        super(poX, poY, 50, 50, 1, (Math.random()*2)+dificultad);
        setFill(Color.RED);
        atacar();
    }

    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                EnemigoComun.super.atacar();
                EnemigoComun.super.rotar();
            }
        }; t.start();
    }
}
