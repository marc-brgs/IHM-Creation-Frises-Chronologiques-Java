package vue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JPanel ;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Controleur;
import modele.Chronologie;
import modele.Date;
import modele.Evenement;
import modele.ModeleTable;

import javax.swing.JLabel ;
import javax.swing.JOptionPane;
import javax.swing.JButton ;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * Abstraction du calendrier.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class PanelAffichage  extends JPanel implements ActionListener, ConstantesCouleurs, ConstantesCalendrier {
	/**
	 * Conteneur situé au nord de PanelCalendrier.
	 */
	private JPanel panelTitre = new JPanel();
	/**
	 * Conteneur situé au mileu de PanelCalendrier.
	 */
	private JPanel panelDiapo = new JPanel();
	/**
	 * Gestionnaire de répartition.
	 */
	private CardLayout gestCard = new CardLayout(10,10);
	/**
	 * Boutons de navigation.
	 */
	private JButton [] tabBoutons = new JButton [2];
	/**
	 * bouton afficher  pour afficher une frise.
	 */
	private JButton boutonAfficher;
	/**
	 * label contenant le titre de la frise
	 */
	private JLabel labelFrise = new JLabel();
	/**
	 * 
	 */
	private ModeleTable modele;
	/**
	 * 
	 */
	private JTable table;
	/**
	 * 
	 */
	private JScrollPane scrollPane;
	/**
	 * frise a afficher
	 */
	private Chronologie frise;
	/**
	 * 
	 */
	private Evenement evtDisplayed;
	
	/**
	 * Constructeur du PanelAffichage.
	 * @param parFrise frise à afficher
	 */
	public  PanelAffichage(Chronologie parFrise)	{
		frise = parFrise;
		evtDisplayed = null;
		
		JPanel panelBouton = new JPanel();
		boutonAfficher = new JButton(INTITULE_BOUTON_AFFICHAGE_FRISE);
		boutonAfficher.setActionCommand(INTITULE_BOUTON_AFFICHAGE_FRISE);
		panelBouton.setLayout (new BorderLayout ());
		panelBouton.add(boutonAfficher, BorderLayout.EAST);

		labelFrise.setHorizontalAlignment(JLabel.CENTER);
	    labelFrise.setVerticalAlignment(JLabel.CENTER);
	    labelFrise.setFont (FONT_16); 
		labelFrise.setText(frise.getTitre());
		
		panelTitre.setLayout (new BorderLayout ());
		panelTitre.add(labelFrise, BorderLayout.CENTER);
		panelTitre.add(panelBouton, BorderLayout.NORTH);
		
		this.setLayout (new BorderLayout (9,9));
		for(int i = 0; i < 2; i++) {
			tabBoutons[i]= new JButton(INTITULES_BOUTONS_NAVIGATION[i]);
			tabBoutons[i].setActionCommand(INTITULES_BOUTONS_NAVIGATION[i]);
		}
		panelDiapo.setPreferredSize(new Dimension(900,250));
		panelDiapo.setVisible(true);
		
		modele = new ModeleTable(frise);
		table = new JTable(modele);
		scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(0,278));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		for(int i = 0; i < frise.getDuree(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new ImageCellRenderer());
			table.getColumnModel().getColumn(i).setPreferredWidth(55);
		}
		for(int i = 0; i < 4; i ++) {
			table.setRowHeight(i, 60);
		}
		table.addMouseListener(l);

		this.add(panelTitre, BorderLayout.NORTH);
		this.add(panelDiapo, BorderLayout.CENTER);
		this.add(tabBoutons[0], BorderLayout.WEST);
		this.add(tabBoutons[1], BorderLayout.EAST);
		this.add(scrollPane,BorderLayout.SOUTH);
	} // PanelAffichage()
	
	/**
	 * Capte des évenements clavier et souris de l'utilisateur sur les boutons de navigation.
	 */
	public void actionPerformed (ActionEvent parEvt){ 
		int compteur = 0;
		List<Evenement> evtList = new ArrayList<Evenement>(frise.getEvenements());
		
		if(evtDisplayed != null) {
			for(Evenement e : frise.getEvenements()) {
				if(e.compareTo(evtDisplayed) == 0)
					break;
				compteur += 1;
			}
		}

		if(parEvt.getActionCommand().equals(INTIT_PRECEDENT)) {
			if(evtDisplayed == null && evtList.get(0) != null) {
				evtDisplayed = evtList.get(0);
				try {
					refreshDiapo(evtDisplayed);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(evtList.get(0) != null){
				compteur -= 1;
				try {
					if(compteur < 0)
						refreshDiapo(evtList.get(0));
					else
						refreshDiapo(evtList.get(compteur));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(parEvt.getActionCommand().equals(INTIT_SUIVANT)) {
			if(evtDisplayed == null && evtList.get(0) != null) {
				evtDisplayed = evtList.get(0);
				try {
					refreshDiapo(evtDisplayed);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(evtList.get(0) != null){	
				compteur += 1;
				try {
					if(compteur > frise.getEvenements().size()-2)
						refreshDiapo(evtList.get(frise.getEvenements().size()-2));
					else
						refreshDiapo(evtList.get(compteur));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	} // actionPerformed()
	
	/**
	 * Affiche l'événement voulu dans le panelDiapo.
	 * @param parEvtDisplayed
	 * @throws IOException
	 */
	public void refreshDiapo(Evenement parEvtDisplayed) throws IOException {
		evtDisplayed = parEvtDisplayed;
		if(evtDisplayed == null)
			panelDiapo.removeAll();
		else {
			if(panelDiapo != null) {
				panelDiapo.removeAll();
			}
			int compteur = 0;
			String newDescription = "";
		    StringTokenizer tokenizer = new StringTokenizer(parEvtDisplayed.getDescription(), " ");
		    
		    while (tokenizer.hasMoreElements()) {
		    	if(compteur == 12) {
		    		newDescription += "<br/>";
		    		compteur = 0;
		    	}
		    	else {
		    		newDescription += " " + tokenizer.nextToken();
		    		compteur += 1;
		    	}
		    }
		    
			String text = new String();
			text = "<html>\r\n" +
				"<h4> <i>"+ parEvtDisplayed.getDate().getJour() + " " + MOIS[parEvtDisplayed.getDate().getMois()-1] + " " + parEvtDisplayed.getDate().getAnnee() +"</i> </h4>\r\n" +
				"<h3> " + parEvtDisplayed.getNom() + "</h3>\r\n" +
				newDescription +"\r\n</html> ";
			
			JLabel label = new JLabel(text);
			BufferedImage myPicture = ImageIO.read(new File(parEvtDisplayed.getCheminImage()));
			ImageIcon iconNonScaled = new ImageIcon(myPicture);
			ImageIcon iconScaled = new ImageIcon(iconNonScaled.getImage().getScaledInstance(200, 175, Image.SCALE_DEFAULT));
			JLabel picLabel = new JLabel(iconScaled);
			picLabel.setSize(200,200);
			picLabel.setVerticalAlignment(JLabel.CENTER);
			panelDiapo.add(picLabel, BorderLayout.WEST);
			panelDiapo.add(label, BorderLayout.CENTER);
			label.revalidate();
		}
		panelDiapo.repaint();
	}//refreshDiapo();
	
	/**
	 * Recrée une nouvelle table avec la frise à afficher.
	 */
	public void reset() {
		table.setModel(new ModeleTable(frise));
		for(int i = 0; i < frise.getDuree(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new ImageCellRenderer());
			table.getColumnModel().getColumn(i).setPreferredWidth(55);
		}
	    labelFrise.setText(frise.getTitre());
	} //reset table
    
	/**
	 * Récupère l'événement (si il y en a un) dans la case ou l'utilisateur vient de cliquer.
	 * Puis indique l'événement à afficher pour le diaporama.
	 */
	MouseListener l = new MouseAdapter() {
        /** 
         * @inherited <p>
         */
        @Override
        public void mousePressed(MouseEvent event) {
            JTable table = (JTable) event.getComponent();
            int col = table.columnAtPoint(event.getPoint());
            int row = table.rowAtPoint(event.getPoint());
            if (!(col < 0 || row < 0)) {
                String chemin = (String) table.getModel().getValueAt(row, col);
                if(chemin != null) {
                	if(frise.getEchelle().equals("Années")) {
                		int annee = frise.getDateDebut().getAnnee();
	                	int poids = 4-row;
	                	int x = 0;
	                	
		    			while(x != col) {
		    				annee++;
		    				x += 1;
		    			}
		    			
		    			for(Evenement e : frise.getEvenements()) {
		    				if(annee == e.getDate().getAnnee() && poids == e.getPoids())
		    					evtDisplayed = e;
		    			}
		    			try {
							refreshDiapo(evtDisplayed);
						} catch (IOException e) {
							e.printStackTrace();
						}
                	}
                	else if(frise.getEchelle().equals("Mois")) {
                		Date dateCourante = frise.getDateDebut();
	                	int poids = 4-row;
	                	int x = 0;
	                	
		    			while(x != col) {
		    				dateCourante = dateCourante.moisProchain();
		    				x += 1;
		    			}
		    			
		    			for(Evenement e : frise.getEvenements()) {
		    				if(dateCourante.getMois() == e.getDate().getMois() && dateCourante.getAnnee() == e.getDate().getAnnee() && poids == e.getPoids())
		    					evtDisplayed = e;
		    			}
		    			try {
							refreshDiapo(evtDisplayed);
						} catch (IOException e) {
							e.printStackTrace();
						}
                	}
                	else if(frise.getEchelle().equals("Jours")) {
	                	Date dateCourante = frise.getDateDebut();
	                	int poids = 4-row;
	                	int x = 0;
	                	
		    			while(x != col) {
		    				dateCourante = dateCourante.dateDuLendemain();
		    				x += 1;
		    			}
		    			
		    			for(Evenement e : frise.getEvenements()) {
		    				if(dateCourante.compareTo(e.getDate()) == 0 && poids == e.getPoids())
		    					evtDisplayed = e;
		    			}
		    			try {
							refreshDiapo(evtDisplayed);
						} catch (IOException e) {
							e.printStackTrace();
						}
	                }
	                
	                
                }
            }
        }
	}; //MouseAdapter

	/**
	 * Met à l'écoute le controleur, les actions sur les boutons "afficher frise",  "<" et ">"
	 * @param controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		boutonAfficher.addActionListener(controleur);
		tabBoutons[0].addActionListener(controleur);
		tabBoutons[1].addActionListener(controleur);
	}
	
	/**
	 * Retourne l'événement affiché dans le diaporama.
	 * @return
	 */
	public Evenement getEvtDisplayed() {
		return evtDisplayed;
	}
	
	/**
	 * Défini l'événement à afficher dans le diaporama.
	 * @param evenement
	 */
	public void setEvtDisplayed(Evenement evenement) {
		evtDisplayed = evenement;
	}
	
	/**
	 * Retourne la barre de déplacement de la table.
	 * @return
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	/**
	 * Définie la frise à afficher.
	 * @param parFrise
	 */
	public void setFrise(Chronologie parFrise) {
		frise = parFrise;
	}
    
} // classe PanelAffichage

