package gamecharacters;

public class Ship {
	private int vida;
	private int fil;
	private int col;

	public Ship() {
		vida = 3;
		fil = 7;
		col = 4;
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
	
	public void mover(boolean izq, int i){ //mover
		if (izq) col-=i;
		else col+=i;
	}


	public void quitarVida() {
		vida--;
	}

	public String imprimir(){
		String vuelta;
		if (vida > 0) vuelta = "^__^";
		else vuelta = "!xx!";
		return vuelta;
	}
	
}

