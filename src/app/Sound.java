package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;
	private ArrayList<URL> soundUrl = new ArrayList<URL>();
	
	public Sound() {
		InputStream is = getClass().getResourceAsStream("/sound/sound.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line = br.readLine();
			while (line != null)
            {
            	soundUrl.add(getClass().getResource("/sound/" + line + ".wav"));
                line = br.readLine();
            }
            br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFile(int index) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl.get(index));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}
	
}
