import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LongestwordInFile {
	
	public static void main(String[] args) throws FileNotFoundException {
		new LongestwordInFile().findLongestWords();	
	}
	
	public String findLongestWords() throws FileNotFoundException {
		String longest_word = "";
		String current;
		Scanner sc = new Scanner(new File("src/test.txt"));
		while (sc.hasNext()) {
			current = sc.next();
			if (current.length() > longest_word.length()) {
				longest_word = current;
			}
		}
		System.out.println("The longest word is: " + longest_word + "\n");
		return longest_word;
	}
}


 
