

import Engine.entity.Entity;
import Engine.graphics.Render;
import Engine.graphics.Sprite;

/**
 * 
 * @author Christian Andersson
 *
 */
public class VictoryScreen extends Entity{

	public VictoryScreen(Sprite sprite, int xPos, int yPos) {
		super(sprite, xPos, yPos);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Render render) {
		render.renderSprite(xPos, yPos, sprite);
		
	}

}
