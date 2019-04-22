/*
 * Player.java
 * 
 * This class hold all information that deals with each individual player
 * 
 */

import java.util.ArrayList;

public class Player
{
	//fields 
	private ArrayList<Domino> hand;
	private boolean isWinner;
	private int numberOfDots;
	
	
	//constructor accepts arraylist of dominoes to use as hand.
	public Player(ArrayList<Domino> inputHand)
	{
		hand = inputHand;
		isWinner = false;
	}
	//getter method retrieves value in isWinner field
	public boolean getIsWinner()
	{
		
		return isWinner;
		
	}
	//method removes a domino from hand ArrayList 
	public Domino removeFromHand(int dIndex)
	{
		
		return hand.remove(dIndex);
		
	}
	//take domino from table and add it to players hand 
	public void addToHand(Domino dominoesFromTable)
	{
		
		hand.add(dominoesFromTable);
		
		
	}
	//returns dominoes in players hand as String 
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
	
	//method sets isWinner field to true when player wins game
	public void setPlayerAsWinner()
	{
		isWinner = true;
	}
	
	//returns information for the whole player class
	public String toString()
	{
		return  "\nWinner: " + isWinner
				+ "\nhand: " + handToString()
				+ "\nDots: " + getNumberOfDots()
				+ "\n";
		
	}
	
	//returns number of dominoes in players hand 
	public int getHandSize()
	{
		return hand.size();
	}
	//returns dominoes in players hand as an ArrayList<Domino>
	public ArrayList<Domino> getHand()
	{
		ArrayList<Domino> copy = new ArrayList<Domino>();
		copy = hand;
		
		return copy;
	}
	//returns true if the player has no dominoes in their hand 
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
	
	//returns sum of domino dots 
	public int getNumberOfDots()
	{
		return numberOfDots;
	}
	//setter method for numberOfDots field 
	public void setNumberOfDots(int dots)
	{
		this.numberOfDots = dots;
	}
}
