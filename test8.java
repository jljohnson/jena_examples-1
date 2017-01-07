package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;

public class test8 extends Object {
static final String inputFileName = "prism_2.2_1.rdf";
    
    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();
       
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        model.read( in, "" );
        
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
        if (iter.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (iter.hasNext()) {
                System.out.println("  " + iter.nextStatement()
                                              .getString());
            }
        } else {
            System.out.println("No Pinedas's were found in the database");
        }            
    }
}
