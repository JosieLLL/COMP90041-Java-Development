import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LinebylineArray {
	
	public static void main(String args[]){
		String strLine = "";
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/file3"));
			while (strLine != null){
				strLine = br.readLine();
				if (strLine==null) {
					break;
				}
				list.add(strLine);
			}
			System.out.println(Arrays.toString(list.toArray()));
            br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("Unable to read the file.");
		}
	}
}
