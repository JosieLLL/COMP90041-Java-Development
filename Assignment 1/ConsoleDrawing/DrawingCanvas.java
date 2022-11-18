import java.util.Scanner;

public class DrawingCanvas {
	private int width, height;
	private char bgChar;
	
	public DrawingCanvas(int canvasWidth, int canvasHeight, char aChar) {
		width = canvasWidth;
		height = canvasHeight;
		bgChar = aChar; 
	}
	
	public void printCanvasInfo() {
		System.out.println("Current drawing canvas settings:");
		System.out.printf("- Width: %d%n", this.getWidth());
		System.out.printf("- Height: %d%n", this.getHeight());
		System.out.printf("- Background character: %s%n%n", this.getBgChar());
	}
	
	public void handleRequest(Scanner keyboard) {
		System.out.print("Canvas width: ");
		width = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("Canvas height: ");
		height = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("Background character: ");
		bgChar = keyboard.next().charAt(0);
		keyboard.nextLine();
	}
	
	public void setWidth(int canvasWidth) {
		width = canvasWidth;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int canvasHeight) {
		height = canvasHeight;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setBgChar(char aChar) {
		bgChar = aChar;
	}
	
	public char getBgChar() {
		return bgChar;
	}
}
