import java.util.Scanner;

public class Square {
	private DrawingCanvas canvas;
	public Square(DrawingCanvas aCanvas) {
		canvas = aCanvas;
	}
	
	public void handleRequest(Scanner keyboard) {
		outer_loop:
		while (true) {
			//Get square side length
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
			
			inner_loop_1:
			while (true) {
				this.draw(size, character, alignment);
				
				System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
				char yesOrNo = keyboard.next().toUpperCase().charAt(0);
				switch(yesOrNo) {
					case 'I':
						if (size < canvas.getHeight() && size < canvas.getWidth()) {
							size = size + 1;
						}
						break;
					case 'O':
						if (size > 1) {
							size = size - 1;
						}
						break;
					default:
						break inner_loop_1;
				}
			}
			
			inner_loop_2:
			while (true) {
				System.out.println("Draw another square (Y/N)?");
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
	
	public void draw(int size, char c, String alignment) {
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
		
		//Draw the rows containing the square
		int row, col;		
		for(row = 0; row < size; row++) {
			for(int k = 0; k < colIndex; k++) System.out.print(canvas.getBgChar());
			for (col = 0; col < size; col++) {
				System.out.print(c);
			}
			for(int k = colIndex + size; k < canvas.getWidth(); k++) System.out.print(canvas.getBgChar());
			System.out.println();
		}
		
		//Draw "empty" rows
		for (int i = size; i < canvas.getHeight(); i++) {
			for (int j = 0; j < canvas.getWidth(); j++) {
				System.out.print(canvas.getBgChar());
			}
			System.out.println();
		}
	}
}
