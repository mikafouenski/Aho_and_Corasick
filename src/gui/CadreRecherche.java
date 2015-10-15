package gui;

import javax.swing.* ; 
import java.awt.* ;

public class CadreRecherche extends JFrame {

	public CadreRecherche(String titre) {
		super(titre);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setContentPane(panneauDeContenu());
		setVisible(true) ;
		setLocationRelativeTo(null);
		setSize(500, 150);
	}
} 
