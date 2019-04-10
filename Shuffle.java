/*
 * Project 1
 * 
 * Shuffle Class has a method to shuffle input ArrayList of Dominoes.
 * 
 */

import java.util.Collections;
import java.util.ArrayList;

public class Shuffle
{
	public static ArrayList<Domino> ShuffleDominoes(ArrayList<Domino> dominoesToShuffle)
	{
		Collections.shuffle(dominoesToShuffle);
		
		return dominoesToShuffle;
	}
	
}
