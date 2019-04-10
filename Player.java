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
	
	
	public String handToString()
	{
		String outString = "";
		
		//iterate though arraylist and print each domino
		//Note: the dominoes have a defined toString() method.
		
		return outString;
	}
	
	
	public void setPlayerAsWinner()
	{
		isWinner = true;
	}
	
	//for the whole player class.
	public String toString()
	{
		String outString = "";
		outString += isWinner + "\n";
		outString += handToString();
		
		return outString;
	}
}
