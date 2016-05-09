package monopoly;

import java.awt.Color;

public class Joueur
{
	private int numeroJoueur; //numéro de joueur
	private String nom;
	private Color couleur;
	private int position;
	private int solde;
	
	public Joueur( String nom, Color couleur)
	{
		this.nom = nom;
		this.couleur = couleur;
	}
	
	public int getNumeroJoueur()
	{
		return( this.numeroJoueur );
	}
	
	public String getNom()
	{
		return( this.nom );
	}
	
	public Color getCouleur()
	{
		return( this.couleur );
	}
	
	public int getPosition()
	{
		return( this.position );
	}
	
	public int getSolde()
	{
		return( this.solde );
	}
	
	public void setNumeroJoueur( int numeroJoueur )
	{
		this.numeroJoueur = numeroJoueur;
	}
	
	public void setNom( String nom )
	{
		this.nom = nom;
	}
	
	public void setCouleur( Color couleur )
	{
		this.couleur = couleur;
	}
	
	public void setPosition( int position )
	{
		this.position = position;
	}
	
	public void setSolde( int solde )
	{
		this.solde = solde;
	}
	
	public void addSolde( int solde )
	{
		this.solde = this.solde + solde;
	}
	
	public void avancer(int deplacement)
	{
		this.position += deplacement;
		if (position > 2)
		{	
			position -= 2; 
		}
		
	}
	
}
