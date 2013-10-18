package textgen.la.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import textgen.la.models.Parser;

public class Window extends JFrame {
	Parser parser;
	JPanel panel;

	public Window() {
		panel = new JPanel();
		panel.setBackground(Color.white);
		parser = new Parser();

		setTitle("TEXTGEN LA+");
		setMinimumSize(new Dimension(640, 480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		initUIComponents();
	}
	
	protected void initUIComponents() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.weightx = 1;
		layoutConstraints.weighty = 1;
		this.add(new JScrollPane(panel), layoutConstraints);
		
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 1;
		layoutConstraints.weighty = 0;
		
		JPanel infoPanel = new JPanel();
		infoPanel.add(new JButton("Hello, world!"));
		this.add(infoPanel, layoutConstraints);
	}

	public static void main(String args[]) {
		Window window = new Window();
	}
}
