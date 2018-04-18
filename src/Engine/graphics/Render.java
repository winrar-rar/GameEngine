package Engine.graphics;

import Engine.entity.Entity;
import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Render {

	private Sprite sprite;
	public int width, height;
	public int SPRITE_SIZE;
	public int SPRITE_SIZE_CALC;

	public int xOffset, yOffset;

	public int[] pixels;

	/**
	 * Constructor for the render class.
	 * @param width the width;
	 * @param height the height.
	 */
	public Render(int width, int height, int SPRITE_SIZE) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		this.SPRITE_SIZE = SPRITE_SIZE;
		SPRITE_SIZE_CALC = (int) Math.sqrt(SPRITE_SIZE);
	}

	
	/**
	 * Clear the screen.
	 */
	public void clear(){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	

/**EGET
 * Rendering a sprite. It has a fixed location on your screen and wont move outside of it.
 * @param xPos the position of the sprite on the screen.
 * @param yPos the position of the sprite on the screen.
 * @param sprite the sprite.
 */
	public void renderSprite(int xPos, int yPos, Sprite sprite){
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yAbsolutPos = y + yPos;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xAbsolutPos = x + xPos;
				if (xAbsolutPos < -sprite.getWidth() || xAbsolutPos >= width || yAbsolutPos < 0 || yAbsolutPos >= height)
					break;
				if (xAbsolutPos < 0)
					xAbsolutPos = 0;
				int colour = sprite.pixels[x + y * sprite.getWidth()];
				if (colour != 0xffff00ff) // render the pixels if the colour is not this colour. //0xff alpha + colour.
				pixels[xAbsolutPos + yAbsolutPos * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
		
	}
	
	
	/**
	 * Renders Tiles.
	 * @param xPos the position of the tile in the level.
	 * @param yPos the position of the tile in the level.
	 * @param tile what kind of tile will be rendered.
	 */
	public void renderTile(int xPos, int yPos, Tile tile) {

		xPos -= xOffset;
		yPos -= yOffset;

		for (int y = 0; y < tile.getSpriteSize(); y++) {
			int yAbsolutPos = y + yPos;
			for (int x = 0; x < tile.getSpriteSize(); x++) {
				int xAbsolutPos = x + xPos;
				if (xAbsolutPos < -tile.getSpriteSize() || xAbsolutPos >= width || yAbsolutPos < 0 || yAbsolutPos >= height)
					break;
				if (xAbsolutPos < 0)
					xAbsolutPos = 0;
				pixels[xAbsolutPos + yAbsolutPos * width] = tile.getSprite().pixels[x + y * tile.getSpriteSize()];
			}
		}
	}

	
	/**
	 * Renders entities.
	 * @param xPos the position of the sprite.
	 * @param yPos the position of the sprite.
	 * @param sprite the sprite that will be rendered.
	 */
	public void renderEntity(int xPos, int yPos, Entity entity) {

		xPos -= xOffset;
		yPos -= yOffset;
		
		
		for (int y = 0; y < entity.getSpriteSize(); y++) {
			int yAbsolutPos = y + yPos;
			for (int x = 0; x < entity.getSpriteSize(); x++) {
				int xAbsolutPos = x + xPos;
				if (xAbsolutPos < -entity.getSpriteSize() || xAbsolutPos >= width || yAbsolutPos < 0 || yAbsolutPos >= height)
					break;
				if (xAbsolutPos < 0)
					xAbsolutPos = 0;
				int colour = entity.getSprite().pixels[x + y * entity.getSpriteSize()];
				if (colour != 0xffff00ff) // render the pixels if the colour is not this colour. //0xff alpha + colour.
					pixels[xAbsolutPos + yAbsolutPos * width] = colour;
			}
		}
		
	}

	/**
	 * 
	 * @param xOffset displacement from the beginning.
	 * @param yOffset displacement from the beginning
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
}
