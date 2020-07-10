package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu {
	private JFrame frame;
	private Sound backgroundSound;
	private MenuPanel menuPanel;
	private InstructionsPanel instructionsPanel;

	public Menu() {
		/*** Configuracao do som ***/
		Sound.clearSoundArray();
		backgroundSound = new Sound("assets" + File.separatorChar + "sounds" + File.separatorChar + "MenuMusic.wav");
		Sound.addSoundToArray(backgroundSound);
		
		/*** Configuracao da janela ***/
		frame = new JFrame("Bravia Escape");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1320, 660);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		instructionsPanel = new InstructionsPanel();
		menuPanel = new MenuPanel();
		frame.add(menuPanel);
		menuPanel.requestFocus();

		frame.setVisible(true);
		backgroundSound.playContinuously();
	}
	
	public Menu(JFrame frameReceived) {
		/*** Configuracao do som ***/
		Sound.clearSoundArray();
		backgroundSound = new Sound("assets" + File.separatorChar + "sounds" + File.separatorChar + "MenuMusic.wav");
		Sound.addSoundToArray(backgroundSound);
		
		frame = frameReceived;
		
		instructionsPanel = new InstructionsPanel();
		menuPanel = new MenuPanel();
		frame.add(menuPanel);
		menuPanel.requestFocus();

		frame.setVisible(true);
		backgroundSound.playContinuously();
	}

	private class MenuPanel extends JPanel { 
		private static final long serialVersionUID = 1715984377894766740L;
		private Image backgroundImage;
		
		public MenuPanel() {
			super();
			setLayout(null);
			backgroundImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"menu.png");
			
			JButton buttonPlay = new JButton("Jogar");
			buttonPlay.setBounds(620, 300, 100, 50);
			buttonPlay.setFocusable(false);
			buttonPlay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.remove(menuPanel);
					frame.add(instructionsPanel);
					SwingUtilities.updateComponentTreeUI(frame);
				}
			});

			JButton buttonExit = new JButton("Sair");
			buttonExit.setBounds(620, 300 + 70, 100, 50);
			buttonExit.setFocusable(false);
			buttonExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			add(Sound.getSoundButton());
			add(buttonPlay);
			add(buttonExit);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(backgroundImage, 0, 0, this);

		}
	}
	
	private class InstructionsPanel extends JPanel {
		private static final long serialVersionUID = 1715984377894766740L;
		
		private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"instructions.png");
		private Image wasdImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"wasd.png");
		private Image arrowsImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"arrows.png");
		private Image gateLockedImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"gate_blue_locked.png");
		private Image gateUnlockedImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"gate_blue_unlocked.png");
		private Image keyImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"key_blue.png");
		private Image enemyImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"enemy.gif");
		private Image bonfireUnlitImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"bonfire_unlit_tile.png");
		private Image bonfireLitImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"bonfire_lit_tile.gif");
		private Image exitImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"exit_tile.png");
		
		public InstructionsPanel() {
			super();
			setLayout(null);
			
			JButton buttonContinue = new JButton("Continuar");
			buttonContinue.setBounds(620, 540, 115, 50);
			buttonContinue.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					backgroundSound.stop();
					frame.remove(instructionsPanel);
					new Window(frame, "stages//level1.csv");
				}
			});
			add(buttonContinue);
			add(Sound.getSoundButton());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(backgroundImage, 0, 0, this);
			
			graficos.drawImage(wasdImage, 590, 64, this);
			graficos.drawImage(arrowsImage, 454, 64, this);
			
			graficos.drawImage(gateLockedImage, 490, 200, this);
			graficos.drawImage(keyImage, 590, 200, this);
			graficos.drawImage(gateUnlockedImage, 700, 200, this);
			
			graficos.drawImage(enemyImage, 630, 280, this);
			
			graficos.drawImage(bonfireUnlitImage, 530, 388, this);
			graficos.drawImage(bonfireLitImage, 650, 388, this);
			
			graficos.drawImage(exitImage, 720, 465, this);
		}
	}
}
