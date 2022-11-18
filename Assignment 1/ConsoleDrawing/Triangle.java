import java.util.Scanner;

public class Triangle {
	private DrawingCanvas canvas;
	
	public Triangle(DrawingCanvas aCanvas) {
		canvas = aCanvas;
	}
	
	public void handleRequest(Scanner keyboard) {
		outer_loop:
		while (true) {
			//Get Isosceles Right Triangle side length
			int size;
			while (true) {
				System.out.println("Side length:");
				size = keyboard.nextInt();
				keyboard.nextLine();
			
				if (size > canvas.getHeight() || size > canvas.getWidth()) {
					System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.%n", 
							canvas.getWidth(), canvas.getHeight());		
				} else {
					break;
				}
			}
			//Get drawing character
			System.out.println("Printing character:");
			char character = keyboard.next().charAt(0);
			keyboard.nextLine();
			
			//Get alignment setting
			System.out.println("Alignment (left, middle, right):");
			String alignment = keyboard.next();
			
			int pos = 0;
			inner_loop_1:
			while (true) {
				this.draw(size, character, alignment, pos);
		        
				System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
				char yesOrNo = keyboard.next().toUpperCase().charAt(0);
				switch(yesOrNo) {
					case 'R':
						if (pos < 3) pos++;
						else pos = 0;
						break;
					case 'L':
						if (pos > 0) pos--;
						else pos = 3;
						break;
					default:
						break inner_loop_1;
				}
			}
			
			inner_loop_2:
			while (true) {
				System.out.println("Draw another triangle (Y/N)?");
				char yesOrNo = keyboard.next().toUpperCase().charAt(0);
				switch(yesOrNo) {
				case 'Y':
					break inner_loop_2;
				case 'N':
					break outer_loop;
				default:
					System.out.println("Unsupported option. Please try again!");
					break;
				}
			}
		}
	}
	
	private void drawEmptyRows(int size) {
		for (int i = size; i < canvas.getHeight(); i++) {
			for (int j = 0; j < canvas.getWidth(); j++) {
				System.out.print(canvas.getBgChar());
			}
			System.out.println();
		}
	}
	
	public void draw(int size, char c, String alignment, int pos) {
		int colIndex = 0;
		switch(alignment) {
			case "left":
				colIndex = 0;
				break;
			case "middle":
				colIndex = (canvas.getWidth() - size) / 2;
				break;
			case "right":
				colIndex = canvas.getWidth() - size;
				break;
			default:
				break;
		}
		
		int row, col;
		switch (pos) {
			case 0: //original position - 0-degree rotation
				//Draw rows containing the triangle
				for(row = 1; row <= size; row++) {
					//In each row, draw the "empty prefix"
					for(int k = 0; k < colIndex; k++) {
						System.out.print(canvas.getBgChar());
					}
					
					//Then draw a part of the triangle on that row
					for (col = size; col > 0; col--) {
						if (col >= row) {
							System.out.print(c);
						} else {
							System.out.print(canvas.getBgChar());
						}
					}
					
					//And finally, draw the "empty suffix"
					for(int k = colIndex + size; k < canvas.getWidth(); k++) {
						System.out.print(canvas.getBgChar());
					}
					
					//End the current row before start drawing the next one
					System.out.println();
				}
				
				//Draw "empty" rows
				drawEmptyRows(size);
				break;
				
			case 1: //90-degree clockwise rotation
				//Draw rows containing the triangle
				for(row = 1; row <= size; row++) {
					for(int k = 0; k < colIndex; k++) {
						System.out.print(canvas.getBgChar());
					}
					for (col = 1; col <= size; col++) {
						if (col < row) {
							System.out.print(canvas.getBgChar());
						} else {
							System.out.print(c);
						}
					}
					for(int k = colIndex + size; k < canvas.getWidth(); k++) {
						System.out.print(canvas.getBgChar());
					}
					System.out.println();
				}
				
				//Draw "empty" rows
				drawEmptyRows(size);
				break;
				
			case 2: //180-degree clockwise rotation
				//Draw rows containing the triangle
				for(row = 1; row <= size; row++) {
					for(int k = 0; k < colIndex; k++) {
						System.out.print(canvas.getBgChar());
					}
					for (col = size; col > 0; col--) {
						if (col > row) {
							System.out.print(canvas.getBgChar());
						} else {
							System.out.print(c);
						}
					}
					for(int k = colIndex + size; k < canvas.getWidth(); k++) {
						System.out.print(canvas.getBgChar());
					}
					System.out.println();
				}
				
				//Draw "empty" rows
				drawEmptyRows(size);
				break;
				
			case 3: //270-degree clockwise rotation
				//Draw rows containing the triangle
				for(row = 0; row < size; row++) {
					for(int k = 0; k < colIndex; k++) {
						System.out.print(canvas.getBgChar());
					}
					for (col = 0; col <= row; col++) {
						System.out.print(c);
					}
					for(int k = row + colIndex + 1; k < canvas.getWidth(); k++) {
						System.out.print(canvas.getBgChar());
					}
					System.out.println();
				}
				
				//Draw "empty" rows
				drawEmptyRows(size);
				break;
			default:
				//The program should not come here
				break;
		}
	}
}
