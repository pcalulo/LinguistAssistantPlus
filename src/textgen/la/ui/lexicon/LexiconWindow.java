package textgen.la.ui.lexicon;

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

public class LexiconWindow extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JPanel panel;

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private Constituent constituent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LexiconWindow dialog = new LexiconWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LexiconWindow() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Lexicon");
		setBounds(100, 100, 601, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {170, 0};
		gbl_buttonPanel.rowHeights = new int[]{23, 23, 23, 23, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
				JButton btnAddStem = new JButton("Add New Stem");
				btnAddStem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onAddStemClick();
					}
				});
				GridBagConstraints gbc_btnAddStem = new GridBagConstraints();
				gbc_btnAddStem.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddStem.insets = new Insets(0, 0, 5, 0);
				gbc_btnAddStem.gridx = 0;
				gbc_btnAddStem.gridy = 0;
				buttonPanel.add(btnAddStem, gbc_btnAddStem);
		
				JButton btnAddFeature = new JButton("Add New Feature");
				btnAddFeature.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onAddFeatureClick();
					}
				});
				GridBagConstraints gbc_btnAddFeature = new GridBagConstraints();
				gbc_btnAddFeature.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddFeature.insets = new Insets(0, 0, 5, 0);
				gbc_btnAddFeature.gridx = 0;
				gbc_btnAddFeature.gridy = 1;
				buttonPanel.add(btnAddFeature, gbc_btnAddFeature);
		
				JButton btnEditFeature = new JButton("Edit a Feature");
				btnEditFeature.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						onEditFeatureClick();
					}
				});
				
				GridBagConstraints gbc_btnEditFeature = new GridBagConstraints();
				gbc_btnEditFeature.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEditFeature.insets = new Insets(0, 0, 5, 0);
				gbc_btnEditFeature.gridx = 0;
				gbc_btnEditFeature.gridy = 2;
				buttonPanel.add(btnEditFeature, gbc_btnEditFeature);
				
				JButton btnEditForms = new JButton("Edit Forms");
				btnEditForms.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onEditFormsClick();
					}
				});
		GridBagConstraints gbc_btnEditForms = new GridBagConstraints();
		gbc_btnEditForms.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditForms.gridx = 0;
		gbc_btnEditForms.gridy = 3;
		buttonPanel.add(btnEditForms, gbc_btnEditForms);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 178, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))
		);
		
		JLabel lblFeature = new JLabel("POS:     ");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Noun", "Verb", "Adjective", "Adverb", "Adposition", "Conjunction", "Particle"}));
		
		JLabel lblView = new JLabel("View:");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Definition", "Features", "Forms"}));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblView, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblFeature, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox_1, 0, 143, Short.MAX_VALUE)
						.addComponent(comboBox_2, Alignment.LEADING, 0, 143, Short.MAX_VALUE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeature, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblView, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Hello", null},
			},
			new String[] {
				"Stem", "Definition"
			}
		));
		panel.add(table, BorderLayout.CENTER);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox, BorderLayout.NORTH);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}

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
				LexiconWindow.this.setVisible(false);
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
		new LexNewStemDialog();
	}

	protected void onAddFeatureClick() {
//		int selectedRowIndex = getFeaturesTable().getSelectedRow();
//		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
//				.getModel();
//
//		if (selectedRowIndex >= 0) {
//			ftm.removeRowAt(selectedRowIndex);
//		}
		new LexNewFeatureDialog();
	}
	
	protected void onEditFeatureClick() {
//		int selectedRowIndex = getFeaturesTable().getSelectedRow();
//		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
//				.getModel();
//
//		if (selectedRowIndex >= 0) {
//			ftm.removeRowAt(selectedRowIndex);
//		}
		new LexEditFeaturesDialog();
	}
	
	protected void onEditFormsClick() {
//		int selectedRowIndex = getFeaturesTable().getSelectedRow();
//		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
//				.getModel();
//
//		if (selectedRowIndex >= 0) {
//			ftm.removeRowAt(selectedRowIndex);
//		}
		new LexEditFormsDialog();
	}

	public Constituent getConstituent() {
		return constituent;
	}
}
