package map;

import bravia.Bravia;

/* Qualquer classe que implementar essa interface deve retornar
 * um objeto Bravia e Map que estao ligados entre si 
 */
public interface IGameCreator { 
	public Map getMap();
	public Bravia getBravia();
}
