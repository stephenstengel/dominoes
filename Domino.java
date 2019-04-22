/*
 * Domino.java
 * 
 * Contains the data structure for an individual domino
 * 
 */
 
import java.util.ArrayList;
 

public class Domino
{
	//fields 
    private int leftSquare;
    private int rightSquare;
    
    //constructor takes two integers and creates a single domino
    public Domino(int leftInput, int rightInput)
    {
		setSquareValues(leftInput, rightInput);
		
		//System.out.println("Domino " + this + " created!");
	}
	
	// used by the contructor to set values of domino
	private void setSquareValues(int leftInput, int rightInput)
	{
		leftSquare = leftInput;
		rightSquare = rightInput;
	}
	
	
	//getter methods for left and right values of domino 
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
	
	//Rotates a domino in place.
	public static Domino rotateDomino(Domino d)
	{
		return new Domino(d.rightSquare, d.leftSquare);
	}
    
}
