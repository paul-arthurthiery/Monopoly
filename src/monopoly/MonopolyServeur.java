package monopoly;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	public TreeMap<Integer,CaseStandard> getCases() throws RemoteException
	{
		return this.casesStandards;
	}
	
	public synchronized TreeMap<Integer, CaseStandard> generateCases() throws RemoteException
	{
		BufferedReader fichier = null;
		
			try 
			{
				fichier = new BufferedReader( new FileReader( "/home/eleves/2019/yxie/workspace/monopoly/src/monopoly/caseStandard.txt" ) );
				String ligne;
				while((ligne = fichier.readLine()) != null)
				{
					String[] mots = ligne.split(",");
					CaseStandard nouvelleCase = new CaseStandard(mots[0], Integer.parseInt(mots[1]), Integer.parseInt(mots[2]), Integer.parseInt(mots[3]), null, Integer.parseInt(mots[4]));
					this.casesStandards.put(Integer.parseInt(mots[1]), nouvelleCase);
				}
			} 
			catch (IOException e) 
			{
			e.printStackTrace();
			}
			return casesStandards;
	}
	
	public synchronized Joueur perdre(TreeMap<Integer,Joueur> joueurs) throws RemoteException
	{
		Iterator <Integer> it = joueurs.keySet().iterator();
		Joueur perdant = null;
		while (it.hasNext())
		{
			Integer cle = it.next();
			int solde = joueurs.get(cle).getSolde();
			if(solde <= 0)
			{
				perdant = joueurs.get(cle);	
				joueurs.remove(cle);
			}
	
		}

		return (perdant);
	}
	
	public synchronized void payment(int monNumero) throws RemoteException
	{
		Joueur joueur = joueurs.get(monNumero);
		int position = joueur.getPosition();
		CaseStandard tile = casesStandards.get(position);
		if(tile.getCode() == 2 && tile.getBoss() != joueur)
		{
			tile.getBoss().addSolde(tile.getPrix());
			joueur.addSolde((-1)*tile.getPrix());
		}
		
	}
	
}
