package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemigoComun extends Enemigo{

    public EnemigoComun() {
        super(Math.random()*1150, -50, 50, 50, 1, (Math.random()+1)*dificultad);
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
                if (getBoundsInParent().intersects(Personaje.getPos()) ){
                    System.out.println("Posicion enemigo: "+getBoundsInParent().getMaxY()+ " "+ getBoundsInParent().getMaxX() +" posicion pj: "+Personaje.getPos().getMaxY()+ " "+Personaje.getPos().getMaxX());
                    panel.getChildren().remove(EnemigoComun.this);//Borramos el enemigo
                    GestorEnemigos.getLista().remove(EnemigoComun.this);//Borramos de la lista los que se salen de la pantalla
                    Vida.reducirVida(); //Nos quitan una vida
                    System.out.println("Te han quitado una vida");
                    t.stop(); //Paramos su animación
                }
            }
        }; t.start();
    }
}
