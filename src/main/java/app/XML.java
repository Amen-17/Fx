package app;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XML {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document docu = implementation.createDocument(null, "juego", null);
            docu.setXmlVersion("1.0");

            Element personajes = docu.createElement("personajes");
            Element personaje = docu.createElement("personaje");

            //los elementos del xml
            Element vida = docu.createElement("vida");
            vida.appendChild(personaje);
            Element puntos = docu.createElement("puntos");
            puntos.appendChild(personaje);
            Element tiempo = docu.createElement("tiempo");
            tiempo.appendChild(personaje);

            personajes.appendChild(personaje);

            docu.getDocumentElement().appendChild(personajes);

            Source source = new DOMSource(docu);
            Result result = new StreamResult(new File("datos_personaje.xml"));

            Transformer transf = TransformerFactory.newInstance().newTransformer();
            transf.transform(source, result);

        } catch (ParserConfigurationException | TransformerException exc) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, exc);
        }
    }
}
