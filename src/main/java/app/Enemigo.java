package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
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
        super(poX, poY, tamX, tamY);
        this.vida = vida;
        this.velocidad = velocidad;
        panel = PanelJuego.getPanel();
        if (dificultad <= 1) dificultad += 0.01;
    }

    public int getVida() {
        return vida;
    }

    public void reducirVida() {
        vida--;
    }

    public AnimationTimer getAnimation() {
        return t;
    }

    protected void comprobarMuerte() {
        if (localToScene(getBoundsInLocal()).intersects(Personaje.getPers().localToScene(Personaje.getPers().getBoundsInLocal()))) {
            System.out.println("Posicion enemigo: " + getBoundsInParent().getMaxY() + " " + getBoundsInParent().getMaxX() + " posicion pj: " + Personaje.getPos().getMaxY() + " " + Personaje.getPos().getMaxX());
            panel.getChildren().remove(this);//Borramos el enemigo
            GestorEnemigos.getLista().remove(this);//Borramos de la lista los que se salen de la pantalla
            Vida.reducirVida(); //Nos quitan una vida
            System.out.println("Te han quitado una vida");
            t.stop(); //Paramos su animación
        }
    }
    // Mejorar todas las variables que he hecho
    protected void atacar() {
        // Obtener la posición absoluta del personaje en la escena
        Bounds bounds = Personaje.getPers().localToScene(Personaje.getPers().getBoundsInLocal());
        double personajeX = bounds.getMinX() + bounds.getWidth() / 2;
        double personajeY = bounds.getMinY() + bounds.getHeight() / 2;

        // Obtener la posición del enemigo
        Bounds posEneAct = localToScene(getBoundsInLocal());
        double enemigoX = posEneAct.getCenterX();
        double enemigoY = posEneAct.getCenterY();

        // Calcular la dirección
        double deltaX = personajeX - enemigoX;
        double deltaY = personajeY - enemigoY;
        double distancia = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        // Normalizar el vector para que la velocidad sea constante
        double movX = (deltaX / distancia) * velocidad;
        double movY = (deltaY / distancia) * velocidad;

        setLayoutX(getLayoutX() + movX);
        setLayoutY(getLayoutY() + movY);

        //Obtiene la posicion del enemigo y comprueba si se choca con el personaje.
        comprobarMuerte();
    }
    // Mejorar todas las variables que he hecho
    protected void rotar(){

        Bounds bounds = Personaje.getPers().localToScene(Personaje.getPers().getBoundsInLocal());
        Bounds posEneAct = localToScene(getBoundsInLocal());
        double personajeX = bounds.getMinX() + bounds.getWidth() / 2;
        double personajeY = bounds.getMinY() + bounds.getHeight() / 2;
        double enemigoX = posEneAct.getCenterX();
        double enemigoY = posEneAct.getCenterY();

        // Calcular la dirección
        double deltaX = personajeX - enemigoX;
        double deltaY = personajeY - enemigoY;
        // Calcular el ángulo de rotación en radianes y convertirlo a grados
        double anguloRadianes = Math.atan2(deltaY, deltaX);
        double anguloGrados = Math.toDegrees(anguloRadianes);

        // Aplicar la rotación al enemigo
        setRotate(anguloGrados);
    }
}
