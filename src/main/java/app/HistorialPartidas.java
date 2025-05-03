package app;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class HistorialPartidas {

    private List<Partida> partidas = new ArrayList<>();

    public HistorialPartidas(String archivoXml) throws Exception {
        cargarPartidasDesdeXML(archivoXml);
    }

    private void cargarPartidasDesdeXML(String archivoXml) throws Exception {
        File file = new File(archivoXml);
        if (!file.exists()) {
            System.out.println("Archivo XML no encontrado.");
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        NodeList lista = doc.getElementsByTagName("personaje");

        for (int i = 0; i < lista.getLength(); i++) {
            Element personaje = (Element) lista.item(i);

            String nombre = personaje.getElementsByTagName("Nombre").item(0).getTextContent();
            int puntos = Integer.parseInt(personaje.getElementsByTagName("EnemigosDerrotados").item(0).getTextContent());
            String tiempo = personaje.getElementsByTagName("Tiempo").item(0).getTextContent();

            partidas.add(new Partida(nombre, puntos, tiempo));
        }
    }

    public Partida ultimaPartida() {
        if (partidas.isEmpty()) return null;
        return partidas.get(partidas.size() - 1);
    }

    public List<Partida> top3Partidas() {
        return partidas.stream().sorted(Comparator.comparingInt(Partida::getPuntuacion).reversed()).limit(3).toList();
    }
}