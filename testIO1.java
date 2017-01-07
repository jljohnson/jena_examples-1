package default_pack;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RIOT;

public class testIO1 {

	 public static void main(String[] args) {
	        InputStream in = Utils.getResourceAsStream("data.ttl");
	        
	        RIOT.init() ;

	        Model model = ModelFactory.createDefaultModel(); // creates an in-memory Jena Model
	        model.read(in, null, "TURTLE"); // parses an InputStream assuming RDF in Turtle format
	        
	        // Write the Jena Model in Turtle, RDF/XML and N-Triples format
	        System.out.println("\n---- Turtle ----");
	        model.write(System.out, "TURTLE");
	        System.out.println("\n---- RDF/XML ----");
	        model.write(System.out, "RDF/XML");
	        System.out.println("\n---- RDF/XML Abbreviated ----");
	        model.write(System.out, "RDF/XML-ABBREV");
	        System.out.println("\n---- N-Triples ----");
	        model.write(System.out, "N-TRIPLES");
	        System.out.println("\n---- RDF/JSON ----");
	        model.write(System.out, "RDF/JSON");
	    }
	
}
