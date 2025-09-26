package com.example.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;

public class Ejemplo3 {
    public static void main(String[] args) {
        // Definiciones
        String personURI1   = "http://example.org/AliceSmith";
        String firstName1   = "Alice";
        String familyName1  = "Smith";
        String fullName1    = firstName1 + " " + familyName1;
        String email1       = "alice@example.org";
        int age1            = 23;

        String personURI2 = "http://example.org/Bob";
        String firstName2 = "Bob";
        int age2 = 30;
        // Bob conoce a Alice
        String knowsURI2 = "http://example.org/AliceSmith";

        String personURI3 = "http://example.org/Charlie";
        String firstName3 = "Charlie";
        int age3 = 29;

        // Crear modelo vacío
        Model model = ModelFactory.createDefaultModel();
        Property structuredName = model.createProperty("http://example.org/structuredName");

        // Crear recurso con URI
        Resource aliceSmith = model.createResource(personURI1);
        aliceSmith.addProperty(FOAF.mbox, email1)
            .addLiteral(FOAF.age, age1)
            .addProperty(FOAF.name, fullName1)
            .addProperty(structuredName, model.createResource()
                             .addProperty(FOAF.firstName, firstName1)
                             .addProperty(FOAF.familyName, familyName1));

        Resource bob = model.createResource(personURI2);
        bob.addLiteral(FOAF.age, age2)
           .addProperty(FOAF.firstName, firstName2)
           .addProperty(FOAF.knows, model.createResource(knowsURI2));

        Resource charlie = model.createResource(personURI3);
        charlie.addLiteral(FOAF.age, age3)
               .addProperty(FOAF.firstName, firstName3)
               .addProperty(FOAF.knows, bob);
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