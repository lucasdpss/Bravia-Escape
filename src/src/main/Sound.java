package main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
	private final float DEFAULT_VOLUME = 0.7f;
	private static boolean mutedGame = false;
	private static JButton soundButton;
	private static ArrayList<Sound> arraySounds = new ArrayList<Sound>(); 

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
				setSoundButton();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro no playsound");
			}
		}else {
			System.out.println("Nao encontrou arquivo de audio");
		}
	}

	public static void addSoundToArray(Sound sound) {
		arraySounds.add(sound);
	}

	public static void clearSoundArray() {
		for(Sound s : arraySounds) {
			s.stop();
		}
		arraySounds.clear();
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
		clip.setFramePosition(0);
		clip.stop();
		clip.flush();
	}

	public void mute() {
		muteControl.setValue(true);
		clip.flush();
	}

	public void unmute() {
		muteControl.setValue(false);
		clip.flush();
	}

	public static void setMutedGame(boolean muted) {
		mutedGame = muted;
	}

	public static boolean getMutedGame() {
		return mutedGame;
	}

	private static void setSoundButton() {
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
					for(Sound s : arraySounds) {
						s.unmute();
					}
					Sound.setMutedGame(false);
					buttonSound.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"speaker.png")));
				}else {
					for(Sound s : arraySounds) {
						s.mute();
					}
					Sound.setMutedGame(true);
					buttonSound.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"mute.png")));
				}
			}
		});

		soundButton = buttonSound;
	}

	public static JButton getSoundButton() {
		return soundButton;
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
