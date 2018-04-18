package Engine.level;


import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Engine.entity.Camera;
import Engine.entity.Entity;
import Engine.graphics.Render;
import Engine.level.tile.Tile;
import IOHandler.InputHandler;

/**
 * 
 * @author Christian Andersson.
 *
 */
public abstract class Level{
	
	public int width;
	public int height;
	protected int[] tiles;
	
	protected List<Entity> entities = new ArrayList<Entity>(); 

	protected Camera camera;

	/**
	 * Constructor to load a level from a file.
	 * @param path where the level is located.
	 */
	public Level(String path){
		loadLevel(path);
		
	}
	
	/**
	 * updates the level.
	 */
	public void update(){	
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}

		levelEvent();
	}
	
	/**
	 * If something should happen in the level.
	 */
	protected abstract void levelEvent();
	

	/**
	 * The render region on the screen.
	 * @param xScroll where the map is at.
	 * @param yScroll where the map is at.
	 * @param render draws to the monitor.
	 */
//	public void render(int xScroll, int yScroll, Render render){
//		render.setOffset(xScroll, yScroll);
//		int x0 = xScroll >> render.SPRITE_SIZE_CALC;
//		int x1 = (xScroll + render.width + render.SPRITE_SIZE) >> render.SPRITE_SIZE_CALC;
//		int y0 = yScroll >> render.SPRITE_SIZE_CALC;
//		int y1 = (yScroll + render.height + render.SPRITE_SIZE) >> render.SPRITE_SIZE_CALC;
//		
//		for(int y = y0; y < y1; y++){
//			for(int x = x0; x < x1; x++){
//				getTile(x, y).render(x, y, render);
//			}
//		}
//		
//		for(int i = 0; i < entities.size(); i++){
//			entities.get(i).render(render);
//		}
//	}
	
	public void render(Render render){
		
		int xScroll = camera.getXPos() - render.width/2;
		int yScroll = camera.getYPos() - render.height/2;
		
		if(yScroll < 0){
			yScroll = 0;
		}
		if(yScroll > (height * render.SPRITE_SIZE) - render.height){
			yScroll = (height * render.SPRITE_SIZE) - render.height;
		}
		if(xScroll < 0){
			xScroll = 0;
		}
		if(xScroll > (width * render.SPRITE_SIZE) - render.width){
			xScroll = (width * render.SPRITE_SIZE) - render.width;
		}	
			
		render.setOffset(xScroll, yScroll);
		int x0 = xScroll >> render.SPRITE_SIZE_CALC;
		int x1 = (xScroll + render.width + render.SPRITE_SIZE) >> render.SPRITE_SIZE_CALC;
		int y0 = yScroll >> render.SPRITE_SIZE_CALC;
		int y1 = (yScroll + render.height + render.SPRITE_SIZE) >> render.SPRITE_SIZE_CALC;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, render);
			}
		}
		
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(render);
		}

	}
	
	/**
	 * Loads the level.
	 * @param path where the level is located.
	 */
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int imageWidth = width = image.getWidth();
			int imageHeight = height = image.getHeight();
			tiles = new int[imageWidth * imageHeight];
			image.getRGB(0, 0, imageWidth, imageHeight, tiles, 0, imageWidth); // loading image, calculate the height & width and converting the image to an array of pixels witch tell what colour every pixel has in
																		// that image.
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem with loading the level file");
		}

	}


	/**
	 * 
	 * @param x location.
	 * @param y location.
	 * @return returns a tile.
	 */
	public abstract Tile getTile(int x, int y);

	/**
	 * Add entity to the List entities.
	 * @param entity the entity that will be added to the list.
	 */
	public void add(Entity entity){
		if(entity != null)
			entity.init(this);
		
		entities.add(entity);
	}

	
	/**
	 * Remove entity from the List entities
	 * @param entity that will be removed.
	 */
	public void remove(Entity entity){
		entities.remove(entity);
	}
	
	/**
	 * Returns the List entities.
	 * @return the list.
	 */
	public List<Entity> getEntities(){
		return entities;
	}
	
	
	/**
	 * sets the camera and adds it to the level.
	 * @param camera the camera.
	 */
	public void setCamera(Camera camera){
		this.camera = camera;
		add(camera);
	}
	
	/**
	 * Returns the camera
	 * @return the camera
	 */
	public Camera getCamera(){
		return camera;
	}

	
}
