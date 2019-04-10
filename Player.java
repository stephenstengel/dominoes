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
	
	
	public void displayHand()
	{
		//iterate though arraylist and print each domino
		//Note: the dominoes have a defined toString() method.
	}
	
	
	public void setPlayerAsWinner()
	{
		isWinner = true;
	}
}
