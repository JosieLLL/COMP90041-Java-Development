import java.io.File;
import java.util.Scanner;
public class DeteleorNot {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a file name");
		String filename = sc.nextLine();
		File tempfile = new File("src/" + filename);
		if (tempfile.exists()) {
			System.out.println("The file " + filename + " exists.");
	    	System.out.println("Do you want to delete the file, enter yes or no.");
	    	if (sc.nextLine().equals("yes")) {
	    		tempfile.delete();
	    	}
		}
		else {
			System.out.println("The file " + filename + " does not exist.");
		}
		sc.close();
	}
}


