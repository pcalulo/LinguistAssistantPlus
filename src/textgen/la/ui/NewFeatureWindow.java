package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import textgen.la.models.Feature;
import textgen.la.models.FeatureList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class NewFeatureWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private FeatureList features;
	ArrayList<Feature> feature = new ArrayList<Feature>();
	private JTextField txta;
	private JTextField txtb;
	private JTextField txtc;
	public boolean checker = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public NewFeatureWindow() {
		getContentPane().setLayout(null);

		txt1 = new JTextField();
		txt1.setColumns(10);
		txt1.setBounds(104, 11, 320, 20);
		getContentPane().add(txt1);

		JLabel label = new JLabel("Feature");
		label.setBounds(22, 14, 72, 14);
		getContentPane().add(label);

		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(104, 73, 320, 20);
		getContentPane().add(txt2);

		JLabel label_1 = new JLabel("Feature");
		label_1.setBounds(22, 76, 72, 14);
		getContentPane().add(label_1);

		txt3 = new JTextField();
		txt3.setColumns(10);
		txt3.setBounds(104, 135, 320, 20);
		getContentPane().add(txt3);

		JLabel label_2 = new JLabel("Feature");
		label_2.setBounds(22, 138, 72, 14);
		getContentPane().add(label_2);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txt1.getText().equals(null)) {
					feature.add(new Feature(txt1.getText(), txta.getText()));
				}
				if (!txt1.getText().equals(null)) {
					feature.add(new Feature(txt2.getText(), txtb.getText()));
				}
				if (!txt1.getText().equals(null)) {
					feature.add(new Feature(txt3.getText(), txtc.getText()));
				}
				checker = true;
			}
		});
		features = new FeatureList(feature);
		btnOk.setBounds(335, 227, 89, 23);
		getContentPane().add(btnOk);

		txta = new JTextField();
		txta.setColumns(10);
		txta.setBounds(104, 42, 320, 20);
		getContentPane().add(txta);

		JLabel lblValue = new JLabel("value");
		lblValue.setBounds(22, 45, 72, 14);
		getContentPane().add(lblValue);

		txtb = new JTextField();
		txtb.setColumns(10);
		txtb.setBounds(104, 104, 320, 20);
		getContentPane().add(txtb);

		JLabel label_3 = new JLabel("value");
		label_3.setBounds(22, 107, 72, 14);
		getContentPane().add(label_3);

		txtc = new JTextField();
		txtc.setColumns(10);
		txtc.setBounds(104, 166, 320, 20);
		getContentPane().add(txtc);

		JLabel label_4 = new JLabel("value");
		label_4.setBounds(22, 169, 72, 14);
		getContentPane().add(label_4);

	}

	public FeatureList returnFeatures() {
		return features;
	}
}
