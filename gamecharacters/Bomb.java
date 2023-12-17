package gamecharacters;

import gamecharacters.DestroyerShip;

public class Bomb {
	private int fila;
	private int col;
	
	
	public Bomb(DestroyerShip destship){
		fila = destship.getFila();
		col = destship.getCol();
	}
	
	public int getFil(){
		return fila;
	}
	
	public int getCol(){
		return col;
	}

	public void setFil() {
		fila ++;
	}
	
	public String imprimir() {
		String vuelta = ".";
		return vuelta;
	}

	
}
