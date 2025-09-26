package com.example.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;


public class Ejemplo2 {
    public static void main(String[] args) {
        // Definiciones
        String personURI   = "http://example.org/AliceSmith";
        String firstName   = "Alice";
        String familyName  = "Smith";
        String fullName    = firstName + " " + familyName;

        // Crear modelo vacío
        Model model = ModelFactory.createDefaultModel();
        Property structuredName = model.createProperty("http://example.org/structuredName");

        // Crear recurso con URI
        Resource aliceSmith = model.createResource(personURI);
        aliceSmith.addProperty(FOAF.name, fullName)
            .addProperty(structuredName,model.createResource()
                             .addProperty(FOAF.firstName, firstName)
                             .addProperty(FOAF.familyName, familyName));

        // Serializar en Turtle
        model.write(System.out, "TURTLE");

        // lista de declaraciones en el modelo
        StmtIterator iter = model.listStatements();

        // muestra el objeto, el predicado y el sujeto de cada declaracion
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement(); // obtener la siguiente declaracion
            Resource  subject   = stmt.getSubject();     // obtener el sujeto
            Property  predicate = stmt.getPredicate();   // obtener el predicado
            RDFNode   object    = stmt.getObject();      // obtener el objeto

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
            System.out.print(object.toString());
            } else {
                // el objeto es un literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }
}