

import Engine.entity.Entity;
import Engine.graphics.Render;
import Engine.graphics.Sprite;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Goal extends Entity{

	public Goal(Sprite sprite, int xPos, int yPos){
		super(sprite, xPos, yPos);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Render render) {
		render.renderEntity((xPos - getSpriteSize()/2), (yPos - getSpriteSize()/2), this);
	}

}
