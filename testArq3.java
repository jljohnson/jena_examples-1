package default_pack;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class testArq3 {

	public static void main(String[] args) {
        FileManager.get().addLocatorClassLoader(testArq3.class.getClassLoader());
        Model model = FileManager.get().loadModel("data.ttl");
        System.out.println("Input data:");
        model.write(System.out, "TURTLE");
        
        File path = new File("src/queries");
        File[] files = path.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("construct-") && name.endsWith(".sparql");
			}
		});
        Arrays.sort(files);

        for (File file : files) {
        	System.out.println("Executing " + file.getName() + " ...");
        	Query query = QueryFactory.read(file.getAbsolutePath());
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            try {
                Model result = qexec.execConstruct();
                model.add(result);
            } finally {
                qexec.close();
            }
		}

        System.out.println("Output data:");
        model.write(System.out, "TURTLE");
	}

}
