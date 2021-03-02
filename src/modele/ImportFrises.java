package modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * classe qui permet d'importer 
 * @author Marc BOURGEOIS et Iban CORNILY
 */
public class ImportFrises extends JDialog {
	/**
	 * liste contenant le nom des fichiers de sauvegardes
	 */
	private String [] listeNomFichiers;
	/**
	 * liste contenant les noms des frises sélectionnées
	 */
	private String [] frisesSelectionnees = {"coronavirus.ser"};
	
	/**
	 * constructeur de ImportFrises
	 * @param parent component parent 
	 * @param title titre de la boite de  dialogue
	 * @param modal 
	 * @param parListeNomFichiers liste de tous les noms des fichiers
	 */
	public ImportFrises(JFrame parent, String title, boolean modal, String[] parListeNomFichiers){
		super(parent, title, modal);
	    listeNomFichiers = parListeNomFichiers;
	    this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	}
	  
	/**
	 * mise en place des composants graphiques de la boite de dialogue
	 */
	private void initComponent(){
		JPanel panFrises = new JPanel();
		panFrises.setBorder(BorderFactory.createTitledBorder("Frises existantes"));
		panFrises.setPreferredSize(new Dimension(440, 60));
		
		//instanciation des JRadioButton
		JRadioButton [] radioBoutons= new JRadioButton[listeNomFichiers.length];
		for (int i = 0 ; i < listeNomFichiers.length ; i++) {
			radioBoutons[i] = new JRadioButton(listeNomFichiers[i]);
			panFrises.add(radioBoutons[i]);
		}
		
		JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    
	    // bouton ok
	    okBouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {   
	    		frisesSelectionnees = getFrises();
	    		setVisible(false);
	    	}

	    	public String[] getFrises(){
	    		String [] result = new String [listeNomFichiers.length];
	    		int y = 0;
	    		for (int i = 0 ; i < listeNomFichiers.length ; i++) {
	    			if(radioBoutons[i].isSelected()){
	    				result[y] = radioBoutons[i].getText();
	    				y++;
	    			}
	    		}
	    		return result ;  
	    	}     
	    });
	    
	    // bouton cancel
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    
	    // ajout des boutons au JPanel control
	    control.add(okBouton);
	    control.add(cancelBouton);
		
	    // ajout a la boite de dialogue
	    this.getContentPane().add(panFrises, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
  
	/**
	 * Donne la liste des frises sélectionnées
	 * @return frisesSelectionnees
	 */
	  public String[] getFrisesSelectionnees() {
		  return frisesSelectionnees;
	  }
}