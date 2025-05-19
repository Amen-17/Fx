package app.personaje;

import javafx.animation.AnimationTimer;

public class Tiempo {
    private static long tiempoInicio;
    private static long tiempoTotal; // en milisegundos
    private static Tiempo t;
    private static AnimationTimer cronometro;

    private Tiempo(){}

    /**
     * inicia el tiempo
     */
    public static void iniciarCronometro() {
        tiempoInicio = System.currentTimeMillis(); //devuelve el tiempo que se le llamó

        cronometro = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
     * pasa los milisegundos a segundos y minutos
     * @return
     */
    public static String getTiempoMinSeg() {
        long segundosTotales = tiempoTotal / 1000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
