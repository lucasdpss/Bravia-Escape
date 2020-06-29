package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Sound {
	private Clip clip;
	private String musicLocation;
	
	public Sound(String musicLocation) {
		this.musicLocation = musicLocation;
	}
	
	public void playContinuously() {
		try {
			File musicPath = new File(musicLocation);

			if(musicPath.exists()) {
				
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			}else {
				System.out.println("Nao encontrou arquivo de audio");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no playsound");
		}
	}
	
	public void playOnce() {
		try {
			File musicPath = new File(musicLocation);

			if(musicPath.exists()) {

				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(1);

			}else {
				System.out.println("Nao encontrou arquivo de audio");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no playsound");
		}
	}
	
	public void stop() {
		clip.stop();
	}
}
