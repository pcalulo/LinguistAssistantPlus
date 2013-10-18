package textgen.la.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import textgen.la.models.Constituent;
import textgen.la.models.Parser;
import textgen.la.models.Sentence;

/**
 * This class extends BaseMainWindow, and provides additional business logic
 * relevant to the functionality of Linguist Assistant+ itself.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class LAMainWindow extends BaseMainWindow {

	public static LAMainWindow instance;
	
	private Sentence sentence;
	private Constituent selectedConstituent;

	public static LAMainWindow getInstance() {
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LAMainWindow window = new LAMainWindow();
					window.getWindowForm().setVisible(true);
					LAMainWindow.instance = window;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LAMainWindow() {
		super();
		initializeBoxInterface();
		initUiComponents();
	}

	private void initializeBoxInterface() {
		JPanel panel;

		panel = new JPanel();
		panel.setBackground(Color.white);
		Parser parser = new Parser();
		this.sentence = parser.getSentence();
		
		BoxCreator bc = new BoxCreator(this.sentence);
		bc.displayBoxes();
		panel.add(bc.getPanel());
		
		getScrollPane().setViewportView(panel);
	}
	
	private void initUiComponents() {
		JButton okButton = getOkButton();
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LAMainWindow.this.onModifyConstituent();
			}
		});
	}
	
	private void onModifyConstituent() {
		if (selectedConstituent != null) {
			selectedConstituent.setLabel(getLabelField().getText());
			selectedConstituent.setConcept(getConceptField().getText());
		}
		
//		JPanel boxes = sentence.toBoxes();
//		getScrollPane().setViewportView(boxes);
	}

	public void setActiveConstituent(Constituent constituent) {
		getLabelField().setText(constituent.getLabel());
		getConceptField().setText(constituent.getConcept());
	}
}
