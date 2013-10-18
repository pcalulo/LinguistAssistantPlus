package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.AbstractListModel;

import textgen.la.models.Constituent;
import textgen.la.models.Feature;

public class FeaturesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList list;

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
		setTitle("Features");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Hello", "world", "the", "quick",
					"brown", "fox" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(list, GroupLayout.DEFAULT_SIZE,
				438, Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(list, GroupLayout.DEFAULT_SIZE,
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

	public void setConstituent(Constituent constituent) {
		List<Feature> features = constituent.getFeatureList().getFeatures();
		final String[] featureArray = new String[features.size()];

		for (int i = 0; i < features.size(); i++) {
			Feature feature = features.get(i);
			featureArray[i] = feature.getName() + " : " + feature.getValue();
		}

		getList().setModel(new AbstractListModel<String>() {
			@Override
			public String getElementAt(int index) {
				// TODO Auto-generated method stub
				return featureArray[index];
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return featureArray.length;
			}

		});
	}

	public JList getList() {
		return list;
	}
}
