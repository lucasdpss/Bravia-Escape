package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuInicial {
	private JFrame frame;
	private Image background;
	private Sound backgroundSound;

	public MenuInicial() {
		ImageIcon image = new ImageIcon("resources\\graphics\\menu.png");
		backgroundSound = new Sound("resources\\sounds\\MenuMusic.wav");
		background = image.getImage();
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
		button1.setBounds(420, 200, 100, 70);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inicio da fase 1");
				backgroundSound.stop();
				frame.dispose(); // fechar o menu
				new Window("resources//stages//testmap.csv");
				JOptionPane.showMessageDialog(null, "Use w, a, s, d ou setas para se mover. Use 1 e 2 para itens");
			}
		});

		JButton button2 = new JButton("Sair");
		button2.setBounds(420, 200 + 80, 100, 70);
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
		private static final long serialVersionUID = 3L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // fill background
			setBackground(Color.lightGray); // set its background color

			Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(background, 0, 0, null);

		}
	}
}
