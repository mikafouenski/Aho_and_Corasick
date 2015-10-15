package gui;

import javax.swing.* ; 

import java.awt.* ;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CadreJolie extends JFrame{
	
    public CadreJolie(String titre) {
        super(titre);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                Quitter();
            }
        });
        setContentPane(Panneau());
        setVisible(true) ;
        setLocationRelativeTo(null);
        setSize(500,500) ;
    }
    
    private JPanel Panneau() {
    	
    	JPanel Fenetre = new JPanel(new BorderLayout()); 
    	this.setJMenuBar(BarreDeMenus());
       	JTextArea ZoneTexte = new JTextArea(); 
       	Fenetre.add(ZoneTexte, BorderLayout.CENTER);
    	return Fenetre;
    } 
       
    private JOptionPane Quitter() {
    	JOptionPane Pane0 = new JOptionPane();
    	JDialog Dialog = new JDialog();
    	int retour = Pane0.showConfirmDialog(Dialog, "Voulez-vous vraiment quitter ?", 
    	      "Quitter ?", JOptionPane.OK_CANCEL_OPTION);
    	if ( retour == JOptionPane.OK_OPTION ) System.exit(0);
       	return Pane0;
    }  

    private JOptionPane ouvrir() {
    	JOptionPane Pane2 = new JOptionPane();
    	JDialog Dialog = new JDialog();
    	System.out.println("coucou");
    	
    	JFileChooser choisirFichier = new JFileChooser();
    	JEditorPane document = new JEditorPane();
    	int test = choisirFichier.showDialog(this, null);
    	File fichier = null;
    	
    	if (test == JFileChooser.APPROVE_OPTION) {
			fichier = choisirFichier.getSelectedFile();
		}
    	
    	String chaine;
    	try {
			byte b[] = Files.readAllBytes(fichier.toPath());
			chaine = new String(b, "UTF-8");
			System.out.println(chaine);
			JTextArea zoneTexte = new JTextArea();
			Panneau().add(zoneTexte);
			zoneTexte.append(chaine);
			
		} catch (IOException e) {
			System.out.println("nope !");
		}
    	
    	/*JOptionPane.showMessageDialog(Dialog, 
    	         "Ouverture non Implémentée",
    	         "Excuses ",
    	         JOptionPane.WARNING_MESSAGE);*/
    	return Pane2;
	
    }

    private JMenuBar BarreDeMenus() {
    	JMenuBar Barre = new JMenuBar();
    	JMenu Menu0 = new JMenu("Fichier"); 
    	Barre.add(Menu0);
    	
    	JMenuItem Ouvrir = new JMenuItem("Ouvrir");
    	Menu0.add(Ouvrir);
    	Ouvrir.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                ouvrir();
             }
             
         });
    	
    	JMenuItem Quitter = new JMenuItem("Quitter");
        Menu0.add(Quitter) ;
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               Quitter();
            }
        });
    	
    	JMenu Menu = new JMenu("Actions") ; 
    	Barre.add(Menu) ;
    	
    	JMenuItem Crea = new JMenuItem("Rechercher");
        Menu.add(Crea);
        Crea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	new CadreRecherche("Recherche...") ;
            }
            
        });
    	
    	return Barre ;
    }	
    
}
