package textgen.la.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Box extends JPanel {
	JLabel label, concept;

	public Box() {
		label = new JLabel();
		concept = new JLabel();
		add(label);
		add(concept);
		
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
				System.out.println("Mouse exited " + label.getText());
				Box.this.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("Mouse entered " + label.getText());
				Box.this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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

	public void setValues(String l, String c) {
		if (c != null)
			label.setText(l + ": ");
		else
			label.setText(l);
		concept.setText(c);

		concept.setFont(new Font("Arial", Font.ITALIC, 12));

	}

}
