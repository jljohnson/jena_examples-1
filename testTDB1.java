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

public class testTDB1 {

	public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(testTDB1.class.getClassLoader());
        InputStream in = fm.open("data.nt");

        Location location = Location.create ("target");

        // Load some initial data
        TDBLoader.load(TDBInternal.getBaseDatasetGraphTDB(TDBFactory.createDatasetGraph(location)), in, false);

        Dataset dataset = TDBFactory.createDataset(location);
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
