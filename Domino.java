/*
 * Project 1
 * Contains the data structure for an individual domino
 * 
 */
 
import java.util.ArrayList;
 

public class Domino
{
    private int left;
    private int right;
    
    //constructor
    Domino(int leftInput, int rightInput)
    {
		setSquares(leftInput, rightInput);
		
		//test printout
		//System.out.println("Domino " + left + right + " created!");
		
	}
	
	
	//called by constructor to set the values of the squares in this domino.
	private void setSquares(int leftInput, int rightInput)
	{
		left = leftInput;
		right = rightInput;
	}
	
	
	//getter methods. Or we could just make left and right public.
	public int getLeft()
	{
		return left;
	}
	
	public int getRight()
	{
		return right;
	}
    
}
