package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.io.PrintWriter;

public class test11 extends Object {
	 public static void main (String args[]) {

		 Model model = ModelFactory.createDefaultModel();

	       Resource r = model.createResource();                                     

	      // add the property
	      r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
	       .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
	       .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));
	      
	      model.write(new PrintWriter(System.out));
	      System.out.println();
	      
	      model = ModelFactory.createDefaultModel();

	       r = model.createResource();                                     

	      // add the property
	      r.addProperty(RDFS.label, "loy")
	       .addLiteral(RDFS.label, 12);
	      
	      model.write( System.out, "N-TRIPLE");
	      }
}
