package controller;

import game.Game;
import game.GamePrinter;

import java.util.Scanner;

public class Controller {
	private Game game;
	String comando;
	Scanner in;
	GamePrinter tablero;
	
	public Controller(Game game, Scanner input){
		this.game = game;
		in = input;
		tablero = new GamePrinter(game, 8,9);
	}
	
	
	public void run() {
		
		boolean exit = true;
		
		while(true) { //cuarta parte del bucle
			game.draw();
			System.out.print(tablero.dibujarTablero(game));
			System.out.print("Command > ");
			comando = in.nextLine();
			exit = game.commands(comando);
			if (exit) {
				game.computerAction(game.getLevel());
				if (!game.update())
					break;
			}
		}
	}
}

 