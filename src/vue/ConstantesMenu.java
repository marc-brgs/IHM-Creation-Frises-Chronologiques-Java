package vue;

/**
 * Chaînes de caracètres utilisées pour la barre de menu.
 * @author Groupe
 *
 */
public interface ConstantesMenu {
	/**
	 * Intitulé de l'item création d'une nouvelle frise.
	 */
	final  String INTIT_CRE = "Création";
	/**
	 * Intitulé de l'item création d'une nouvelle frise.
	 */
	final  String INTIT_CRE_FRI = "Création frise";
	/**
	 * Intitulé de l'item création d'une nouvelle frise.
	 */
	final  String INTIT_CRE_EVT = "Création événement";
	/**
	 * Intitulé de l'item affichage du menu.
	 */
	final  String INTIT_AFF = "Affichage";
	/**
	 * Intitulé de l'item fermer du menu.
	 */
	final  String INTIT_FER = "Fermer";
	/**
	 * Groupement des intitulés des items du menu.
	 */
	final  String [] INTIT_MENU = {INTIT_CRE+"                              ", INTIT_AFF , INTIT_FER , INTIT_CRE_FRI , INTIT_CRE_EVT };
	
} // ConstantesMenu