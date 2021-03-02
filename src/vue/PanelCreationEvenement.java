package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets; 
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;  
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import modele.Chronologie;
import modele.Date;
import modele.Evenement;
import modele.ListeFrises; 

/**
 * Abstraction du formulaire permettant d'ajouter du événement à l'agenda.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class PanelCreationEvenement extends JPanel implements ConstantesCalendrier, ConstantesCouleurs{ 
	/**
	* Date sélectionné par l'utilisateur.
	*/
	private Date date;
	/**
	 * Champ de texte correspondant au jour de l'événement.
	 */
	private JTextField champJour = new JTextField(2);
	/**
	 * Champ de texte correspondant au mois de l'événement.
	 */
	private JTextField champMois = new JTextField (2);
	/**
	 * Champ de texte correspondant à l'année de l'événement.
	 */
	private JTextField champAnnee = new JTextField (4);
	
	/**
	 * Bouton permettant d'ajouter un événement à la chronologie.
	 */
	private JButton boutonAjout = new JButton (INTITULE_BOUTON_AJOUT_EVENEMENT);
	/**
	 * JComboBox correspondant au nom de la frise dans laquelle ajouter l'événement.
	 */
	private JComboBox <String> champNomFrise ;
	/**
	 * Champ de texte correspondant au titre de l'événement.
	 */
	private JTextField champTitre = new JTextField (4);
	
	/**
	 * Champ de texte correspondant à la description de l'événement.
	 */
	private JTextArea champDescription = new JTextArea (8,8);
	/**
	 * Champ de texte correspondant au chemin de l'image de l'événement.
	 */
	private JTextField champCheminFichier = new JTextField (4);
	/**
	 * JComboBox correspondant au poids de l'événement.
	 */
	private JComboBox <String> poids = new JComboBox <String> (POIDS);
	/**
	 * JButton permettant de supprimer un événement
	 */
	private JButton boutonSuppression = new JButton (INTITULE_BOUTON_SUPRESSION_EVT);
	/**
	 * tableau contenant les noms des frises existantes
	 */
	private String [] nomsFrises;
	/**
	 * liste de toutes les frises
	 */
	private ListeFrises frises;
	
	/**
	 * Constructeur par défaut de PanelFormulaire.
	 * @param parListeFrises liste de toutes les frises.
	 */
	public PanelCreationEvenement (ListeFrises parListeFrises) {
		 
	 	champNomFrise =  new JComboBox <String> (parListeFrises.getNomsFrises());
	 	nomsFrises = parListeFrises.getNomsFrises();
	 	frises = parListeFrises;
		
		// Le gestionnaire de répartition
		setLayout (new GridBagLayout ());	
		GridBagConstraints contraintes = new GridBagConstraints ();
	  	contraintes.insets = new Insets (6,6,6,6); 
		contraintes.anchor = GridBagConstraints.WEST;
		
		
		// Les élément graphiques
		JLabel labelDate = new JLabel("Date de l'événement :");
		JLabel labelTitreFormulaire = new JLabel ("Ajout d'un événement", JLabel.LEFT);
		JLabel labelNomFrise1 = new JLabel ("Nom de la frise dans laquelle", JLabel.LEFT);
		JLabel labelNomFrise2 = new JLabel ("ajouter l'événement : ", JLabel.LEFT);
	 	JLabel labelTitre = new JLabel ("Titre : ", JLabel.LEFT);
		JLabel labelDescription = new JLabel ("Description : ", JLabel.LEFT);
		JLabel labelPoids = new JLabel("Poids de l'événement : ");
		JLabel labelCheminFichier = new JLabel ("Chemin de l'image : ", JLabel.LEFT);
		JLabel slash1 = new JLabel (" / ");
		JLabel slash2 = new JLabel (" / ");
		
		// titre du formulaire
		contraintes.gridy=0; contraintes.gridwidth = 4; contraintes.gridx=0; contraintes.fill=GridBagConstraints.HORIZONTAL;
		this.add(labelTitreFormulaire, contraintes);
		contraintes.fill=GridBagConstraints.NONE;
		
		// boutonAjout
		boutonAjout.setActionCommand(INTITULE_BOUTON_AJOUT_EVENEMENT);
		contraintes.gridwidth = 1;	contraintes.gridx = 5; contraintes.anchor = GridBagConstraints.EAST;
		this.add(boutonAjout, contraintes);
		contraintes.anchor = GridBagConstraints.WEST;
		
		// labelDate et champs de la date
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0; 
		this.add (labelDate,contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champJour, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ;
		this.add (slash1, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champMois, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (slash2, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champAnnee, contraintes);
		
		// labelNomFrise et champNomFrise
		Box labelNomFrise = Box.createVerticalBox(); /// box qui va contenir le labelNomFrise
		labelNomFrise.add(labelNomFrise1); ///divison du labelNomFrise
		labelNomFrise.add(labelNomFrise2);
		labelNomFrise.add(Box.createVerticalGlue()); /// affichage sur 2 lignes
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0; contraintes.gridheight = 1; 
		contraintes.fill = GridBagConstraints.VERTICAL; contraintes.anchor = GridBagConstraints.NORTHWEST;
		this.add (labelNomFrise,contraintes); 
		contraintes.gridx++; contraintes.gridwidth = 5;
		this.add (champNomFrise, contraintes); 
		contraintes.fill = GridBagConstraints.NONE;
		
		// labelTitre et champTitre
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0; contraintes.gridheight = 1;
		this.add (labelTitre,contraintes); 
		contraintes.gridx++; contraintes.gridwidth = 5 ; 
		contraintes.fill = GridBagConstraints.HORIZONTAL;		
		this.add (champTitre, contraintes); 
		contraintes.fill = GridBagConstraints.NONE;
		
		// labelDescription et champDescription
		contraintes.gridy++; contraintes.gridx =0 ;
		contraintes.anchor = GridBagConstraints.NORTHWEST;
		this.add (labelDescription,contraintes);
		contraintes.gridx++; contraintes.gridwidth = 5;
		contraintes.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(champDescription),contraintes);
		
		// labelPoids et poids
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0; 
		this.add (labelPoids,contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (poids, contraintes); 
		
		// labelCheminFichier et champCheminFichier
		contraintes.gridy++; contraintes.gridx =0 ;
		this.add (labelCheminFichier,contraintes);
		contraintes.gridx++; contraintes.gridwidth = 5;
		this.add(champCheminFichier,contraintes);
		
		//boutonSuppression
		boutonSuppression.setActionCommand(INTITULE_BOUTON_SUPRESSION_EVT);
		contraintes.gridy++; contraintes.gridx = 1 ; contraintes.gridwidth = 6;
		contraintes.anchor = GridBagConstraints.CENTER;
		this.add(boutonSuppression, contraintes);
		
		// fontes, couleurs, aspect, accessibilité
		 
		boutonAjout.setFont (FONT_14); 
		boutonAjout.setBackground(MASTIC.darker());
		boutonAjout.setForeground(CREAM);
		boutonAjout.setFocusPainted(false);
		
		labelTitreFormulaire.setFont(FONT_16);
		
		labelDate.setFont(FONT_12);
		labelDate.setDisplayedMnemonic('D');
		labelDate.setLabelFor(champJour);
		
		labelPoids.setFont(FONT_12);
		labelPoids.setDisplayedMnemonic('P');
		labelPoids.setLabelFor(poids);
		
		labelNomFrise1.setFont(FONT_12);
		labelNomFrise2.setFont(FONT_12);
		labelNomFrise1.setDisplayedMnemonic('N');
		labelNomFrise1.setLabelFor(champNomFrise);	
		
		labelTitre.setFont(FONT_12);
		labelTitre.setDisplayedMnemonic('T');
		labelTitre.setLabelFor(champTitre);	
		
		labelDescription.setFont(FONT_12);
		labelDescription.setDisplayedMnemonic('e');
		labelDescription.setLabelFor(champDescription);	
		
		labelCheminFichier.setFont(FONT_12);
		labelCheminFichier.setDisplayedMnemonic('C');
		labelCheminFichier.setLabelFor(champCheminFichier);	
	
		boutonSuppression.setFont(FONT_14);
		
		setBackground(GREY);
	 
		reset(); 
	 
		setPreferredSize(new Dimension (300,340));
	}

	/**
	 * Reinitialise le formulaire.
	 */
	public void reset () {
		
		champJour.setText(new String()); 
		champMois.setText(new String()); 
		champAnnee.setText(new String()); 
		champTitre.setText(new String()); 
		champCheminFichier.setText(new String("ressources/default.png"));
		champDescription.setText(new String());
		
		// le focus est donné au premier champ de saisie
		champJour.requestFocus();
	}
	

	/**
	 * Donne la date du formulaire.
	 * @return date entrée par l'utilisateur
	 */
	public Date getDate () {
		return new Date(Integer.parseInt(champJour.getText()), Integer.parseInt(champMois.getText()), Integer.parseInt(champAnnee.getText()));
	}
	
	/**
	 * Donne le premier champ du formulaire.
	 * @return champJour (premier champ du formulaire)
	 */
	public JTextField getChampRecevantFocus() {
		return champTitre;
	}	
	
	/**
	 * le contrôleur s'enregistre à l'écoute des actions sur le bouton ajout
	 * @param controleur contrôleur qui gère les actions des composants
	 */
	public void enregistreEcouteur(Controleur controleur) {
		 boutonAjout.addActionListener(controleur);	
		 boutonSuppression.addActionListener(controleur);
	}
	
	/**
	 * Créé un nouvel évenement avec les informations qu'a saisi l'utilisateur.
	 * @return évenement instancié à partir des champs complété par l'utilisateur
	 */
	public Evenement getEvenement() {
		return new Evenement (getDate(), champTitre.getText(), champDescription.getText(), Integer.parseInt(POIDS[poids.getSelectedIndex()]),  champCheminFichier.getText());
				// ajouter l'heure, la description 		non en s'en 	
	}
	
	/**
	 * Donne les noms des frises
	 * @return un tableau de String contenant les noms des frises
	 */
	public String getNomFrise() {
		return nomsFrises[champNomFrise.getSelectedIndex()];
	}
	
	/**
	 * Donne le nom de l'événement
	 * @return le nom de l'événement entré par l'utilisateur
	 */
	public String getNomEvt() {
		return champTitre.getText();
	}
	
	/**
	 * Donne le poids de l'événement
	 * @return le nom de l'événement entré par l'utilisateur
	 */
	public int getPoidsEvt() {
		return Integer.parseInt(POIDS[poids.getSelectedIndex()]);
	}
	
	/**
	 * Actualise la liste des frises
	 * @param parFrises liste des frises actualisée
	 */
	public void setFrises(ListeFrises parFrises) {
		frises = parFrises;
		nomsFrises = parFrises.getNomsFrises();
		champNomFrise =  new JComboBox <String> (parFrises.getNomsFrises());
	}

	/**
	 * actualise la JComboBox avec le nom des frises
	 * @param parListeFrises liste contenant toutes les frises
	 */
	public void resetChampNomFrise(ListeFrises parListeFrises) {
		champNomFrise =  new JComboBox <String> (parListeFrises.getNomsFrises());
	}

	/**
	 * Retourne le boutonAjout
	 * @return boutonAjout
	 */
	public JButton getBoutonAjout() {
		return boutonAjout;
	}
}
