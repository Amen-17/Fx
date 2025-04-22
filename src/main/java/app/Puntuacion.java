package app;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Puntuacion extends Text {

    private static int puntuacion = 0;
    private static Puntuacion p;

    /**
     * El constructor ha de ser privado debido a que no queremos que todo el mundo pueda crear una nueva instancia de esta clase.
     */
    private Puntuacion(){
       super(String.valueOf(puntuacion));
       setFont(new Font("Roboto",40));//Le cambio la fuente y su tamaño
       setFill(Color.ALICEBLUE);
    }

    public static void subirPuntuacion(int punto){
        puntuacion += punto; //Sumamos los puntos
        p.setText(String.valueOf(puntuacion)); //Actualizamos los puntos que se muestran por pantalla
    }

    /**
     * Cuando necesitemos crear una instancia de este objeto llamaremos este método para evitar crear instancias de más.
     * @return Devuelve el único objeto Puntuación que permitimos crear.
     */
    public static Puntuacion getPuntuacion(){ //Si no existe p lo crea y lo devuelve, si no solo lo devuelve
        if (p == null){
            p = new Puntuacion();
        }
        return p;
    }

    public static int getPuntos() {
        return puntuacion;
    }
}
