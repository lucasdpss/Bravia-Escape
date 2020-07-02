package main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Sound {
	private Clip clip;
	private FloatControl gainControl;
	private BooleanControl muteControl;
	private final float DEFAULT_VOLUME = 0.8f;
	private static boolean mutedGame = false;
	
	public Sound(String musicLocation) {
		File musicPath = new File(musicLocation);
		AudioInputStream audioInput;
		if(musicPath.exists()) {
			try {
				audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				
				/*** Configuracao de volume ***/
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float range = gainControl.getMaximum() - gainControl.getMinimum();
				float gain = (range * DEFAULT_VOLUME) + gainControl.getMinimum();
				gainControl.setValue(gain);
				
				muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro no playsound");
			}
		}else {
			System.out.println("Nao encontrou arquivo de audio");
		}
	}
	
	public void playContinuously() {
		if(mutedGame) mute();
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void playOnce() {
		if(mutedGame) mute();
		clip.start();
		clip.loop(1);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void mute() {
		muteControl.setValue(true);
	}
	
	public void unmute() {
		muteControl.setValue(false);
	}
	
	public static void setMutedGame(boolean muted) {
		mutedGame = muted;
	}
	
	public static boolean getMutedGame() {
		return mutedGame;
	}
	
	public static JButton getSoundButton(Sound backgroundSound) {
		ImageIcon speakerIcon;
		if(Sound.getMutedGame()) speakerIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"mute.png"));
		else speakerIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"speaker.png"));
		JButton buttonSound = new JButton(speakerIcon);
		buttonSound.setBounds(0, 580, 70, 50);
		buttonSound.setBorder(BorderFactory.createEmptyBorder());
		buttonSound.setContentAreaFilled(false);
		buttonSound.setFocusable(false);
		buttonSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Sound.getMutedGame()) {
					backgroundSound.unmute();
					Sound.setMutedGame(false);
					buttonSound.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"speaker.png")));
				}else {
					backgroundSound.mute();
					Sound.setMutedGame(true);
					buttonSound.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"mute.png")));
				}
			}
		});
		
		return buttonSound;
	}
	
	public void setVolume(float volume) {
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * volume) + gainControl.getMinimum();
		gainControl.setValue(gain);
	}
	
	public void resetVolume() {
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * DEFAULT_VOLUME) + gainControl.getMinimum();
		gainControl.setValue(gain);
	}
}
