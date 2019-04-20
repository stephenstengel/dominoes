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
	private ArrayList<Domino> boneYard;
	private ArrayList<Domino> lineOfPlay;
	
	private final int NUM_PLAYERS = 2;
	public Player[] players = new Player[NUM_PLAYERS];
	
	// stores most recently placed domino
	private Domino placed;
	// keeps track of whos turn it is 
	private int whoseTurn;

	private final int HAND_SIZE = 10;


	//constructor
	public Table()
	{
		//make the dominoes
		boneYard = createDominoes();
		lineOfPlay = new ArrayList<Domino>();

		for (int i = 0; i < NUM_PLAYERS; i++)
		{
			players[i] = new Player( makeStartHand() );
		}


		//remaining dominoes stay in boneYard
	}


	//Make a player starting hand for use the constructor
	private ArrayList<Domino> makeStartHand()
	{
		ArrayList<Domino> startHand = new ArrayList<Domino>();
		for (int i = 0; i < HAND_SIZE; i++){
			startHand.add( boneYard.remove(0) );
		}

		return startHand;
	}
	
	//Method to determine which player has the highest sum of domino dots.
	public int highestDomino()
	{
		int sumPlayer1 = getSumOfDots(players[0]);
		int sumPlayer2 = getSumOfDots(players[1]);
		
		System.out.println("player1: " + sumPlayer1);
		System.out.println("player2: " + sumPlayer2);
		
		if (sumPlayer1 > sumPlayer2)
		{
			System.out.println("\nPlayer 1 has the largest domino so"
								+ " they will place first");

			return (int)0;
		}
		else if (sumPlayer1 == sumPlayer2)
		{
			System.out.println("\nBoth players are tied for number of dots!");
			System.out.println("Chosing at random...");

			Random randomObject = new Random();
			int randomPlayerNumber = randomObject.nextInt(2) + 1;
			System.out.println("Player " + randomPlayerNumber + " goes first!");
			
			return randomPlayerNumber;
		}
		else
		{
			System.out.println("\nPlayer 2 has the largest domino so he will place first");

			return (int)1;
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
	// getter method returns most recently placed domino 
	public Domino getPlaced(){
		
		return placed;
		
	}
	// getter method returns whos turn it is to place a domino
	public int getTurn(){
		
		return whoseTurn;
	}
	
	
	
	// method has player1/player2 place a domino based on variable whoseTurn 
	public void nextMove()
	{
		int right;
		int left;
	
		if (whoseTurn==0)
		{
			ArrayList<Domino> pOneHand= players[0].getHand();
			right=placed.getRightSquare();
			left= placed.getLeftSquare();
			for (int i = 0; i < players[0].getHandSize(); i++)
			{
				
				int R1= pOneHand.get(i).getRightSquare();
				int L1= pOneHand.get(i).getLeftSquare();
				if (right==R1|right==L1|left==R1|right==L1)
				
				{
					
					System.out.print("\nPlayer 1 places:" + pOneHand.get(i));
					placed= pOneHand.get(i);
					
					players[0].removeFromHand(i);
					whoseTurn=1;
					break;
					
				}else 
				{
					// right now set to skip player if they don't have needed domino
					whoseTurn=1;
				}

				
			}
			
		}
		else if(whoseTurn==1)
		{
			ArrayList<Domino> pTwoHand= players[1].getHand();
			right=placed.getRightSquare();
			left= placed.getLeftSquare();
			for (int i=0;i<players[1].getHandSize();i++)
			{
				int R2= pTwoHand.get(i).getRightSquare();
				int L2=pTwoHand.get(i).getLeftSquare();
				if (right==R2|right==L2| left== R2| left==L2)
				{
					System.out.print("\nPlayer 2 places: " + pTwoHand.get(i));
					placed= pTwoHand.get(i);
					
					players[1].removeFromHand(i);
					whoseTurn=0;
					break;
				}
				else
				{
					
					whoseTurn=0;
					
				}
			}
		}
		
	}
	
	// method to place first domino onto table from players hand 
	public void placeFirstDomino(int playerToTakeFrom)
	{
		Random randomObject = new Random();
		
		int dominoIndex = randomObject.nextInt(players[playerToTakeFrom].getHandSize());
		
		System.out.print("Player " + (playerToTakeFrom + 1) + " places:" + players[playerToTakeFrom].getHand().get(dominoIndex));
		
		placed = players[playerToTakeFrom].removeFromHand(dominoIndex);
		
		addToRightOfLine(placed);
		updateTurn();
	}
		
	
	
	//Will print the current pieces on the table.
	public String toString()
	{
		return "Player1: " + players[0] + "\nPlayer2: " + players[1] + "\nTable: " + printTablePieces() + "\nLine of play: " + printLineOfPlay() + "\n\n";
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
		return boneYard.toString(); //change later
	}
	
	//Returns the contents of the line of play as a string.
	public String printLineOfPlay()
	{
		return lineOfPlay.toString();
	}
    
    //Adds to the left of the line
    private void addToLeftOfLine(Domino dominoToAdd)
    {
		lineOfPlay.add(0, dominoToAdd);
	}
	
	//Adds to the right of the line.
	private void addToRightOfLine(Domino dominoToAdd)
	{
		lineOfPlay.add(dominoToAdd);
	}
	
	//Updates the current turn
	private void updateTurn()
	{
		whoseTurn = (whoseTurn % NUM_PLAYERS);
	}
	
	
}
