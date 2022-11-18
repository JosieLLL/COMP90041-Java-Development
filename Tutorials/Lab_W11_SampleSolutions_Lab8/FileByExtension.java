import java.io.File;
import java.io.FilenameFilter;
public class FileByExtension {
	
	public static void main(String args[]){
		File file = new File("src/");
		String[] list = file.list();
		for (String f:list){
			if(f.endsWith(".java")) {
				System.out.println(f);
			}
			
		}
	}
}
