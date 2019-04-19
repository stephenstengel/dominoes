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
		
		if (startGame.highestDomino()==1)
		{
			// call method that places some domino on table and removes it from players hand from table class 
			startGame.placeDomino(1);
			
		}
		else
		{
			startGame.placeDomino(2);
			
		}
		
		
	}
}
