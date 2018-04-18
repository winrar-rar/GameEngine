package Engine.entity;

import java.util.Random;

import Engine.graphics.Render;
import Engine.graphics.Sprite;
import Engine.level.Level;

/**
 * Basic entity class that is abstract.
 * @author Christian Andersson.
 *
 */
public abstract class Entity extends GameObject{

	protected int xPos, yPos;
	private boolean removed = false;
	protected Level level;	
	
	public Entity(Sprite sprite, int xPos, int yPos){
		super(sprite);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Abstract method for updating entities.
	 */
	public abstract void update();

	/**
	 * Abstract method for rendering entities.
	 * @param render
	 */
	public abstract void render(Render render);
	
	/**
	 * Remove the entity from level.
	 */
	public void remove(){
		removed = true;
	}
	
	/**
	 * 
	 * @return if removed.
	 */
	public boolean isRemoved(){
		return removed;
	}
	
	/**
	 * 
	 * @param level the level that the entity is in.
	 */
	public void init(Level level){
		this.level = level;
	}
	
	/**
	 * Get the xPos of the GameObject.
	 * @return the xPos.
	 */
	public int getXPos(){
		return xPos;
	}
	
	/**
	 * Get the yPos of the GameObject.
	 * @return the yPos.
	 */
	public int getYPos(){
		return yPos;
	}
	
	
	
	
	
	
	
	//EGET
	
	/**
	 * 
	 * @param xPos the xPos that the entity will start at.
	 * @param yPos the yPos that the entity will start at.
	 */
	public void setPos(int xPos, int yPos){
		xPos = setPosX(xPos);
		yPos = setPosY(yPos);
	}
	
	/**
	 * Sets the xPos to a new value.
	 * @param xPos the argument for the new xPos.
	 * @return returns the new xPos.
	 */
	public int setPosX(int xPos){
		return this.xPos = xPos;
		
	}	
	
	/**
	 * Sets the yPos to a new value.
	 * @param yPos the argument for the new yPos.
	 * @return returns the new yPos.
	 */
	public int setPosY(int yPos){
		return this.yPos = yPos;
	}
	
}
