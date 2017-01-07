package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

public class test4 extends Object {

    static String tutorialURI  = "http://hostname/rdf/tutorial/";
    static String loydaName   = "Loyda Pineda";
    static String loydaEmail1 = "loyda_pineda@gmail.com";
    static String loydaEmail2 = "loyda@womenwhocode.com";
    static String title        = "Examples of RDF and the Jena API";
    static String date         = "06/01/2017";
    
    public static void main (String args[]) {
    
        String personURI    = "http://somewhere/LoydaPineda";
        String givenName    = "Loyda";
        String familyName   = "Pineda";
        String fullName     = givenName + " " + familyName;
        Model model = ModelFactory.createDefaultModel();

   
        Resource loydaPineda 
          = model.createResource(personURI)
                 .addProperty(VCARD.FN, fullName)
                 .addProperty(VCARD.N, 
                              model.createResource()
                                   .addProperty(VCARD.Given, givenName)
                                   .addProperty(VCARD.Family, familyName));
        
        // write the model in XML form 
        model.write(System.out);
    }

}
