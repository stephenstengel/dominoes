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
		Table myTable= new Table();
		System.out.println("Players hands (Table shows avaliable pieces):\n\n" +myTable.toString());
		
		//run the game
		Domino x; 
		x = myTable.placeFirstDomino( myTable.highestDomino() );
		
		// loop will iterate until a player is declared a winner 
		while (myTable.player1.getIsWinner() != true && myTable.player2.getIsWinner() != true)
		{
			
			
			// need to add way for players to grab dominos from table and a way to declare a winner 
			// right now the players take turns placing a domino but when one runs out of required domino the other 
			// player goes multiple times until they both run out and the game crashes 
			myTable.nextMove();
			
		
			//myTable.player1.setPlayerAsWinner();
			
			
		}
		
		
	}
}
