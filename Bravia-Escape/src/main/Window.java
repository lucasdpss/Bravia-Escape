package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bravia.Bravia;
import map.*;


public class Window {
	private JFrame frame;
	private PanelWindow panel;
	private Image background;
	private Clip clip;
	private Map map;
	private Bravia bravia;

	public Window(String levelPath) {
		ImageIcon image = new ImageIcon("resources\\fundo1.png");
		background = image.getImage();

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		panel = new PanelWindow();
		panel.setLayout(null);
		frame.add(panel);
		
		MapGenerator mapGenerator = new MapGenerator(levelPath);
		map = mapGenerator.generateMap();
		bravia = new Bravia(map, map.getIBravia(), map.getJBravia());

		frame.setVisible(true);
		playSound("sounds//Fase1.wav");
	}

	/*** Classe da Panel usada em toda a extensao da janela ***/
	private class PanelWindow extends JPanel{ 
		private static final long serialVersionUID = 280911429679355275L;

		public PanelWindow() {
			setFocusable(true);
			setDoubleBuffered(true);
			addKeyListener(new UserAdapter());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); 
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(background, 0, 0, this);

			g.setColor(Color.DARK_GRAY);

			for(int i=0;i < 9;i++) {
				for(int j=0;j < 9;j++) {
					g.drawRect(30 + i*30, 30 + j*30, 30, 30);
				}
			}

		}
	}

	/*** Classe para ler a entrada do usuario ***/
	private class UserAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
				System.out.println("li up");

			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				System.out.println("li right");

			}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				System.out.println("li left");

			}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				System.out.println("li down");

			}else if(e.getKeyCode() == KeyEvent.VK_1) {
				System.out.println("li 1");

			}else if(e.getKeyCode() == KeyEvent.VK_2) {
				System.out.println("li 2");

			}

			SwingUtilities.updateComponentTreeUI(frame);
			panel.repaint();
		}
	}
 
	
	public void playSound(String musicLocation) {

		try {
			File musicPath = new File(musicLocation);

			if(musicPath.exists()) {

				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			}else {
				System.out.println("nao achou arquivo de audio");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "erro no playsound");
		}
	}
}
