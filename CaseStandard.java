package monopoly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class CaseStandard extends Case
{	

	private int code; //type de case
	//0 : case vide --- (0,0,null,occupation) ;
	//1 : possible d'acheter une maison --- (1,0,null,occupation) ;
	//2 : maison déjà acheté par boss --- (2,maison,boss,occupation);
	private int maison;
	private Joueur boss;
	private TreeMap<Integer,Joueur> occupation;
	private int prix;
	
	public CaseStandard(String adresse, int numero, int code, int maison,Joueur boss,int prix) 
	{		
		super(adresse, numero);
		this.code = code;
		this.maison = maison;
		this.boss = boss;
		this.prix = prix;
		occupation = new TreeMap<Integer,Joueur>();
		
	}
	public Joueur getBoss()
	{
		return( this.boss );
	}
	
	public void setBoss(Joueur joueur)
	{
		this.boss = joueur;
	}
	
	public TreeMap<Integer, Joueur> getOccupation()
	{
		return occupation;
	
			
	}

	public int getCode()
	{
		return( this.code );
	}
	
	public int getMaison()
	{
		return( this.maison );
	}
	
	public void setCode( int code )
	{
		this.code = code;
	}
	
	public void setMaison( int maison )
	{
		this.maison = maison;
	}
	
	public void setPrix(int prix)
	{
		this.prix = prix;
	}
	
	public int getPrix()
	{
		return prix;
	}
	
	public void addOccupation( Joueur joueur )
	{
		if( this.numero == joueur.getPosition())
		{
			occupation.put(joueur.getNumeroJoueur(), joueur);
		}
		else
		{
			System.err.println("bug");
		}
	}
	
	public void supprimerOccupation( Joueur joueur )
	{
		if ( occupation.containsValue(joueur) )
		{
			occupation.remove(joueur.getNumeroJoueur());
		}
		else
		{
			System.err.println("Ce joueur n'existe pas.");
		}
	}
	
	

}
