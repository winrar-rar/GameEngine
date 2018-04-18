

import Engine.graphics.Sprite;
import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class TreeTile extends Tile{

	public TreeTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public boolean solid(){
		return true;
	}
}
