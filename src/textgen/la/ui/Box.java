package textgen.la.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import textgen.la.models.Constituent;

public class Box extends JPanel {
	JLabel labelText, conceptText;
	private Constituent constituent;

	public Box() {
		labelText = new JLabel();
		conceptText = new JLabel();
		add(labelText);
		add(conceptText);
		
		conceptText.setFont(new Font("Arial", Font.ITALIC, 12));
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				System.out.println("Mouse exited " + labelText.getText());
				Box.this.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("Mouse entered " + labelText.getText());
				Box.this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainWindow wnd = MainWindow.getInstance();
				wnd.setActiveConstituent(Box.this.getConstituent());
			}
		});
	}

	public void setBackgroundColor(int c) {
		switch (c) {
		case 0:
			this.setBackground(new Color(255, 180, 180));
			break;
		case 1:
			this.setBackground(new Color(180, 255, 180));
			break;
		case 2:
			this.setBackground(new Color(180, 180, 255));
			break;
		case 3:
			this.setBackground(new Color(255, 255, 180));
			break;
		case 4:
			this.setBackground(new Color(255, 180, 255));
			break;
		}
	}

	@Deprecated
	public void setValues(String l, String c) {
		if (c != null)
			labelText.setText(l + ": ");
		else
			labelText.setText(l);
		conceptText.setText(c);
	}

	public Constituent getConstituent() {
		return constituent;
	}

	public void setConstituent(Constituent constituent) {
		this.constituent = constituent;
		
		this.labelText.setText(constituent.getLabel());
		this.conceptText.setText(constituent.getConcept());
		
		// TODO: Remove this when the "CL constituents have null labels" issue is resolved
		if (constituent.getLabel() == null) {
			this.labelText.setText("CL");
		}
	}

}
