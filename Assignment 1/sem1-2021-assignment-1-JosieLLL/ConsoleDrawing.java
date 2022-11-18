import java.util.Scanner;

public class ConsoleDrawing {
	
	private static Scanner keyboard = new Scanner(System.in);
	private static int width;
	private static int height;
	private static char backgroundChar;
	
	
	public static void main(String[] args) {
		width = Integer.parseInt(args[0]);
		height = Integer.parseInt(args[1]);
		backgroundChar = args[2].charAt(0);
		
		// displaying a welcome message
		System.out.println("----WELCOME TO MY CONSOLE DRAWING APP----");
		System.out.println("Current drawing canvas settings:");
		System.out.println("- Width: " + width);
		System.out.println("- Height: " + height);
		System.out.println("- Background character: " + backgroundChar);
		System.out.println();
		
		// displaying main menu 
		mainMenu();
		
		// drawing canvas operated
		canvasOperation();	
	}
	
	
	private static void mainMenu() {
		System.out.println("Please select an option. Type 4 to exit.");
		System.out.println("1. Draw triangles");
		System.out.println("2. Draw squares");
		System.out.println("3. Update drawing canvas settings");
		System.out.println("4. Exit");
	}
		
	
	private static void canvasOperation() {
		// reading a user input and control the error message
		int option = keyboard.nextInt();
		while ( option < 1 || option > 4) {
			System.out.println("Unsupported option. Please try again!");
			mainMenu();
			option = keyboard.nextInt();
		}
				
		// call the corresponding method to draw the graph
		switch(option) {
			case 1:
				// ask more triangle information
				// side length
				System.out.println("Side length:");
				int sideLengthTri = keyboard.nextInt();
				
				while (sideLengthTri > height || sideLengthTri > width) {
					System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.\n", width, height);
					System.out.println("Side length:");
					sideLengthTri = keyboard.nextInt();
				}
				
				// printing character
				System.out.println("Printing character:");
				char printingCharTri = keyboard.next().toUpperCase().charAt(0);
				
				// alignment
				System.out.println("Alignment (left, middle, right):");
				String alignmentTri = keyboard.next();
				
				if ( !alignmentTri.equals("left") && !alignmentTri.equals("middle") && !alignmentTri.equals("right")) {
					alignmentTri = "left";
				}
				
				// draw triangle
				DrawingCanvas drawing = new DrawingCanvas(width, height, backgroundChar, sideLengthTri, printingCharTri, alignmentTri);
				drawing.drawingTriangle();
				
				// ask if the user wants to rotate the triangle
				System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
				char rotation = keyboard.next().charAt(0);
				int angle = 360;
				//rotation function
				while (rotation == 'L' || rotation == 'l' || rotation == 'R' || rotation == 'r') {
					
					if (rotation == 'R' || rotation == 'r') {
						if (angle % 360 == 0) {
							drawing.drawing90();
							angle += 90;
						}
						else if (angle % 360 == 90) {
							drawing.drawing180();
							angle += 90;
						}
						else if (angle % 360 == 180) {
							drawing.drawing270();
							angle += 90;
						}
						else if (angle % 360 == 270) {
							drawing.drawingTriangle();
							angle += 90;
						}
					}
					
					if (rotation == 'L' || rotation == 'l') {
						if (angle % 360 == 0) {
							drawing.drawing270();
							angle -= 90;
						}
						else if (angle % 360 == 270) {
							drawing.drawing180();
							angle -= 90;
						}
						else if (angle % 360 == 180) {
							drawing.drawing90();
							angle -= 90;
						}
						else if (angle % 360 == 90) {
							drawing.drawingTriangle();
							angle -= 90;
						}
					}
					
					System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
					rotation = keyboard.next().charAt(0);
				}
				
				
				// ask if the user want to draw another triangle
				System.out.println("Draw another triangle (Y/N)?");
				char anotherTri = keyboard.next().charAt(0);
				while (true) {
					if (anotherTri == 'Y' || anotherTri == 'y') {
						// ask another triangle information
						// side length
						System.out.println("Side length:");
						sideLengthTri = keyboard.nextInt();
						
						while (sideLengthTri > height || sideLengthTri > width) {
							System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.\n", width, height);
							System.out.println("Side length:");
							sideLengthTri = keyboard.nextInt();
						}
						
						// printing character
						System.out.println("Printing character:");
						printingCharTri = keyboard.next().toUpperCase().charAt(0);
						
						// alignment
						System.out.println("Alignment (left, middle, right):");
						alignmentTri = keyboard.next();
						
						if ( !alignmentTri.equals("left") && !alignmentTri.equals("middle") && !alignmentTri.equals("right")) {
							alignmentTri = "left";
						}
						DrawingCanvas drawingAnother = new DrawingCanvas(width, height, backgroundChar, sideLengthTri, printingCharTri, alignmentTri);
						drawingAnother.drawingTriangle();
						
						// ask if the user wants to rotate the new triangle
						System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
						rotation = keyboard.next().charAt(0);
						angle = 360;
						while (rotation == 'L' || rotation == 'l' || rotation == 'R' || rotation == 'r') {
							
							if (rotation == 'R' || rotation == 'r') {
								if (angle % 360 == 0) {
									drawingAnother.drawing90();
									angle += 90;
								}
								else if (angle % 360 == 90) {
									drawingAnother.drawing180();
									angle += 90;
								}
								else if (angle % 360 == 180) {
									drawingAnother.drawing270();
									angle += 90;
								}
								else if (angle % 360 == 270) {
									drawingAnother.drawingTriangle();
									angle += 90;
								}
							}
							
							if (rotation == 'L' || rotation == 'l') {
								if (angle % 360 == 0) {
									drawingAnother.drawing270();
									angle -= 90;
								}
								else if (angle % 360 == 270) {
									drawingAnother.drawing180();
									angle -= 90;
								}
								else if (angle % 360 == 180) {
									drawingAnother.drawing90();
									angle -= 90;
								}
								else if (angle % 360 == 90) {
									drawingAnother.drawingTriangle();
									angle -= 90;
								}
							}
							
							System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
							rotation = keyboard.next().charAt(0);
						}
						
		
						System.out.println("Draw another triangle (Y/N)?");
						anotherTri = keyboard.next().charAt(0);
					}
					else if (anotherTri == 'N' || anotherTri == 'n') {
						mainMenu();
						canvasOperation();
						break;
					}
					else {
						do{
							System.out.println("Unsupported option. Please try again!");
							System.out.println("Draw another triangle (Y/N)?");
							anotherTri = keyboard.next().charAt(0);
						} while (anotherTri != 'Y' && anotherTri != 'y' && anotherTri != 'N' && anotherTri != 'n');
					}	
				}
				break;
				
			case 2:
				// ask more square information
				// side length
				System.out.println("Side length:");
				int sideLengthSqr = keyboard.nextInt();
				
				while (sideLengthSqr > height || sideLengthSqr > width) {
					System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.\n", width, height);
					System.out.println("Side length:");
					sideLengthSqr = keyboard.nextInt();
				}
				
				// printing character
				System.out.println("Printing character:");
				char printingCharSqr = keyboard.next().toUpperCase().charAt(0);
				
				// alignment
				System.out.println("Alignment (left, middle, right):");
				String alignmentSqr = keyboard.next();
				
				if ( !alignmentSqr.equals("left") && !alignmentSqr.equals("middle") && !alignmentSqr.equals("right")) {
					alignmentSqr = "left";
				}
				
				// draw square
				Square drawingSqr = new Square(width, height, backgroundChar, sideLengthSqr, printingCharSqr, alignmentSqr); 
				drawingSqr.normalSquare();
				
				// ask if the user want to zoom in/out
				System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
				char zoom = keyboard.next().charAt(0);
				//rotation function
				while (zoom == 'I' || zoom == 'i' || zoom == 'O' || zoom == 'o') {
					
					if (zoom == 'I' || zoom == 'i') {
						drawingSqr.zoomIn();
					}
					
					if (zoom == 'O' || zoom == 'o') {
						drawingSqr.zoomOut();
					}
					
					System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
					zoom = keyboard.next().charAt(0);
				}
				
				
				// ask if the user want to draw another triangle
				System.out.println("Draw another square (Y/N)?");
				char anotherSqr = keyboard.next().charAt(0);
				while (true) {
					if (anotherSqr == 'Y' || anotherSqr == 'y') {
						// ask another Square information
						// side length
						System.out.println("Side length:");
						sideLengthSqr = keyboard.nextInt();
						
						while (sideLengthSqr > height || sideLengthSqr > width) {
							System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.\n", width, height);
							System.out.println("Side length:");
							sideLengthSqr = keyboard.nextInt();
						}
						
						// printing character
						System.out.println("Printing character:");
						printingCharSqr = keyboard.next().toUpperCase().charAt(0);
						
						// alignment
						System.out.println("Alignment (left, middle, right):");
						alignmentSqr = keyboard.next();
						
						if ( !alignmentSqr.equals("left") && !alignmentSqr.equals("middle") && !alignmentSqr.equals("right")) {
							alignmentSqr = "left";
						}
						Square drawingAnotherSqr = new Square(width, height, backgroundChar, sideLengthSqr, printingCharSqr, alignmentSqr);
						drawingAnotherSqr.normalSquare();
						
						// ask if the user wants to zoom in/out the new triangle
						System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
						zoom = keyboard.next().charAt(0);
						while (zoom == 'I' || zoom == 'i' || zoom == 'O' || zoom == 'o') {
							
							if (zoom == 'I' || zoom == 'i') {
								drawingAnotherSqr.zoomIn();
							}
							
							if (zoom == 'O' || zoom == 'o') {
								drawingAnotherSqr.zoomOut();
							}
							
							System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
							zoom = keyboard.next().charAt(0);
						}
						
						System.out.println("Draw another square (Y/N)?");
						anotherSqr = keyboard.next().charAt(0);
					}
					else if (anotherSqr == 'N' || anotherSqr == 'n') {
						mainMenu();
						canvasOperation();
						break;
					}
					else {
						do{
							System.out.println("Unsupported option. Please try again!");
							System.out.println("Draw another square (Y/N)?");
							anotherSqr = keyboard.next().charAt(0);
						} while (anotherSqr != 'Y' && anotherSqr != 'y' && anotherSqr != 'N' && anotherSqr != 'n');
					}	
				}
				break;
				
			case 3:
				// reading new settings
				System.out.print("Canvas width: ");
				width = keyboard.nextInt();
				
				System.out.print("Canvas height: ");
				height = keyboard.nextInt();
				
				System.out.print("Background character: ");
				backgroundChar = keyboard.next().charAt(0);
				
				// confirmation message
				System.out.println("Drawing canvas has been updated!\n");
				
				// displaying new settings
				System.out.println("Current drawing canvas settings:");
				System.out.println("- Width: " + width);
				System.out.println("- Height: " + height);
				System.out.println("- Background character: " + backgroundChar);
				System.out.println();
				
				// displaying main menu again
				mainMenu();
				
				// the system run as usual
				canvasOperation();
				break;
			
			case 4:
				System.out.println("Goodbye!");
				keyboard.close();
				System.exit(0);
				break;
		
		}
	}
	
	
	
}
