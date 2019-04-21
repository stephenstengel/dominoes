/*
 * Player.java
 */

import java.util.ArrayList;

public class Player
{
	private ArrayList<Domino> hand;
	private boolean isWinner;
	
	
	//constructor. Accepts arraylist of dominoes to use as hand.
	public Player(ArrayList<Domino> inputHand)
	{
		hand = inputHand;
		isWinner = false;
	}
	public boolean getIsWinner()
	{
		
		return isWinner;
		
	}
	public Domino removeFromHand(int dIndex)
	{
		
		return hand.remove(dIndex);
		
	}
	// take from domino from table and add it to players hand 
	public void addToHand(Domino dominoesFromTable)
	{
		
		hand.add(dominoesFromTable);
		
		
	}
	
	public String handToString()
	{
		String outString = "";
		
		//iterate though arraylist and print each domino
		//Note: the dominoes have a defined toString() method.
		for (int i=0; i<hand.size(); i++){
			outString += hand.get(i);
			
		}
		
		return outString;
	}
	
	
	public void setPlayerAsWinner()
	{
		isWinner = true;
	}
	
	//for the whole player class.
	public String toString()
	{
		String outString = "\nWinner:";
		outString += isWinner + "\n";
		outString += handToString();
		
		return outString;
	}
	
	public int getHandSize()
	{
		return hand.size();
	}
	
	public ArrayList<Domino> getHand()
	{
		ArrayList<Domino> copy = new ArrayList<Domino>();
		copy = hand;
		
		return copy;
	}
	
	public boolean checkIfHandEmpty()
	{
		if ( this.getHandSize() <= 0 )
		{
			return true;
		}
		
		return false;
	}
	
	//checks if hand is empty or if player was set as winner by something else.
	public boolean checkAndSetIfWinner()
	{
		if ( this.checkIfHandEmpty() || this.getIsWinner() )
		{
			setPlayerAsWinner();

			return true;
		}
		
		return false;
	}
}
