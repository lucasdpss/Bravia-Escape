package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import enemy.*;
import bravia.Bravia;
import cells.Cell;
import cells.Color;
import map.Map;
import map.MapGenerator;


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
	private Image inventoryItemsImage;

	public Window(String levelPath) {
		backgroundImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\fundo1.png");
		shadowImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\shadow.gif"); 
		backgroundSound = new Sound("resources\\sounds\\Fase1.wav");
		inventoryKeysImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\keys_pocket.png");
		inventoryItemsImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\items_pocket.png");

		/*** Janela da aplicacao tera um tamanho fixo ***/
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		/*** Todos os elementos serao posicionados de forma absoluta ***/
		panel = new PanelWindow();
		panel.setLayout(null);
		frame.add(panel);
		
		MapGenerator mapGenerator = new MapGenerator(levelPath);
		map = mapGenerator.generateMap();
		bravia = map.getBravia();
		
		/*** Calcula a origem a partir do tamanho do mapa***/
		YOrigem = (500 - map.getMapHeight()*32)/2 - 10;
		XOrigem = (1000 - map.getMapWidth()*32)/2 - 100;       //cada quadrado tem 32 pixels

		frame.setVisible(true);
		backgroundSound.playContinuously();
		
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
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(backgroundImage, 0, 0, this);
			
			Cell[][] mapCell = map.getMapCell();
			Enemy[][] mapEnemy = map.getMapEnemy();
			
			map.clearLights();
			map.illuminate(bravia.getRange(), bravia.getIPos(), bravia.getJPos());

			for(int i=0;i < map.getMapHeight();i++) {
				for(int j=0;j < map.getMapWidth();j++) {
					Cell cell = mapCell[i][j];
					Enemy enemy = mapEnemy[i][j];
					
					if(cell.isPermanentlyLit() || cell.isLit()) {                      
						g2d.drawImage(cell.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}else {
						g2d.drawImage(shadowImage, XOrigem + j*32, YOrigem + i*32, this);
						//g.fillRect(XOrigem + j*32, YOrigem + i*32,32,32);       //quadrado escuro
					}
					
					if(enemy != null && enemy.isLit()) {
						g2d.drawImage(enemy.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}
				}
			}
			
			g2d.drawImage(inventoryKeysImage,XOrigem + map.getMapWidth()*32 + 64,YOrigem,this);
			int offset = 0;
			for(Color color : Color.values()) {
				Image keyImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\key_" + color.name().toLowerCase() + ".png");
				if(bravia.hasKey(color)) g2d.drawImage(keyImage,XOrigem + map.getMapWidth()*32 + 64 + offset,YOrigem + 32,this);
				offset += 32;
			}
			
			g2d.drawImage(inventoryItemsImage,XOrigem + map.getMapWidth()*32 + 64,YOrigem + 96,this);
			//falta printar os itens
			
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

			}else if(e.getKeyCode() == KeyEvent.VK_1) {
				System.out.println("li 1");

			}else if(e.getKeyCode() == KeyEvent.VK_2) {
				System.out.println("li 2");

			}

			SwingUtilities.updateComponentTreeUI(frame);
			panel.repaint();
		}
	}
}
