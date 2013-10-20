package textgen.la.ui.ontology;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import textgen.la.models.Constituent;
import textgen.la.ui.displaymodels.FeatureTableModel;

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
	private JTable table;
	private JPanel panel;

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private Constituent constituent;
	private JTable table_1;

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
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Lexicon");
		setBounds(100, 100, 666, 477);
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Noun", "Verb", "Adjective", "Adverb", "Adposition", "Conjunction", "Particle"}));
		
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
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddStem)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Hello", null, null},
			},
			new String[] {
				"Stem", "Definition", "Mapping"
			}
		));
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
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

	public void setConstituent(Constituent constituent) {
		this.constituent = constituent;

		FeatureTableModel ftm = new FeatureTableModel(constituent);
		getFeaturesTable().setModel(ftm);

		if (constituent.getConcept() != null) {
			setTitle(constituent.getLabel() + " : " + constituent.getConcept());
		} else {
			setTitle(constituent.getLabel());
		}
	}

	public JTable getFeaturesTable() {
		return table;
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

	public Constituent getConstituent() {
		return constituent;
	}
}
