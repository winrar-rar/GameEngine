package Engine.physics;

import java.util.List;

import Engine.entity.Entity;
import Engine.level.Level;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class GamePhysics {

	private int gravity;
	private boolean wantGravity = true;

	/**
	 * The collision method between tiles and entities.
	 * @param level the level.
	 * @param xPos the X-position.
	 * @param yPos the Y-Position.
	 * @param xAxis
	 * @param yAxis
	 * @param entity the entity.
	 * @return if solid or not.
	 */
	public boolean collisionTile(Level level, int xPos, int yPos, int xAxis, int yAxis, Entity entity) {
		boolean solid = false;
		
		try{
			int sqrt = (int) Math.sqrt(entity.getSpriteSize());
			
			for (int corner = 0; corner < 4; corner++) {
				// Divide by tile size to get from pixel to tile precision
				int xTile = ((xPos + xAxis) + corner % 2 * (entity.getSpriteSize() - 1) - (entity.getSpriteSize() / 2)) >> sqrt;
				int yTile = ((yPos + yAxis) + corner / 2 * (entity.getSpriteSize() - 1) - (entity.getSpriteSize() / 2)) >> sqrt;
				if (level.getTile(xTile, yTile).solid())
					solid = true;
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
		return solid;
	}


	/**
	 * Turn the gravity on or off depending on need
	 */
	public void useGravity(boolean wantGravity) {
		this.wantGravity = wantGravity;
	}

	/**
	 * Set the gravity variable
	 */
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	/**
	 * Get the gravity variable from this class
	 */
	public int getGravity() {
		if (wantGravity)
			return gravity;

		return 0;
	}

	/**
	 * Is gravity set to be used? Return wantGravity
	 */
	public boolean gameWantGravity() {
		return wantGravity;
	}

	/**
	 * The first collision method, is called outside of the GamePhysics class
	 * from a Entity and only calls the private collision function for every
	 * entity in the game except the one we are basing the collision check on
	 * since we do not want to compare it with itself It returns the result from
	 * the protected collision detection function as a boolean
	 */
//	public boolean collisionEnity(Entity entity, int xVel, int yVel, List<Entity> entities) {
//		for (int i = 0; i < entities.size(); i++) {
//			if (!entity.equals(entities.get(i))) {
//				if (collision(entity, xVel, yVel, entities.get(i))) {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
	
	public Entity collisionEnity(Entity entity, int xVel, int yVel, List<Entity> entities) {
		Entity temp = null;
		
		for (int i = 0; i < entities.size(); i++) {
			if (!entity.equals(entities.get(i))) {
//				if (collision(entity, xVel, yVel, entities.get(i))) {
//					return true;
				
//				}
				temp = collision(entity, xVel, yVel, entities.get(i));
				if(temp != null) break;
			}
		}

		return temp;
	}

	/**
	 * the second collision detection method, does the actual collision
	 * detection, returns a boolean if there has been a collision or not
	 */
//	protected boolean collision(Entity a, int xVel, int yVel, Entity b) {
//		try{
//			int leftA, leftB;
//			int rightA, rightB;
//			int topA, topB;
//			int bottomA, bottomB;
//
//			leftA = a.getXPos() + xVel;
//			leftB = b.getXPos();
//			rightA = a.getXPos() + a.getSpriteSize() + xVel;
//			rightB = b.getXPos() + b.getSpriteSize();
//			topA = a.getYPos() + yVel;
//			topB = b.getYPos();
//			bottomA = a.getYPos() + a.getSpriteSize() + yVel;
//			bottomB = b.getYPos() + b.getSpriteSize();
//		
//			if (bottomA <= topB) {
//				return false;
//			}
//
//			if (topA >= bottomB) {
//				return false;
//			}
//
//			if (rightA <= leftB) {
//				return false;
//			}
//
//			if (leftA >= rightB) {
//				return false;
//			}
//			return true;
//		}catch(NullPointerException e){	
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	protected Entity collision(Entity a, int xVel, int yVel, Entity b) {
		try{
			int leftA, leftB;
			int rightA, rightB;
			int topA, topB;
			int bottomA, bottomB;

			leftA = a.getXPos() + xVel;
			leftB = b.getXPos();
			rightA = a.getXPos() + a.getSpriteSize() + xVel;
			rightB = b.getXPos() + b.getSpriteSize();
			topA = a.getYPos() + yVel;
			topB = b.getYPos();
			bottomA = a.getYPos() + a.getSpriteSize() + yVel;
			bottomB = b.getYPos() + b.getSpriteSize();
		
			if (bottomA <= topB) {
				return null;
			}

			if (topA >= bottomB) {
				return null;
			}

			if (rightA <= leftB) {
				return null;
			}

			if (leftA >= rightB) {
				return null;
			}

		}catch(NullPointerException e){	
			e.printStackTrace();
		}
		return b;
	}

}
