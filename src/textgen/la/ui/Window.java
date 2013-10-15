package textgen.la.ui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import textgen.la.models.ConstitList;
import textgen.la.models.Constituent;
import textgen.la.models.Parser;
import textgen.la.models.Sentence;

public class Window extends JFrame{
	Parser parser;
	JPanel panel;
	public Window() 
	{
		panel = new JPanel();
		panel.setBackground(Color.white);
		parser = new Parser(panel);
		
		setTitle("TEXTGEN LA+");
	    setSize( 150, 100 );
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    setVisible(true);
	    
	    add(panel);
	    
	    //setLayout( new FlowLayout() );       // set the layout manager
	}
	
	public static void main(String args[])
	{
		Window window = new Window();
	}
}
