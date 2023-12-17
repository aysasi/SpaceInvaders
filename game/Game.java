package game;
import gamecharacters.DestroyerShipList;
import gamecharacters.DestroyerShip;
import gamecharacters.RegularShipList;
import gamecharacters.Ship;
import gamecharacters.ShipLaser;
import java.util.Random;
import gamecharacters.BombList;
import gamecharacters.Ovni;
import gamecharacters.RegularShip;
import gamecharacters.Bomb;

public class Game {
	
	public enum Level {EASY, HARD, INSANE};
	
	Level nivel;
	Random rand;
	RegularShipList listaRegular;
	DestroyerShipList listaDestroyer;
	Ship ship;
	BombList listaBombas;
	ShipLaser laser;
	DestroyerShip destroyer;
	RegularShip regular;
	Ovni ovni;
	Bomb bomba;
	int contCic;
	int puntos;
	boolean hayOvni;
	boolean hayLaser;
	boolean shockwave;
	boolean izq;
	boolean reset;
	
	
	public Game(String level, long l) {
		if (level.toLowerCase().equals("easy")) this.nivel = Level.EASY;
		else if (level.toLowerCase().equals("hard")) this.nivel = Level.HARD;
		else this.nivel = Level.INSANE;
		rand = new Random(l);
		listaRegular = new RegularShipList(nivel);
		listaDestroyer = new DestroyerShipList(nivel);
		ship = new Ship();
		listaBombas = new BombList();
		contCic = 0;
		puntos = 0;
		hayLaser = false;
		hayOvni = false;
		shockwave = false;
		izq = true;
		reset = false;
	}
	
	
	public void initGame(Level level){
		
	}
		
	
	public String characterAtToString(int i, int j) { //comprobar funcion
		String objeto = null;
		boolean found = false;
		if (found == false){
			regular = listaRegular.buscarNave(i, j);
			if (regular != null){
				found = true;
				objeto = regular.imprimir();
			}
		}
		if (found == false){
			destroyer=listaDestroyer.buscarNave(i, j);
			if (destroyer != null){
				found = true;
				objeto = destroyer.imprimir();
			}
		}
		
		if (found == false && listaBombas.getCont() > 0) {
			bomba = listaBombas.buscarBomb(i, j);
			if (bomba != null){
				found = true;
				objeto = bomba.imprimir();
			}
		}
		if (found == false && hayLaser) {
			if(laser.getCol() == j && laser.getFila() == i){
				found = true;
				objeto = laser.imprimir();
			}
		}
		if (found == false && hayOvni) {
			if(ovni.getCol() == j && ovni.getFil() == i){
				found = true;
				objeto = ovni.imprimir();
			}
		}
		if (found == false && ship.getFila() == i && ship.getCol()==j){
			objeto = ship.imprimir();
		}
		if (objeto == null) objeto = " "; //esa casilla no contiene nada
		return objeto; //revisar esto
	}; 
	
	public void draw() {
		System.out.println("Life: " + Integer.toString(ship.getVida()));
		System.out.println("Number of cycles: " + Integer.toString(contCic));
		System.out.println("Points: " + Integer.toString(puntos));
		System.out.println("Remaining aliens: " + Integer.toString(listaDestroyer.getCont() + listaRegular.getCont()+ (hayOvni ? 1 : 0)));
		if (shockwave) System.out.println("Shockwave: YES");
		else System.out.println("Shockwave: NO");
		
	}		
	
	
	public boolean update(){
		boolean vuelta = true;
		if (hayLaser){
			if (laser.getFila() != 0){
				laser.setPos();
				regular = listaRegular.buscarNave(laser.getFila(), laser.getCol());
				if (regular != null){
					regular.setQuitaVida();
					laser = null;
					hayLaser = false;
				}
				else if (listaDestroyer.buscarNave(laser.getFila(), laser.getCol()) != null){
					destroyer = listaDestroyer.buscarNave(laser.getFila(), laser.getCol());
					destroyer.setQuitaVida();
					laser = null;
					hayLaser = false;
				}
				else if (hayOvni && ovni.getCol() == laser.getCol() && ovni.getFil() == laser.getFila()){
					ovni.quitarVida();
					laser = null;
					hayLaser = false;
				}
				else if (listaBombas.buscarBomb(laser.getFila(), laser.getCol()) != null){
					bomba = listaBombas.buscarBomb(laser.getFila(), laser.getCol());
					listaBombas.borrarBomba(bomba);	
					laser = null;
					hayLaser = false;
				}
			}
			else {
				laser = null;
				hayLaser = false;
			}
		}
		listaBombas.setPos();
		if (listaBombas.getCont() > 0){
			
			if (hayLaser && listaBombas.buscarBomb(laser.getFila(), laser.getCol()) != null){
				bomba = listaBombas.buscarBomb(laser.getFila(), laser.getCol());
				listaBombas.borrarBomba(bomba);	
				laser = null;
				hayLaser = false; 
			}
			else if (listaBombas.buscarBomb(ship.getFila(), ship.getCol()) != null){
				bomba = listaBombas.buscarBomb(ship.getFila(), ship.getCol());
				listaBombas.borrarBomba(bomba);
				ship.quitarVida();

			}
	}
		//ha muerto ship?
		
		if (ship.getVida() == 0){
			vuelta = false;
			System.out.print("Aliens win");
		}
		
		//matar naves
		puntos += listaRegular.matar();
		puntos += listaDestroyer.matar();
		if (hayOvni && ovni.getVida() == 0){
			puntos += ovni.getPuntos();
			ovni = null;
			shockwave = true;
			hayOvni = false;
		}
		
		// mover naves

		izq = listaRegular.mover(izq, nivel, contCic);
		listaDestroyer.mover(izq, listaRegular.getBajar(), nivel, contCic);
		if(hayOvni) ovni.mover();
		
		if (listaDestroyer.getCont() + listaRegular.getCont()+ (hayOvni ? 1 : 0) == 0){
			System.out.println("Player wins");
			vuelta = false;
		}
		if (listaDestroyer.estaFila() || listaRegular.estaFila() || (hayOvni && ovni.estaFila())){
			vuelta = false;
			System.out.print("Aliens win");
		}
	
	contCic++;
	return vuelta;
	}	
		
	public void computerAction(Level level){
		if (level == Level.EASY) {
			if(listaDestroyer.getCont()  > 0 && rand.nextInt() % 10 == 0){
				listaDestroyer.disparar(rand.nextInt(listaDestroyer.getCont()), listaBombas);
			}
			
			if(!hayOvni && (rand.nextInt() % 2 == 0)){
				hayOvni=true;
				ovni = new Ovni();
			}
			
		}
		else if(level == Level.HARD){
			if(listaDestroyer.getCont()  > 0 && rand.nextInt() % 3 == 0){
				listaDestroyer.disparar(rand.nextInt(listaDestroyer.getCont()), listaBombas);
			}
			if(!hayOvni && (rand.nextInt() % 5 == 0)){
				hayOvni=true;
				ovni = new Ovni();
			}
		}
		else {
			if(listaDestroyer.getCont()  > 0 && rand.nextInt() % 2 == 0){
				listaDestroyer.disparar(rand.nextInt(listaDestroyer.getCont()), listaBombas);
			}
			if(!hayOvni && (rand.nextInt() % 10 == 0)){
				hayOvni=true;
				ovni = new Ovni();
			}
		}
	}
	
	public boolean commands(String comando){ 

			if(comando.toLowerCase().equals("move left 1") || comando.toLowerCase().equals("m left 1")) {
				ship.mover(true, 1);
			}
			else if (comando.toLowerCase().equals("move left 2") || comando.toLowerCase().equals("m left 2")){
				ship.mover(true, 2);
			}
			else if(comando.toLowerCase().equals("move right 1") || comando.toLowerCase().equals("m right 1")){
				ship.mover(false, 1);
			}
			else if(comando.toLowerCase().equals("move right 2") || comando.toLowerCase().equals("m right 2")){
				ship.mover(false, 2);
			}
			else if(comando.toLowerCase().equals("shoot") || comando.toLowerCase().equals("s")) {
				if (hayLaser == false){
					hayLaser= true;
					laser = new ShipLaser(ship);
				}
			}
			else if (comando.toLowerCase().equals("shockwave") || comando.toLowerCase().equals("w")){
				if (shockwave){
					listaDestroyer.quitarVida();
					listaRegular.quitarVida();
					if(hayOvni==true) {
						ovni.quitarVida();
				}
				}
				else {
					System.out.println("Shockwave not available. You have to kill an ovni first.");
				}
				
			}
			else if(comando.toLowerCase().equals("reset") || comando.toLowerCase().equals("r")) {
					reset = true;
					
			}
			else if(comando.toLowerCase().equals("list") || comando.toLowerCase().equals("l")) {
				
			}
			else if(comando.toLowerCase().equals("none") || comando.toLowerCase().equals("n") || comando.equals("")) {
				
			}
			else if(comando.toLowerCase().equals("help") || comando.toLowerCase().equals("h")) {
				System.out.println("move <left|right><1|2>: Moves Ship to the indicated direction.");
				System.out.println("shoot: Ship launches a missile.");
				System.out.println("shockWave: Ship releases a shock wave.");
				System.out.println("list: Prints the list of available ships.");
				System.out.println("reset: Starts a new game.");
				System.out.println("help: Prints this help message.");
				System.out.println("[none]: Skips one cycle.");
				return false;
			}
			else {
				System.out.println("Error.");
				return false;
			}

		return true;
	
		}
	
	public Level getLevel(){
		return nivel;
	}
	
	public boolean getReset(){
		return reset;
	}
}
	
	


