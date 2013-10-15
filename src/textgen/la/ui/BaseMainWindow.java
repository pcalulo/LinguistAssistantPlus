package textgen.la.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import textgen.la.models.Constituent;
import textgen.la.models.Parser;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;

import javax.swing.JTextField;

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
	private JScrollPane scrollPane;
	private JTextField labelField;
	private JTextField conceptField;

	/**
	 * Create the application.
	 */
	public BaseMainWindow() {
		initialize();
		initializeBoxInterface();
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

		JButton btnHelloWorld = new JButton("Hello World!");

		JPanel panel = new JPanel();
		GroupLayout gl_infoPanel = new GroupLayout(infoPanel);
		gl_infoPanel.setHorizontalGroup(gl_infoPanel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_infoPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 276,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnHelloWorld).addContainerGap()));
		gl_infoPanel
				.setVerticalGroup(gl_infoPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_infoPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_infoPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_infoPanel
																		.createSequentialGroup()
																		.addComponent(
																				btnHelloWorld,
																				GroupLayout.PREFERRED_SIZE,
																				48,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																Alignment.TRAILING,
																gl_infoPanel
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));

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
	}

	private void initializeBoxInterface() {
		JPanel panel;

		panel = new JPanel();
		panel.setBackground(Color.white);
		parser = new Parser(panel);

		scrollPane.setViewportView(panel);
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
}