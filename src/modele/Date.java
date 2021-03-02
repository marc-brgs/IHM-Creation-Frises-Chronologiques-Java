package modele;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Calendar;
 
/**
 * Abstraction des dates.
 * Possède un jour, un mois, une année et un numéro dans la semaine.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class Date implements Comparable <Date> , Serializable{
	/**
	 * Représentation d'un jour.
	 */
	private int jour;
	/**
	 * Représentation d'un mois.
	 */
	private int mois;
	/**
	 * Représentation d'une année.
	 */
  	private int annee;
  	/**
  	 * Représentation d'un numéro dans la semaine.
  	 */
  	private int jourSemaine ;
  
  /**
   * Constructeur par défaut de Date.
   * Défini la date comme la date d'aujourd'hui.
   */
  public Date() { 
	  GregorianCalendar dateAuj = new GregorianCalendar ();
	  annee = dateAuj.get (Calendar.YEAR);
	  mois = dateAuj.get (Calendar.MONTH)+1; // janvier = 0, fevrier = 1...
	  jour = dateAuj.get (Calendar.DAY_OF_MONTH);
	  int day_of_week = dateAuj.get (Calendar.DAY_OF_WEEK); // 1 dimanche, 2 lundi, ...,  7 samedi
	  jourSemaine = day_of_week == 1 ? 6 : day_of_week-2;
  }
  
  /**
   * Construteur de Date.
   * @param parJour
   * @param parMois
   * @param parAnnee
   */
  public Date (int parJour, int parMois, int parAnnee)   {   
	jour = parJour;
	mois = parMois;
	annee = parAnnee; 
	GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
	int day_of_week = date.get (Calendar.DAY_OF_WEEK); // 1 dimanche, 2 lundi, ...,  7 samedi	
	jourSemaine = day_of_week == 1 ? 6 : day_of_week-2;
  }
  
 
   
  /**
   * retourne 0 si this et parDate sont égales, 
   * -1 si this précède parDate,
   *  1 si parDate précède this
   */
  public int compareTo (Date parDate) {
    if (annee < parDate.annee)
		return -1;
	if (annee > parDate.annee)
		return 1;
	if (mois < parDate.mois)
		return -1;
	if (mois > parDate.mois)
		return 1;
	if (jour < parDate.jour)
		return -1;
	if (jour > parDate.jour)
		return 1;
	return 0;	
  }
 
  /**
   * Retourne la date du lendemain.
   * @return
   */
  public Date dateDuLendemain ()   {	
    if (jour < dernierJourDuMois(mois,annee))
		     return  new Date (jour+1,mois,annee);
		else if (mois < 12)
				return new Date (1,mois+1,annee);
			 else return new Date (1,1,annee+1);	
  }  
  
  /**
   * Retourne la date de la veille.
   * @return
   */
  public Date dateDeLaVeille () {    
	if (jour > 1)
			return  new Date (jour-1,mois,annee);
	else if (mois > 1)
			   return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
		 else return  new Date (31,12,annee-1);
  }	 
  
  /**
   * Retourne le dernier jour du mois.
   * @param parMois
   * @param parAnnee
   * @return
   */
  public static int dernierJourDuMois (int parMois, int parAnnee) {
		switch (parMois) {
			 case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
			 case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
			 default : return 31 ;
			}  // switch
	  } 
  
  /**
   * Indique si parAnnee est une année bissextile.
   * @param parAnnee
   * @return
   */
  private static boolean estBissextile(int parAnnee) {
			return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
	  }
  
  /**
   * Manière de représenter une date en chaîne de caractère.
   */
  public String toString () {
    String chaine = new String();
    switch (jourSemaine) {
         case 0: chaine = "lundi"; break;
		 case 1: chaine = "mardi"; break;
		 case 2: chaine = "mercredi"; break;
		 case 3: chaine = "jeudi"; break;
		 case 4: chaine = "vendredi"; break;
		 case 5: chaine = "samedi"; break;
		 case 6: chaine = "dimanche"; break;
		}
	chaine += " " + jour + " ";
	switch (mois) {
		 case 1: chaine += "janvier"; break;
		 case 2: chaine += "février"; break;
		 case 3: chaine += "mars"; break;
		 case 4: chaine += "avril"; break;
		 case 5: chaine += "mai"; break;
		 case 6: chaine += "juin"; break;
		 case 7: chaine += "juillet"; break;
		 case 8: chaine += "août"; break;
		 case 9: chaine += "septembre"; break;
		 case 10: chaine += "octobre"; break;
		 case 11: chaine += "novembre"; break;
		 case 12: chaine += "décembre"; break;
		}	
	return chaine;
  }  

  /**
   * Retourne la date du premier jour de la semaine.
   * @return
   */
  public Date datePremierJourSemaine() {
	  Date datePrem = this;
	  while(datePrem.getJourSemaine()!=0) {
		  datePrem = datePrem.dateDeLaVeille();
	  }
	  return datePrem;
  }
  
/**
 * Retourne l'année de la date.
 * @return
 */
public int getAnnee() { 
	return annee;
}

public Date getAnneeSuivante() { 
	return (new Date(jour, mois, annee+1));
}

/**
 * Retourne le jour de la date.
 * @return
 */
public int getJour() { 
	return jour;
}

/**
 * Retourne le mois de la date.
 * @return
 */
public int getMois() { 
	return mois;
}

/**
 * Retourne le jour de la semaine de la date.
 * @return
 */
public int getJourSemaine () {
	return jourSemaine;
}

/**
 * Indique si la this correspond à la date d'aujourd'hui.
 * @return
 */
public boolean isToday() {
	return new Date().compareTo(this) == 0;
}

public Date moisProchain() {
	if(mois+1 != 13)
		return new Date(jour, mois+1, annee);
	else
		return new Date(jour, 1, annee+1);
}
}  // class Date