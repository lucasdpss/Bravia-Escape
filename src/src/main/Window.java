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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bravia.Bravia;
import cells.Cell;
import cells.Color;
import enemy.Enemy;
import map.GameBuilder;
import map.IGameCreator;
import map.Map;


public class Window {
	private JFrame frame;
	private PanelWindow panel;
	private Image backgroundImage;
	private Image shadowImage;
	private Sound backgroundSound;
	private Map map;
	private Bravia bravia;
	private int XOrigem, YOrigem; //usados para centralizar o grid na tela
	private Image inventoryKeysImage;
	private String levelPath;

	public Window(String levelPath) {
		this.levelPath = levelPath;
		backgroundImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\fundo.png");
		shadowImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\shadow.gif"); 
		backgroundSound = new Sound("assets\\sounds\\Fase1.wav");
		inventoryKeysImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\keys_pocket.png");

		/*** Janela da aplicacao tera um tamanho fixo ***/
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1320, 660);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		/*** Todos os elementos serao posicionados de forma absoluta ***/
		panel = new PanelWindow();
		frame.add(panel);
		
		/*** Cria os elementos do jogo ***/
		IGameCreator gameCreator = new GameBuilder(this,levelPath);
		map = gameCreator.getMap();
		bravia = gameCreator.getBravia();
		
		/*** Calcula a origem a partir do tamanho do mapa***/
		YOrigem = (660 - map.getMapHeight()*32)/2;
		XOrigem = (1320 - map.getMapWidth()*32)/2;       //cada quadrado tem 32 pixels

		frame.setVisible(true);
		backgroundSound.playContinuously();
		
	}
	
	public void nextWindow() {  //deve seguir o padrao "stages//levelX.csv"
		int currentLevel = levelPath.charAt(13) - '0';
		File f = new File("stages");
		int numberOfStages = f.list().length;
		if(currentLevel >= numberOfStages) {
			backgroundSound.stop();
			frame.dispose(); // fechar a janela atual
			new Menu();
			return;
		}
		String nextLevel = "stages//level" + String.valueOf(currentLevel + 1) + ".csv";
		backgroundSound.stop();
		frame.dispose(); // fechar a janela atual
		new Window(nextLevel);
	}

	/*** Classe da Panel usada em toda a extensao da janela ***/
	private class PanelWindow extends JPanel{ 
		private static final long serialVersionUID = 280911429679355275L;

		public PanelWindow() {
			this.setLayout(null);
			this.add(Sound.getSoundButton(backgroundSound));
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
				Image keyImage = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\key_" + color.name().toLowerCase() + ".png");
				if(bravia.hasKey(color)) g2d.drawImage(keyImage,XOrigem + map.getMapWidth()*32 + 64 + offset,YOrigem + 32,this);
				offset += 32;
			}
			
			/*** Desenhar Bravia ***/
			g2d.drawImage(bravia.getImage(),XOrigem+bravia.getJPos()*32,YOrigem+bravia.getIPos()*32,this);

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

			}

			SwingUtilities.updateComponentTreeUI(frame);
			panel.repaint();
		}
	}
}
