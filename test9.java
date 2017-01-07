package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.*;

public class test9 extends Object{
	static final String inputFileName1 = "prism_2.3_1.rdf";    
    static final String inputFileName2 = "prism_2.4.2_1.rdf";
    
    public static void main (String args[]) {
        Model model1 = ModelFactory.createDefaultModel();
        Model model2 = ModelFactory.createDefaultModel();
       
        InputStream in1 = FileManager.get().open(inputFileName1);
        if (in1 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        InputStream in2 = FileManager.get().open(inputFileName2);
        if (in2 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName2 + " not found");
        }
        
        model1.read( in1, "" );
        model2.read( in2, "" );
        
        // merge the graphs
        Model model = model1.union(model2);
        
        // print the graph as RDF/XML
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();
    }
}
