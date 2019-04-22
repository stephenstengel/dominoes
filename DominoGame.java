/*
 * DominoesGame.java 
 * 
 * This is where the Domino game will be played 
 * 
 * Stephen Stengel
 * Jose Rodriquez
 * 
 */


public class DominoGame
{
	
	public static void main (String args[])
	{
		
		System.out.println("This is going to be a dominoes game!\n");
		
		//create the table
		Table myTable= new Table();
		//print inital state of table and players 
		System.out.println("State of table:\n\n" + myTable.toString());
		
		//run the game
		myTable.placeFirstDomino( myTable.highestDomino() );
		
		//print state of table after first domino is placed 
		System.out.println( "\n\nNew State:\n" + myTable.toString() + "\nEnd new state print.\n");
		
		//winning player will be winnerValue+1
		int winnerValue = myTable.playGame();
		
		System.out.println("\n\n##################################"
							+ "\nBoard state: \n" + myTable.toString() );
		
		System.out.println("Value of winnerValue: " + winnerValue);
		
		if (winnerValue <= -1)
		{
			System.out.println("The game was blocked by Player " + (winnerValue * -1) + "! Player with fewest dots wins!");
			
			System.out.println("Player 1 has: " + myTable.players[0].getNumberOfDots() + " dots!");
			System.out.println("Player 2 has: " + myTable.players[1].getNumberOfDots() + " dots!");
			
			if ( myTable.players[0].getNumberOfDots() == myTable.players[1].getNumberOfDots() )
			{
				System.out.println("It's a tie! Each player has the same number of dots!");
			}
			else if ( myTable.players[0].getNumberOfDots() > myTable.players[1].getNumberOfDots() )
			{
				System.out.println("Player 2 wins!");
			}
			else
			{
				System.out.println("Player 1 wins!");
			}
		}
		else if (winnerValue == 0 || winnerValue == 1)
		{
			System.out.println("The winner is Player" + (winnerValue + 1) + "!");
		}
		else
		{
			System.out.println("ERROR in main!");
		}
		
	}
}
