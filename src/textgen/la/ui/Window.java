package textgen.la.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import textgen.la.models.Parser;

public class Window extends JFrame {
	Parser parser;
	JPanel panel;

	public Window() {
		panel = new JPanel();
		panel.setBackground(Color.white);
		parser = new Parser(panel);

		setTitle("TEXTGEN LA+");
		setMinimumSize(new Dimension(640, 480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		add(panel);

		// setLayout( new FlowLayout() ); // set the layout manager
	}

	public static void main(String args[]) {
		Window window = new Window();
	}
}
