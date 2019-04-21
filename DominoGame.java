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
		
		
		int winnerValue = myTable.playGame();
		
		
		if (winnerValue == -1)
		{
			System.out.println("The game was a tie! The game was blocked and each"
								+ " player has the same number of pips!");

			//print number
		}
		else if (winnerValue == 0 || winnerValue == 1)
		{
			System.out.println("The winner is Player" + (winnerValue + 1) + "!");
		}
		
		
	}
}
