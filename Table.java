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
	private ArrayList<Domino> player1hand;
	private ArrayList<Domino> player2hand; 
	
	
	//constructor
	public Table()
	{
		//make the dominoes
		dominoesOnTable = createDominoes();
		
		
		//move first seven to player1.hand or whatever
		//pieces that are moved to hand should are removed from table 
		player1hand = new ArrayList<Domino>();
		for (int i=0; i <7; i++){
			player1hand.add(dominoesOnTable.get(i));
			dominoesOnTable.remove(i);
			
		}
		player1= new Player(player1hand);
		
		
		
		//move next seven to player2
		player2hand = new ArrayList<Domino>();
		for (int i=7; i<14; i++){
			player2hand.add(dominoesOnTable.get(i));
			dominoesOnTable.remove(i);
			
		}
		player2=new Player(player2hand);
		//keep remaining in dominoesOnTable
		
		
		
		
		
	}
	
	//method to determine which player has the highest domino 
	public int highestDomino (){
		
		int max1=0;
		int max2=0;
		for (int i=0; i<7;i++){
			
			int sum1= player1hand.get(i).getLeftSquare()+player1hand.get(i).getRightSquare();
			
			if (sum1>max1){
				
				max1=sum1;
			}
			
		}	
		 
		 for(int j=0; j<7;j++){
			 
			 int sum2= player2hand.get(j).getLeftSquare()+player2hand.get(j).getRightSquare();
			 
			 if (sum2>max2){
				 max2=sum2;
				 
			 }
		 }
		
		
		if (max1>max2){
			System.out.print("\nPlayer 1 has the largest domino so he will place first");
			//returns number of player that goes first in case we need it in DominoGame
			return 1 ;
		
	
		}else if(max1==max2){
			//we could also make the player who starts random 
			System.out.print("\nBoth players are tied for largest domino Player 1 places first");
			
			return 1;
		}else{
			System.out.print("\nPlayer 2 has the largest domino so he will place first");
			return 2;
			
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
