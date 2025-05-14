package app.enemigo;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class EnemigoComun extends Enemigo {
    private Bounds pocEneCom;

    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, (Math.random()*2)*dificultad);
        setFill(Color.RED);
        atacar();
    }

    public EnemigoComun(double poX, double poY){
        super(poX, poY, 50, 50, 1, (Math.random()*2)+dificultad);
        setFill(Color.RED);
        atacar();
        imagen = new Image("file:src/main/java/app/imgs/EnemiCom.png");
        setFill(new ImagePattern(imagen));
    }

    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                if (GestorEnemigos.comprobarColisiones(EnemigoComun.this)){
                    EnemigoComun.super.atacar();
//                }
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
