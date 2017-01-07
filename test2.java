package default_pack;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;


public class test2 extends Object{

	public static void main(String args[])
	{
		//Some definitions
		String personURI="http://someWhere/LoydaPineda";
		String givenName="Loyda";
		String familyName="Pineda";
		String fullName= givenName + "" + familyName;
		
		//Create empty model
		Model model=ModelFactory.createDefaultModel();
		
		//Create Resource and add properties cascading style
		
		Resource loydaPineda=model.createResource(personURI).addProperty(VCARD.FN, fullName)
				.addProperty(VCARD.N, model.createResource()
						.addProperty(VCARD.Given, givenName)
						.addProperty(VCARD.Family,familyName));
		
		//System.out.println(loydaPineda.getProperty(VCARD.Family));
	}
}
