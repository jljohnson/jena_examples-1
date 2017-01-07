package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class test10 extends Object{
	
static final String inputFileName = "prism_2.2_1.rdf";
    
    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();
       
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        model.read(new InputStreamReader(in), "");
        
        // create a bag
        Bag pineda = model.createBag();
        
        // select all the resources with a VCARD.FN property
        // whose value ends with "Pineda"
        StmtIterator iter = model.listStatements(
            new 
                SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                    @Override
                    public boolean selects(Statement s) {
                            return s.getString().endsWith("Pineda");
                    }
                });
        // add the Pineda's to the bag
        while (iter.hasNext()) {
            pineda.add( iter.nextStatement().getSubject());
        }
        
        // print the graph as RDF/XML
        model.write(new PrintWriter(System.out));
        System.out.println();
        
        // print out the members of the bag
        NodeIterator iter2 = pineda.iterator();
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
                System.out.println("  " +
                    ((Resource) iter2.next())
                                     .getRequiredProperty(VCARD.FN)
                                     .getString());
            }
        } else {
            System.out.println("The bag is empty");
        }
    }

}
