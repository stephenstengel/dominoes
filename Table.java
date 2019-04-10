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
		
		//move first seven to player1.hand or whatever
		
		//move next seven to player2
		
		//keep remaining in dominoesOnTable
		
		
	}
	
	
	//Will print the current pieces on the table.
	public String toString()
	{
		//call player1.toString
		
		//player2.toString()
		
		//printTablePieces()
		
		return player1 + "\n" + player2 + "\n" + printTablePieces();
	}
	
	
	private ArrayList<Domino> createUnsortedDominoes()
	{
		ArrayList<Domino> unsortedDominoes = new ArrayList<Domino>();
		
		return unsortedDominoes;
	}
	
	
	//might need/want to make static later?
	private String printTablePieces()
	{
		return dominoesOnTable.toString(); //change later
	}
    
}
