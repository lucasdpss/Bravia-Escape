package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bravia.Bravia;
import cells.Cell;
import cells.Color;
import enemy.Enemy;
import exceptions.InvalidMapGen;
import map.GameBuilder;
import map.IGameCreator;
import map.Map;


public class Window implements ILevelController{
	private JFrame frame;
	private PanelWindow panel;
	private Image backgroundImage;
	private Image shadowImage;
	private Image backgroundPauseImage;
	private Image pauseImage;
	private Sound backgroundSound;
	private Map map;
	private Bravia bravia;
	private int XOrigem, YOrigem; //usados para centralizar o grid na tela
	private Image inventoryKeysImage;
	private String levelPath;
	private PanelPause panelPause;
	private Sound backgroundMenuSound;

	public Window(JFrame frameReceived, String levelPath) {
		this.levelPath = levelPath;
		backgroundImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"fundo.png");
		pauseImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"pauseMenu.png");
		backgroundPauseImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"fundoPause.png");
		shadowImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"shadow.gif"); 
		inventoryKeysImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"keys_pocket.png");

		/*** Configuracao do som***/
		Sound.clearSoundArray();
		backgroundSound = new Sound("assets"+File.separatorChar+"sounds"+File.separatorChar+"Fase1.wav");
		backgroundMenuSound = new Sound("assets"+File.separatorChar+"sounds"+File.separatorChar+"PauseMenu.wav");
		Sound.addSoundToArray(backgroundMenuSound);

		/*** Janela da aplicacao tera um tamanho fixo ***/
		frame = frameReceived;

		/*** Todos os elementos serao posicionados de forma absoluta ***/
		panel = new PanelWindow();
		panelPause = new PanelPause();
		frame.add(panel);
		panel.requestFocus();

		/*** Cria os elementos do jogo ***/
		IGameCreator gameCreator;
		try {
			gameCreator = new GameBuilder(this,levelPath);
			map = gameCreator.getMap();
			bravia = gameCreator.getBravia();

			/*** Calcula a origem do sistema ***/
			YOrigem = (660 - map.getMapHeight()*32)/2 - 14;
			XOrigem = (1320 - map.getMapWidth()*32)/2 - 120;       //cada quadrado tem 32 pixels

			frame.setVisible(true);
			backgroundSound.playContinuously();
		} catch (InvalidMapGen error) {
			error.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro no " + levelPath);
			frame.dispose();
			new Menu();
		}
	}

	public void nextWindow() {  //deve seguir o padrao "stages//levelX.csv"
		int currentLevel = levelPath.charAt(13) - '0';
		File f = new File("stages");
		int numberOfStages = f.list().length;
		if(currentLevel >= numberOfStages) {
			backgroundSound.stop();
			frame.remove(panel);
			new Menu(frame);
			return;
		}
		String nextLevel = "stages//level" + String.valueOf(currentLevel + 1) + ".csv";
		backgroundSound.stop();
		frame.remove(panel);
		new Window(frame,nextLevel);
	}

	/*** Classe da Panel usada em toda a extensao da janela ***/
	private class PanelWindow extends JPanel{ 
		private static final long serialVersionUID = 280911429679355275L;

		public PanelWindow() {
			this.setLayout(null);
			this.setFocusable(true);
			this.setDoubleBuffered(true);
			this.addKeyListener(new UserAdapter());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); 

			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(backgroundImage, 0, 0, this);

			Cell[][] mapCell = map.getMapCell();
			Enemy[][] mapEnemy = map.getMapEnemy();

			/*** Iluminacao ***/
			map.clearLights();
			map.illuminate(bravia.getRange(), bravia.getIPos(), bravia.getJPos());
			for(Enemy enemy : map.listEnemies()) {
				map.illuminate(enemy.getLightRange(), enemy.getIPos(), enemy.getJPos());
			}

			/*** Desenhar cells e enemies iluminados ***/
			for(int i=0;i < map.getMapHeight();i++) {
				for(int j=0;j < map.getMapWidth();j++) {
					Cell cell = mapCell[i][j];
					Enemy enemy = mapEnemy[i][j];

					if(cell.isPermanentlyLit() || cell.isLit()) {                      
						g2d.drawImage(cell.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}else {
						g2d.drawImage(shadowImage, XOrigem + j*32, YOrigem + i*32, this);
					}

					if(enemy != null && enemy.isLit()) {
						g2d.drawImage(enemy.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}
				}
			}

			/*** Desenhar inventario ***/
			g2d.drawImage(inventoryKeysImage,XOrigem + map.getMapWidth()*32 + 64,YOrigem,this);
			int offset = 0;
			for(Color color : Color.values()) {
				Image keyImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"key_" + color.name().toLowerCase() + ".png");
				if(bravia.hasKey(color)) g2d.drawImage(keyImage,XOrigem + map.getMapWidth()*32 + 64 + offset,YOrigem + 32,this);
				offset += 32;
			}

			/*** Desenhar Bravia ***/
			g2d.drawImage(bravia.getImage(),XOrigem+bravia.getJPos()*32,YOrigem+bravia.getIPos()*32,this);

		}
	}

	private class PanelPause extends JPanel{
		private static final long serialVersionUID = 825594860440255597L;

		public PanelPause() {
			this.setLayout(null);
			this.add(Sound.getSoundButton());
			this.setFocusable(true);
			this.setDoubleBuffered(true);
			this.addKeyListener(new UserAdapter(){ 
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
						backgroundMenuSound.stop();
						if(!Sound.getMutedGame()) {
							backgroundSound.unmute();
						}
						frame.remove(panelPause);
						frame.add(panel);
						panel.requestFocus();
						SwingUtilities.updateComponentTreeUI(frame);
					}
				}
			});
			
			JButton buttonContinue = new JButton("Continuar");
			buttonContinue.setBounds(620, 300, 100, 50);
			buttonContinue.setFocusable(false);
			buttonContinue.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					backgroundMenuSound.stop();
					if(!Sound.getMutedGame()) {
						backgroundSound.unmute();
					}
					frame.remove(panelPause);
					frame.add(panel);
					panel.requestFocus();
					SwingUtilities.updateComponentTreeUI(frame);
				}
			});
			JButton buttonReset = new JButton("Reiniciar");
			buttonReset.setBounds(620, 300 + 70, 100, 50);
			buttonReset.setFocusable(false);
			buttonReset.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					backgroundSound.stop();
					frame.remove(panelPause);
					new Window(frame,levelPath);
				}
			});
			JButton buttonMenu = new JButton("Menu Principal");
			buttonMenu.setBounds(620, 370 + 70, 100, 50);
			buttonMenu.setFocusable(false);
			buttonMenu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					backgroundSound.stop();
					backgroundMenuSound.stop();
					frame.remove(panelPause);
					new Menu(frame);
				}
			});
			JButton buttonExit = new JButton("Sair");
			buttonExit.setBounds(620, 440 + 70, 100, 50);
			buttonExit.setFocusable(false);
			buttonExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			add(buttonContinue);
			add(buttonReset);
			add(buttonExit);
			add(buttonMenu);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); 

			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(backgroundPauseImage, 0, 0, this);

			Cell[][] mapCell = map.getMapCell();
			Enemy[][] mapEnemy = map.getMapEnemy();

			/*** Iluminacao ***/
			map.clearLights();
			map.illuminate(bravia.getRange(), bravia.getIPos(), bravia.getJPos());
			for(Enemy enemy : map.listEnemies()) {
				map.illuminate(enemy.getLightRange(), enemy.getIPos(), enemy.getJPos());
			}

			/*** Desenhar cells e enemies iluminados ***/
			for(int i=0;i < map.getMapHeight();i++) {
				for(int j=0;j < map.getMapWidth();j++) {
					Cell cell = mapCell[i][j];
					Enemy enemy = mapEnemy[i][j];

					if(cell.isPermanentlyLit() || cell.isLit()) {                      
						g2d.drawImage(cell.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}else {
						g2d.drawImage(shadowImage, XOrigem + j*32, YOrigem + i*32, this);
					}

					if(enemy != null && enemy.isLit()) {
						g2d.drawImage(enemy.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}
				}
			}

			/*** Desenhar inventario ***/
			g2d.drawImage(inventoryKeysImage,XOrigem + map.getMapWidth()*32 + 64,YOrigem,this);
			int offset = 0;
			for(Color color : Color.values()) {
				Image keyImage = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"key_" + color.name().toLowerCase() + ".png");
				if(bravia.hasKey(color)) g2d.drawImage(keyImage,XOrigem + map.getMapWidth()*32 + 64 + offset,YOrigem + 32,this);
				offset += 32;
			}

			/*** Desenhar Bravia ***/
			g2d.drawImage(bravia.getImage(),XOrigem+bravia.getJPos()*32,YOrigem+bravia.getIPos()*32,this);


			/*** Desenhar fundo do menu pause ***/
			g2d.drawImage(pauseImage, 0, 0, this);
		}
	}

	/*** Classe para ler a entrada do usuario ***/
	private class UserAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
				bravia.move('U');
				map.moveEnemies();

			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				bravia.move('R');
				map.moveEnemies();

			}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				bravia.move('L');
				map.moveEnemies();

			}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				bravia.move('D');
				map.moveEnemies();

			}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				frame.remove(panel);
				frame.add(panelPause);
				backgroundMenuSound.playContinuously();
				backgroundSound.mute();
				panelPause.requestFocus();
				SwingUtilities.updateComponentTreeUI(frame);
			}

			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
}
