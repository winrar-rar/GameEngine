//tile render itself

package Engine.level.tile;

import Engine.entity.GameObject;
import Engine.graphics.Render;
import Engine.graphics.Sprite;

/**
 * 
 * @author Christian Andersson.
 *
 */
public abstract class Tile extends GameObject{
	
	/**
	 * Makes sure that each tile has a sprite-
	 * @param sprite what sprite the tile has.
	 */
	public Tile(Sprite sprite){
		super(sprite);
	}
	
	/**
	 * 
	 * @param x position where to render the tile.
	 * @param y position where to render the tile.
	 * @param render shows it on the screen.
	 */
	public void render(int x, int y, Render render){
		render.renderTile(x << render.SPRITE_SIZE_CALC, y << render.SPRITE_SIZE_CALC, this);
	}
	
	/**
	 * 
	 * @return if the tile is solid or not.
	 */
	public boolean solid(){
		return false;
	}
	
}
