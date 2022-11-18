public class Square {
	
	private int width;
	private int height;
	private char backgroundChar;
	private int sideLength;
	private char printingChar;
	private String alignment;
	
	// Constructor
	public Square (int width, int height, char backgroundChar, int sideLength, char printingChar, String alignment) {
		this.width = width;
		this.height = height;
		this.backgroundChar = backgroundChar;
		this.sideLength = sideLength;
		this.printingChar = printingChar;
		this.alignment = alignment;
	}
	
	
	// drawing a normal square
	public void normalSquare() {
		
		// setting the index of starting column
		int col_index = colIndex();
		
		// start drawing
		for (int i = 1; i <= height; i++) { // row
			if (i <= sideLength) {
				for (int j = 1; j<= width; j++) { // column
					if (j < col_index) {
						System.out.print(backgroundChar);
					}
					else if (j < (col_index + sideLength)) {
						System.out.print(printingChar);
					}
					else {
						System.out.print(backgroundChar);
					}
				}
				System.out.println();
			}
			else {
				for (int j = 1; j <= width; j++) {
					System.out.print(backgroundChar);
				}
				System.out.println();
			}
		}
		
	}
	
	
	// drawing a zoom-in square
	public void zoomIn() {
		
		// setting the index of starting column
		int col_index = colIndex();
		
		
		// start drawing
		if ((sideLength + 1) < (width - col_index) && sideLength < height) {
			sideLength += 1;
			
			col_index = colIndex();
			
			for (int i = 1; i <= height; i++) { // row
				if (i <= sideLength) {
					for (int j = 1; j<= width; j++) { // column
						if (j < col_index) {
							System.out.print(backgroundChar);
						}
						else if (j < (col_index + sideLength)) {
							System.out.print(printingChar);
						}
						else {
							System.out.print(backgroundChar);	
						}
					}
					System.out.println();
				}	
				else {				
					for (int j = 1; j <= width; j++) {
						System.out.print(backgroundChar);
					}
					System.out.println();
				}
			}
		}
		else {
			
			col_index = colIndex();
			
			for (int i = 1; i <= height; i++) { // row
				if (i <= sideLength) {
					for (int j = 1; j<= width; j++) { // column
						if (j < col_index) {
							System.out.print(backgroundChar);
						}
						else if (j < (col_index + sideLength)) {
							System.out.print(printingChar);
						}
						else {
							System.out.print(backgroundChar);	
						}
					}
					System.out.println();
				}	
				else {				
					for (int j = 1; j <= width; j++) {
						System.out.print(backgroundChar);
					}
					System.out.println();
				}
			}	
		}
		
		
	}
		
	
	// drawing a zoom-out square
	public void zoomOut() {
		 
		// start drawing
		if (sideLength > 1) {
			
			sideLength -= 1;
			// setting the index of starting column
			int col_index = colIndex();
			
			for (int i = 1; i <= height; i++) { // row
				if (i <= sideLength) {
					for (int j = 1; j<= width; j++) { // column
						if (j < col_index) {
							System.out.print(backgroundChar);
						}
						else if (j < (col_index + sideLength)) {
							System.out.print(printingChar);
						}
						else {
							System.out.print(backgroundChar);	
						}
					}
					System.out.println();
				}	
				else {				
					for (int j = 1; j <= width; j++) {
						System.out.print(backgroundChar);
					}
					System.out.println();
				}
			}
		}
		else {
			
			int col_index = colIndex();
			
			for (int i = 1; i <= height; i++) { // row
				if (i <= sideLength) {
					for (int j = 1; j<= width; j++) { // column
						if (j < col_index) {
							System.out.print(backgroundChar);
						}
						else if (j < (col_index + sideLength)) {
							System.out.print(printingChar);
						}
						else {
							System.out.print(backgroundChar);	
						}
					}
					System.out.println();
				}	
				else {				
					for (int j = 1; j <= width; j++) {
						System.out.print(backgroundChar);
					}
					System.out.println();
				}
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
