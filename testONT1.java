package default_pack;

import java.util.Iterator;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.reasoner.ValidityReport.Report;
import org.apache.jena.util.FileManager;

public class testONT1 {

	public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(testONT1.class.getClassLoader());
        
        Model tbox = FileManager.get().loadModel("src/inference/tbox.owl", null, "RDF/XML"); // http://en.wikipedia.org/wiki/Tbox
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner().bindSchema(tbox.getGraph());
        Model abox = FileManager.get().loadModel("src/inference/abox.owl", null, "RDF/XML"); // http://en.wikipedia.org/wiki/Abox

        InfModel inf = ModelFactory.createInfModel(reasoner, abox);

        ValidityReport validityReport = inf.validate(); 

        if ( !validityReport.isValid() ) {
        	System.out.println("Inconsistent");
            Iterator<Report> iter = validityReport.getReports();
            while ( iter.hasNext() ) {
            	Report report = iter.next();
            	System.out.println(report);
            }        	
        } else {
        	System.out.println("Valid");
        }

    }

	
}
