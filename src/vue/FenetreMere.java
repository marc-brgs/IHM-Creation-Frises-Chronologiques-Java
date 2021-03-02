package vue;

import javax.swing.Box;
import javax.swing.JFrame ;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import controleur.Controleur;
import modele.Date;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Abstraction de la fenetre principale.
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public class FenetreMere extends JFrame implements ConstantesCouleurs, ConstantesMenu {
	/**
	 * Barre de menu de la fenêtre.
	 */
	private JMenuBar menuBar;
	
	private JMenuItem tabItemMenuDeroulant[];
	
	JMenuItem tabItem[];
	/**
	 * Constructeur de FenetreAgenda.
	 * @param parTitre Intitulé de la fenêtre.
	 */
	public FenetreMere (String parTitre) {
		super (parTitre); 
	    PanelGlobal contentPane  = new PanelGlobal();
		setContentPane (contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(900,600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		/**
		 * Remplace setSize().
		 * Ajuste la taille de la fenêtre automatiquement.
		 */
		pack();
		setVisible(true);
		/** accessibilité : à l'ouverture du formulaire le focus est donné au premier champ de saisie
	 	cela doit être fait après le setVisible(true); sinon ne fonctionne pas
		*/
		setLocation(80,50);
		
		// Barre de menu et ses items.
 		menuBar = new JMenuBar();
 		this.setJMenuBar(menuBar);
 		menuBar.setVisible(true);
 		
 		JMenu menuDeroulant = new JMenu(ConstantesMenu.INTIT_MENU[0]);
 		menuDeroulant.setMnemonic('C');
 		tabItemMenuDeroulant = new JMenuItem[2];
 		tabItemMenuDeroulant[0] = new JMenuItem(ConstantesMenu.INTIT_MENU[3], 'R'); ///, ConstantesMenu.INTIT_MENU[i].charAt(10)
 		tabItemMenuDeroulant[0].setAccelerator (KeyStroke.getKeyStroke('R', java.awt.Event.CTRL_MASK));
 		tabItemMenuDeroulant[0].addActionListener(contentPane);
 		tabItemMenuDeroulant[0].setActionCommand(ConstantesMenu.INTIT_MENU[3]);
 		menuDeroulant.add(tabItemMenuDeroulant[0]);
 		
 		tabItemMenuDeroulant[1] = new JMenuItem(ConstantesMenu.INTIT_MENU[4], 'E'); ///, ConstantesMenu.INTIT_MENU[i].charAt(10)
 		tabItemMenuDeroulant[1].setAccelerator (KeyStroke.getKeyStroke('E', java.awt.Event.CTRL_MASK));
 		tabItemMenuDeroulant[1].addActionListener(contentPane);
 		tabItemMenuDeroulant[1].setActionCommand(ConstantesMenu.INTIT_MENU[4]);
 		menuDeroulant.add(tabItemMenuDeroulant[1]);
 		
 		menuBar.add(menuDeroulant);
 		
 		tabItem = new JMenuItem[3];
 		for(int i = 1; i < 3; i++) {
 			tabItem[i] = new JMenuItem(ConstantesMenu.INTIT_MENU[i], ConstantesMenu.INTIT_MENU[i].charAt(0)); ///, ConstantesMenu.INTIT_MENU[i].charAt(0)
 			tabItem[i].setAccelerator (KeyStroke.getKeyStroke(ConstantesMenu.INTIT_MENU[i].charAt(0), java.awt.Event.CTRL_MASK));
 			tabItem[i].addActionListener(contentPane);
 			tabItem[i].setActionCommand(ConstantesMenu.INTIT_MENU[i]);
 			tabItem[i].setOpaque(false);
 			menuBar.add(tabItem[i]);
 		}
 		menuBar.add(Box.createHorizontalStrut(580));
		
		menuBar.setVisible(true);
		
	} // FenetreAgenda()
	
	/**
	 * Lancement du programme.
	 * @param args Chaînes de caractères données lors du lancement.
	 */
	public static void main (String  [] args) {
	 	new FenetreMere ("Frise chronologique");
	}  // main ()

	/**
	 * Création des marges.
	 */
	public Insets getInsets() {
		return new Insets(35,20,20,20); //(60,20,20,20) si résolution 4k
	} // getInsets()
	
} // class FenetreAgenda