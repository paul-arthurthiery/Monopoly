package monopoly;

import java.util.TreeMap;

public class Joueurs 
{
	private TreeMap<Integer,Joueur> joueurs;
	
	public Joueurs()
	{
		this.joueurs = null;
	}
	
	public void addJoueur( Joueur joueur )
	{
		if ( joueurs.containsValue(joueur) )
		{
			System.out.println("Ce joueur existe déjà");
		}
		else
		{
			joueurs.put(joueur.getNumeroJoueur(), joueur);
		}
	}
	
}
