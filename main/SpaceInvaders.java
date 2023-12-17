package main;
import game.Game.Level;
import game.Game;

import java.util.Scanner;

import controller.Controller;

public class SpaceInvaders {
	
	public static void main(String[] args) {
		Game game;
		Controller controller;
		
		Scanner input = new Scanner(System.in);
		String level = "";
		
		Boolean level_correct = false;
		while(!level_correct) {
			System.out.print( "Enter level (easy, hard, insane): " );
			level = input.nextLine();
			if (level.toLowerCase().equals("easy") || level.toLowerCase().equals("hard") || level.toLowerCase().equals("insane")) {
				level_correct = true;
			}
			else {
				System.out.println( "String not recognized.");
			}
		}
		
		
		do{
			game = new Game(level, System.currentTimeMillis());
			controller = new Controller(game, input);
			controller.run();
		} while (game.getReset());
		
		
	}
}
