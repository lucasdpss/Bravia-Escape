package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cells.*;
import enemy.Enemy;

public class MapGenerator implements IMapGenerator{
	private String mapSource;
	private int mapHeight, mapWidth;
	private int IEntrance, JEntrance;
	private Map mapGenerated;
	private Cell[][] mapCell;
	private Enemy[][] mapEnemy;
	private String level[][];

	public MapGenerator(String levelPath){
		setMapSource(levelPath);
		mapGenerated = new Map();
		buildMap();
		loadMap();
		printMapStdout();
	}
	
	/*** metodo para configurar o mapa  ***/
	private void buildMap() {
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
			setIEntrance(Integer.parseInt(lineSplit[1]));
			
			/*** criar matriz mapa vazia ***/
			level = new String[this.mapHeight][this.mapWidth];
			mapCell = new Cell[this.mapHeight][this.mapWidth];
			mapEnemy = new Enemy[this.mapHeight][this.mapWidth];
			
			/*** preencher matriz mapa ***/
			for(int i=0; i < this.mapHeight; i++) {
				line = file.readLine();
				lineSplit = line.split(",");
				for(int j=0; j < this.mapWidth; j++) {
					level[i][j] = lineSplit[j];
					if(lineSplit[i] == "En") {
						mapEnemy[i][j] = new Enemy(mapGenerated,i,j);
					}else {
						mapCell[i][j] = cellObject(lineSplit[j]);
					}
				}
			}
			
			file.close();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
	
	/*** metodo para passar as informacoes coletadas para o mapGenerated ***/
	private void loadMap() {
		mapGenerated.setMapCell(mapCell);
		mapGenerated.setMapEnemy(mapEnemy);
		mapGenerated.setMapHeight(mapHeight);
		mapGenerated.setMapWidth(mapWidth);
		mapGenerated.setIBravia(IEntrance);
		mapGenerated.setJBravia(JEntrance);
	}
	
	/*** metodo para devolver o mapa criado ***/
	public Map generateMap() {
		return mapGenerated;
	}
	
	/*** metodo para retornar o objeto cell certo de acordo com a identificacao ***/
	private Cell cellObject(String id) {
		char first = id.charAt(0);
		switch (first) {
		case '-':  //piso
			return new Floor();
		case 'W':  //Wall
			return new Wall();
		case 'G':  //Gate
			return new Gate(id.charAt(1) - '0');
		case 'B':  //Bonfire
			return new Bonfire();
		case 'K':  //Key
			return new Key(id.charAt(1) - '0');
		case 'C':  //Key
			return new Chest();
		case 'E':  //Exit
			return new Exit();
		default:
			return null;
		}
	}
	
	/*** metodo usado para verificar a leitura do csv***/
	public void printMapStdout() { 
		System.out.print(mapHeight);
		System.out.print(" ");
		System.out.println(mapWidth);
		System.out.print(IEntrance);
		System.out.print(" ");
		System.out.println(JEntrance);
		for(int i=0; i < this.mapHeight; i++) {
			for(int j=0; j < this.mapWidth; j++) {
				System.out.print(level[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void setMapSource(String mapSource) {
		this.mapSource = mapSource;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public void setIEntrance(int IEntrance) {
		this.IEntrance = IEntrance;
	}
	public void setJEntrance(int JEntrance) {
		this.JEntrance = JEntrance;
	}

	public String getMapSource() {
		return mapSource;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public int getIEntrance() {
		return IEntrance;
	}
	public int getJEntrance() {
		return JEntrance;
	}
	public Cell[][] getMapCells() {
		return null;
	}
	public Enemy[][] getMapEnemies() {
		return null;
	}
	
}
