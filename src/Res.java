

import Audio.Audio;
import Engine.graphics.Sprite;
import Engine.graphics.SpriteSheet;
import Engine.level.Level;
import Engine.level.tile.Tile;
import IOHandler.InputHandler;

/**
 * This class is the resource class that have all the resources that the game needs. I.e. sprites, audios, etc.
 * @author Christian
 *
 */
public class Res {
	public final static int RES_SPRITE_SIZE = 16; 

	public static SpriteSheet mobs = new SpriteSheet("/textures/spritesheet_Entity.png", 48);
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet_Tile.png", 48);

	public static Sprite outsideLevelTile_Sprite = new Sprite(RES_SPRITE_SIZE, 0xf000000);//0x70ff85);
	public static Sprite ground_Sprite = new Sprite(RES_SPRITE_SIZE, 1, 0, tiles);
	public static Sprite air_Sprite = new Sprite(RES_SPRITE_SIZE, 0, 0, tiles); 
	public static Sprite tree_Sprite = new Sprite(RES_SPRITE_SIZE,2, 0, tiles);
	public static Sprite underground_Sprite = new Sprite(RES_SPRITE_SIZE, 1, 1, tiles);
	
	public static Sprite ufo_1_Sprite = new Sprite(RES_SPRITE_SIZE, 1, 0, mobs);
	public static Sprite ufo_2_Sprite = new Sprite(RES_SPRITE_SIZE, 2, 0, mobs);
	public static Sprite player_Sprite = new Sprite(RES_SPRITE_SIZE, 0, 0, mobs);
	public static Sprite goal_Sprite = new Sprite(RES_SPRITE_SIZE, 0, 1, mobs);
	
	public static Sprite victory_Sprite = new Sprite(400, 100, "/textures/Victory_Screen.png");
	
	public static Tile outsideLevelTile = new OutsideLevelTile(outsideLevelTile_Sprite);
	public static Tile ground_Tile = new GroundTile(ground_Sprite);
	public static Tile air_Tile = new AirTile(air_Sprite);
	public static Tile tree_Tile = new TreeTile(tree_Sprite);
	public static Tile underground_Tile = new UndergroundTile(underground_Sprite);
	
	public static int col_air = 0xffC4FFFB; 
	public static int col_ground = 0xffFFB375;
	public static int col_tree = 0xff6FFF4F;
	public static int col_ground_under = 0xffA85C39;
	
	public static Audio space = new Audio("/sound/SpaceE.wav");
	public static Audio bgm1 = new Audio("/sound/fl0821.wav");
	public static Audio bang = new Audio("/sound/Bang.wav");
	
	public static Level level1 = new Level1("/levels/game_level1.png");

	
}
