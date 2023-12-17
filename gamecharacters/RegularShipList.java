package gamecharacters;
import game.Game.Level;
import gamecharacters.RegularShip;

public class RegularShipList {
	private final static int MAX = 8;
	private RegularShip[] regship;
	private int cont;
	private int bajar;
	public RegularShipList(Level level) {
		regship = new RegularShip[MAX];
		cont = 0;
		bajar = 1;
		int j=0;
			for (int i =3; i < 7; i++) {
				regship[j] =  new RegularShip(1, i);
				j++;
			}
		
		if (level == Level.EASY) cont= 4;
		else {
			cont = MAX;
			int k=4;
				for (int i =3; i < 7; i++) {
					regship[k] = new RegularShip (2, i);
					k++;
				}
		}
	}



public RegularShip buscarNave(int i, int j) {
	int k=0;
	boolean found = false;
	while (found == false && k < cont){
		if(regship[k].getFila()==i && regship[k].getCol()==j) found = true;
		else k++;
	}
	if (found == false) return null;
	return regship[k];
}

public int getCont() {
	return cont;
}

public void quitarVida(){
	for (int i = 0; i < cont; i++)
		regship[i].setQuitaVida();
}

public void quitarVidaNave(int pos){
	regship[pos].setQuitaVida();
	
}

public int getVida (int pos){
	return regship[pos].getVida();
}

public void borrarNave (RegularShip regular){
	int pos =0;
	boolean found = false;
	while (!found){
		if (regship[pos] == regular)
			found = true;
		else
			pos++;
	}
	regship[pos] = null;
	for(int i = pos; i < cont-1; i++){
		regship[i] = regship[i+1];
	}
	regship[cont-1] = null;
	cont--;
}

public boolean mover(boolean izq, Level nivel, int contCic) {
	boolean vuelta = izq;
	if((!borde() || bajar == 0)){
			bajar = 1;
			if (nivel == Level.EASY && (contCic % 3) == 0){
				for (int i =0; i < cont; i++){
					regship[i].setCol(izq);
				}
			}
			else if (nivel == Level.HARD && (contCic % 2) == 0){
				for (int i =0; i < cont; i++){
					regship[i].setCol(izq);
				}
			}
			else if (nivel == Level.INSANE){
				for (int i =0; i < cont; i++){
					regship[i].setCol(izq);
				}
			}

		}
		
	else{ 
		vuelta = !izq;
		bajar=0;
		for (int i =0; i < cont; i++){
			regship[i].setFil();
		}
		
	}
	return vuelta;
}
public boolean borde(){
	boolean bound = false;
	int i =0;
	while (i < cont && !bound){
		if (regship[i].getCol() == 0 || regship[i].getCol() == 8) bound = true;
		i++;
	}
	return bound;
}

public int getBajar(){
	return bajar;
}

public boolean estaFila(){
	boolean vuelta = false;
	for (int i =0; i < cont; i++){
		if (regship[i].getFila()==8) vuelta = true;
	}
	return vuelta;
}



public int matar() {
	int puntos=0;
	for (int i = 0; i < cont; i ++){
		if (regship[i].getVida() == 0){
			puntos += regship[i].getPuntos();
			borrarNave(regship[i]);
		}
	}
	return puntos;
}

}