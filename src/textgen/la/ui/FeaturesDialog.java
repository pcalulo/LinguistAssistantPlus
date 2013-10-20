package textgen.la.ui;

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

public class FeaturesDialog extends JDialog {

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
			FeaturesDialog dialog = new FeaturesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FeaturesDialog() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Features");
		setBounds(100, 100, 476, 306);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Features",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel buttonPanel = new JPanel();
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] { 82, 0 };
		gbl_buttonPanel.rowHeights = new int[] { 0, 24, 24, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		buttonPanel.setLayout(gbl_buttonPanel);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddButtonClick();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		buttonPanel.add(btnAdd, gbc_btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRemoveButtonClick();
			}
		});
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.anchor = GridBagConstraints.WEST;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 1;
		buttonPanel.add(btnRemove, gbc_btnRemove);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 364,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE,
								94, GroupLayout.PREFERRED_SIZE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel
				.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 234,
						Short.MAX_VALUE)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addGap(12)
								.addComponent(buttonPanel,
										GroupLayout.DEFAULT_SIZE, 222,
										Short.MAX_VALUE)));
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { "Hello",
				"World" }, }, new String[] { "Name", "Value" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel.add(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOkButtonClick();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
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
				FeaturesDialog.this.setVisible(false);
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

	protected void onAddButtonClick() {
		int selectedRowIndex = getFeaturesTable().getSelectedRow();
		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
				.getModel();

		System.out.println(selectedRowIndex);

		ftm.insertEmptyRow(selectedRowIndex);
	}

	protected void onRemoveButtonClick() {
		int selectedRowIndex = getFeaturesTable().getSelectedRow();
		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
				.getModel();

		if (selectedRowIndex >= 0) {
			ftm.removeRowAt(selectedRowIndex);
		}
	}

	protected void onOkButtonClick() {
		FeatureTableModel ftm = (FeatureTableModel) getFeaturesTable()
				.getModel();
		constituent.getFeatureList().setFeatures(ftm.getFeatures());

		logger.info("Closing dialog via OK");
		setVisible(false);
	}

	public Constituent getConstituent() {
		return constituent;
	}
}
