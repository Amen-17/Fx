package app.datos;

import app.personaje.Tiempo;

public class Partida { //me ayuda a leer el xml
    private String nombre;
    private int puntuacion;
    private String tiempo;

    /**
     * ayuda a guardar los datos en el xml
     * @param nombre
     * @param puntuacion
     * @param tiempo
     */
    public Partida(String nombre, int puntuacion, String tiempo) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + ", Puntos: " + getPuntuacion() + ", Tiempo: " + Tiempo.getTiempoMinSeg();
    }
}
