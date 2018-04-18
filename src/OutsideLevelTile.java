

import Engine.graphics.Render;
import Engine.graphics.Sprite;
import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class OutsideLevelTile extends Tile{

	public OutsideLevelTile(Sprite sprite) {
		super(sprite);
	}	
	
	/**
	 * Returns solid is true.
	 */
	public boolean solid(){
		return true;
	}

}
