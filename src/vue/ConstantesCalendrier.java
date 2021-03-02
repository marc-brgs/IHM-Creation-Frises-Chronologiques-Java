package vue;

import java.awt.Color;

/**
 * Chaînes de caractères utilisées pour les dates, horaires et boutons.
 * @author Marc
 *
 */
public interface ConstantesCalendrier {
	/**
	 * Abréviations des noms des jours de la semaine en 2 caractères.
	 */
	final String [] JOURS_SEMAINE = {"lu","ma","me","je","ve","sa","di"} ;  
	/**
	 * Noms des mois de l'année.
	 */
	final  String [] MOIS = {"janvier", "février","mars","avril","mai","juin","juillet", "août","septembre","octobre","novembre","décembre"};
	final  String [] MOIS_RAC = {"janv", "févr","mars","avril","mai","juin","juil", "août","sep","oct","nov","déc"};
	final  String [] MOIS_ULTRA_RAC = {"jan", "fév","mar","avr","mai","juin","juil", "aoû","sep","oct","nov","déc"};
	/**
	 * Intitulé du bouton "précèdent".
	 */
	final String INTIT_PRECEDENT = "<";
	/**
	 * Intitulé du bouton "suivant".
	 */
	final String INTIT_SUIVANT = ">";
	/**
	 * Groupement des différents boutons de navigation.
	 */
	final  String [] INTITULES_BOUTONS_NAVIGATION = {INTIT_PRECEDENT, INTIT_SUIVANT};
	/**
	 * Intitulé du bouton "ajouter".
	 */
	final String INTITULE_BOUTON_AJOUT_EVENEMENT ="+";
	/**
	 * Intitulé du bouton "ajouter".
	 */
	final String INTITULE_BOUTON_AJOUT_FRISE =" + ";
	/**
	 * Intitulé du bouton "supprimer frise".
	 */
	final String INTITULE_BOUTON_SUPRESSION_FRISE ="Supprimer une frise";
	/**
	 * Intitulé du bouton "supprimer événement".
	 */
	final String INTITULE_BOUTON_SUPRESSION_EVT ="Supprimer un événement";
	/**
	 * Intitulé du bouton "afficher".
	 */
	final String INTITULE_BOUTON_AFFICHAGE_FRISE ="Afficher une frise";
	
	public final String [] ECHELLE = {"Jours", "Mois", "Années"};
	
	public final String [] POIDS = {"1","2","3","4"};
	
} // ConstantesCalendrier
