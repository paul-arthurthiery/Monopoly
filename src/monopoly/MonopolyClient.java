

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MonopolyClient 
{

	public static void main(String[] args) 
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry();
			MonopolyInterface monopolyInterface = (MonopolyInterface)registry.lookup("MONOPOLY");
			System.out.println("monopolyInterface trouvé");
			new FenetreConnexion(monopolyInterface);
			System.out.println("Fenêtre de connexion crée");
			int monNumero = 0;
		}
		

		catch (Exception e) 
		{
			System.err.println(e);
		}

	}
	

}
