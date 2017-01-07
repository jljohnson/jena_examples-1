package default_pack;

import java.io.InputStream;

public class Utils {

	public static InputStream getResourceAsStream(String filename) {
	    InputStream in = Utils.class.getClassLoader().getResourceAsStream(filename);
	    return in;
	}
}
