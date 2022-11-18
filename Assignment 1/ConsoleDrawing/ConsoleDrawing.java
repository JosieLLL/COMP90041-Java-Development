import java.util.Scanner;

public class ConsoleDrawing {

	public static void main(String[] args) {
		int canvasWidth = Integer.parseInt(args[0]);
		int canvasHeight = Integer.parseInt(args[1]);
		char bgChar = args[2].charAt(0);
		
		//Create the main drawing canvas and use it in all classes
		DrawingCanvas mainCanvas = new DrawingCanvas(canvasWidth, canvasHeight, bgChar);
		Triangle aTriangle = new Triangle(mainCanvas);
		Square aSquare = new Square(mainCanvas);
		
		System.out.println("----WELCOME TO MY CONSOLE DRAWING APP----");
		mainCanvas.printCanvasInfo();
		
		Scanner keyboard = new Scanner(System.in);
		
		mainLoop:
		while (true) {
			System.out.println("Please select an option. Type 4 to exit.");
			System.out.println("1. Draw triangles");
			System.out.println("2. Draw squares");
			System.out.println("3. Update drawing canvas settings");
			System.out.println("4. Exit");
			
			int option = keyboard.nextInt();
			switch (option) {
				case 1:
					aTriangle.handleRequest(keyboard);
					break;
				case 2:
					aSquare.handleRequest(keyboard);
					break;
				case 3:
					mainCanvas.handleRequest(keyboard);
					System.out.printf("Drawing canvas has been updated!%n%n");
					mainCanvas.printCanvasInfo();
					break;
				case 4:
					break mainLoop;
				default:
					System.out.println("Unsupported option. Please try again!");
					break;
			}
		}
		
		System.out.println("Goodbye!");
		keyboard.close();		
	}
}
