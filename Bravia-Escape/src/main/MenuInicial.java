package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MenuInicial {
	private JFrame frame;
	private Image fundo;
	private Clip clip;
	
	public MenuInicial() {
		ImageIcon referencia = new ImageIcon("resources\\menu.png");
		fundo = referencia.getImage();
	}
	
	public void show() {

        frame = new JFrame("Menu Bravia Escape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500); 
        frame.setResizable(false);  
        frame.setLocationRelativeTo(null);
        
        MeuPainelMenu painel = new MeuPainelMenu();
        painel.setLayout(null);
        frame.add(painel);
        
        JButton botao1 = new JButton("Jogar");
        botao1.setBounds(420, 200, 100,70);
        botao1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inicio da fase 1");
				//clip.stop(); //parar a musica
				//frame.dispose(); //fechar o menu
			}
		});
        
        
        JButton botao2 = new JButton("Sair");
        botao2.setBounds(420, 200 + 80, 100,70);
        botao2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        painel.add(botao1);
        painel.add(botao2);
        
        frame.setVisible(true);
        playSound("sounds//MenuMusic.wav");

    }
	
	private class MeuPainelMenu extends JPanel{  //criado para sobrecarregar paintComponent() e colocar fundo
		private static final long serialVersionUID = 3L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);    // fill background
	        setBackground(Color.lightGray); // set its background color
	        
	        Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(fundo, 0, 0, null);
			
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
