package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bravia.Bravia;
import cells.Cell;
import enemy.Enemy;
import map.Map;
import map.MapGenerator;


public class Window {
	private JFrame frame;
	private PanelWindow panel;
	private Image backgroundImage;
	private Image shadow;
	private Sound backgroundSound;
	private Map map;
	private Bravia bravia;
	private int XOrigem, YOrigem; //usados para centralizar o grid na tela

	public Window(String levelPath) {
		backgroundImage = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\fundo1.png");
		shadow = null; //colocar imagem da sombra
		backgroundSound = new Sound("resources\\sounds\\Fase1.wav");

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
		bravia = new Bravia(map, map.getIBravia(), map.getJBravia());
		
		/*** Calcula a origem a partir do tamanho do mapa***/
		//tratar excecoes aqui
		YOrigem = (500 - map.getMapHeight()*32)/2 - 10;
		XOrigem = (1000 - map.getMapWidth()*32)/2;       //cada quadrado tem 32 pixels

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
			g.setColor(Color.BLACK);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(backgroundImage, 0, 0, this);
			
			Cell[][] mapCell = map.getMapCell();
			Enemy[][] mapEnemy = map.getMapEnemy();

			for(int i=0;i < map.getMapHeight();i++) {
				for(int j=0;j < map.getMapWidth();j++) {
					Cell cell = mapCell[i][j];
					Enemy enemy = mapEnemy[i][j];
					
					if(!cell.isPermanentlyLit() || !cell.isLit()) {                          //inverter!! usado apenas para teste
						g2.drawImage(cell.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}else {
						g.fillRect(XOrigem + j*32, YOrigem + i*32,32,32);         //colocar imagem da sombra
					}
					
					if(enemy != null && enemy.isLit()) {
						g2.drawImage(enemy.getImage(), XOrigem + j*32, YOrigem + i*32, this);
					}
				}
			}
			
			g2.drawImage(bravia.getImage(),XOrigem+bravia.getJPos()*32,YOrigem+bravia.getIPos()*32,this);

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
}
