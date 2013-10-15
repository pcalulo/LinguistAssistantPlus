package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import textgen.la.models.Constituent;
import textgen.la.models.Feature;
import textgen.la.models.FeatureList;

import java.awt.Point;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;

public class FeatureWindow extends JFrame {

	private JPanel contentPane;
	JLabel headerLabel;
	JTextPane featuresPane;

	public FeatureWindow(Point location) {
		setResizable(false);
		setBackground(SystemColor.control);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setTitle("Features");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(location.x - 290, location.y, 292, 178);

		setVisible(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		headerLabel = new JLabel("Features of <blah>: <blah>");
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(headerLabel, BorderLayout.NORTH);

		featuresPane = new JTextPane();
		featuresPane.setEditable(false);
		featuresPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(featuresPane, BorderLayout.CENTER);
	}

	public void setFeatureWindowContent(Constituent c) {
		setVisible(true);
		String header = "Features of " + c.getLabel();
		if (c.getConcept() != null)
			header += ": " + c.getConcept();
		headerLabel.setText(header);

		Document doc = featuresPane.getDocument();

		StyleContext sc = new StyleContext();
		Style nameStyle = sc.addStyle("name", null);
		Style valueStyle = sc.addStyle("value", null);

		Font nameFont = new Font("Arial", Font.BOLD, 14);
		StyleConstants.setFontFamily(nameStyle, nameFont.getFamily());
		StyleConstants.setBold(nameStyle, true);

		Font valueFont = new Font("Arial", Font.PLAIN, 14);
		StyleConstants.setFontFamily(valueStyle, valueFont.getFamily());
		StyleConstants.setBold(valueStyle, false);

		MutableAttributeSet lineSpaceSet = new SimpleAttributeSet();
		StyleConstants.setLineSpacing(lineSpaceSet, (float) 0.3);

		featuresPane.setParagraphAttributes(lineSpaceSet, true);

		ArrayList<Feature> features = c.getFeatureList().getFeatures();
		if (features.size() == 0) {
			try {
				doc.insertString(doc.getLength(), "none", valueStyle);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < features.size(); i++) {
				Feature f = features.get(i);
				try {
					doc.insertString(doc.getLength(), "  • " + f.getName(),
							nameStyle);
					doc.insertString(doc.getLength(), ": " + f.getValue()
							+ "\n", valueStyle);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}