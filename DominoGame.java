/*
 * Dominoes Game
 * 
 * Copyright 2019 Stephen Stengel <stephen.stengel@cwu.edu>,
 *                Jose Rodriquez
 */


public class DominoGame
{
	public static void main (String args[])
	{
		System.out.println("This is going to be a dominoes game!\n");
		
		//create the table
		Table startGame= new Table();
		System.out.println("Players hands (Table shows avaliable pieces):\n\n" +startGame.toString());
		
		//run the game
		Domino x; 
		int highestDom=startGame.highestDomino();
		
		if (highestDom==1)
		{
			// call method that places some domino on table and removes it from players hand from table class 
			x=startGame.placeFirstDomino(1);
			
		}
		else
		{
			x=startGame.placeFirstDomino(2);
			
		}
		
		// loop will iterate until a player is declared a winner 
		while (startGame.player1.getIsWinner() != true && startGame.player2.getIsWinner() != true)
		{
			
			
			// need to add way for players to grab dominos from table and a way to declare a winner 
			// right now the players take turns placing a domino but when one runs out of required domino the other 
			// player goes multiple times until they both run out and the game crashes 
			startGame.nextMove();
			
		
			//startGame.player1.setPlayerAsWinner();
			
			
		}
		
		
	}
}
