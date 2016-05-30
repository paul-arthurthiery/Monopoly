
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FenetreConnexion extends JFrame implements ActionListener
{
    
	private JTextField saisie;
    private JRadioButton rouge, vert, bleu, jaune; 
    private ButtonGroup radio;
    private FenetreJeu fenetreJeu;
    private FenetreConnexion fenetreConnexion;
    private Color couleur;
    private MonopolyInterface monopolyInterface;
    private int monNumero;
    private Joueur ceJoueur;
    
	public FenetreConnexion( MonopolyInterface monopolyInterface )
	{
		this.monopolyInterface = monopolyInterface;
		GridLayout gl = new GridLayout(0, 1);
		this.setLayout(gl);
		this.setSize(8000,800);
		JLabel label1 = new JLabel("Le Monopoly de Patoche");
		label1.setPreferredSize(new Dimension(500, 100));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setForeground(Color.RED);
		
		JLabel label2 = new JLabel ("Entrez votre nom :"); 
		this.add(label1);
		this.add(label2);

		
		
		this.saisie = new JTextField(10);
		saisie.addActionListener(this);
		this.add(saisie);
		
		JLabel label3 = new JLabel ("Choisissez une couleur :");
	    this.add(label3);
		
	    bleu = new JRadioButton("bleu");
		bleu.addActionListener(this);
		this.add(bleu);
		rouge = new JRadioButton("rouge");
		rouge.addActionListener(this);
		this.add(rouge);
		vert = new JRadioButton("vert");
		vert.addActionListener(this);
		this.add(vert);
		jaune = new JRadioButton ("jaune"); 
		jaune.addActionListener(this);
		this.add(jaune);
		
		radio = new ButtonGroup();
		radio.add(bleu);
		radio.add(rouge);
		radio.add(vert);
		radio.add(jaune);
	    
	    JButton suivant = new JButton("Créer le joueur");
		suivant.addActionListener(this);
		suivant.setActionCommand("Créer le joueur");
	    this.add(suivant);
	    
	    setVisible(true);
		
		
		
		
		
	}
		

		
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		switch(e.getActionCommand())
		{
			
			case "Créer le joueur":
			{		
				String valeur = saisie.getText();
				try 
				{
					if (bleu.isSelected())
					{
						couleur = Color.BLUE;
					}
					else if (rouge.isSelected())
					{
						couleur = Color.RED;
					}
					else if (vert.isSelected())
					{
						couleur = Color.GREEN;
					}
					else if (jaune.isSelected())
					{
						couleur = Color.YELLOW;
					}
					else
					{
						System.out.println("Choisissez une couleur");
					}
					monopolyInterface.setJoueur(couleur,valeur);
					monNumero = monopolyInterface.getJoueurs().size();
					ceJoueur = monopolyInterface.getJoueurs().get(monNumero);
				} 
				catch (RemoteException e1) 
				{
					e1.printStackTrace();
				}
			
				this.setVisible(false);
				try 
				{
					new FenetreJeu(monopolyInterface, ceJoueur);
				} 
				catch (HeadlessException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			}
			default:
			{
				break;
			}
		}
	}
	
}