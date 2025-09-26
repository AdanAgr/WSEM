package p1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class App {
    public static void main(String[] args) {
        // Crear un modelo RDF
        Model model = ModelFactory.createDefaultModel();
        
        // Crear un recurso
        Resource alice = model.createResource("http://example.org/alice");
        
        alice.addProperty(VCARD.FN, "Alice");
        
        // Mostrar el modelo en formato Turtle
        model.write(System.out, "TURTLE");
        
        System.out.println("Jena configurado correctamente!");
    }
}