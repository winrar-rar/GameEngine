package Engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	
	public int[] pixels;
	
	/**
	 * Constructor for the spritesheet.
	 * @param path the path to spritesheet.
	 * @param size the size spritesheet.
	 */
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels  = new int[SIZE * SIZE];
		load();
	}
	
	/**
	 * Loads the spritesheet to the game so sprits can be seen.
	 */
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
