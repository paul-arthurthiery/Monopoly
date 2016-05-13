package monopoly;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class MonopolyServeur extends UnicastRemoteObject implements MonopolyInterface
{
	private TreeMap<Integer,Joueur> joueurs; //Integer = numero du joueur
	private TreeMap<Integer,CaseStandard> casesStandards;
	//private ArrayList<Color> couleur;
	private TreeMap<String,Color> couleur;
	private int i;
	private int tour; //numero de joueur en cours de jeu


	
	public MonopolyServeur() throws RemoteException
	{
		super();
		joueurs = new TreeMap<Integer,Joueur>();
		couleur = new TreeMap<String,Color>();
		couleur.put("Rouge",Color.RED);
		couleur.put("Bleu",Color.BLUE);
		couleur.put("Vert",Color.GREEN);
		couleur.put("Gris",Color.GRAY);
		i=0;
		tour=1;

	}
	
	
	public static void main(String[] args) 
	{
		try
		{
			MonopolyServeur monopoly = new MonopolyServeur();
			System.out.println("Le serveur est créé");
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("MONOPOLY", monopoly);
			System.out.println("monopoly enregistré, attente de connexions");
		}
		catch (Exception e)
		{
			System.err.println("Monopoly serveur exception: " + e.getMessage());
		}
	}


	@Override
	public int lanceDes(int numeroJoueur) throws RemoteException 
	{
		Des des = new Des();
		return(des.getDes());
		
	}



	@Override
	public TreeMap<Integer,Joueur> getJoueurs() throws RemoteException 
	{
		return(this.joueurs);
		
	}


	@Override
	public int getEtatCase(int numeroCase) throws RemoteException 
	{
		CaseStandard tile = casesStandards.get(numeroCase);
		return (tile.getCode());
	}


	@Override
	public CaseStandard getCaseStandard(int numeroCase) throws RemoteException 
	{
		return(casesStandards.get(numeroCase));
	}


	@Override
	public synchronized void achat(int numeroJoueur, int numeroCase) throws RemoteException 
	{
		CaseStandard tile = casesStandards.get(numeroCase);
		Joueur joueur = joueurs.get(numeroJoueur);
		joueur.addSolde(tile.getPrix()*(-1));
		tile.setBoss(joueur);
	}


	@Override
	public synchronized void payement(int numeroJoueur, int numeroCase) throws RemoteException 
	{
		CaseStandard tile = casesStandards.get(numeroCase);
		Joueur joueur = joueurs.get(numeroJoueur);
		joueur.addSolde(tile.getPrix()*(-1));
		tile.getBoss().addSolde(tile.getPrix());
	}
	
	
	public synchronized Color getCouleur( String color ) throws RemoteException
	{
		Iterator <String> it = couleur.keySet().iterator();
		Color a = null;
		while (it.hasNext())
		{
			String cle = it.next();
			if(cle==color)
			{
				a = couleur.get(cle);
				couleur.remove(cle);
			}
			else
			{
				System.err.println("Couleur introuvable ou déjà prise");
			}
		}
		return a;
	}
	
	public synchronized void setJoueur(Color couleur, String nom) throws RemoteException
	{
		Iterator <Integer> it = joueurs.keySet().iterator();
		while (it.hasNext())
		{
			Integer cle = it.next();
			String name = joueurs.get(cle).getNom();
			if(name==nom)
			{
				System.err.println("Ce nom de joueur existe déjà");
			}
			else
			{
				joueurs.put(joueurs.size()+1,new Joueur(nom,couleur));
			}
		}
	}
	
	public synchronized boolean ready() throws RemoteException
	{
		i = i+1;
		if (i==joueurs.size())
		{
			System.out.println("Début du jeu");
			return true;
		}
		return false;
	}
	
	public synchronized void setTour( int tour ) throws RemoteException
	{
		this.tour = tour;
	}
	
	public synchronized int getTour() throws RemoteException
	{
		return(this.tour);
	}
	
	public synchronized boolean jouer(int numeroJoueur) throws RemoteException, InterruptedException
	{

			while(true)
			{
				if(numeroJoueur==this.tour)
				{
					return true;
				}
				else
				{
					Thread.sleep(1000);
				}
			}

	}
	
}
