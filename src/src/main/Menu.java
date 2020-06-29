package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu {
	private JFrame frame;
	private Image backgroundImage;
	private Sound backgroundSound;

	public Menu() {
		backgroundImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\menu.png");
		backgroundSound = new Sound("assets\\sounds\\MenuMusic.wav");
	}

	public void show() {

		frame = new JFrame("Menu Bravia Escape");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1320, 660);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setLayout(null);
		frame.add(menuPanel);
		
		InstructionsPanel instructionsPanel= new InstructionsPanel();
		instructionsPanel.setLayout(null);
		
		JButton buttonContinue = new JButton("Continuar");
		buttonContinue.setBounds(620, 540, 110, 50);
		buttonContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundSound.stop();
				frame.dispose();
				new Window("stages//level1.csv");
			}
		});
		instructionsPanel.add(buttonContinue);

		JButton button1 = new JButton("Jogar");
		button1.setBounds(620, 300, 70, 50);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\instructions.png");
				frame.remove(menuPanel);
				frame.add(instructionsPanel);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		JButton button2 = new JButton("Sair");
		button2.setBounds(620, 300 + 70, 70, 50);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuPanel.add(button1);
		menuPanel.add(button2);

		frame.setVisible(true);
		backgroundSound.playContinuously();

	}

	private class MenuPanel extends JPanel { // criado para sobrecarregar paintComponent() e colocar fundo
		private static final long serialVersionUID = 1715984377894766740L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(backgroundImage, 0, 0, this);

		}
	}
	
	private class InstructionsPanel extends JPanel { // criado para sobrecarregar paintComponent() e colocar fundo
		private static final long serialVersionUID = 1715984377894766740L;
		private Image wasdImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\wasd.png");
		private Image arrowsImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\arrows.png");
		private Image gateLockedImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\gate_blue_locked.png");
		private Image gateUnlockedImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\gate_blue_unlocked.png");
		private Image keyImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\key_blue.png");
		private Image enemyImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\enemy.gif");
		private Image bonfireUnlitImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\bonfire_unlit_tile.png");
		private Image bonfireLitImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\bonfire_lit_tile.gif");
		private Image exitImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\exit_tile.png");

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
