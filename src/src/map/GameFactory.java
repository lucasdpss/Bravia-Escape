package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bravia.Bravia;
import cells.Cell;
import enemy.Enemy;
import main.Checkpoint;
import main.Window;

public class GameFactory implements IGameCreator{
	private String mapSource;
	private int mapHeight, mapWidth;
	private int IEntrance, JEntrance;
	private Map mapGenerated;
	private Cell[][] mapCell;
	private Enemy[][] mapEnemy;
	private String mapText[][];
	private Bravia braviaGenerated;
	private Window window;
	
	public GameFactory(Window window, String levelPath) {
		this.mapSource = levelPath;
		this.window = window;
		this.mapGenerated = new Map();
		loadGame();
		buildGame();
		printMapStdout();
	}
	
	public Map getMap() {
		return mapGenerated;
	}
	
	public Bravia getBravia() {
		return braviaGenerated;
	}
	
	/*** metodo para carregar informacoes dos objetos a serem criados ***/
	private void loadGame() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(this.mapSource));
			String line;
			String[] lineSplit;
			
			/*** ler a primeira linha ***/
			line = file.readLine();
			lineSplit = line.split(",");
			setMapHeight(Integer.parseInt(lineSplit[0]));
			setMapWidth(Integer.parseInt(lineSplit[1]));
			
			/*** ler a segunda linha ***/
			line = file.readLine();
			lineSplit = line.split(",");
			setIEntrance(Integer.parseInt(lineSplit[0]));
			setJEntrance(Integer.parseInt(lineSplit[1]));
			
			/*** criar matriz mapa vazia ***/
			mapText = new String[this.mapHeight][this.mapWidth];
			mapCell = new Cell[this.mapHeight][this.mapWidth];
			mapEnemy = new Enemy[this.mapHeight][this.mapWidth];
			
			/*** preencher matriz mapa ***/
			for(int i=0; i < this.mapHeight; i++) {
				line = file.readLine();
				lineSplit = line.split(",");
				for(int j=0; j < this.mapWidth; j++) {
					String objectID = lineSplit[j];
					mapText[i][j] = objectID;
					mapEnemy[i][j] = EnemyFactory.getEnemy(objectID, mapGenerated, i, j);
					mapCell[i][j] = CellFactory.getCell(objectID, window, mapGenerated, i, j);
				}
			}
			
			file.close();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
	
	/*** Instancia os dois principais objetos a serem criados ***/
	private void buildGame() {
		mapGenerated.setMapCell(mapCell);
		mapGenerated.setMapEnemy(mapEnemy);
		mapGenerated.setMapHeight(mapHeight);
		mapGenerated.setMapWidth(mapWidth);
		mapGenerated.setBravia(new Bravia(mapGenerated, IEntrance, JEntrance));
		braviaGenerated = mapGenerated.getBravia();
		
		//rotina para gerar checkpoint ao iniciar a fase
		try {
			Checkpoint.setStartPos(IEntrance, JEntrance);
			Checkpoint.setKeyInventory(braviaGenerated);
			Checkpoint.setMapCell(mapCell);
			Checkpoint.setMapEnemy(mapEnemy);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/*** metodo usado para verificar a leitura do csv***/
	private void printMapStdout() { 
		System.out.print(mapHeight);
		System.out.print(" ");
		System.out.println(mapWidth);
		System.out.print(IEntrance);
		System.out.print(" ");
		System.out.println(JEntrance);
		for(int i=0; i < this.mapHeight; i++) {
			for(int j=0; j < this.mapWidth; j++) {
				System.out.print(mapText[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	private void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	private void setIEntrance(int IEntrance) {
		this.IEntrance = IEntrance;
	}
	private void setJEntrance(int JEntrance) {
		this.JEntrance = JEntrance;
	}
	
}
