/*
 * Table.java
 * 
 * This is the Class with the data structure that represents everything that is
 * currently in the game including: players, player hands, the dominoes on the
 * board.
 * 
 */
 
 import java.util.ArrayList;
 

public class Table
{
	private ArrayList<Domino> dominoesOnTable;
	
	public Player player1;
	public Player player2;
	
	
	//constructor
	public Table()
	{
		//make the dominoes
		dominoesOnTable = createDominoes();
		
		
		//move first seven to player1.hand or whatever
		
		//move next seven to player2
		
		//keep remaining in dominoesOnTable
		
		
	}
	
	
	//Will print the current pieces on the table.
	public String toString()
	{
		return "Player1: " + player1 + "\nPlayer2: " + player2 + "\nTable: " + printTablePieces();
	}
	
	
	//need code added to create the correct number and combinations.
	private ArrayList<Domino> createUnsortedDominoes()
	{
		ArrayList<Domino> unsortedDominoes = new ArrayList<Domino>();
		
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
