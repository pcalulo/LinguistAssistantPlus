package textgen.la.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import textgen.la.models.ConstitList;
import textgen.la.models.Constituent;
import textgen.la.models.FeatureList;
import textgen.la.models.Parser;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;

/**
 * BaseMainWindow acts as a container for the UI initialization code generated
 * by WindowBuilder. The implementation of business logic should, as much as
 * possible, be put inside the LAMainWindow class, which inherits from this.
 * This class should only be concerned with UI code.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class BaseMainWindow {

	private JFrame windowForm;
	private Parser parser;
	private JTextField labelField;
	private JTextField conceptField;
	private JScrollPane scrollPane;
	private JButton okButton;
	private FeatureList features;
	private Constituent cons;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnHelp;
	private JMenuItem mntmNew;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmClose;
	private JMenuItem mntmAbout;
	private JToolBar toolBar;
	private JButton btnSelectVerse;
	private Component horizontalGlue;
	private JLabel lblBookTitle;

	/**
	 * Create the application.
	 */
	public BaseMainWindow() {
		initialize();
		customInitialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		windowForm = new JFrame();
		windowForm.setTitle("Linguist Assistant+");
		windowForm.setBounds(100, 100, 643, 435);
		windowForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scrollPane = new JScrollPane();
		windowForm.getContentPane().add(scrollPane,
				BorderLayout.CENTER);

		JPanel infoPanel = new JPanel();
		windowForm.getContentPane()
				.add(infoPanel, BorderLayout.SOUTH);

		okButton = new JButton("OK");

		JPanel panel = new JPanel();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					NewFeatureWindow frame = new NewFeatureWindow();
					frame.setVisible(true);
					//while(!frame.checker){
					//}
					features = frame.returnFeatures();
					//cons = new Constituent(labelField.getText(),conceptField.getText(), features, new ConstitList(), <parent depth>);
					
					
			}
		});
		GroupLayout gl_infoPanel = new GroupLayout(infoPanel);
		gl_infoPanel.setHorizontalGroup(
			gl_infoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_infoPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_infoPanel.setVerticalGroup(
			gl_infoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_infoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(okButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addContainerGap(23, Short.MAX_VALUE))
		);

		JLabel lblLabel = new JLabel("Label");

		labelField = new JTextField();
		labelField.setColumns(10);

		JLabel lblConcept = new JLabel("Concept");

		conceptField = new JTextField();
		conceptField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel.createSequentialGroup()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING,
										false)
										.addComponent(lblConcept,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblLabel,
												GroupLayout.DEFAULT_SIZE, 56,
												Short.MAX_VALUE))
						.addGap(4)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(conceptField,
												GroupLayout.DEFAULT_SIZE, 216,
												Short.MAX_VALUE)
										.addComponent(labelField,
												GroupLayout.DEFAULT_SIZE, 216,
												Short.MAX_VALUE))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblLabel,
														GroupLayout.PREFERRED_SIZE,
														24,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														labelField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblConcept)
												.addComponent(
														conceptField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(14, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		infoPanel.setLayout(gl_infoPanel);
		
		toolBar = new JToolBar();
		windowForm.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		btnSelectVerse = new JButton("Select Verse");
		toolBar.add(btnSelectVerse);
		
		horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		lblBookTitle = new JLabel("Book/verse goes here");
		toolBar.add(lblBookTitle);
		
		menuBar = new JMenuBar();
		windowForm.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New Text");
		mnFile.add(mntmNew);
		
		mntmOpen = new JMenuItem("Open Text");
		mnFile.add(mntmOpen);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
	
	/**
	 * Runs any additional initialization that the WindowBuilder options don't cover
	 */
	private void customInitialize() {
		// Speed up scrollbar scrolling
		getScrollPane().getHorizontalScrollBar().setUnitIncrement(16);
	}

	protected JTextField getLabelField() {
		return labelField;
	}

	protected JTextField getConceptField() {
		return conceptField;
	}
	protected JFrame getWindowForm() {
		return windowForm;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JButton getOkButton() {
		return okButton;
	}
}
