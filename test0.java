package default_pack;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

public class test0 extends Object {
	static String personURI="http://somewhere/LoydaPineda";
	static String fullName="Loyda Pineda";
	
	
	public static void main (String args[])
	{
	System.out.print("Hola \n");
	//Create Empty Model
	Model model=ModelFactory.createDefaultModel();
	
	//Create the Resource
	Resource loydaPineda=model.createResource(personURI);
	
	//add the property
	loydaPineda.addProperty(VCARD.FN, fullName);
	
	//System.out.print(loydaPineda.getProperty(VCARD.FN));

	}

}
