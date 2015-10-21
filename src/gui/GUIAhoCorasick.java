package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import com.sun.org.apache.bcel.internal.generic.NEW;

import aho_and_corasick.AhoCorasick;
import aho_and_corasick.SearchResult;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class GUIAhoCorasick extends JFrame {

	private JPanel contentPane;
	private JTextField textLookFor;
	private JTextArea textFound;
	private static JTextArea jtext;
	private AhoCorasick tree = new AhoCorasick();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAhoCorasick frame = new GUIAhoCorasick();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIAhoCorasick() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				Quitter();
			}
		});
		setBounds(100, 100, 610, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mnFile.add(mntmOpenFile);
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ouvrir();
			}

		});

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitter();
			}

		});

		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);

		JMenuItem mntmSearch = new JMenuItem("Search");
		mnAction.add(mntmSearch);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add();
			}

		});

		textLookFor = new JTextField();
		textLookFor.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap(160, Short.MAX_VALUE)
						.addComponent(textLookFor, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAdd))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(3)
										.addComponent(textLookFor)))
						.addContainerGap())
				);
		panel.setLayout(gl_panel);

		jtext = new JTextArea();
		jtext.setLineWrap(true);
		contentPane.add(jtext, BorderLayout.CENTER);
		jtext.setColumns(10);
		JScrollPane zoneScroll = new JScrollPane(jtext);
		getContentPane().add(zoneScroll);
		
		JPanel panel_2 = new JPanel();
		zoneScroll.setRowHeaderView(panel_2);
		
				textFound = new JTextArea();
				textFound.setRows(22);
				panel_2.add(textFound);
				textFound.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnSearch = new JButton("Search");
		panel_1.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Search();
			}

		});
	}

	private JOptionPane Quitter() {
		JOptionPane Pane0 = new JOptionPane();
		JDialog Dialog = new JDialog();
		int retour = Pane0.showConfirmDialog(Dialog, "Voulez-vous vraiment quitter ?", 
				"Quitter ?", JOptionPane.OK_CANCEL_OPTION);
		if ( retour == JOptionPane.OK_OPTION ) System.exit(0);
		return Pane0;
	}

	private Dialog Ouvrir(){
		JDialog Dialog = new JDialog();
		System.out.println("coucou");

		JFileChooser choisirFichier = new JFileChooser();
		JEditorPane document = new JEditorPane();
		int test = choisirFichier.showDialog(this, null);
		File fichier = null;
		byte[] b = null;

		if (test == JFileChooser.APPROVE_OPTION) {
			fichier = choisirFichier.getSelectedFile();
			//b = fichier.getPath().getBytes();
		}

		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new InputStreamReader(new FileInputStream(fichier), "UTF-8"));
			String str;
			while ((str = buff.readLine()) != null) {
				jtext.append("\n"+str);
			}
		} catch (IOException e) {
			System.out.println("nope !");
		}

		return Dialog;
	}

	private void Add(){
		String word = textLookFor.getText();
		tree.add(word.getBytes(), word);

		textFound.append(word + "\n\n");
		textLookFor.setText(null);
	}

	private void Search(){
		tree.prepare();
		String text = jtext.getText();

		Iterator searcher = tree.search(text.getBytes());
		while (searcher.hasNext()) {
			SearchResult result = (SearchResult) searcher.next();
			System.out.println(result.getDisplay());
			System.out.println("Found at index: " + result.getLastIndex());
			Result(result.getDisplay().toString(), result.getLastIndex());
		}
	}

	public static void Result(String result, int index){
		Highlighter highlighter = jtext.getHighlighter();
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		
		
		int end = index;
		int begin = end - result.length() + 2;
		try {
			highlighter.addHighlight(begin, end, painter );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
