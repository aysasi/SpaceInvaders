package gamecharacters;

public class DestroyerShip {
	private int vida;
	private int fil;
	private int col;
	private boolean hayDisparo;
	private final static int puntos = 10;
	
	public DestroyerShip(int fil, int col) {
		vida = 1;
		this.fil = fil;
		this.col = col;
		hayDisparo = false;
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
	
	public void setQuitaVida(){
		vida--;
	}

	
	public boolean getDisparo(){
		return hayDisparo;
	}
	
	public void setDisparo(boolean op){
		hayDisparo = op;
	}

	public int getPuntos(){
		return puntos;
	}

	public void setCol(boolean izq) {
		if (izq) col--;
		else col++;
	}

	public void setFil(){
		fil++;
	}
	
	public String imprimir () {
		String vuelta = "D[" + Integer.toString(vida) + "]";
		return vuelta;
	}



	
}