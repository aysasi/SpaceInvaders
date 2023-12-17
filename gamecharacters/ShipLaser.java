package gamecharacters;

public class ShipLaser {
	private int fil;
	private int col;
	
	
	public ShipLaser(Ship ship){
		fil = ship.getFila();
		col = ship.getCol();
	}
	
	public void setPos(){
		fil--;
	}
	
	public int getFila(){
		return fil;
	}
	
	public int getCol(){
		return col;
	}
	
	public String imprimir(){
		String vuelta = "oo";
		return vuelta;
	}
	
}

