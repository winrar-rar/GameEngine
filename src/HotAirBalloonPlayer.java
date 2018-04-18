

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Engine.entity.Camera;
import Engine.entity.Entity;
import Engine.graphics.Sprite;
import IOHandler.InputHandler;

/**
 * The Player class.
 * @author Christian Andersson
 *
 */
public class HotAirBalloonPlayer extends Camera{

	public HotAirBalloonPlayer(Sprite sprite, int xPos, int yPos, int speed,
			InputHandler input) {
		super(sprite, xPos, yPos, speed, input);
		// TODO Auto-generated constructor stub
	}


	/**
	 * update
	 */
	public void update(){
		
		xAxis = 0;
		yAxis = 0;
			
		if(isFalling()){
			yAxis += Game.engine.getGPhysics().getGravity();
			
		}
		
		
		if(isJumping()){
			try {
				Game.engine.playSound("jump");
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			yAxis-=2;
			setJumping(false);
		}
			
		if (input.up){
			setJumping(true);
		}
		if (input.left){
			xAxis-=1;
		}
		if (input.right){
			xAxis+=1;
		}
	
		if (xAxis != 0 || yAxis != 0) {
			move(xAxis, yAxis);
			this.setWalking(true);
		} else {
			this.setWalking(false);
		}
		

		
		if(Game.engine.getGPhysics().collisionTile(level, xPos, yPos, xAxis, yAxis, this)){
			setAlive(false);
			try {
				Game.engine.playSound("bang");
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		
		Entity temp = Game.engine.getGPhysics().collisionEnity(this, xAxis, yAxis, level.getEntities());
		if(Game.engine.getGPhysics().collisionEnity(this, xAxis, yAxis, level.getEntities()) != null){
			if(temp instanceof Goal){
				this.setFoundGoal(true);
			}
		}
		
		if(Game.engine.getGPhysics().collisionEnity(this, xAxis, yAxis, level.getEntities()) != null){
			if(temp instanceof Ufo){
				this.setAlive(false);
				try {
					Game.engine.playSound("bang");
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
