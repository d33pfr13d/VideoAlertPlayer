package utility.vap.tools;

import java.io.File;

public class Constants {

	// Used to ensure only one instance of the VAP is open at a time
	public static File lockFile = new File("./vap.lock");

}
