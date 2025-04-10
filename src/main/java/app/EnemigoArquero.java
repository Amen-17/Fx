package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemigoArquero extends Enemigo {

    private static Bounds posEnArc;
    private AnimationTimer t;
    private static long tDisp = 600_000_000;
    private static long tAnter;

    public EnemigoArquero() {
        super(Math.random() * 1150, -50, 50, 50, 1, 2);
        atacar();
        setFill(Color.BLUE);
    }
    //Solo dispara uno solo, ni idea
    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                posEnArc = getBoundsInParent();
                EnemigoArquero.super.rotar();
                if (comprobarDistancia(posEneArc(), Personaje.getPos())) {
                    if ( l - tAnter > tDisp) {
                        disparar();
                        tAnter = l;
                    }
                } else {
                    EnemigoArquero.super.atacar();
                }
            }
        };
        t.start();
    }

    private void disparar() {
        //Angulo actual
        double angulo = getRotate();
        double radio = getBoundsInParent().getHeight() / 2; // Distancia desde el centro a la parte superior
        double anguloRad = Math.toRadians(angulo);
        //Posicion X
        double disparoX = getBoundsInParent().getCenterX() + Math.cos(anguloRad) * radio;
        //Posicion Y
        double disparoY = getBoundsInParent().getCenterY() + Math.sin(anguloRad) * radio;
        Disparo d = new Disparo(disparoX, disparoY, angulo, false);
        //Agregar el disparo a la pantalla
        panel.getChildren().add(d);
    }

    private boolean comprobarDistancia(Bounds ene, Bounds pj) {
        if (Math.abs(ene.getCenterX() - pj.getCenterX()) < 120 && Math.abs(ene.getCenterY() - pj.getCenterY()) < 120) {
            return true;
        }
        return false;
    }

    public AnimationTimer getAnimation() {
        return t;
    }

    public static Bounds posEneArc() {
        return posEnArc;
    }
}
