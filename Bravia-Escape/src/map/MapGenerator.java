package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bravia.Bravia;
import cells.*;
import enemy.Enemy;

public class MapGenerator implements IMapGenerator{
	private String mapSource;
	private int mapHeight, mapWidth;
	private int IEntrance, JEntrance;
	private Map mapGenerated;
	private Cell[][] mapCells;
	private Enemy[][] mapEnemy;
	private String mapText[][];

	public MapGenerator(String levelPath){
		setMapSource(levelPath);
		mapGenerated = new Map();
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
			mapText = new String[this.mapHeight][this.mapWidth];
			mapCells = new Cell[this.mapHeight][this.mapWidth];
			mapEnemy = new Enemy[this.mapHeight][this.mapWidth];
			
			/*** preencher matriz mapa ***/
			for(int i=0; i < this.mapHeight; i++) {
				line = file.readLine();
				lineSplit = line.split(",");
				for(int j=0; j < this.mapWidth; j++) {
					mapText[i][j] = lineSplit[j];
					if(lineSplit[i] == "En") {
						mapEnemy[i][j] = new Enemy(mapGenerated,i,j);
					}else {
						mapCells[i][j] = cellObject(lineSplit[j], i, j);
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
		mapGenerated.setMapCell(mapCells);
		mapGenerated.setMapEnemy(mapEnemy);
		mapGenerated.setMapHeight(mapHeight);
		mapGenerated.setMapWidth(mapWidth);
		mapGenerated.setBravia(new Bravia(mapGenerated, IEntrance, JEntrance));
	}
	
	/*** metodo para devolver o mapa criado ***/
	public Map generateMap() {
		buildMap();
		loadMap();
		printMapStdout();
		return mapGenerated;
	}
	
	/*** metodo para retornar o objeto cell certo de acordo com a identificacao ***/
	private Cell cellObject(String id, int iPos, int jPos) {
		char first = id.charAt(0);
		switch (first) {
		case '-':  //piso
			return new Floor(iPos,jPos);
		case 'W':  //Wall
			return new Wall(iPos,jPos);
		case 'G':  //Gate
			return new Gate(iPos,jPos,Color.getColor(id.charAt(1) - '0'));
		case 'B':  //Bonfire
			return new Bonfire(iPos,jPos, mapGenerated);
		case 'K':  //Key
			return new Key(iPos,jPos,Color.getColor(id.charAt(1) - '0'));
		case 'C':  //Key
			return new Chest(iPos, jPos);
		case 'E':  //Exit
			return new Exit(iPos, jPos);
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
				System.out.print(mapText[i][j] + " ");
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
