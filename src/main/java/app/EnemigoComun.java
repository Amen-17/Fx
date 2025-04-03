package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemigoComun extends Enemigo{

    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, 2);
        setFill(Color.RED);
        atacar();
    }

    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {

                // Obtener la posición absoluta del personaje en la escena
                Bounds bounds = Personaje.getPers().localToScene(Personaje.getPers().getBoundsInLocal());
                double personajeX = bounds.getMinX() + bounds.getWidth() / 2;
                double personajeY = bounds.getMinY() + bounds.getHeight() / 2;

                // Obtener la posición del enemigo
                Bounds boundsEnemigo = localToScene(getBoundsInLocal());
                double enemigoX = boundsEnemigo.getCenterX();
                double enemigoY = boundsEnemigo.getCenterY();

                // Calcular la dirección
                double deltaX = personajeX - enemigoX;
                double deltaY = personajeY - enemigoY;
                double distancia = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

                // Normalizar el vector para que la velocidad sea constante
                double velocidad = 2; // Ajusta la velocidad del enemigo
                double movX = (deltaX / distancia) * velocidad;
                double movY = (deltaY / distancia) * velocidad;

                setLayoutX(getLayoutX() + movX);
                setLayoutY(getLayoutY() + movY);

                if (getBoundsInParent().getMinY() >= panel.getHeight()
                        || getBoundsInParent().intersects(Personaje.getPos()) ){ //Obtiene la posicion del enemigo y solicita el máximo de la Y, la parte inferior, y lo compara con la altura del panel, la parte inferior.
                    panel.getChildren().remove(EnemigoComun.this);//Borramos el enemigo
                    GestorEnemigos.getLista().remove(EnemigoComun.this);//Borramos de la lista los que se salen de la pantalla
                    Vida.reducirVida();
                    t.stop();
                }
            }
        }; t.start();
    }
}
