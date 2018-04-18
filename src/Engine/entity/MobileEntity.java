package Engine.entity;

import Engine.Engine;
import Engine.graphics.Render;
import Engine.graphics.Sprite;

/**
 * The Entity class for entities that is movable.
 * @author Christian
 *
 */
public abstract class MobileEntity extends Entity{

	protected static final int DIR_NORTH = 0;
	protected static final int DIR_EAST = 1;
	protected static final int DIR_SOUTH = 2;
	protected static final int DIR_WEST = 3;
	
	protected boolean falling = true;
	protected boolean walking = false;
	protected boolean jumping = false;
	protected boolean alive = true;
	protected boolean foundGoal = false;

	protected int direction = -1;
	protected int speed;
	protected int xAxis = 0, yAxis = 0;
	
	/**
	 * Constructor for MobileEntity-
	 * @param xPos the x-position for the entity.
	 * @param yPos the y-position for the entity.
	 * @param speed the movement speed.
	 */
	public MobileEntity(Sprite sprite, int xPos, int yPos, int speed) {
		super(sprite, xPos, yPos);
		this.speed = speed;
	}
	
	/**
	 * move method for all moving entities.
	 * @param xAxis 
	 * @param yAxis
	 */
	public void move(int xAxis, int yAxis) {
		if(xAxis != 0 && yAxis != 0){
			move(xAxis, 0);
			move(0, yAxis);
			return;
		}
		
		if(xAxis > 0) direction = DIR_EAST;
		if(xAxis < 0) direction = DIR_WEST;
		if(yAxis > 0) direction = DIR_SOUTH;
		if(yAxis < 0) direction = DIR_NORTH;
		
		if (!Engine.getGPhysics().collisionTile(level, xPos, yPos, xAxis, yAxis, this) && (Engine.getGPhysics().collisionEnity(this, xAxis, yAxis, level.getEntities())== null)) {
			xPos += xAxis * speed;
			yPos += yAxis * speed;
		}

	}

	
	/**
	 * Set if the entity is jumping or not.
	 * @param walking the boolean.
	 */
	public void setFalling(boolean falling){
		this.falling = falling;
	}
	
	/**
	 * Return boolean if the entity is jumping. 
	 * @return if the boolean is true or false.
	 */
	public boolean isFalling(){
		return falling;
	}
	
	/**
	 * Set if the entity is jumping or not.
	 * @param walking the boolean.
	 */
	public void setJumping(boolean jumping){
		this.jumping = jumping;
	}
	
	/**
	 * Return boolean if the entity is jumping. 
	 * @return if the boolean is true or false.
	 */
	public boolean isJumping(){
		return jumping;
	}
	
	/**
	 * Set if the entity is walking or not.
	 * @param walking the boolean.
	 */
	public void setWalking(boolean walking){
		this.walking = walking;
	}
	
	/**
	 * Return boolean if the entity is walking. 
	 * @return if the boolean is true or false.
	 */
	public boolean isWalking(){
		return walking;
	}
	
	/**
	 * Set if the entity is alive or not. 
	 * @param alive
	 */
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	/**
	 * Return boolean if the entity is alive or not.
	 * @return the boolean.
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * This is a method mostly for the player but, other entities can use it if they have found their goal, whatever that is.
	 * * @param foundGoal if the boolean is true or not.
	 */
	public void setFoundGoal(boolean foundGoal){
		this.foundGoal = foundGoal;
	}
	
	/**
	 * if the Entity has found the goal or not.
	 * @return the boolean
	 */
	public boolean FoundGoal(){
		return foundGoal;
	}
	

	/**
	 * Abstract method for updating entities.
	 */
	public abstract void update();

	/**
	 * Method for rendering mobile Entities.
	 */
	public void render(Render render){
		render.renderEntity((xPos - getSpriteSize()/2), (yPos - getSpriteSize()/2), this);
	}

}
