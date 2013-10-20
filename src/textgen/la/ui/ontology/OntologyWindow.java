package textgen.la.ui.ontology;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import textgen.la.models.Constituent;
import textgen.la.models.ontology.Noun;
import textgen.la.models.ontology.OntologyParser;
import textgen.la.models.ontology.POS;
import textgen.la.ui.displaymodels.FeatureTableModel;
import textgen.la.ui.displaymodels.OntologyTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JScrollBar;

public class OntologyWindow extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTable ontologyTable;
	private JPanel panel;

	
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private OntologyParser parser;
	private JTable table_1;
	
	TableModel nounModel, verbModel, adjModel,
			advModel, adpModel, conjModel, partModel, relModel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			OntologyWindow dialog = new OntologyWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OntologyWindow() {
		
		parser = new OntologyParser();
		fillUpTables();
		
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Lexicon");
		setBounds(100, 100, 740, 477);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ontology", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lexicon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new BorderLayout(0, 0));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)))
					.addGap(3))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
					.addGap(9))
		);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Hello", null},
			},
			new String[] {
				"Stem", "Definition"
			}
		));
		panel_2.add(new JScrollPane(table_1), BorderLayout.CENTER);
		
		JLabel lblFeature = new JLabel("POS:     ");
		
		final JComboBox posBox = new JComboBox();
		
		posBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeModel(posBox.getSelectedIndex());
			}
		});
		posBox.setModel(new DefaultComboBoxModel(new String[] {"Noun", "Verb", "Adjective", "Adverb", "Conjunction", "Particle", "Relation"}));
		
				JButton btnAddStem = new JButton("Add New Stem");
				btnAddStem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onAddStemClick();
					}
				});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblFeature)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(posBox, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddStem, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeature, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(posBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddStem)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new BorderLayout(0, 0));

		ontologyTable = new JTable();
		ontologyTable.setFillsViewportHeight(true);
		ontologyTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Stem", "Definition"
			}
		));
		panel.add(new JScrollPane(ontologyTable), BorderLayout.CENTER);
		contentPanel.setLayout(gl_contentPanel);

		customInitialize();
		
	}

	/**
	 * Do extra initialization steps that the drag-and-drop UI doesn't allow
	 */
	public void customInitialize() {
		// Display the table's header
		JTable table = getFeaturesTable();
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		getFeaturesPanel().add(header, BorderLayout.NORTH);

		// Close when Escape is pressed
		KeyStroke escapeKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
				0);
		this.getRootPane().registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OntologyWindow.this.setVisible(false);
			}
		}, escapeKeystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

//	public void setConstituent(Constituent constituent) {
//		this.constituent = constituent;
//
//		FeatureTableModel ftm = new FeatureTableModel(constituent);
//		getFeaturesTable().setModel(ftm);
//
//		if (constituent.getConcept() != null) {
//			setTitle(constituent.getLabel() + " : " + constituent.getConcept());
//		} else {
//			setTitle(constituent.getLabel());
//		}
//	}

	public JTable getFeaturesTable() {
		return ontologyTable;
	}

	public JPanel getFeaturesPanel() {
		return panel;
	}

	// == Event handlers! ==

	protected void onAddStemClick() {
//		int selectedRowIndex = getFeaturesTable().getSelectedRow();
//		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
//				.getModel();
//
//		System.out.println(selectedRowIndex);
//
//		ftm.insertEmptyRow(selectedRowIndex);
		new OntNewConceptDialog();
	}
//
//	protected void onAddFeatureClick() {
////		int selectedRowIndex = getFeaturesTable().getSelectedRow();
////		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
////				.getModel();
////
////		if (selectedRowIndex >= 0) {
////			ftm.removeRowAt(selectedRowIndex);
////		}
//		new LexNewFeatureDialog();
//	}
//	
//	protected void onEditFeatureClick() {
////		int selectedRowIndex = getFeaturesTable().getSelectedRow();
////		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
////				.getModel();
////
////		if (selectedRowIndex >= 0) {
////			ftm.removeRowAt(selectedRowIndex);
////		}
//		new LexEditFeaturesDialog();
//	}
//	
//	protected void onEditFormsClick() {
////		int selectedRowIndex = getFeaturesTable().getSelectedRow();
////		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
////				.getModel();
////
////		if (selectedRowIndex >= 0) {
////			ftm.removeRowAt(selectedRowIndex);
////		}
//		new LexEditFormsDialog();
//	}
	
	public void createNounTableModel()
	{
		ArrayList<POS> nounList = (ArrayList<POS>) parser.getOntology().getNounList();
		nounModel = new OntologyTableModel(nounList);
	}
	
	public void createVerbTableModel()
	{
		ArrayList<POS> verbList = (ArrayList<POS>) parser.getOntology().getVerbList();
		verbModel = new OntologyTableModel(verbList);
	}
	
	public void createAdjectiveTableModel()
	{
		ArrayList<POS> adjList = (ArrayList<POS>) parser.getOntology().getAdjList();
		adjModel = new OntologyTableModel(adjList);
	}
	
	public void createAdverbTableModel()
	{
		ArrayList<POS> advList = (ArrayList<POS>) parser.getOntology().getAdvList();
		advModel = new OntologyTableModel(advList);
	}
	
	public void createConjunctionTableModel()
	{
		ArrayList<POS> conjList = (ArrayList<POS>) parser.getOntology().getConjList();
		conjModel = new OntologyTableModel(conjList);
	}
	
	public void createParticleTableModel()
	{
		ArrayList<POS> pList = (ArrayList<POS>) parser.getOntology().getPList();
		partModel = new OntologyTableModel(pList);
	}
	
	public void createRelationTableModel()
	{
		ArrayList<POS> rList = (ArrayList<POS>) parser.getOntology().getRList();
		relModel = new OntologyTableModel(rList);
	}

	protected void fillUpTables()
	{
		createNounTableModel();
		createVerbTableModel();
		createAdjectiveTableModel();
		createAdverbTableModel();
		createConjunctionTableModel();
		createParticleTableModel();
		createRelationTableModel();
	}
	
	public void changeModel(int index)
	{
		
		switch(index)
		{
			case 0:
				ontologyTable.setModel(nounModel);
				break;
			case 1:
				ontologyTable.setModel(verbModel);
				break;
			case 2:
				ontologyTable.setModel(adjModel);
				break;
			case 3:
				ontologyTable.setModel(advModel);
				break;
			case 4:
				ontologyTable.setModel(conjModel);
				break;
			case 5:
				ontologyTable.setModel(partModel);
				break;
			case 6:
				ontologyTable.setModel(relModel);
				break;
		}
	}
}
