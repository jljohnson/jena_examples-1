package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.util.FileManager;

import java.io.*;



public class test5  extends Object{
	static final String inputFileName  = "prism_2.2_1.rdf";
    
    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML 
        model.read(in, "");
                    
        model.write(System.out);            
    }
}

