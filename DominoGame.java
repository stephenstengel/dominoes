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
		System.out.println("State of table:\n\n" + myTable.toString());
		
		//run the game
		myTable.placeFirstDomino( myTable.highestDomino() );
		
		System.out.println( "\n\nNew State:\n" + myTable.toString() + "\nEnd new state print.\n");
		
		
		// loop will iterate until a player is declared a winner 
		while ( myTable.checkAreThereAnyWinners() == -1 )
		{
			
			// need to add way for players to grab dominos from table and a way to declare a winner 
			// right now the players take turns placing a domino but when one runs out of required domino the other 
			// player goes multiple times until they both run out and the game crashes 
			myTable.nextMove();
			
		
			//myTable.player1.setPlayerAsWinner();
			
			
			if ( myTable.isBoneyardEmpty() )
			{
				break;
			}
		}
		
		
		
		for (int i = 0; i < 2; i++)
		{
			if (myTable.players[i].checkIfWinner() )
			{
				System.out.println("\n\nPlayer " + (i + 1) + " is the winner!");
				System.out.println("Board State:\n" + myTable.toString() );
			}
		}
		
		if ( myTable.isBoneyardEmpty() )
		{
			System.out.println("It was a tie!");
			
			System.out.println("Board State:\n" + myTable.toString() );
		}
		
		
		
	}
}
