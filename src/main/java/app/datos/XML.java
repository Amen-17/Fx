package app.datos;

import app.Util;
import app.personaje.Puntuacion;
import app.personaje.Tiempo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XML {

    private final Puntuacion puntuacion;
    private final Tiempo tiempo;

    /**
     * datos que tendrá el documento
     * @param puntuacion guarda la puntuacion
     * @param tiempo guarda el tiempo
     */
    public XML(Puntuacion puntuacion, Tiempo tiempo) {
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    /**
     * almecena los datos de las partidas en un archivo XML
     * @throws Exception
     */
    public void guardarPartida() throws Exception {
        File archivo = Util.getArchivoPartidas();  // Archivo donde se guardan las partidas
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc;
        Element raiz;

        // Si el archivo ya existe, lo carga; si no, lo crea desde cero
        if (archivo.exists()) {
            doc = builder.parse(archivo);  // Carga el XML existente
            raiz = (Element) doc.getDocumentElement(); // Obtiene el nodo raíz <personajes>
        } else {
            doc = builder.newDocument(); // Crea un nuevo documento XML
            raiz = doc.createElement("personajes"); // Nodo raíz
            doc.appendChild(raiz); ; // Añade el nodo raíz al documento
        }

        // Crea un nuevo nodo de personaje con nombre único (Jugador1, Jugador2, etc.)

        int numJugador = doc.getElementsByTagName("personaje").getLength() + 1;
        String nombre = "Jugador" + numJugador;

        Element personaje = doc.createElement("personaje"); // Nodo principal del personaje

        // Nodo <Nombre>
        Element nomb = doc.createElement("Nombre");
        nomb.appendChild(doc.createTextNode(nombre));
        personaje.appendChild(nomb);

        // Nodo <EnemigosDerrotados>
        Element punt = doc.createElement("EnemigosDerrotados");
        punt.appendChild(doc.createTextNode(String.valueOf(puntuacion.getPuntos())));
        personaje.appendChild(punt);

        // Nodo <Tiempo>
        Element tiemp = doc.createElement("Tiempo");
        tiemp.appendChild(doc.createTextNode(tiempo.getTiempoMinSeg()));
        personaje.appendChild(tiemp);

        raiz.appendChild(personaje);  // Añade el nodo del personaje al nodo raíz

        // Limpia espacios en blanco (opcional, si lo implementa HistorialPartidas)
        HistorialPartidas.limpiarEspaciosEnBlanco(doc);

        // Configura y ejecuta el guardado del archivo XML con formato bonito
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Indentación activada
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(doc), new StreamResult(archivo));

        System.out.println("Partida guardada en: " + archivo.getAbsolutePath()); // Mensaje de confirmación
    }

}

