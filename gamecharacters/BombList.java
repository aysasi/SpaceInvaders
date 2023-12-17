package gamecharacters;
import gamecharacters.Bomb;

public class BombList {
	private Bomb[] bomblist;
	private int cont;
	
	
	public BombList() {
		bomblist = new Bomb[4];//mirar
		cont = 0;
}
	
	public void createBomb(DestroyerShip destship){
		bomblist[cont]= new Bomb (destship);
		cont++;
	}
	
	public Bomb buscarBomb(int i, int j) {
		int k=0;
		boolean found = false;
		while (found == false && k < cont){
			if(bomblist[k].getFil()==i && bomblist[k].getCol()==j) found = true;
			else k++;
		}
		if (found == false) return null;
		return bomblist[k];
	}
	
	public void borrarBomba(Bomb bomba){
		int pos = 0;
		boolean found = false;
		while (!found){
			if (bomblist[pos] == bomba)
				found = true;
			else
				pos++;
		}
		bomblist[pos] = null;
		for(int i = pos; i < cont-1; i++){
			bomblist[i] = bomblist[i+1];
		}
		bomblist[cont-1] = null;
		cont--;
	}

	public int getCont() {
		return cont;
	}

	public void setPos() {
		for (int i = 0 ; i < cont; i++){
			bomblist[i].setFil();
			if (bomblist[i].getFil()== 8){
				borrarBomba(bomblist[i]);
			}
		}
	}
}

