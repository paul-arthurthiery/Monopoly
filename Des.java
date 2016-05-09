package monopoly;

import java.util.Random;

public class Des 
{
	private Random aRandom;
	private int randomNumber;
	public Des()
	{		 
		Random aRandom = new Random();
		int range = 1; //6
		int fraction = (int) (range * aRandom.nextDouble());
		randomNumber =  (int)(fraction + 1);    
	}
	
	public void affiche()
	{
		System.out.println(randomNumber);
	}
	
	public int getDes()
	{
		return ( randomNumber);
	}
}

