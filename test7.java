package default_pack;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;


public class test7 extends Object{
 
static final String inputFileName = "prism_2.2_1.rdf";
    
    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();
       
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        model.read( in, "");
        
        // select all the resources with a VCARD.FN property
        ResIterator iter = model.listResourcesWithProperty(VCARD.FN);
        if (iter.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (iter.hasNext()) {
                System.out.println("  " + iter.nextResource()
                                              .getRequiredProperty(VCARD.FN)
                                              .getString() );
            }
        } else {
            System.out.println("No vcards were found in the database");
        }            
    }
	
}
