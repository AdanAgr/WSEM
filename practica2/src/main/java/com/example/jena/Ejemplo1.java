package com.example.jena;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;


public class Ejemplo1 {
    public static void main(String[] args) {
        // Crear un modelo RDF
        Model model = ModelFactory.createDefaultModel();

        // Crear un recurso
        Resource alice = model.createResource("http://example.org/alice");

        alice.addProperty(FOAF.name, "Alice")
                .addProperty (FOAF.age, "23") ;

        // Serializar en Turtle
        System.out.println("RDF/XML");
        model.write(System.out, "RDF/XML");
        RDFDataMgr.write(System.out,model, RDFFormat.RDFXML) ;
        System.out.println("TURTLE");
        model.write(System.out, "TURTLE");
        RDFDataMgr.write(System.out,model, RDFFormat.TURTLE) ;
        System.out.println("N-Triples");
        model.write(System.out, "N-Triples");
        RDFDataMgr.write(System.out,model, RDFFormat.NTRIPLES) ;
        System.out.println("JSON-LD");
        model.write(System.out, "JSONLD");
        RDFDataMgr.write(System.out,model, RDFFormat.JSONLD) ;
        System.out.println("N3");
        model.write(System.out, "N3");
        RDFDataMgr.write(System.out,model, RDFFormat.NT) ;
        System.out.println("TRIG");
        model.write(System.out, "TRIG");
        RDFDataMgr.write(System.out,model, RDFFormat.TRIG) ;

        System.out.println("Jena configurado");
    }
}