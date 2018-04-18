

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Audio.AudioPlayer;
import Engine.Engine;
import Engine.level.Level;
import IOHandler.InputHandler;

public class Game{

	private InputHandler input;
	private HotAirBalloonPlayer player;
	private Level level;
	private static double tick = 60.0;
	
	private Ufo ufo1, ufo2, ufo3;
	private Goal goal;
	
	public final static int RES_SPRITE_SIZE = 16; 
	public static final Engine engine = new Engine(); //To us this is the most convenient way.
	
	
	/**
	 * Main method that starts everything.
	 * @param args extra argument that are not used.
	 */
	public static void main(String[] args) {
		Game game;
		game = new Game();
	}
	
	public Game(){
		engine.setSpriteSize(RES_SPRITE_SIZE);
		
		input = new InputHandler();
		Level level = Res.level1;
		
		engine.setTick(tick);
		engine.setTitle("Hot Air Balloon Ride");
		engine.init();	
		
		player = new HotAirBalloonPlayer(Res.player_Sprite,44, 44, 1, input);
		ufo3 = new Ufo(Res.ufo_2_Sprite, 688, 528, 2);
		ufo2 = new Ufo(Res.ufo_2_Sprite, 688, 144, 2);
		ufo1 = new Ufo(Res.ufo_1_Sprite, 256, 256, 2);
		goal = new Goal(Res.goal_Sprite, 464, 760);
		
		engine.setLevel(level);
		engine.addEntitytoLevel(ufo3);
		engine.addEntitytoLevel(ufo2);
		engine.addEntitytoLevel(ufo1);
		engine.addEntitytoLevel(goal);
		
		engine.setGravity(1);	
		
		try {
			engine.addSound("bgm", Res.bgm1);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			engine.addSound("jump", Res.space);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			engine.addSound("bang", Res.bang);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		engine.setCamera(player);
		engine.setInputHandler(input);
		
		try {
			engine.playSoundLoop("bgm");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
