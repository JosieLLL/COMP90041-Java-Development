import java.io.File;

public class TemporaryFileExample {
	
	public static void main(String[] args) {
		System.out.println(new File(".").getAbsolutePath());
		File temp = new File("src/Sally.txt");
		if (temp.exists()) {
			System.out.print("Sally file exits in the current directory");
		}
		else {
			System.out.print("Sally file does not exit in the current directory");
		}
	}
}
