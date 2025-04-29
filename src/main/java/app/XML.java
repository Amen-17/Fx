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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XML extends Personaje{ //Omar piensalo detenidamente

    private Document datos_personaje;
    private Puntuacion puntuacion;

    public XML(String nombre) {
        super(nombre);
        this.puntuacion = Puntuacion.getPuntuacion();
    }

    public Puntuacion getPuntuacion() {
        return Puntuacion.getPuntuacion();
    }

    public void GeneradorDOM() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        datos_personaje= builder.newDocument();
    }

    public void GenerarDocumento() throws Exception{  //todavia creo que no se puede hacer que se añadan algunas cosas

        Element personajes = datos_personaje.createElement("personajes");
        datos_personaje.appendChild(personajes);
        Element personaje = datos_personaje.createElement("personaje");

        //los elementos del xml

        Element nombre = datos_personaje.createElement("nombre");
        personaje.appendChild(nombre);
        nombre.appendChild(datos_personaje.createTextNode(getNombre()));

        Element enemDerrotados = datos_personaje.createElement("enemDerrotados");
        personaje.appendChild(enemDerrotados);
        String punt = Integer.toString(Puntuacion.getPuntos());
        enemDerrotados.appendChild(datos_personaje.createTextNode(punt));

        Element tiempo = datos_personaje.createElement("tiempo");
        personaje.appendChild(tiempo);
        Tiempo t= Tiempo.getTiempoTotal();
        String tex= t.getTiempoMinSeg();
        tiempo.appendChild(datos_personaje.createTextNode(tex));

        personajes.appendChild(personaje);

    }

    public void GenerarXML() throws Exception{
        Source source = new DOMSource(datos_personaje);

        File ruta = new File("./");
        FileWriter fw = new FileWriter(ruta);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);

        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();
        transformer.transform(source, result);
    }
}
