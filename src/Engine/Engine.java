//Collion

package Engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import Audio.Audio;
import Audio.AudioPlayer;
import Engine.entity.Camera;
import Engine.entity.Entity;
import Engine.graphics.Render;
import Engine.level.Level;
import Engine.physics.GamePhysics;
import IOHandler.InputHandler;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int WIDTH = 500;//720;
	private static int HEIGHT = WIDTH / 16 * 9;
	private static int SCALE = 2;
	private static String TITLE;
	private int spriteSize;
	private Camera camera;
	
	private double tick;

	private Render render;
	private InputHandler input;
	
	private AudioPlayer audioPlayer; 
	private static GamePhysics physics;
	
	private Level level;

	private JFrame jframe;

	private BufferedImage view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // creating an image.
	private int[] pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData(); // accessing the image so it can be modified.

	private Thread engineThread;
	private boolean running = false;

	/**
	 * Constructor for the main class.
	 * The other components are added here.
	 */
	public Engine() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);

		jframe = new JFrame();
		jframe.setResizable(false);
		jframe.setTitle(TITLE);
		jframe.add(this);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);

	}

	/**
	 * Starts the game by creating and starting thread and setting running boolean to true.
	 */
	public synchronized void start() {
		physics = new GamePhysics();
		audioPlayer = new AudioPlayer();	
		
		render = new Render(WIDTH, HEIGHT, this.getSpriteSize(spriteSize));
		audioPlayer = new AudioPlayer();
		
		running = true;
		engineThread = new Thread(this, "Engine");
		engineThread.start();
		
	}

	/**
	 * When the game is stopped this is called so the thread dosn't run anymore.
	 */
	public synchronized void stop() {
		running = false;
		try {
			engineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Close the Engine.
	 */
	public synchronized void Close() {
		running = false;
		try {
			engineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Method that tells when the game can render and update.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nanoSec = 1000000000.0 / tick;
		double delta = 0;

		int frames = 0;
		int updates = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSec;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				jframe.setTitle(TITLE + " | FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * Update method that updates the game all components. Is called from the run-method. 
	 */
	public void update() {
		
		if(input != null)
			input.update();
		
		if(level != null){
			level.update();
		}
		
	}

	/**
	 * Renders method so pictures can be seen to the screen. Is called from the run-method.
	 */
	public void render() {
		
		BufferStrategy buffStrat = getBufferStrategy();
		if (buffStrat == null) {
			createBufferStrategy(3);
			return;
		}
		
		render.clear();
		
		if(level != null)
			level.render(render);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = render.pixels[i];
		}

		Graphics graph = buffStrat.getDrawGraphics(); //Create a link between graphics and the buffer.
		graph.drawImage(view, 0, 0, getWidth(), getHeight(), null);
		graph.dispose();
		buffStrat.show();

	}

	/**
	 * Initialise the engine.  
	 */
	public void init(){
		start();
	}
	
	/**
	 * Adds an entity to the level.
	 * @param entity the entity.
	 */
	public void addEntitytoLevel(Entity entity){
		if(level != null)
			level.add(entity);
	}
	
	/**
	 * Remove an entity from the level.
	 * @param entity the entity.
	 */
	public void removeEntityfromLevel(Entity entity){
		level.remove(entity);
	}
	
	/**
	 * Set what inputHandler to use.
	 * @param input the InputHandler.
	 */
	public void setInputHandler(InputHandler input){
		this.input = input;
		addKeyListener(input);
	}
	
	/**
	 * Set the current level.
	 * @param level the level.
	 */
	public void setLevel(Level level){
		this.level = level;
	}
	
	/**
	 * 
	 * @param tick
	 */
	public void setTick(double tick){
		this.tick = tick;
	}
	
	/**
	 * Set the games title.
	 * @param title the title.
	 */
	public void setTitle(String title){
		this.TITLE = title;
	}
	
	/**
	 * Gets the sprite size to the render.
	 * @param size the size of sprites.
	 * @return the size.
	 */
	public int getSpriteSize(int size){
		return size = setSpriteSize(size);
	}
	
	/**
	 * Sets the sprite size.
	 * @param size the sprite size.
	 * @return the sprite size.
	 */
	public int setSpriteSize(int size){
		return this.spriteSize = size;
	}
	
	/**
	 * Sets how big the gravity should be.
	 * @param gravity the gravity.
	 */
	public void setGravity(int gravity){
		physics.setGravity(gravity);
	}
	
	/**
	 * Gives a reference to the GamePhysics to whatever needs it.
	 * @return the reference.
	 */
	public static GamePhysics getGPhysics(){
		return physics;
	}
	
	/**
	 * Sets the camera to the level.
	 * @param camera the camera.
	 */
	public void setCamera(Camera camera){
		this.camera = camera;
		level.setCamera(camera);
	}
	
	/**
	 * Adds a sound.
	 * @param soundName what the sound should be called.
	 * @param audio the audio.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void addSound(String soundName, Audio audio) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioPlayer.addSound(soundName, audio);
	}

	/**
	 * Plays the sound once.
	 * @param soundName the name of the sound.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void playSound(String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioPlayer.playSound(soundName);
	}
	
	/**
	 * Plays the sound and loops it.
	 * @param soundName the name of the sound.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void playSoundLoop(String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioPlayer.playSoundLoop(soundName);
	}
	
	
}
