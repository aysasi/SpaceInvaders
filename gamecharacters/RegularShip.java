package gamecharacters;

public class RegularShip {
	private int vida;
	private int fil;
	private int col;
	private final static int puntos = 5;
	
	public RegularShip(int fil, int col) {
		vida = 2;
		this.fil = fil;
		this.col = col;
	}
	
	public int getFila(){
		return fil;
	}
	
	public int getCol(){
		return col;
	}
	
	public int getVida(){
		return vida;
	}
	
	public void setQuitaVida (){
		vida--;
	}
 
	public int getPuntos() {
		return puntos;
	}
	
	public void setCol(boolean direction) {
		if (direction){
			col--;
		}
		else col++;		
	}

	public void setFil() {
		fil++;
	}
	
	public String imprimir () {
		String vuelta = "C[" + Integer.toString(vida) + "]";
		return vuelta;
	}

}

