

import java.util.Random;

import Engine.entity.Entity;
import Engine.entity.MobileEntity;
import Engine.graphics.Render;
import Engine.graphics.Sprite;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Ufo extends MobileEntity{
	
	private int time = 0;
	protected final Random random = new Random();

	public Ufo(Sprite sprite, int xPos, int yPos, int speed) {
		super(sprite, xPos, yPos, speed);
	}
	
	@Override
	public void update() {
		time++;
		if(time % 60 == 0){
			xAxis = random.nextInt(3) -1;
			yAxis = random.nextInt(3) -1;
		}
		
		
		if(yAxis < 0)
			direction = DIR_NORTH;
		if(yAxis > 0)
			direction = DIR_SOUTH;
		if(xAxis < 0)
			direction = DIR_WEST;
		if(xAxis > 0)
			direction = DIR_EAST;
		
		if(xAxis != 0 || yAxis !=0){
			move(xAxis, yAxis);
			
		}
		
	}

}
