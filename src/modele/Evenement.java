package modele;

import java.io.Serializable;

/**
 * Abstraction d'un évenement qui comporte une Date, un nom, un lieu.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class Evenement implements Comparable <Evenement> , Serializable{

	/**
	 * Date de l'évenement.
	 */
	private Date date;
	/**
	 * Nom de l'évenement.
	 */
	private String nom;
	/**
	 * Lieu de l'évenement.
	 */
	private String description;
	/**
	 * Importance de l'événement de 1 à 4.
	 */
	private int poids;
	/**
	 * 
	 */
	private String cheminImage;
	/**
	 * Nombre d'évenement instanciés.
	 * Commum pour tout les évenements.
	 */
	private static int nombreDobjets ; // les champs entiers sont initialisés par défaut à 0
				// on peut quand même faire l'initialisation ici avec la déclaration

	/**
	 * Constructeur par défaut d'Evenement.
	 */
	public Evenement (){ 
    	nombreDobjets ++;
	}
	
	/**
	 * Constructeur d'événement avec poids prédéfini
	 * @param parDate
	 * @param parNom
	 * @param parDescription
	 * @param parCheminImage
	 */
	public Evenement (Date parDate, String parNom , String parDescription, String parCheminImage) {
        date = parDate;
        nom = parNom;
        description = parDescription;
        poids = 1;
        nombreDobjets ++;
        cheminImage = parCheminImage;
    }
	
	/**
	 * Constructeur d'Evenement
	 * @param parDate
	 * @param parNom
	 * @param parDescription
	 * @param parPoids
	 * @param parCheminImage
	 */
	public Evenement (Date parDate, String parNom , String parDescription, int parPoids, String parCheminImage) {
		date = parDate;
		nom = parNom;
		description = parDescription;
		poids = parPoids;
		cheminImage = parCheminImage;
		nombreDobjets ++;     
		cheminImage = parCheminImage;
	}
  
  
  // 3) Accesseurs et modifieurs
  
	/**
	 * Retourne la date de l'évenement.
	 * @return
	 */
	public Date getDate ()  {
		return date;
	}
	
	/**
	 * Retourne le nom de l'évenement.
	 * @return
	 */
	public String getNom () {	
		return nom;
	}
	
	/**
	 * Retourne la description de l'événement.
	 * @return
	 */
	public String getDescription() {	
		return description;
	}
	
	/**
	 * Retourne le nombre d'objets instanciés.
	 * @return
	 */
	public static int getNombreDobjets () { // accesseur sur un champ static
		return nombreDobjets;
	}
  
	/**
	 * Défini la description de l'événnement.
	 * @param parDescription
	 */
	public void setDescription (String parDescriptionn) {
		description = parDescriptionn;
	}

	/**
	  * Compare l'évenement à un autre pour indiquer si il est plus vieux ou plus récent.
	  * si precede = -1: this précède parEvt
	  * si precede = 1: parEvt précède this
	  * si precede = 0: les évenements sont similaire en tout points
	  */
	public int compareTo (Evenement parEvt) {
		int precede = this.date.compareTo(parEvt.date);
		if (precede == 0)
			// les dates sont égales on compare les noms des événements par ordre alphabétique
			return  (this.nom+this.description).compareTo (parEvt.nom+parEvt.description);		
		else return precede;
	} 
	
	/**
	 * Manière de représenter un évenement en chaîne de caractère.
	 */
	public String toString () {
		return date + " - " + nom + " - " + description ;
	}

	public int getPoids() {
		return poids;
	}
		
	public String getCheminImage() {
		return cheminImage;
	}
} // classe Evenement