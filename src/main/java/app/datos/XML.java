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

    public XML(Puntuacion puntuacion, Tiempo tiempo) {
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    public void guardarPartida() throws Exception {
        File archivo = Util.getArchivoPartidas();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc;

        Element raiz;

        if (archivo.exists()) {
            doc = builder.parse(archivo);
            raiz = (Element) doc.getDocumentElement();
        } else {
            doc = builder.newDocument();
            raiz = doc.createElement("personajes");
            doc.appendChild(raiz);
        }

        int numJugador = doc.getElementsByTagName("personaje").getLength() + 1;
        String nombre = "Jugador" + numJugador;

        Element personaje = doc.createElement("personaje");

        Element nomb = doc.createElement("Nombre");
        nomb.appendChild(doc.createTextNode(nombre));
        personaje.appendChild(nomb);

        Element punt = doc.createElement("EnemigosDerrotados");
        punt.appendChild(doc.createTextNode(String.valueOf(puntuacion.getPuntos())));
        personaje.appendChild(punt);

        Element tiemp = doc.createElement("Tiempo");
        tiemp.appendChild(doc.createTextNode(tiempo.getTiempoMinSeg()));
        personaje.appendChild(tiemp);

        raiz.appendChild(personaje);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(archivo));
        System.out.println("Partida guardada en: " + archivo.getAbsolutePath());
    }
}

