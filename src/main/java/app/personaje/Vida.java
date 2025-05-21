package app.personaje;

import app.Util;
import app.datos.XML;
import app.paneles.Escenas;
import app.paneles.PanelPrincipal;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 * Clase que representa el sistema de vidas del personaje en el juego.
 * Cada vida es representada visualmente por un ícono (imagen) en forma de Rectangle.
 */

public class Vida extends Rectangle {

    private static ArrayList<Vida> vidas;
    private Image img = new Image("file:src/main/java/app/imgs/vida.png");
    private static int indiceAct = 3;
    public static boolean muerto;


    /*
     * Constructor privado. Crea una "vida" con una imagen.
     * Cada objeto de esta clase es un ícono de vida (Rectangle con textura).
     * Se llama internamente desde el metodo getVidas().
     */
    /*
     * este metodo guarda en pantalla y representa las imagenes
     */
    private Vida() {
        super(50, 50);                         // Dimensiones de la imagen de vida (50x50)
        setFill(new ImagePattern(img));        // Rellena el rectángulo con la imagen de vida
        muerto = false;                        // Inicialmente, el personaje está vivo
    }

    /*
     * Inicializa las vidas si no se han creado aún, y devuelve la lista actual de vidas.
     * Solo se crean una vez, al principio.
     * @return ArrayList con los objetos Vida activos.
     */

    /*
     * si no se ha llamado antes a vidas se crea y se le añade 3 imagenes
     * @return el numero devidas de ese momenro
     */


    public static ArrayList<Vida> getVidas() {
        if (vidas == null) {
            vidas = new ArrayList<>();

            // Se crean 3 vidas por defecto
            for (int cont = 0; cont < 3; cont++) {
                vidas.add(new Vida());
            }
        }
        return vidas;
    }

    /**
     * Reduce una vida. Elimina visualmente una imagen de vida de la pantalla.
     * Cuando el índice de vida llega a 0, finaliza el juego y se lanza la escena de Game Over.
     */

    public static void reducirVida() {
        if (indiceAct > 0) {
            System.out.println("Mi indice es"+indiceAct);  // Mensaje de depuración
            indiceAct--;  // Disminuye el contador de vidas
            Vida v = vidas.get(indiceAct);   // Obtiene la vida a eliminar
            PanelPrincipal.getPanelinf().getChildren().remove(v); // Elimina la imagen del panel
        }

        // Si el personaje se queda sin vidas:

        if (indiceAct == 0) {
            Escenas.getEscena().setGameOver(); // Cambia la escena a Game Over
            Util.pararJuego(); // Detiene todas las acciones del juego
        }
    }

    /**
     * Reinicia el estado de las vidas para volver a comenzar el juego desde el principio.
     * Vuelve a poner 3 vidas y limpia la lista.
     */
    public static void reiniciarVidas(){
        indiceAct = 3; // Restaura el número de vidas
        vidas = null;  // Borra la lista para forzar su recreación con getVidas()
    }
}
