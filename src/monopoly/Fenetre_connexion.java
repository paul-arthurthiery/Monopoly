package monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Fenetre_connexion extends JFrame implements ActionListener
{
    private JTextField saisie;
    private JRadioButton b1, b2, b3, b4; 
    private ButtonGroup radio;
    private Color couleur;
    private MonopolyInterface monopolyInterface;
    
	public Fenetre_connexion(MonopolyInterface monopolyInterface)
	{
		
		setVisible(false);
		this.monopolyInterface = monopolyInterface;
		
		GridLayout gl = new GridLayout(0, 1);
		this.setLayout(gl);
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel label1 = new JLabel("Bienvenue dans le Monopoly");
		label1.setPreferredSize(new Dimension(500, 100));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setForeground(Color.RED);
		
		JLabel label2 = new JLabel ("Entrez votre nom :"); 
		this.add(label1);
		this.add(label2);
		
		
		
		this.saisie = new JTextField(10);
		saisie.addActionListener(this);
		saisie.setActionCommand("SAISIE");
		this.add(saisie);
		
		JLabel label3 = new JLabel ("Choisissez une couleur :");
	    this.add(label3);
		
	    b1 = new JRadioButton("bleu");
		b1.addActionListener(this);
		this.add(b1);
		b2 = new JRadioButton("rouge");
		b2.addActionListener(this);
		this.add(b2);
		b3 = new JRadioButton("vert");
		b3.addActionListener(this);
		this.add(b3);
		b4 = new JRadioButton ("jaune"); 
		b4.addActionListener(this);
		this.add(b4);
		
		radio = new ButtonGroup();
		radio.add(b1);
		radio.add(b2);
		radio.add(b3);
		radio.add(b4);
	    
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
		case "bleu":
		{
			couleur = Color.BLUE;
			break;
		}
		case "rouge":
		{
			couleur = Color.RED;
			break;
		}
		case "vert":
		{
			couleur = Color.GREEN;
			break;
		}
		case "jaune":
		{
			couleur = Color.YELLOW;
			break;
		}
		case "Créer le joueur":
		{			
			String valeur = saisie.getText();
			try 
			{
				monopolyInterface.setJoueur(couleur,valeur);
			} 
			catch (RemoteException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(DO_NOTHING_ON_CLOSE);
			
		}
		default:
		{
			break;
		}
	}
	}
	

}