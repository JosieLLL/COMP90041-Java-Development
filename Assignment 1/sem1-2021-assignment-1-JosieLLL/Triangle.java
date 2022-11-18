public class Triangle {
	
	private int width;
	private int height;
	private char backgroundChar;
	private int sideLength;
	private char printingChar;
	private String alignment;
	
	// Constructor
	public Triangle(int width, int height, char backgroundChar, int sideLength, char printingChar, String alignment) {
		this.width = width;
		this.height = height;
		this.backgroundChar = backgroundChar;
		this.sideLength = sideLength;
		this.printingChar = printingChar;
		this.alignment = alignment;
	}
	
	
	// drawing a normal Triangle
	public void normalTriangle() {
		
		// setting the index of starting column
		int col_index = colIndex();
		
		// start drawing
		int finishPoint = sideLength;
		for (int i = 1; i <= height; i++) { // row
			if (i <= sideLength) {
				for (int j = 1; j<= width; j++) { // column
					if (j < col_index) {
						System.out.print(backgroundChar);
					}
					else if (j < (col_index + finishPoint)) {
						System.out.print(printingChar);
					}
					else {
						System.out.print(backgroundChar);
					}
				}
				System.out.println();
				finishPoint --;
			}
			else {
				for (int j = 1; j <= width; j++) {
					System.out.print(backgroundChar);
				}
				System.out.println();
			}
		}
		
	}
	
	
	// Rotation of Triangle
	public void clockwise90() {
		
		// setting the index of starting column
		int col_index = colIndex();
				
		// start drawing
		int finishPoint = sideLength;
		for (int i = 1; i <= height; i++) { // row
			if (i <= sideLength) {
				for (int j = 1; j<= width; j++) { // column
					if (j < col_index) {
						System.out.print(backgroundChar);
					}
					else if (j < (col_index + finishPoint)) {
						System.out.print(printingChar);
					}						
					else {
						System.out.print(backgroundChar);
					}
				}
				System.out.println();				
				col_index++;
				finishPoint --;
			}
			else {
				for (int j = 1; j <= width; j++) {
					System.out.print(backgroundChar);
				}
				System.out.println();
			}
		}
		
	}
	
	
	public void clockwise180() {
		
		// setting the index of starting column
		int col_index = colIndex();
				
		// start drawing
		int finishPoint = sideLength;
		int decrement = 1;
		for (int i = 1; i <= height; i++) { // row
			if (i <= sideLength) {
				for (int j = 1; j<= width; j++) { // column
					if (j < (col_index + finishPoint - decrement)) {
						System.out.print(backgroundChar);
					}
					else if (j < (col_index + finishPoint)) {
						System.out.print(printingChar);
					}						
					else {
						System.out.print(backgroundChar);
					}
				}
				System.out.println();				
				decrement++;
			}
			else {
				for (int j = 1; j <= width; j++) {
					System.out.print(backgroundChar);
				}
				System.out.println();
			}
		}
		
	}
	
	
	public void clockwise270() {
		
		// setting the index of starting column
		int col_index = colIndex();
				
		// start drawing
		int increment = 1;
		for (int i = 1; i <= height; i++) { // row
			if (i <= sideLength) {
				for (int j = 1; j<= width; j++) { // column
					if (j < col_index) {
						System.out.print(backgroundChar);
					}
					else if (j < (col_index + increment)) {
						System.out.print(printingChar);
					}						
					else {
						System.out.print(backgroundChar);
					}
				}
				System.out.println();				
				increment++;
			}
			else {
				for (int j = 1; j <= width; j++) {
					System.out.print(backgroundChar);
				}
				System.out.println();
			}
		}	
		
	}
	
	
	// setting the index of starting column
	private int colIndex() {
		
		int col_index = 0;
		
		if (alignment.equals("left")) {
			col_index = 1;
		}
		if (alignment.equals("middle")) {
			col_index = (width - sideLength) / 2 + 1;
		}
		if (alignment.equals("right")) {
			col_index = width - sideLength + 1;
		}
		
		return col_index;
	}
	
	
	
}
