package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;


public class Sound {
	private Clip clip;
	
	public Sound(String musicLocation) {
		File musicPath = new File(musicLocation);
		AudioInputStream audioInput;
		if(musicPath.exists()) {
			try {
				audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				
				/*** Configuracao de volume ***/
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float range = gainControl.getMaximum() - gainControl.getMinimum();
				float gain = (range * 0.7f) + gainControl.getMinimum();
				gainControl.setValue(gain);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro no playsound");
			}
		}else {
			System.out.println("Nao encontrou arquivo de audio");
		}
	}
	
	public void playContinuously() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void playOnce() {
		clip.start();
		clip.loop(1);
	}
	
	public void stop() {
		clip.stop();
	}
}
