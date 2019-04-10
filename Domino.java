/*
 * Domino.java
 * 
 * Contains the data structure for an individual domino
 * 
 */
 
import java.util.ArrayList;
 

public class Domino
{
    private int leftSquare;
    private int rightSquare;
    
    //constructor
    public Domino(int leftInput, int rightInput)
    {
		setSquareValues(leftInput, rightInput);
		
		//System.out.println("Domino " + this + " created!");
	}
	
	
	private void setSquareValues(int leftInput, int rightInput)
	{
		leftSquare = leftInput;
		rightSquare = rightInput;
	}
	
	
	//getter methods. Or we could just make leftSquare and rightSquare public.
	public int getLeftSquare()
	{
		return leftSquare;
	}
	
	public int getRightSquare()
	{
		return rightSquare;
	}
	
	//print this domino
	public String toString()
	{
		return "[" + leftSquare + "|" + rightSquare + "]";
	}
    
}
