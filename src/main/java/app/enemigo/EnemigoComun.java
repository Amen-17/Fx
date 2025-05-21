package app.enemigo;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class EnemigoComun extends Enemigo {
    private Bounds pocEneCom;  // Almacena la posición actual del enemigo para poder detectar colisiones, etc.


    /**
     *Constructor básico de la clase EnemigoComun.
     *Genera una posición aleatoria horizontal y lo coloca arriba del todo en la pantalla (fuera del límite visible).
     *El tamaño es de 50x50, con 1 vida, y velocidad aleatoria basada en la dificultad.
     *Se le asigna color rojo y comienza su comportamiento de ataque.
     */
    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, (Math.random()*2)*dificultad);
        setFill(Color.RED);  // Color del enemigo
        atacar();  // Inicia su movimiento y rotación
    }
    /**
     * Constructor de la clase EnemigoComun que nos permite que aparezca de forma aleatoria por los 4 lados de la pantalla.
     *
     * @param poX Posición aleatoria X
     * @param poY Posición aleatoria Y
     */
    public EnemigoComun(double poX, double poY){
        super(poX, poY, 50, 50, 1, (Math.random()*2)+dificultad);
        setFill(Color.RED); // Color de fondo base
        atacar();  // Empieza la lógica de movimiento
        imagen = new Image("file:src/main/java/app/imgs/EnemiCom.png");
        setFill(new ImagePattern(imagen));
    }

    /**
     * Sobrescribe el metodo atacar de la clase padre.
     * Crea un AnimationTimer que se ejecuta en cada frame del juego.
     * Este llama a los métodos de movimiento (`atacar`) y de rotación (`rotar`) del enemigo.
     * También actualiza las coordenadas actuales del enemigo (en escena).
     */
    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                EnemigoComun.super.atacar(); // Llama al ataque de la superclase (movimiento hacia el jugador)
                EnemigoComun.super.rotar(); // Hace que el enemigo gire para mirar al jugador
                pocEneCom = EnemigoComun.this.localToScene(EnemigoComun.this.getBoundsInLocal()); // Guarda su posición exacta en pantalla

            }
        }; t.start();
    }

    /**
     * Metodo getter sobrescrito que devuelve la posición actual del enemigo.
     * @return Límites del enemigo dentro de la escena, usados para detectar colisiones.
     */
    @Override
    public Bounds getPosc() {
        return pocEneCom;
    }
}
