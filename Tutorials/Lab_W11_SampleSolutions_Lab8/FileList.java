import java.io.File;
import java.util.Date;

public class FileList {
	
	public static void main(String args[]) {
		File file = new File("../../");
        String[] fileList = file.list();
        for (String name:fileList) {
        	System.out.println(name);
        }
	}
}


