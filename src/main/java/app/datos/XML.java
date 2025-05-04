package app.datos;

import app.personaje.Personaje;
import app.personaje.Puntuacion;
import app.personaje.Tiempo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XML {

    private File archivo;
    private Personaje personaje;
    private Puntuacion puntuacion;
    private Tiempo tiempo;

    public XML(File archivo, Personaje personaje, Puntuacion puntuacion, Tiempo tiempo) {
        this.archivo = archivo;
        this.personaje = personaje;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    public void guardarPartida() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //prepara un documento
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc;


        if (archivo.exists()) { //comprueba si existe
            doc = builder.parse(archivo);
        } else {
            doc = builder.newDocument();
            Element root = doc.createElement("personajes");
            doc.appendChild(root);
        }


        NodeList lista = doc.getElementsByTagName("personaje"); //coge los nodos ya existente para darle nombre de jugador1...
        int numJugador = lista.getLength() + 1;
        String nombre = "Jugador" + numJugador;

        // crea los elementos
        Element personajeElem = doc.createElement("personaje");

        Element nombreElem = doc.createElement("Nombre");
        nombreElem.appendChild(doc.createTextNode(nombre));
        personajeElem.appendChild(nombreElem);

        Element puntosElem = doc.createElement("EnemigosDerrotados");
        puntosElem.appendChild(doc.createTextNode(String.valueOf(puntuacion.getPuntos())));
        personajeElem.appendChild(puntosElem);

        Element tiempoElem = doc.createElement("Tiempo");
        tiempoElem.appendChild(doc.createTextNode(tiempo.getTiempoMinSeg()));
        personajeElem.appendChild(tiempoElem);

        doc.getDocumentElement().appendChild(personajeElem);// añade al documento


        Transformer transformer = TransformerFactory.newInstance().newTransformer();// Guardar en el documento
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(archivo));
    }
}

