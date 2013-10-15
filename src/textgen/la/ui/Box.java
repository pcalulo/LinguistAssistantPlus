package textgen.la.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Box extends JPanel{
	JLabel label, concept;
	
	public Box()
	{
		label = new JLabel();
		concept = new JLabel();
		add(label);
		add(concept);
	}
	
	public void setBackgroundColor(int c)
	{
		switch (c)
		{
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
	
	public void setValues (String l, String c)
	{
		if (c != null)
			label.setText(l+": ");
		else
			label.setText(l);
		concept.setText(c);
		
		concept.setFont(new Font ("Arial", Font.ITALIC, 12));
		
	}
	
}
