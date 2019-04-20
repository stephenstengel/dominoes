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

	private int whoseTurn;

	private final int HAND_SIZE = 10;
	
	private boolean areThereAnyWinner = false;


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
			int randomPlayerNumber = randomObject.nextInt(2);
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
		int right = getRightmostSquareInLine();
		int left = getLeftmostSquareInLine();
	
		boolean didPutPieceThisTurn = false;
		
		while (!didPutPieceThisTurn)
		{
			if ( isMatchInHand(players[whoseTurn]) )
			{
				for (int i = 0; i < players[whoseTurn].getHandSize(); i++)
				{
					int thisRight = players[whoseTurn].getHand().get(i).getRightSquare();
					int thisLeft = players[whoseTurn].getHand().get(i).getLeftSquare();
					
					if (left == thisRight)
					{
						System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
						addToLeftOfLine( players[whoseTurn].removeFromHand(i) );
						didPutPieceThisTurn = true;
						
						break;
					}
					else if (left == thisLeft)
					{
						System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
						addToLeftOfLine( Domino.rotateDomino( players[whoseTurn].removeFromHand(i) ) );
						didPutPieceThisTurn = true;
						
						break;
					}
					else if (right == thisRight)
					{
						System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
						addToRightOfLine( Domino.rotateDomino( players[whoseTurn].removeFromHand(i) ) );
						didPutPieceThisTurn = true;
						
						break;
					}
					else if (right == thisLeft)
					{
						System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
						addToRightOfLine( players[whoseTurn].removeFromHand(i) );
						didPutPieceThisTurn = true;

						break;
					}
					else
					{
						System.out.println("ERROR WILL ROBINSON!!");
					}
					
				}
			}
			else
			{
				while ( !isMatchInHand( players[whoseTurn] ) && !isBoneyardEmpty() )
				{
					System.out.println("boneyard loop");
					//need to check if boneyard empty as well.
					//if they need to draw and it is empty the other player should be set as winner.
					drawFromBoneyard();
				}
			}
			
		}
		
		
		
		System.out.println("switching turns.");
		updateTurn();
	}
	
	
	//Adds a domino from the boneyard to the current player's hand.
	private void drawFromBoneyard()
	{
		//need to put a domino in there!
		players[whoseTurn].addToHand( this.boneYard.remove(0) );
	}
	
	
	//checks if there is a match in hand to one of the ends of the lineOfPlay
	private boolean isMatchInHand(Player p)
	{
		int right = getRightmostSquareInLine();
		int left = getLeftmostSquareInLine();
		
		for (int i = 0; i < players[whoseTurn].getHandSize(); i++)
		{
			int thisRight = players[whoseTurn].getHand().get(i).getRightSquare();
			int thisLeft = players[whoseTurn].getHand().get(i).getLeftSquare();
			
			if ( (left == thisRight) || ( left == thisLeft) || (right == thisRight) || (right == thisLeft) )
			{
				return true;
			}
		}
		
		return false;
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
		return "Player1: " + players[0] + "\nPlayer2: " + players[1] + "\nBoneyard: " + printBoneyard() + "\nLine of play: " + printLineOfPlay() + "\n\n";
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
	private String printBoneyard()
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
		whoseTurn = (whoseTurn + 1) % NUM_PLAYERS;
	}
	
	//Checks if there are any winners on the table.
	//Returns -1 if none, or the number of the player if they are a winner.
	public int checkAreThereAnyWinners()
	{
		for (int i = 0; i < NUM_PLAYERS; i++)
		{
			if (this.players[i].checkIfWinner() )
			{
				return i;
			}
		}
		
		return -1;
	}
	
	//Checks if boneyard is empty
	public boolean isBoneyardEmpty()
	{
		if ( boneYard.size() <= 0 )
		{
			return true;
		}
		
		return false;
	}
	
	//Gets the value of the rightmost square on the lineOfPlay
	private int getLeftmostSquareInLine()
	{
		return lineOfPlay.get(0).getLeftSquare();
	}
	
	//Get the value of the leftmost square on the lineOfPlay
	private int getRightmostSquareInLine()
	{
		return lineOfPlay.get( lineOfPlay.size() - 1 ).getRightSquare();
	}
}
