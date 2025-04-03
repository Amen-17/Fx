package app;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

public class EnemigoComun extends Enemigo{

    public EnemigoComun() {
        super(Math.random()*450, -50, 50, 50, 1, (Math.random()*2)+dificultad);
        setFill(Color.RED);
        atacar();
    }

    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setLayoutY(getLayoutY()+velocidad);
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
