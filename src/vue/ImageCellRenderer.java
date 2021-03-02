package vue;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe qui permet de mettre l'image de l'événement
 * à son emplacement dans la JTable
 * @author Marc BOURGEOIS et Iban CORNILY
 *
 */
public final class ImageCellRenderer extends DefaultTableCellRenderer {
	 
	/**
	 * Remplace les événements de la table du panelAffichage par l'image qui leur est liée.
	 */
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {

		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		JLabel label = (JLabel)component;
		String cheminImage = String.valueOf(value);

		ImageIcon iconNonScaled = new ImageIcon(cheminImage);
		ImageIcon iconScaled = new ImageIcon(iconNonScaled.getImage().getScaledInstance(55, 60, Image.SCALE_DEFAULT));
		
		if ( iconScaled.getImageLoadStatus()==java.awt.MediaTracker.COMPLETE ) {
			label.setIcon(iconScaled);
			table.setRowHeight(row, 60);
		}
		else {
			label.setIcon(null);
			table.setRowHeight(row, 60);
		}
		label.setText(""); // on efface le texte

		return component;
	}
}