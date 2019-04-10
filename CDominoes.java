/*
 * Project 1
 * Contains the data structure for an individual domino
 * 
 */
 
import java.util.ArrayList;
 

public class CDominoes
{
    private int left;
    private int right;
    
    //constructor
    CDominoes(int leftInput, int rightInput)
    {
		setSquares(leftInput, rightInput);
	}
	
	
	//called by constructor to set the values of the squares in this domino.
	private void setSquares(int leftInput, int rightInput)
	{
		left = leftInput;
		right = rightInput;
	}
    
}
