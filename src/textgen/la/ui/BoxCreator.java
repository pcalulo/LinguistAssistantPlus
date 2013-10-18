package textgen.la.ui;

import java.util.ArrayList;

import javax.swing.JPanel;

import textgen.la.models.ConstitList;
import textgen.la.models.Sentence;
import textgen.la.models.Constituent;

public class BoxCreator {

	Sentence sentence;
	JPanel panel;
	
	public BoxCreator(Sentence s) 
	{
		sentence = s;
		
		panel = new JPanel();
	}
	
	public void displayBoxes() 
	{
		//PRINT VALUES
		ArrayList<Constituent> clauses = sentence.getClauses();
		for (int i = 0; i<clauses.size(); i++)
		{
			Box box = new Box();
			box.setConstituent(clauses.get(i));
			displayChildren(clauses.get(i), box);
			panel.add(box);
		}
	}
	
	public void displayChildren(Constituent parentConstit, Box parentBox)
	{
		if (parentConstit == null)
			return;
		
		ConstitList cList = parentConstit.getConstitList();
		for (int i = 0; i < cList.getLength(); i++)
		{
			Constituent c = cList.getConstit(i);
			Box box = new Box();
			box.setConstituent(c);
			parentBox.add(box);
			displayChildren(c, box);
		}
		
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
