/*
 * Table.java
 * 
 * This is the Class with the data structure that represents everything that is
 * currently in the game including: players, player hands, the dominoes on the
 * board.
 * 
 */
 
 import java.util.ArrayList;
 import java.util.Random;
 

public class Table
{
	private ArrayList<Domino> dominoesOnTable;
	
	public Player player1;
	public Player player2;

	private final int HAND_SIZE = 7;


	//constructor
	public Table()
	{
		//make the dominoes
		dominoesOnTable = createDominoes();

		player1 = new Player( makeStartHand() );
		player2 = new Player( makeStartHand() );

		//remaining dominoes stay in dominoesOnTable
	}


	//Make a player starting hand for use the constructor
	private ArrayList<Domino> makeStartHand()
	{
		ArrayList<Domino> startHand = new ArrayList<Domino>();
		for (int i = 0; i < HAND_SIZE; i++){
			startHand.add( dominoesOnTable.remove(0) );
		}

		return startHand;
	}
	
	//Method to determine which player has the highest sum of domino dots.
	public int highestDomino()
	{
		int sumPlayer1 = getSumOfDots(player1);
		int sumPlayer2 = getSumOfDots(player2);
		
		System.out.println("player1: " + sumPlayer1);
		System.out.println("player2: " + sumPlayer2);
		
		if (sumPlayer1 > sumPlayer2)
		{
			System.out.println("\nPlayer 1 has the largest domino so"
								+ " they will place first");

			return 1;
		}
		else if (sumPlayer1 == sumPlayer2)
		{
			System.out.println("\nBoth players are tied for number of dots!");
			System.out.println("Chosing at random...");

			Random randomObject = new Random();
			int returnVal = randomObject.nextInt(2) + 1;
			System.out.println("Player " + returnVal + " goes first!");
			
			return returnVal;
		}
		else
		{
			System.out.println("\nPlayer 2 has the largest domino so he will place first");

			return 2;
		}
	}
	
	//Gets the sum of dots on a domino
	public int getSumOfDots(Player aPlayer)
	{
		int sumOfDots = 0;
		
		for (int i = 0; i < aPlayer.getHandSize(); i++)
		{
			int temp = aPlayer.getHand().get(i).getLeftSquare()
						+ aPlayer.getHand().get(i).getRightSquare();

			sumOfDots += temp;	
		}
		
		return sumOfDots;
	}
	
	// method to place a domino onto table from players hand 
	public void placeDomino(int player)
	{
		Random randomObject = new Random();
		if (player==1)		
		{
			ArrayList<Domino> hand1= player1.getHand();
			int dominoIndexOne = randomObject.nextInt(player1.getHandSize());
			System.out.print("Player 1 places:" + hand1.get(dominoIndexOne));
			player1.removeFromHand(dominoIndexOne);
		}else 
		{
			ArrayList<Domino> hand2=player2.getHand();
			int dominoIndexTwo = randomObject.nextInt(player2.getHandSize());
			System.out.print("Player 2 places: " +hand2.get(dominoIndexTwo));
			player2.removeFromHand(dominoIndexTwo);
			
			
		}
		
		
	}
		
	
	
	//Will print the current pieces on the table.
	public String toString()
	{
		return "Player1: " + player1 + "\n\nPlayer2: " + player2 + "\n\nTable: " + printTablePieces();
	}
	
	
	//need code added to create the correct number and combinations.
	private ArrayList<Domino> createUnsortedDominoes()
	{
		ArrayList<Domino> unsortedDominoes = new ArrayList<Domino>();
		//code creates 28 domino set 
		unsortedDominoes.add(new Domino(0,0));
		unsortedDominoes.add(new Domino(1,0));
		unsortedDominoes.add(new Domino(2,0));
		unsortedDominoes.add(new Domino(3,0));
		unsortedDominoes.add(new Domino(4,0));
		unsortedDominoes.add(new Domino(5,0));
		unsortedDominoes.add(new Domino(6,0));
		unsortedDominoes.add(new Domino(1,1));
		unsortedDominoes.add(new Domino(2,1));
		unsortedDominoes.add(new Domino(3,1));
		unsortedDominoes.add(new Domino(4,1));
		unsortedDominoes.add(new Domino(5,1));
		unsortedDominoes.add(new Domino(6,1));
		unsortedDominoes.add(new Domino(2,2));
		unsortedDominoes.add(new Domino(3,2));
		unsortedDominoes.add(new Domino(4,2));
		unsortedDominoes.add(new Domino(5,2));
		unsortedDominoes.add(new Domino(6,2));
		unsortedDominoes.add(new Domino(3,3));
		unsortedDominoes.add(new Domino(4,3));
		unsortedDominoes.add(new Domino(5,3));
		unsortedDominoes.add(new Domino(6,3));
		unsortedDominoes.add(new Domino(4,4));
		unsortedDominoes.add(new Domino(5,4));
		unsortedDominoes.add(new Domino(6,4));
		unsortedDominoes.add(new Domino(5,5));
		unsortedDominoes.add(new Domino(6,5));
		unsortedDominoes.add(new Domino(6,6));
	
		
		return unsortedDominoes;
	}
	
	private ArrayList<Domino> sortDominoes(ArrayList<Domino> dominoesToSort)
	{
		return Shuffle.shuffleDominoes(dominoesToSort);
	}
	
	private ArrayList<Domino> createDominoes()
	{
		ArrayList<Domino> someDominoes = createUnsortedDominoes();
		
		return sortDominoes(someDominoes);
	}
	
	
	//might need/want to make static later?
	//change to iterate through printing punctuation and toString of each domino.
	private String printTablePieces()
	{
		return dominoesOnTable.toString(); //change later
	}
    
}
