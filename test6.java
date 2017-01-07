package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;

public class test6 extends Object {
	
	static final String inputFileName = "prism_2.2_1.rdf";
    static final String loydaPinedaURI = "http://somewhere/LoydaPineda/";
    
    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();
       
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        model.read(new InputStreamReader(in), "");
        
        // retrieve the loyda Pineda vcard resource from the model
        Resource vcard = model.getResource(loydaPinedaURI);

        // retrieve the value of the N property
        Resource name = (Resource) vcard.getRequiredProperty(VCARD.N)
                                        .getObject();
        // retrieve the given name property
        String fullName = vcard.getRequiredProperty(VCARD.FN)
                               .getString();
        // add two nick name properties to vcard
        vcard.addProperty(VCARD.NICKNAME, "Loydiux")
             .addProperty(VCARD.NICKNAME, "Loy");
        
        // set up the output
        System.out.println("The nicknames of \"" + fullName + "\" are:");
        // list the nicknames
        StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);
        while (iter.hasNext()) {
            System.out.println("    " + iter.nextStatement().getObject()
                                            .toString());
        }
    }

}
