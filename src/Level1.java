

//The first level the player start at.



import Engine.level.Level;
import Engine.level.tile.Tile;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Level1 extends Level {
	
	private VictoryScreen vic = new VictoryScreen(Res.victory_Sprite, width/2, height);;
	private int victory = 0;
	
	public Level1(String path) {
		super(path);
		
	}
	
	/**
	 * 
	 * @param x location.
	 * @param y location.
	 * @return returns a tile.
	 */
	public Tile getTile(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) return Res.outsideLevelTile;
		if(tiles[x + y * width] == Res.col_ground) return Res.ground_Tile;
		if(tiles[x + y * width] == Res.col_air) return Res.air_Tile;
		if(tiles[x + y * width] == Res.col_tree) return Res.tree_Tile;
		if(tiles[x + y * width] == Res.col_ground_under) return Res.underground_Tile;
		
		return Res.outsideLevelTile;
	}

	@Override
	protected void levelEvent() {
		
		if(!this.getCamera().isAlive()){
			this.getCamera().setPos(44, 44);
			this.getCamera().setAlive(true);
		}
		
		if(this.getCamera().FoundGoal()){
			victory++;
			victory();
			this.getCamera().setPos(44, 44);
		}
	}
	
	/**
	 * The Victory will only be added once, otherwise the frame will drop to almoust zero after a while.
	 */
	protected void victory(){
		if(victory == 1){
			add(vic);
			victory = 2;
		}
	}

}


