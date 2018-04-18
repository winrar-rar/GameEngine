package Engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Sprite {
	
	public final int SIZE;
	private int x, y;

	public int[] pixels;
	private SpriteSheet sheet;

	/**
	 * Constructor with spritesheet.
	 * @param size the sprite size.
	 * @param x the coordinate in the spritesheet.
	 * @param y the coordinate in the spritesheet.
	 * @param sheet the spritesheet where the sprite is located.
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		loadFromSheet();
	}
	
	/**
	 * Constructor with no spritesheet.
	 * @param size the sprite size.
	 * @param colour the colour of the sprite in hexadecimal.
	 */
	public Sprite(int size, int colour){
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	/**
	 * Method to give a sprite a colour.
	 * @param colour the coour of the sprite.
	 */
	private void setColour(int colour){
		for(int i = 0; i < SIZE * SIZE; i++){
			pixels[i] = colour;
		}
	}
	
	/**
	 * Extracting a sprite from the spritesheet.
	 */
	private void loadFromSheet() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
				
			}
		}
	}
	
	
	
	
	//EGET
	private String path;
	private int width, height;
	
	/**
	 * Constructor for a single sprite.
	 * @param width the with of the sprite.
	 * @param height the height of the sprite.
	 * @param path the path where the sprite is.
	 */
	public Sprite(int width, int height, String path){
		SIZE = 0;
		this.width = width;
		this.height = height;
		this.path = path;
		pixels = new int[width * height];
		loadFromPath();
	}
	
	/**
	 * Loads the Sprite from a path.
	 */
	private void loadFromPath(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the widths of the sprite.
	 * @return the width.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Gets the height of the sprite.
	 * @return the height.
	 */
	public int getHeight(){
		return height;
	}
	
}
