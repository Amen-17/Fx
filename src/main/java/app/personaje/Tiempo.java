package app.personaje;

import javafx.animation.AnimationTimer;

public class Tiempo {
    private long tiempoInicio;
    private long tiempoTotal; // en milisegundos
    private static Tiempo t;
    private AnimationTimer cronometro;

    private Tiempo(){}

    public void iniciarCronometro() {
        tiempoInicio = System.currentTimeMillis(); //devuelve el tiempo que se le llamó

        cronometro = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tiempoTotal = System.currentTimeMillis() - tiempoInicio;
            }
        };
        cronometro.start();
    }

    public void detenerCronometro() {
        cronometro.stop();
    }

    public static Tiempo getTiempoTotal() {
        if (t==null){
            t=new Tiempo();
        }
        return t;
    }

    public long getTiemTot() {
        return tiempoTotal;
    }

    public String getTiempoMinSeg() {
        long segundosTotales = tiempoTotal / 1000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
