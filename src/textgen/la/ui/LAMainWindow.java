package textgen.la.ui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import textgen.la.models.Constituent;

/**
 * This class extends BaseMainWindow, and provides additional business logic
 * relevant to the functionality of Linguist Assistant+ itself.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class LAMainWindow extends BaseMainWindow {

	public static LAMainWindow instance;

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

	public void setActiveConstituent(Constituent constituent) {
		getLabelField().setText(constituent.getLabel());
		getConceptField().setText(constituent.getConcept());
	}
}
