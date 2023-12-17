package gamecharacters;

public class Ovni {
	private int fil;
	private int col;
	private int vida;
	private boolean bajar;
	private boolean izq;
	private final static int puntos = 25;
	
	
	public Ovni(){
		fil=0;
		col=8;
		vida=1;
		bajar= true;
		izq = true;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void quitarVida(){
		vida--;
	}
	
	public int getFil(){
		return fil;
	}
	
	public int getCol(){
		return col;
	}

	public int getVida() {
		return vida;
	}

	public void mover() {
		if (!borde() || !bajar){
			bajar = true;
			if (izq) col--;
			else col++;
		}
		else{
			bajar = false;
			fil++;
		}
	}
	
	public boolean borde(){
		boolean bound = false;
		if (col == 0 || col == 8){
			bound = true;
			izq = !izq;
		}
		return bound;
	}
	
	public boolean estaFila(){
		boolean vuelta = false;
		if (fil == 8) vuelta = true;
		return vuelta;
	}
	
	public String imprimir (){
		String vuelta = "O[" + Integer.toString(vida) + "]";
		return vuelta;
	}
}


