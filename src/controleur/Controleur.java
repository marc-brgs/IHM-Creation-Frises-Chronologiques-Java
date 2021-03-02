package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import modele.Chronologie;
import modele.Date;
import modele.Evenement;
import modele.ListeFrises;
import utils.LectureEcriture;
import vue.ConstantesCalendrier;
import vue.ConstantesMenu;
import vue.FenetreMere;
import vue.PanelAffichage;
import vue.PanelCreationEvenement;
import vue.PanelCreationFrise;
import vue.PanelGlobal;


/**
 * Recueille les évenements de différents JPanel afin de faire certaines actions.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class Controleur implements ActionListener, ConstantesCalendrier , ConstantesMenu{
	/**
	 * chronologie à afficher.
	 */
	private Chronologie frise;
	/**
	 * liste des chronologies.
	 */
	private ListeFrises listeFrises;
	/**
	 * Formulaire d'évenements.
	 */
	private PanelCreationEvenement panelCreationEvenement;
	/**
	 * Formulaire de frises.
	 */
	private PanelCreationFrise panelCreationFrise;
	/**
	 * Table d'évenements.
	 */
	private PanelAffichage panelAffichage;
	/**
	 * panel diaporama qui contient les autres panels
	 */
	private PanelGlobal panelGlobal;
	
	/**
	 * Constructeur du controleur
	 * @param parPanelGlobal Panel global qui contient panelCreation, panelCreationFrise, et panelAffichage.
	 * @param parFrise Frise chronologique principale.
	 * @param parPanelCreationEvenement Panel qui permet de créer des événements pour les ajouter à une frise.
	 * @param parPanelAffichage Panel d'affichage des événements d'une frise chronologique.
	 * @param parPanelCreationFrise Panel de création d'une nouvelle chronologie.
	 * @param parListeFrises Liste de toutes les chronologies.
	 */
	public Controleur (PanelGlobal parPanelGlobal, Chronologie parFrise, PanelCreationEvenement parPanelCreationEvenement, PanelAffichage parPanelAffichage, PanelCreationFrise parPanelCreationFrise, ListeFrises parListeFrises) {
		panelGlobal = parPanelGlobal;
		frise = parFrise;
		listeFrises = parListeFrises;
		panelCreationEvenement = parPanelCreationEvenement;
		panelCreationFrise = parPanelCreationFrise;
		panelAffichage = parPanelAffichage;
		
		panelCreationEvenement.enregistreEcouteur(this);  // le controleur s'enregistre à l'écoute des actions sur le formulaire	
		panelCreationFrise.enregistreEcouteur(this);
		panelAffichage.enregistreEcouteur(this);
	}

	@Override
	/**
	 * Réagi aux clics de l'utilisateur sur les BoutonDate et le bouton ajout du panelFormulaire.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		int compteur = 0;
		List<Evenement> evtList = new ArrayList<Evenement>(frise.getEvenements());
		Evenement evtDisplayed = panelAffichage.getEvtDisplayed();
		float colEvtDisplayed = 0;
		float colMax = frise.getDuree();
		
		if(evtDisplayed != null) {
			for(Evenement e : frise.getEvenements()) {
				if(e.compareTo(evtDisplayed) == 0)
					break;
				compteur += 1;
			}
			
			colEvtDisplayed = frise.getColEvt(evtDisplayed);
		}
		
		/*
		 * Bouton pour afficher l'événement précédent de la table
		 */
		if(parEvt.getActionCommand().equals(INTIT_PRECEDENT)) {
            panelAffichage.getScrollPane().getHorizontalScrollBar().setValue((int)(panelAffichage.getScrollPane().getHorizontalScrollBar().getMaximum()*((colEvtDisplayed-8)/colMax)));
            evtList = new ArrayList<Evenement>(frise.getEvenements());
            if(evtDisplayed == null && evtList.size() != 0) {
                evtDisplayed = evtList.get(0);
                try {
                    panelAffichage.refreshDiapo(evtDisplayed);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(evtList.size() != 0){
                compteur -= 1;
                try {
                    if(compteur < 0) {
                        panelAffichage.refreshDiapo(evtList.get(0));
                    }
                    else
                        panelAffichage.refreshDiapo(evtList.get(compteur));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(evtDisplayed != null) {
                if(frise.getColEvt(evtDisplayed) != 0) {
                    colEvtDisplayed = frise.getColEvt(evtList.get(compteur));
                    panelAffichage.getScrollPane().getHorizontalScrollBar().setValue((int)(panelAffichage.getScrollPane().getHorizontalScrollBar().getMaximum()*((colEvtDisplayed-8)/colMax)));
                }
            }
        }
		
		/*
		 * Bouton pour afficher l'événement suivant de la table
		 */
		if(parEvt.getActionCommand().equals(INTIT_SUIVANT)) {
            panelAffichage.getScrollPane().getHorizontalScrollBar().setValue((int)(panelAffichage.getScrollPane().getHorizontalScrollBar().getMaximum()*((colEvtDisplayed-7)/colMax)));
            evtList = new ArrayList<Evenement>(frise.getEvenements());
            if(evtDisplayed == null && evtList.size() != 0) {
                evtDisplayed = evtList.get(0);
                try {
                    panelAffichage.refreshDiapo(evtDisplayed);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(evtList.size() != 0){
                compteur += 1;
                try {
                    if(compteur > frise.getEvenements().size()-2)
                        panelAffichage.refreshDiapo(evtList.get(frise.getEvenements().size()-1));
                    else
                        panelAffichage.refreshDiapo(evtList.get(compteur));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(evtDisplayed != null) {
                if(compteur < evtList.size()) {
                    colEvtDisplayed = frise.getColEvt(evtList.get(compteur));
                    panelAffichage.getScrollPane().getHorizontalScrollBar().setValue((int)(panelAffichage.getScrollPane().getHorizontalScrollBar().getMaximum()*((colEvtDisplayed-7)/colMax)));
                }
            }
        }
		
		panelCreationEvenement.getChampRecevantFocus().requestFocus(); 
		
		/*
		 * bouton du panelCreationFrise pour ajouter une frise
		 */
		if (parEvt.getActionCommand().equals(INTITULE_BOUTON_AJOUT_FRISE)) {

			
			Chronologie chronologie = new Chronologie(panelCreationFrise.getNomFrise() , panelCreationFrise.getDateDebut(),  panelCreationFrise.getDateFin(), panelCreationFrise.getEchelle(), panelCreationFrise.getFichier());
			boolean ajout = listeFrises.ajout(chronologie);
			if(ajout) {
				JOptionPane.showMessageDialog((JButton)parEvt.getSource(), "La frise " + panelCreationFrise.getNomFrise() + " a été créée!");
				File fichier = new File(panelCreationFrise.getFichier());
				LectureEcriture.ecriture(fichier, chronologie);
				panelCreationFrise.reset();
				panelGlobal.resetPanelCreation(listeFrises);
			}
		}
		
		/*
		 * bouton du panelCreation pour ajouter un événement à une frise
		 */
		if (parEvt.getActionCommand().equals(INTITULE_BOUTON_AJOUT_EVENEMENT)) {
			Evenement evt = panelCreationEvenement.getEvenement();

			Chronologie friseASauvegarder = listeFrises.getFrise(panelCreationEvenement.getNomFrise());
	
			if((evt.getDate().compareTo(friseASauvegarder.getDateDebut()) < 0) || (evt.getDate().compareTo(friseASauvegarder.getDateFin()) > 0)) {
				JOptionPane.showMessageDialog((JButton)parEvt.getSource(), ("L'événement n'a pas été ajouté à la frise!\nLa date de l'événement n'est pas comprise dans cette frise."));
			}
			else if(friseASauvegarder.checkNomDoublon(evt.getNom()))
				JOptionPane.showMessageDialog((JButton)parEvt.getSource(), ("L'événement n'a pas été ajouté à la frise!\nIl existe déjà un événement portant ce nom dans cette frise."));
			else if(friseASauvegarder.checkEmplacementVide(new Date(evt.getDate().getJour(), evt.getDate().getMois(), evt.getDate().getAnnee()), evt.getPoids()) == false)
				JOptionPane.showMessageDialog((JButton)parEvt.getSource(), ("L'événement n'a pas été ajouté à la frise!\nIl existe déjà un événement à cet emplacement de la frise."));
			else { 
				friseASauvegarder.ajout(evt);
				File fichier = new File(friseASauvegarder.getCheminFichier());
				LectureEcriture.ecriture(fichier, friseASauvegarder);
				JOptionPane.showMessageDialog((JButton)parEvt.getSource(), ("Evénement ajouté à la frise: "+ friseASauvegarder.getTitre().toString()));
				panelAffichage.setFrise(friseASauvegarder);
				frise = friseASauvegarder;
				panelCreationEvenement.reset();
				panelAffichage.reset();
			}
		}
		/*
		 * bouton pour supprimer un événement
		 */
		else if(parEvt.getActionCommand().equals(INTITULE_BOUTON_SUPRESSION_EVT)) {
			String reponse1 = (String)JOptionPane.showInputDialog(null, "Dans quelle frise voulez-vous supprimer un événement?", "Suppression d'un événement",  JOptionPane.QUESTION_MESSAGE, null,listeFrises.getNomsFrisesSuppression(), "chronologie");
			if(reponse1 != null) {
				Chronologie friseASauvegarder = listeFrises.getFrise(reponse1);
				String reponse2 = (String)JOptionPane.showInputDialog(null, "Quel événement voulez-vous supprimer?", "Suppression d'un événement",  JOptionPane.QUESTION_MESSAGE, null, friseASauvegarder.getNomsEvenementsSuppression(), "chronologie");
				if(reponse2 != null) {
					listeFrises.getFrise(reponse1).remove(friseASauvegarder.getEvenement(reponse2));
					File fichier = new File(friseASauvegarder.getCheminFichier());
					LectureEcriture.ecriture(fichier, friseASauvegarder);
					JOptionPane.showMessageDialog((JButton)parEvt.getSource(), ("Evénement supprimé de la frise: "+ friseASauvegarder.getTitre().toString()));
					panelAffichage.setFrise(friseASauvegarder);
					frise = friseASauvegarder;
					panelAffichage.reset();
					evtDisplayed = null;
					try {
						panelAffichage.refreshDiapo(null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		/*
		 * bouton pour supprimer une frise
		 */
		else if(parEvt.getActionCommand().equals(INTITULE_BOUTON_SUPRESSION_FRISE)) {
			String s = (String)JOptionPane.showInputDialog(null, "Quelle frise voulez-vous supprimer?", "Suppression d'une frise",  JOptionPane.QUESTION_MESSAGE, null,listeFrises.getNomsFrisesSuppression(), "chronologie");
			if(s != null) {
				File fichier = new File((listeFrises.getFrise(s)).getCheminFichier());
				fichier.delete();
				listeFrises.suppression(s);
				panelGlobal.resetPanelCreation(listeFrises);
				panelCreationEvenement.resetChampNomFrise(listeFrises);
				Chronologie chronologie = new Chronologie("", new Date(1,1,2000), new Date(1,1,2000), "", "");
				panelAffichage.setFrise(chronologie);
				frise = chronologie;
				panelAffichage.reset();
				evtDisplayed = null;
				try {
					panelAffichage.refreshDiapo(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/*
		 * bouton du panelAffichage pour afficher une frise
		 */
		else if(parEvt.getActionCommand().equals(INTITULE_BOUTON_AFFICHAGE_FRISE)) {
			String s = (String)JOptionPane.showInputDialog(null, "Quelle frise voulez-vous afficher ?","Affichage d'une frise",JOptionPane.QUESTION_MESSAGE, null, listeFrises.getNomsFrises(),"chronologie");
			if(s != null) {
				frise = listeFrises.getFrise(s);
				panelAffichage.setFrise(frise);
				panelAffichage.reset();
				evtDisplayed = null;
				try {
					panelAffichage.refreshDiapo(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} //actionPerformed	

	public void setPanelCreation(PanelCreationEvenement parPanelCreation) {	
		panelCreationEvenement = parPanelCreation;
		panelCreationEvenement.enregistreEcouteur(this);  // le controleur s'enregistre à l'écoute des actions sur le formulaire
	}
		
} // class Controleur

 