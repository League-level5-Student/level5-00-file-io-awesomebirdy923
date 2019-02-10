package _05_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;

	// 1. Create a 2D array of pixels. Do not initialize it yet.

	Pixel[][] pixels;

	private Color color;

	public GridPanel(int w, int h, int r, int c) {
		System.out.println("Test");
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;

		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;

		color = Color.BLACK;

		setPreferredSize(new Dimension(windowWidth, windowHeight));

		// 2. Initialize the pixel array using the rows and cols variables.

		pixels = new Pixel[rows][cols];

		// 3. Iterate through the array and initialize each element to a new pixel.

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				pixels[i][j] = new Pixel(pixelWidth, pixelHeight);
			}
		}

	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setColor(Color c) {
		color = c;
	}

	public void clickPixel(int mouseX, int mouseY) {
		// 5. Use the mouseX and mouseY variables to change the color
		// of the pixel that was clicked. *HINT* Use the pixel's dimensions.

		pixels[mouseX / pixelWidth][mouseY / pixelHeight].color = color;
		repaint();
	}

	public void erasePixel(int mouseX, int mouseY) {
		pixels[mouseX / pixelWidth][mouseY / pixelHeight].color = Color.WHITE;
	}

	public void setPixelArray(Pixel[][] p) {
		pixels = p;
	}

	public Pixel[][] getPixelArray() {
		return pixels;
	}

	public void update() {
		repaint();
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setDimensions(int width, int height) {
		this.windowWidth = width;
		this.windowHeight = height;
	}

	public void setPixelScale(int width, int height) {
		this.pixelWidth = width;
		this.pixelHeight = height;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 4. Iterate through the array.
		// For every pixel in the list, fill in a rectangle using the pixel's color.
		// Then, use drawRect to add a grid pattern to your display.\

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				// Pixel p = pixels[i][j];
				g.setColor(pixels[i][j].color);
				g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
			}
		}
	}
}
