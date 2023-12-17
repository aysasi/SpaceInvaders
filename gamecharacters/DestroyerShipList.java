package gamecharacters;
import game.Game.Level;
import gamecharacters.DestroyerShip;
import gamecharacters.BombList;

public class DestroyerShipList {
	private final static int MAX = 4;
	private DestroyerShip[] destship;
	private int cont;
	public DestroyerShipList(Level level) {
		destship = new DestroyerShip[MAX];
		
		if (level == Level.EASY) {
			cont= 2;
			int j =0;
				for (int i = 4; i <= 5; i++) {
					destship[j]  = new DestroyerShip(2, i);
					j++;
				}
			
		}
		else if (level == Level.HARD) {
			cont= 2;
			int j =0;
				for (int i = 4; i <= 5; i++) {
					destship[j] = new DestroyerShip(3, i);
					j++;
				}
			
		}
		else {
			cont = MAX;
			int j =0;
				for (int i = 3; i <= 6; i++) {
					destship[j] = new DestroyerShip(3, i);
					j++;
				}
			}
		}

	


public DestroyerShip buscarNave(int i, int j) {
	int k = 0;
	boolean found = false;
	while (found == false && k < cont){
		if(destship[k].getFila()==i && destship[k].getCol()==j) found = true;
		else k++;
	}
	if (found == false) return null;
	return destship[k];
}


public int getCont() {
	return cont;
}

public void quitarVida(){
	for (int i = 0; i < cont; i++){
		destship[i].setQuitaVida();
	}
}

public void quitarVidaNave(int pos){
	destship[pos].setQuitaVida();
}

public void disparar(int pos, BombList bombs){
	if (destship[pos].getDisparo() == false){
		destship[pos].setDisparo(true);
		bombs.createBomb(destship[pos]);
	}
}

public void borrarNave (DestroyerShip destroyer){
	int pos =0;
	boolean found = false;
	while (!found){
		if (destship[pos] == destroyer)
			found = true;
		else
			pos++;
	}
	destship[pos] = null;
	for(int i = pos; i < cont-1; i++){
		destship[i] = destship[i+1];
	}
	destship[cont-1] = null;
	cont--;
}

public int getPuntos(){
	return destship[0].getPuntos();
}

public int getVida (int pos){
	return destship[pos].getVida();
}

public void mover(boolean izq, int bajar, Level nivel, int contCic) {
	if (bajar == 0){
		for (int i = 0; i < cont; i++){
			destship[i].setFil();
		}
	}
	else {
		if (nivel == Level.EASY && (contCic % 3) == 0){
			for (int i =0; i < cont; i++){
				destship[i].setCol(izq);
			}
		}
		else if (nivel == Level.HARD && (contCic % 2) == 0){
			for (int i =0; i < cont; i++){
				destship[i].setCol(izq);
			}
		}
		else if (nivel == Level.INSANE){
			for (int i =0; i < cont; i++){
				destship[i].setCol(izq);
			}
		}
	}
	
}

public boolean estaFila(){
	boolean vuelta = false;
	for (int i =0; i < cont; i++){
		if (destship[i].getFila()==8) vuelta = true;
	}
	return vuelta;
}




public int matar() {
	int puntos = 0;
	for (int i = 0; i < cont; i++){
		if(destship[i].getVida() == 0){
			puntos += destship[i].getPuntos();
			borrarNave(destship[i]);
		}
	}
	return puntos;
}



}
