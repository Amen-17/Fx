package app.datos;

import app.Util;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class HistorialPartidas {

    private List<Partida> partidas = new ArrayList<>();

    public HistorialPartidas(File archivoXml) throws Exception {
        cargarPartidasDesdeXML(archivoXml);
    }

    public HistorialPartidas() throws Exception {
        File archivo = Util.getArchivoPartidas();

        if (!archivo.exists()) {
            // con esto si por alguna razon no existe lo creo
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("personajes");
            doc.appendChild(root);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(archivo));
        }

        cargarPartidasDesdeXML(archivo);
    }

    private void cargarPartidasDesdeXML(File archivo) throws Exception {
        if (!archivo.exists()) {
            System.out.println("Archivo XML no encontrado.");
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivo);

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