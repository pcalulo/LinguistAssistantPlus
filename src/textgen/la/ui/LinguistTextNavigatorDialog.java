package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTree;

import textgen.la.models.directory.LinguistText;
import textgen.la.ui.displaymodels.LinguistTextTreeModel;

import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;

public class LinguistTextNavigatorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LinguistTextNavigatorDialog dialog = new LinguistTextNavigatorDialog(
					null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LinguistTextNavigatorDialog(Frame parent) {
		super(parent);
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
				JButton okButton = new JButton("OK");
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
	}

	public JTree getTree() {
		return tree;
	}

	public void setLinguistText(LinguistText linguistText) {
		LinguistTextTreeModel model = new LinguistTextTreeModel(linguistText);
		getTree().setModel(model);
	}
}
