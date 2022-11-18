public class DrawingCanvas {
	
	private int width;
	private int height;
	private char backgroundChar;
	private int sideLength;
	private char printingChar;
	private String alignment;
	private Triangle t;

	
	// Constructor - for triangle
	public DrawingCanvas(int width, int height, char backgroundChar, int sideLength, char printingChar, String alignment) {
		this.width = width;
		this.height = height;
		this.backgroundChar = backgroundChar;
		this.sideLength = sideLength;
		this.printingChar = printingChar;
		this.alignment = alignment;
		t = new Triangle(width, height, backgroundChar, sideLength, printingChar, alignment);
	}
	
	
	// draw triangles
	public void drawingTriangle() {
		t.normalTriangle();
	}
	
	public void drawing90() {
		t.clockwise90();
	}
	
	public void drawing180() {
		t.clockwise180();
	}
	
	public void drawing270() {
		t.clockwise270();
	}
		
}
