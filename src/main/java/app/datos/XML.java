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
     * @param puntuacion
     * @param tiempo
     */
    public XML(Puntuacion puntuacion, Tiempo tiempo) {
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
    }

    /**
     * almecena los datos de las partidas
     * @throws Exception
     */
    public void guardarPartida() throws Exception {
        File archivo = Util.getArchivoPartidas();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc;
        Element raiz;

        // carga o crea el documento
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

        //guerda el documento
        HistorialPartidas.limpiarEspaciosEnBlanco(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(doc), new StreamResult(archivo));

        System.out.println("Partida guardada en: " + archivo.getAbsolutePath());
    }

}

