package app;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Enemigo extends Rectangle {

    protected PanelJuego panel;
    protected int vida;
    protected double velocidad;
    protected AnimationTimer t;
    protected Image imagen;
    protected static double dificultad = 0;
    protected int puntuacion;

    public Enemigo(double poX, double poY, double tamX, double tamY, int vida, double velocidad) {
        super(poX,poY,tamX,tamY);
        this.vida = vida;
        this.velocidad = velocidad;
        panel = PanelJuego.getPanel();
        if (dificultad <= 1) dificultad += 0.01;
    }

    protected abstract void atacar();

    public int getVida(){
        return vida;
    }

    public void reducirVida(){
        vida--;
    }

    public AnimationTimer getAnimation(){
        return t;
    }
}
