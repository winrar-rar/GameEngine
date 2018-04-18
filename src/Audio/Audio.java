package Audio;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class Audio {

	private Clip clip;
	
	/**
	 * Constructor that takes a String variable path to the audiofile and converts it into a Clip for playing.
	 * @param path the path.
	 */
	public Audio(String path){
		try {
			AudioInputStream audio_input = AudioSystem.getAudioInputStream(getClass().getResource(path));
			
			AudioFormat baseFormat = audio_input.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
			
			AudioInputStream decoded_input = AudioSystem.getAudioInputStream(decodeFormat, audio_input);
			
			clip = AudioSystem.getClip();
			clip.open(decoded_input);
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Play the Clip as long as it is not null or already running.
	 */
	public void play(){
		if(clip == null){
			return;
		}
		
		if(clip.isRunning()){
			return;
		}
		
		stop();
		
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop(){
		if(clip == null){
			return;
		}
		
		if(clip.isRunning()){
			return;
		}
		
		clip.setFramePosition(0);
		clip.loop(clip.LOOP_CONTINUOUSLY);

	}
	
	/**
	 * Stop the Clip if it is running.
	 */
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	
	/**
	 * First stop playing the Clip and then close it so that it has been cleared.
	 */
	public void close(){
		stop();
		clip.close();
	}
	
	
}
