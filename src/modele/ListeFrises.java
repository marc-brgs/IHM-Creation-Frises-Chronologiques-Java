package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * classe qui contient toutes les chronologies pour les faire parvenir plus facilement aux autres classes
 * @author Marc BOURGEOIS et Iban CORNILY
 */
public class ListeFrises implements Serializable{
	
	/**
	 * ArrayList qui contient les chronologies
	 */
	private ArrayList <Chronologie> frises;
	
	/**
	 * Constructeur par défaut de ListeFrises
	 */
	public ListeFrises () {
		frises = new ArrayList <Chronologie> ();
	}
	
	/**
	 * Ajout d'une chronologie à la liste
	 * @param parFrise frise à ajouter
	 */
	public boolean ajout (Chronologie parFrise) {
		// Ajout dans la ArrayList
		String nomFrise = parFrise.getTitre();
		String[] nomsFrisesListe = this.getNomsFrises();
		Boolean nomUnique = true;
		if(nomFrise.contentEquals("Coronavirus: La menace invisible") && (parFrise.getCheminFichier().equals("sauvegardes/coronavirus"))) {
			frises.add (parFrise);
			return nomUnique;
		}
		else if ( ! (nomFrise.contentEquals("Coronavirus: La menace invisible")) ) {
			int i = 0;
			while((i < nomsFrisesListe.length) && nomUnique) {
				if((nomsFrisesListe.length > 0) && (nomFrise.equals(nomsFrisesListe[i]))){
					System.out.println(nomsFrisesListe[i]);
					nomUnique = false;
				}
				i++;
			}
			if (nomUnique) {
				frises.add (parFrise);
			}
			else {
				JOptionPane.showMessageDialog( null, "Une frise avec ce nom existe déjà. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			return nomUnique;
			//frises.add (parFrise);
		}
		else {
			nomUnique = false;
			JOptionPane.showMessageDialog( null, "Une frise avec ce nom existe déjà. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return nomUnique;
	}
	
	/**
	 * Retourne une frise grâce au nom passé en paramètre,
	 * si aucune frise n'a ce nom, et que la liste est vide, 
	 * alors une frise vide est créée, sinon
	 * la première frise de la liste est retournée
	 * @param parString nom de la frise recherchée
	 * @return la frise portant le nom donné, la première si elle n'existe pas
	 */
	public Chronologie getFrise (String parString) {
	    for (Chronologie frise : frises) {
			if (frise.getTitre().equals (parString)) {
				return frise;
			}
		}
	    if(this.isEmpty())
	    	return new Chronologie("", new Date(1,1,2020), new Date(1,1,2020), "", "");
	    else
	    	return frises.get(0);
	} // compteNbEvt
	
	/**
	 * affichage de la liste en String
	 * @return la liste en String
	 */
	public String toString () {
		String chaine = "\n";
		for (Chronologie frise : frises) {
			chaine = frise.getTitre() + frise + "\n";
		}
		return chaine;
	}
	
	/**
	 * retourne une frise de la liste 
	 * @param nomfrise frise à afficher en String
	 * @return la frise en String
	 */
	public String toString (String nomfrise) {
		String chaine = "\n" + nomfrise + this.getFrise(nomfrise) + "\n";
		return chaine;
	}
	
	/**
	 * retourne les noms des frises de la liste
	 * @return une liste String des noms de frises
	 */
	public String [] getNomsFrises () {
		String [] resultat = new String [frises.size()];
		int i = 0;
		for (Chronologie frise : frises) {
			resultat[i] = frise.getTitre();
			i++;
		}
		return resultat;
	}
	
	/**
	 * retourne les noms des frises de la liste pour la suppression
	 * sauf la frise coronavirus
	 * @return une liste String des noms de frises
	 */
	public String [] getNomsFrisesSuppression () {
		String [] resultat = new String [frises.size()];
		int i = 0;
		String titre;
		for (Chronologie frise : frises) {
			titre = frise.getTitre();
			if (titre != "Coronavirus: La menace invisible")
				resultat[i] = titre;
			i++;
		}
		return resultat;
	}

	/**
	 * Supprime la frise dont le nom est passé en paramètre
	 * @param nomFrise nom de la frise à supprimer
	 */
	public void suppression(String nomFrise) {
		for(int pos = 0; pos<frises.size(); pos++) {
			if( (!(frises.get(pos).getTitre().equals("Coronavirus: La menace invisible"))) && (frises.get(pos).getTitre().equals(nomFrise))) {
				frises.remove(frises.get(pos));
				break;
			}
		}
	}
	
	
	/**
	 * test si la listeFrises est vide
	 * @return vrai si elle vide, faux sinon
	 */
	public boolean isEmpty() {
		if(frises.size() == 0)
			return true;
		return false;
	}
}