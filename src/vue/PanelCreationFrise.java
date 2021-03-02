package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
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

/**
 * Abstraction du formulaire permettant d'ajouter du événement à l'agenda.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class PanelCreationFrise extends JPanel implements ConstantesCalendrier, ConstantesCouleurs{ 
	
	/**
	 * Champ de texte correspondant à l'intitulé de la frise.
	 */
	private JTextField champNomFrise = new JTextField(2);

	/**
	 * Champ de texte correspondant au jour de début de la frise.
	 */
	private JTextField champJourDebut = new JTextField(2);
	/**
	 * Champ de texte correspondant au mois du début de la frise.
	 */
	private JTextField champMoisDebut = new JTextField (2);
	/**
	 * Champ de texte correspondant à l'année du début de la frise.
	 */
	private JTextField champAnneeDebut = new JTextField (4);
	
	/**
	 * Champ de texte correspondant au jour de fin de la frise.
	 */
	private JTextField champJourFin = new JTextField (2);
	/**
	 * Champ de texte correspondant au mois de fin de la frise.	
	 */
	private JTextField champMoisFin = new JTextField (2);
	/**
	 * Champ de texte correspondant à l'année de fin de la frise.
	 */
	private JTextField champAnneeFin = new JTextField (4);
	
	/**
	 * Bouton permettant d'ajouter une nouvelle frise
	 */
	private JButton boutonAjout = new JButton (INTITULE_BOUTON_AJOUT_FRISE);
	
	/**
	 * JComboBox permettant de choisir l'échelle d'affichage de la frise.
	 */
	private JComboBox <String> echelle = new JComboBox <String> (ECHELLE);
	/**
	 * Champ de texte correspondant au chemin du fichier de sauvegarde.
	 */
	private JTextField fichier = new JTextField ("sauvegardes\\" , 2);
	
	/**
	 * Bouton permettant de supprimer une frise
	 */
	private JButton boutonSuppression = new JButton (INTITULE_BOUTON_SUPRESSION_FRISE);
	
	/**
	 * Constructeur par défaut de PanelFormulaire.
	 */
	public PanelCreationFrise () {	
		
		// Le gestionnaire de répartition
		setLayout (new GridBagLayout ());	
		GridBagConstraints contraintes = new GridBagConstraints ();
	  	contraintes.insets = new Insets (6,6,6,6); 
		contraintes.anchor = GridBagConstraints.WEST;
		
		
		// Les élément graphiques	
		JLabel nomFrise = new JLabel ("Nom de la frise :  ");
		JLabel dateDebut = new JLabel("Date de début : ");
		JLabel dateFin = new JLabel("Date de fin : ");
		JLabel slash1 = new JLabel (" / ", JLabel.LEFT);
		JLabel slash2 = new JLabel (" / ", JLabel.LEFT);
		JLabel slash3 = new JLabel (" / ", JLabel.LEFT);
		JLabel slash4 = new JLabel (" / ", JLabel.LEFT);
		JLabel titre = new JLabel ("Création d'une frise", JLabel.LEFT);
		JLabel labelEchelle = new JLabel ("Echelle : ", JLabel.LEFT);
		JLabel labelFichier = new JLabel ("Chemin du fichier : ", JLabel.LEFT);
		
		// labelTitre
		contraintes.gridy=0; contraintes.gridwidth = 4;	contraintes.gridx=0;
		this.add(titre, contraintes);
		
		// boutonAjout
		boutonAjout.setActionCommand(INTITULE_BOUTON_AJOUT_FRISE);
		contraintes.gridwidth = 1;	contraintes.gridx=5;
		this.add(boutonAjout, contraintes);
		
		contraintes.fill = GridBagConstraints.NONE;
		
		// nomFrise et champNomFrise
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0;
		this.add (nomFrise,contraintes); 
		contraintes.gridx++; contraintes.gridwidth =5 ; contraintes.fill = GridBagConstraints.HORIZONTAL;
		this.add (champNomFrise, contraintes); 
		
		// dateDebut et champs date début
		contraintes.gridy++; contraintes.gridwidth =1;	contraintes.gridx=0; contraintes.fill = GridBagConstraints.NONE;
		this.add (dateDebut,contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 	
		this.add (champJourDebut, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (slash1, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champMoisDebut, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (slash2, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champAnneeDebut, contraintes);
		
		
		// dateFin et champs date fin
		contraintes.gridy++; contraintes.gridx =0 ; contraintes.gridwidth = 1;
		this.add (dateFin,contraintes);  
		contraintes.gridx++; contraintes.gridwidth = 1;	
		this.add (champJourFin, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (slash3, contraintes); 
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champMoisFin, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (slash4, contraintes);
		contraintes.gridx++; contraintes.gridwidth =1 ; 
		this.add (champAnneeFin, contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		
		// labelEchelle et echelle
		contraintes.gridy++; contraintes.gridx =0 ; contraintes.gridwidth = 1;
		this.add (labelEchelle,contraintes);  
		contraintes.gridx++; contraintes.gridwidth =5 ;
		this.add(echelle,contraintes); 
		
		// labelFichier et fichier
		contraintes.gridy++; contraintes.gridx =0 ; contraintes.fill = GridBagConstraints.HORIZONTAL;
		this.add (labelFichier,contraintes);  
		contraintes.gridx++; contraintes.gridwidth =5 ; contraintes.fill = GridBagConstraints.HORIZONTAL;
		this.add(fichier,contraintes);
		
		
		// bouton suppression
		boutonSuppression.setActionCommand(INTITULE_BOUTON_SUPRESSION_FRISE);
		contraintes.gridy++; contraintes.gridx = 0 ; contraintes.gridwidth = 6;
		contraintes.anchor = GridBagConstraints.CENTER;
		this.add(boutonSuppression, contraintes);
		
		
		// fontes, couleurs, aspect, accessibilité
		 
		titre.setFont(FONT_16);
		
		boutonAjout.setFont (FONT_14); 
		boutonAjout.setBackground(MASTIC.darker());
		boutonAjout.setForeground(CREAM);
		boutonAjout.setFocusPainted(false);
		
		nomFrise.setFont(FONT_12);
		nomFrise.setDisplayedMnemonic('N');
		nomFrise.setLabelFor(champNomFrise);
		
		dateDebut.setFont(FONT_12);
		dateDebut.setDisplayedMnemonic('D');
		dateDebut.setLabelFor(champJourDebut);
		
		dateFin.setFont(FONT_12);
		dateFin.setDisplayedMnemonic('F');
		dateFin.setLabelFor(champJourFin);	
		
		labelEchelle.setFont(FONT_12);
		labelEchelle.setDisplayedMnemonic('E');
		labelEchelle.setLabelFor(echelle);		
		
		labelFichier.setFont(FONT_12);
		labelFichier.setDisplayedMnemonic('C');
		labelFichier.setLabelFor(fichier);		
	
		boutonSuppression.setFont(FONT_14);
		
		setBackground(GREY);
	 
		reset (); 
	 
		setPreferredSize(new Dimension (300,340));
	}

	/**
	 * Reinitialise le formulaire.
	 */
	public void reset () {
		champNomFrise.setText(new String());
		champJourDebut.setText(new String());
		champMoisDebut.setText(new String());
		champAnneeDebut.setText(new String());
		champJourFin.setText(new String());
		champMoisFin.setText(new String());
		champAnneeFin.setText(new String());
		echelle.setSelectedIndex(0);
		fichier.setText(new String("sauvegardes/"));
	}
	
	/**
	 * Donne le nom de la frise.
	 * @return champ du nom de la frise entré par l'utilisateur dans le formulaire.
	 */
	public String getNomFrise () {
		return champNomFrise.getText();
	}
	
	/**
	 * Donne la date du début.
	 * @return date du début entrée par l'utilisateur dans le formulaire.
	 */
	public Date getDateDebut () {
		return new Date(Integer.parseInt(champJourDebut.getText()),Integer.parseInt(champMoisDebut.getText()),Integer.parseInt(champAnneeDebut.getText()));
	}
	
	/**
	 * Donne la date de la fin.
	 * @return date de la fin entrée par l'utilisateur dans le formulaire.
	 */
	public Date getDateFin () {
		return new Date(Integer.parseInt(champJourFin.getText()),Integer.parseInt(champMoisFin.getText()),Integer.parseInt(champAnneeFin.getText()));
	}
	
	/**
	 * Donne l'échelle d'affichage de la frise.
	 * @return echelle d'affichage sélectionnée par l'utilisateur dans le formulaire.
	 */
	public String getEchelle () {
		return ECHELLE[echelle.getSelectedIndex()];
	}
	
	/**
	 * Donne le chemin du fichier de sauvegarde.
	 * @return chemin du fichier de sauvegarde entré par l'utilisateur dans le formulaire.
	 */
	public String getFichier () {
		return fichier.getText();
	}
	
	/**
	 * le contrôleur s'enregistre à l'écoute des actions sur le boutonAjout et le boutonSuppression
	 * @param controleur contrôleur qui gère les actions des composants
	 */
	public void enregistreEcouteur(Controleur controleur) {
		 boutonAjout.addActionListener(controleur);		
		 boutonSuppression.addActionListener(controleur);
	}
	
}
