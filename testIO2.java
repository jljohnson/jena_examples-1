package default_pack;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class testIO2 {

	 public static void main(String[] args) {
	        FileManager fm = FileManager.get(); 
	        fm.addLocatorClassLoader(testIO1.class.getClassLoader());
	        // load an RDF file using the FileManager
	        Model model = fm.loadModel("data.ttl", null, "TURTLE");
	        model.write(System.out, "TURTLE");
	    }
}
