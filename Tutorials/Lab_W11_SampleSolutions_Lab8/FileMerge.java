import java.io.*;

public class FileMerge {
	
	public static void main(String[] args) throws IOException {
		
		PrintWriter pw = new PrintWriter("src/file3");
		BufferedReader br = new BufferedReader(new FileReader("src/file1"));
		String line = br.readLine();
		while (line != null) {
			pw.println(line);
			line = br.readLine();
		}
		br.close();
		br = new BufferedReader(new FileReader("src/file2"));
		line = br.readLine();
		while (line != null) {
			pw.println(line);
			line = br.readLine();
		}
		pw.flush();
		br.close();
        pw.close();
          
        System.out.println("Merged file1 and file2 into file3");
		
	}
	
}


