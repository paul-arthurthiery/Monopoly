
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class FenetreJeu extends JFrame implements ActionListener
{
	

	private JTextField saisie;
	private MonopolyInterface monopolyInterface;
	private Joueur ceJoueur;
	private Integer hints;
	private DefaultListModel<String> dataListe;
	private JList<String> gauche;
	private JButton ready;
	private JFrame fenetreJeu;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;


	public FenetreJeu(MonopolyInterface monopolyInterface, Joueur ceJoueur) throws HeadlessException, IOException 
	{
		
		
		super();
		this.monopolyInterface = monopolyInterface;
		this.ceJoueur = ceJoueur; 

		fenetreJeu = new JFrame("Fenêtre de Jeu");
		monopolyInterface.generateCases();
		
		this.setLayout(new GridBagLayout());
		this.setSize(1280,980);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) 
		{
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		
		dataListe = new DefaultListModel<String>();	
		gauche = new JList<String>();
		Iterator<Joueur> it;
		try 
		{
			it = monopolyInterface.getJoueurs().values().iterator();
			while(it.hasNext())
			{
				Joueur joueur = it.next();
				dataListe.addElement(joueur.getNom());
				dataListe.addElement(Integer.toString(joueur.getSolde()));
			}
			gauche.validate();
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
		JLabel label1 = new JLabel("Joueurs"); 
		gauche.add(label1);
		gauche.setBorder(new BevelBorder(BevelBorder.RAISED));
		if (shouldWeightX) 
		{
            c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(gauche,c);

		
		JPanel droite = new JPanel(); 
		MyImage image = null;
		image = new MyImage("/Users/Paul-Arthur/Desktop/board.jpg");
		droite.add(image);
		if (shouldWeightX) 
		{
            c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 0;
		this.add(droite,c);
		
		
		JPanel console = new JPanel();
		JLabel label2 = new JLabel ("Console");
		console.add(label2);
		console.setBorder(new BevelBorder(BevelBorder.RAISED));
		if (shouldWeightX) 
		{
            c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weighty = 1.0;
		c.gridy = 1;
		this.add(console,c);

		JPanel boutons = new JPanel(new GridLayout(2,2));
		boutons.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JLabel label3 = new JLabel("Boutons");
		label3.setHorizontalAlignment(JLabel.CENTER);
		boutons.add(label3);
		
		ready = new JButton("Prêt ?");
		ready.addActionListener(this);
		ready.setActionCommand("SUIVANT");
		droite.add(ready);
		droite.setBorder(new BevelBorder(BevelBorder.RAISED));
		boutons.add(ready);
		if (shouldWeightX) 
		{
            c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 2;
		c.weighty = 1.0;
		c.gridy = 1;
		this.add(boutons,c);
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
		        if(JOptionPane.showConfirmDialog(null, "Are you sure ?", "Are you sure ?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
		        {
		        	try 
					{
		        		System.out.println(monopolyInterface.getJoueurs().size());
						monopolyInterface.removeJoueur(ceJoueur);
					} 
					catch (RemoteException e1) 
					{
						e1.printStackTrace();
					}
		        	System.exit(DO_NOTHING_ON_CLOSE);
		        }
			}
		}
		);

		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		switch(action)
		{	
			
			case "SUIVANT":
				try 
				{
					monopolyInterface.ready();
				} 
				catch (RemoteException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ready.setVisible(false);
			
			
				
		
			default :
				break;
		}
		
		
		
	}



}








