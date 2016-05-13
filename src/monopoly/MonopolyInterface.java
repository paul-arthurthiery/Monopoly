package monopoly;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeMap;

public interface MonopolyInterface extends Remote
{
	int lanceDes(int numeroJoueur) throws RemoteException;
	TreeMap<Integer,Joueur> getJoueurs() throws RemoteException;
	int getEtatCase(int numeroCase) throws RemoteException;
	void achat(int numeroJoueur, int numeroCase) throws RemoteException;
	CaseStandard getCaseStandard(int numerocase) throws RemoteException;
	void payement(int numeroJoueur, int numeroCase) throws RemoteException;
	Color getCouleur( String color )throws RemoteException;
	void setJoueur(Color couleur, String nom) throws RemoteException;
	boolean ready() throws RemoteException;
	void setTour( int tour ) throws RemoteException;
	int getTour() throws RemoteException;
	boolean jouer(int numeroJoueur) throws RemoteException, InterruptedException;
}
