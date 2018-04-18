package Audio;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class AudioPlayer {
	private Map<String, Audio> sounds = new HashMap<String, Audio>();
	
	public void addSound(String name, Audio audio){
			sounds.put(name, audio);
	}
	
	public void playSound(String name){
		try{
			sounds.get(name).play();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void playSoundLoop(String name){
		try{
			sounds.get(name).loop();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void stopSound(String name){
		try{
			sounds.get(name).close();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
