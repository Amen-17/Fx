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

    private static List<Partida> partidas = new ArrayList<>();

    // Constructor que carga las partidas desde un archivo XML externo
    public HistorialPartidas(File archivoXml) throws Exception {
        cargarPartidasDesdeXML(archivoXml);
    }

    /**
     * cargar los nuevos datos de la partid en el xml
     * @throws Exception
     */
    public HistorialPartidas() throws Exception {
        File archivo = Util.getArchivoPartidas();

        // Si no existe el archivo, lo crea con la raíz <personajes>
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

        // Carga el contenido del XML (todas las partidas)
        cargarPartidasDesdeXML(archivo);
    }

    /**
     * carga el contenido del xml, creando el nombre del jugador
     * @param archivo
     * @throws Exception
     */
    private void cargarPartidasDesdeXML(File archivo) throws Exception {
        if (!archivo.exists()) {
            System.out.println("Archivo XML no encontrado.");
            return;
        }

        partidas.clear();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivo);

        NodeList lista = doc.getElementsByTagName("personaje");

        // Recorre todos los nodos <personaje> y crea objetos Partida
        for (int i = 0; i < lista.getLength(); i++) {
            Element personaje = (Element) lista.item(i);

            String nombre = personaje.getElementsByTagName("Nombre").item(0).getTextContent();
            int puntos = Integer.parseInt(personaje.getElementsByTagName("EnemigosDerrotados").item(0).getTextContent());
            String tiempo = personaje.getElementsByTagName("Tiempo").item(0).getTextContent();

            partidas.add(new Partida(nombre, puntos, tiempo));
        }
    }

    /**
     * regresa los datos recopilados en la ultima partida
     * @return
     */
    public Partida ultimaPartida() {
        if (partidas.isEmpty()) return null;
        return partidas.get(partidas.size() - 1);
    }

    /**
     * regresará las 3 mejores partidas
     * @return
     */

    public static List<Partida> top3Partidas() {
        try {
            HistorialPartidas historial = new HistorialPartidas();
            return historial.partidas.stream()
                    .sorted(Comparator.comparingInt(Partida::getPuntuacion).reversed())
                    .limit(3)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // retorna lista vacía si falla
        }
    }

    /**
     * Limpia los espacios en blanco dentro de los nodos del XML
     * Muy útil para evitar nodos vacíos por indentación al guardar
     * @param node Nodo raíz a limpiar
     */
    public static void limpiarEspaciosEnBlanco(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node hijo = children.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE && hijo.getTextContent().trim().isEmpty()) {
                node.removeChild(hijo);
                i--; // Ajusta el índice porque eliminaste un nodo
            } else if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                limpiarEspaciosEnBlanco(hijo); // recursivo
            }
        }
    }
}