package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.Marshaller.Listener;

import textgen.la.models.directory.LinguistText;
import textgen.la.models.directory.VerseReference;
import textgen.la.ui.displaymodels.LinguistTextTreeModel;

public class LinguistTextNavigatorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTree tree;

	private LAMainWindow mainWindow;
	private LinguistText linguistText;
	private VerseReference selectedVerseRef;

	public interface VerseSelectionListener {
		public void onVerseSelected(LinguistText linguistText,
				VerseReference verseRef);
	}

	private VerseSelectionListener verseSelectionListener;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LinguistTextNavigatorDialog dialog = new LinguistTextNavigatorDialog(
					null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LinguistTextNavigatorDialog(Frame parent,
			VerseSelectionListener listener) {
		super(parent);
		verseSelectionListener = listener;

		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Select Verse");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		tree = new JTree();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tree, GroupLayout.DEFAULT_SIZE,
				438, Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tree, GroupLayout.DEFAULT_SIZE,
				228, Short.MAX_VALUE));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOkClick();
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancelClick();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		customInitialize();
	}

	/**
	 * Performs additional initialization that cannot be specified through the
	 * WindowBuilder UI
	 */
	protected void customInitialize() {
		// Close when Escape is pressed
		KeyStroke escapeKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
				0);
		this.getRootPane().registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				onCancelClick();
			}
		}, escapeKeystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Set tree selection mode
		getTree().getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				onTreeNodeSelected(event);
			}
		});
	}

	private void onTreeNodeSelected(TreeSelectionEvent event) {
		TreeNode node = (TreeNode) getTree().getLastSelectedPathComponent();
		VerseReference verseRef;

		if (node == null) {
			// nothing selected
			return;
		}

		if (node.isLeaf()) {
			verseRef = (VerseReference) node;
			System.out.print("Selected: ");
			verseRef.printContents();

			this.selectedVerseRef = verseRef;
		} else {
			this.selectedVerseRef = null;
		}

		// Enable OK button if we have a verse selected
		getOkButton().setEnabled(selectedVerseRef != null);
	}

	protected void onCancelClick() {
		setVisible(false);
	}

	protected void onOkClick() {
		getTree().getSelectionPath();
		verseSelectionListener.onVerseSelected(linguistText, selectedVerseRef);
		setVisible(false);
	}

	public JTree getTree() {
		return tree;
	}

	public void setLinguistText(LinguistText linguistText) {
		this.linguistText = linguistText;

		LinguistTextTreeModel model = new LinguistTextTreeModel(linguistText);
		getTree().setModel(model);
	}

	public LinguistText getLinguistText() {
		return this.linguistText;
	}

	public JButton getOkButton() {
		return okButton;
	}
}
