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
	
	//Returns the sum of dots on dominoes in hand. Also saves it in a field.
	public int getSumOfDots(Player aPlayer)  //could add a global field for number of dots.
	{
		int sumOfDots = 0;
		
		for (int i = 0; i < aPlayer.getHandSize(); i++)
		{
			int temp = aPlayer.getHand().get(i).getLeftSquare()
						+ aPlayer.getHand().get(i).getRightSquare();

			sumOfDots += temp;	
		}

		if ( aPlayer.getHandSize() == 0 )
		{
			aPlayer.setNumberOfDots( (int) 0 );
			return (int) 0;
		}
		
		aPlayer.setNumberOfDots(sumOfDots);
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
	
	
	//Method for playing the game. Returns the number of winning player.
	//If the game is blocked, returns -1 if player1 blocked, and -2 if blocked by player2.
	public int playGame()
	{
		System.out.println("Entering playGame");
		while ( !isGameOver() )
		{
			while ( !isMatchInHand() )
			{
				if ( isBoneyardEmpty() )
				{
					//count dots with getSumOfDots to save sums to fields.
					//return -1 to indicate tie.
					getSumOfDots(players[0]);
					getSumOfDots(players[1]);

					System.out.println("Player " + (whoseTurn + 1) + " is unable to place a domino!");
					
					return -1 - whoseTurn;
				}
				
				drawFromBoneyard();
			}
			System.out.println("Entering play a piece part");
			//play
			placeAPiece();
			
			
			//switch turns
			updateTurn();
		}
		
		System.out.println("Entering winner check loop");
		
		return whoWon();
	}
	
	private void placeAPiece()
	{
		int right = getRightmostSquareInLine();
		int left = getLeftmostSquareInLine();
		
		for (int i = 0; i < players[whoseTurn].getHandSize(); i++)
		{
			int thisRight = players[whoseTurn].getHand().get(i).getRightSquare();
			int thisLeft = players[whoseTurn].getHand().get(i).getLeftSquare();
			
			if (left == thisRight)
			{
				System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
				addToLeftOfLine( players[whoseTurn].removeFromHand(i) );
				
				break;
			}
			else if (left == thisLeft)
			{
				System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
				addToLeftOfLine( Domino.rotateDomino( players[whoseTurn].removeFromHand(i) ) );
				
				break;
			}
			else if (right == thisRight)
			{
				System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
				addToRightOfLine( Domino.rotateDomino( players[whoseTurn].removeFromHand(i) ) );
				
				break;
			}
			else if (right == thisLeft)
			{
				System.out.println("\nPlayer " + (whoseTurn + 1) + " places: " + players[whoseTurn].getHand().get(i) );
				addToRightOfLine( players[whoseTurn].removeFromHand(i) );
	
				break;
			}
			else
			{
				System.out.println("Player " + (whoseTurn + 1) + " could not place domino number " + i);
			}
			
		}
	}
	
	
	//Checks if the game is over
	private boolean isGameOver()
	{
		for (int i = 0; i < NUM_PLAYERS; i++)
		{
			if ( players[i].checkAndSetIfWinner() )
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	//Returns the winner. Call only if game was not blocked.
	//Returns 0 for player1, 1 for player 2, and -99 for error.
	//Also, updates dot totals.
	private int whoWon()
	{
		for (int i = 0; i < NUM_PLAYERS; i++)
		{
			if ( players[i].checkAndSetIfWinner() )
			{
				return i;
			}
		}
		
		return -99;
	}
	
	
	//Adds a domino from the boneyard to the current player's hand.
	private void drawFromBoneyard()
	{
		//need to put a domino in there!
		players[whoseTurn].addToHand( this.boneYard.remove(0) );
	}
	
	
	//checks if there is a match in hand to one of the ends of the lineOfPlay
	private boolean isMatchInHand()
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
		
	
	
	//Will print the current state of the table.
	public String toString()
	{
		//Update dot totals.
		for (int i = 0; i < NUM_PLAYERS; i++)
		{
			getSumOfDots(players[i]);
		}

		return "Player1: " + players[0]
				+ "\nPlayer2: " + players[1]
				+ "\nBoneyard: " + printBoneyard()
				+ "\nLine of play: " + printLineOfPlay()
				+ "\n\n";
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
			if (this.players[i].checkAndSetIfWinner() )
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
