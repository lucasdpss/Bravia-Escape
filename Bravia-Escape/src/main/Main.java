package main;

import map.MapGenerator;

public class Main {

	public static void main(String[] args) {
		MenuInicial menu = new MenuInicial();
		menu.show();
		
		new MapGenerator("resources//testmap.csv"); //testar a leitura do csv (o construtor printa na tela)
	}

}
