import java.io.File;
import java.util.Date;

public class LastModifiedTime {
	
	public static void main(String[] args) {
		File file = new File("src/test.txt");
		Date date = new Date(file.lastModified());
		System.out.print("\nThe file was last modified on: " + date + "\n");	 
	}
}

