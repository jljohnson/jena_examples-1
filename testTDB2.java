package default_pack;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.apache.jena.tdb.base.file.Location;
import org.apache.jena.tdb.sys.TDBInternal;
import org.apache.jena.util.FileManager;

public class testTDB2 {
	  public static void main(String[] args) {
	        FileManager fm = FileManager.get();
	        fm.addLocatorClassLoader(testTDB2.class.getClassLoader());
	        InputStream in = fm.open("data/data.nt");

	        Location location = Location.create ("target");
	        Dataset dataset = TDBFactory.createDataset(location);
	        dataset.begin(ReadWrite.WRITE);
	        try {
	            TDBLoader.load(TDBInternal.getBaseDatasetGraphTDB(dataset.asDatasetGraph()), in, false);
	            dataset.commit();
	        } catch (Exception e) {
	            dataset.abort();
	        } finally {
	            dataset.end();
	        }

	        dataset.begin(ReadWrite.READ);
	        try {
	            Iterator<Quad> iter = dataset.asDatasetGraph().find();
	            while ( iter.hasNext() ) {
	                Quad quad = iter.next();
	                System.out.println(quad);
	            }
	        } finally {
	            dataset.end();
	        }
	    }

}
