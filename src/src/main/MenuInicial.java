package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuInicial {
	private JFrame frame;
	private Image backgroundImage;
	private Sound backgroundSound;

	public MenuInicial() {
		backgroundImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\menu.png");
		backgroundSound = new Sound("assets\\sounds\\MenuMusic.wav");
	}

	public void show() {

		frame = new JFrame("Menu Bravia Escape");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		MenuPanel panel = new MenuPanel();
		panel.setLayout(null);
		frame.add(panel);

		JButton button1 = new JButton("Jogar");
		button1.setBounds(460, 200, 70, 50);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inicio da fase 1");
				backgroundSound.stop();
				frame.dispose(); // fechar o menu
				new Window("stages//level1.csv");
				JOptionPane.showMessageDialog(null, "Use w, a, s, d ou setas para se mover.");
			}
		});

		JButton button2 = new JButton("Sair");
		button2.setBounds(460, 200 + 70, 70, 50);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(button1);
		panel.add(button2);

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
}
