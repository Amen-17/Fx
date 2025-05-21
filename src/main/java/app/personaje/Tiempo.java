package app.personaje;

import javafx.animation.AnimationTimer;

/**
 * Clase que gestiona el cronómetro del juego.
 * Mide el tiempo total que ha transcurrido desde el inicio de la partida.
 */


public class Tiempo {
    private static long tiempoInicio;
    private static long tiempoTotal; // en milisegundos
    private static Tiempo t;
    private static AnimationTimer cronometro;

    // Constructor privado para evitar instanciación externa (Singleton)
    private Tiempo(){}


    /**
     * Inicia el cronómetro. Registra el tiempo de inicio y actualiza el tiempo total
     * continuamente mediante un AnimationTimer.
     */

    public static void iniciarCronometro() {
        // Guarda el momento exacto del inicio en milisegundos
        tiempoInicio = System.currentTimeMillis(); //devuelve el tiempo que se le llamó

        // Crea un temporizador que se ejecuta en cada frame
        cronometro = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Actualiza el tiempo total restando el momento actual al tiempo de inicio
                tiempoTotal = System.currentTimeMillis() - tiempoInicio;
            }
        };
        cronometro.start();
    }

    /**
     * detiene el cronometro
     * @return
     */
    public static Tiempo detenerCronometro() {
        cronometro.stop();
        return t;
    }

    /**
     * devuelve el tiempo o lo crea si no se ha hecho ya
     *  Si no existe, la crea
     * @return
     */
    public static Tiempo getTiempoTotal() {
        if (t==null){
            t=new Tiempo();
        }
        return t;
    }

    public long getTiemTot() {
        return tiempoTotal;
    }

    /**
     * Devuelve el tiempo transcurrido en formato "MM:SS", es decir, minutos y segundos.
     * Convierte milisegundos a segundos y los formatea para visualización.
     * @return tiempo en formato "minutos:segundos"
     */
    public static String getTiempoMinSeg() {
        long segundosTotales = tiempoTotal / 1000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos); // Devuelve el formato 00:00
    }
}
