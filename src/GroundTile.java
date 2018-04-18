

import Engine.graphics.Render;
import Engine.graphics.Sprite;
import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class GroundTile extends Tile {

	public GroundTile(Sprite sprite) {
		super(sprite);
	}

	/**
	 * returns if the tile is solid or not.
	 */
	public boolean solid(){
		return true;
	}

}