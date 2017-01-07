package default_pack;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class testDataTypes1 {
	
    private static final String BASE = "http://example.org/"; 

	public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        
        Resource subject = r("s");
        
        model.addLiteral (subject, p("p1"), 10);
        model.addLiteral (subject, p("p2"), 0.5);
        model.addLiteral (subject, p("p3"), (float)0.5);
        model.addLiteral (subject, p("p4"), l(20));
        model.addLiteral (subject, p("p5"), l(0.99));
        model.addLiteral (subject, p("p6"), true);
        model.add (subject, p("p7"), l("2012-03-11", XSDDatatype.XSDdate));
        model.add (subject, p("p8"), l("P2Y", XSDDatatype.XSDduration));

        model.setNsPrefix("example", BASE);
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

        model.write(System.out, "TURTLE");
    }
    
    private static Resource r ( String localname ) {
        return ResourceFactory.createResource ( BASE + localname );
    }
    
    private static Property p ( String localname ) {
        return ResourceFactory.createProperty ( BASE, localname );
    }

    private static Literal l ( Object value ) {
        return ResourceFactory.createTypedLiteral ( value );
    }

    private static Literal l ( String lexicalform, RDFDatatype datatype ) {
        return ResourceFactory.createTypedLiteral ( lexicalform, datatype );
    }
}
