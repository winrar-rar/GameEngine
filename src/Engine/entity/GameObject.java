package Engine.entity;

import Engine.graphics.Sprite;
import Engine.level.Level;

/**
 * The base class for the other GameObjects.
 * @author Christian Andersson.
 *
 */
public abstract class GameObject{
	
	public GameObject(Sprite sprite){
		this.sprite = sprite;
	}
	
	protected Sprite sprite;
	
	/**
	 * Return the sprite of the SpriteObject.
	 * @return the sprite.
	 */
	public Sprite getSprite(){
		return sprite;
	}
	
	/**
	 * Return the size of the sprite
	 * @return the sprites size.
	 */
	public int getSpriteSize(){
			return sprite.SIZE;
	}

	
	
}
