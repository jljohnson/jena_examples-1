package default_pack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import org.apache.jena.graph.Node;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.FmtUtils;
import org.apache.jena.util.FileManager;

public class testArq4 {
	public static void main(String[] args) throws IOException {
		FileManager.get().addLocatorClassLoader(testArq2.class.getClassLoader());
		Model model = FileManager.get().loadModel("data.ttl");
		Query query = QueryFactory.create("SELECT * WHERE { ?s ?p ?o }");
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		FileOutputStream out = new FileOutputStream("sxssf.xlsx");
		Workbook wb = new SXSSFWorkbook(10000);
		Sheet sh = wb.createSheet();

		int rows = 0;
		int columns = 0;
		try {
			ResultSet resultSet = qexec.execSelect();
			List<String> varNames = resultSet.getResultVars();
			List<Var> vars = new ArrayList<Var>(varNames.size());

			// first row with var names
			Row row = sh.createRow(rows++);
			for (String varName : varNames) {
				Var var = Var.alloc(varName);
				Cell cell = row.createCell(columns++);
				cell.setCellValue(var.toString());
				vars.add(var);
			}

			// other rows with bindings
			while (resultSet.hasNext()) {
				Binding bindings = resultSet.nextBinding();
				row = sh.createRow(rows++);
				columns = 0;
				for (Var var : vars) {
					Node n = bindings.get(var);
					if (n != null) {
						Cell cell = row.createCell(columns++);
						String value = FmtUtils.stringForNode(n, (SerializationContext) null);
						cell.setCellValue(value);
					}
				}
			}
		} finally {
			qexec.close();
		}

		wb.write(out);
		out.close();
	}

}
