package textgen.la.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

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
	
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

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

		/*
		 * This is a messy attempt at vertically centering the box UI. Using a
		 * GridBagLayout, two JPanels, one on top and one below the boxes,
		 * occupy all extra vertical space. We can use these panels later on to
		 * contain additional info about the constituents.
		 */
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		panel.add(new JPanel(), c);

		c.weighty = 0;
		c.gridy = 1;
		panel.add(bc.getPanel(), c);

		c.weighty = 1;
		c.gridy = 2;
		panel.add(new JPanel(), c);

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

		// JPanel boxes = sentence.toBoxes();
		// getScrollPane().setViewportView(boxes);
	}

	public void setActiveConstituent(Constituent constituent) {
		getLabelField().setText(constituent.getLabel());
		getConceptField().setText(constituent.getConcept());
	}

	@Override
	protected void onNewTextClick() {
		logger.info("onNewTextClick!");
	}

	@Override
	protected void onOpenTextClick() {
		logger.info("onOpenTextClick!");
	}

	@Override
	protected void onAboutClick() {
		logger.info("onAboutClick!");
	}
}
