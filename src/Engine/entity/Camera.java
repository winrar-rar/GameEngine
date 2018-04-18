package Engine.entity;

import Engine.graphics.Render;
import Engine.graphics.Sprite;
import IOHandler.InputHandler;

/**
 * Basic abstract camera class.
 * @author Christian Andersson.
 *
 */
public abstract class Camera extends MobileEntity{

	protected InputHandler input;

	/**
	 * Construcor for the camera.
	 * @param xPos the x-position of the entity.
	 * @param yPos the y-position of the entity.
	 * @param speed the movement speed.
	 * @param input the inputhandler.
	 */
	public Camera(Sprite sprite, int xPos, int yPos, int speed, InputHandler input) {
		super(sprite, xPos, yPos, speed);
		this.input = input;
	}

	/**
	 * Abstract method for updating entities.
	 */
	public abstract void update();
	
}
