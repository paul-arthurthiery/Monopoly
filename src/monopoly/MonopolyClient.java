package monopoly;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MonopolyClient 
{

	public static void main(String[] args) 
	{
		try
		{
			
			//String server ="adresse";
			Registry registry = LocateRegistry.getRegistry();
			MonopolyInterface monopolyInterface = (MonopolyInterface)registry.lookup("MONOPOLY");
			System.out.println("monopolyInterface trouvé");
			
			new Fenetre_connexion(monopolyInterface);
			int monNumero = 0;
	/*		System.out.println("Entrez votre couleur : ");
			BufferedReader buffer01 = new BufferedReader( new InputStreamReader( System.in ) );
			String couleur = buffer01.readLine();
			Color a = monopolyInterface.getCouleur(couleur);
			System.out.println("Entrez votre nom : ");
			BufferedReader buffer02 = new BufferedReader( new InputStreamReader( System.in ) );
			String nom = buffer02.readLine();
			monopolyInterface.setJoueur(a,nom);
*/			monNumero = monopolyInterface.getJoueurs().size();
			System.out.println("Joueur créé");
			System.out.println("Êtes vous prêt ? ");
			BufferedReader buffer03 = new BufferedReader( new InputStreamReader( System.in ) );
			
			while(monopolyInterface.jouer(monNumero))
			{
				
			}
			
		}
		catch (Exception e) 
		{
			System.err.println(e);
		}

	}
	

}
