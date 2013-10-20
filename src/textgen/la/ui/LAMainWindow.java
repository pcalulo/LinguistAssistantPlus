package textgen.la.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import textgen.la.models.Constituent;
import textgen.la.models.Parser;
import textgen.la.models.Sentence;
import textgen.la.models.directory.LinguistText;
import textgen.la.models.directory.ManifestFileFilter;
import textgen.la.models.directory.ManifestReader;
import textgen.la.models.directory.VerseReference;
import textgen.la.ui.LinguistTextNavigatorDialog.VerseSelectionListener;
import textgen.la.ui.displaymodels.LinguistTextTreeModel;

/**
 * This class extends BaseMainWindow, and provides additional business logic
 * relevant to the functionality of Linguist Assistant+ itself.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class LAMainWindow extends BaseMainWindow implements VerseSelectionListener {

	public static LAMainWindow instance;

	private Sentence sentence;
	private Constituent selectedConstituent;

	private LinguistText linguistText;

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

	public LinguistText getLinguistText() {
		return linguistText;
	}

	public void setLinguistText(LinguistText linguistText) {
		this.linguistText = linguistText;
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
		int result;
		File selectedFile;
		LinguistText text = null;

		logger.info("onOpenTextClick!");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ManifestFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		result = fileChooser.showOpenDialog(this.getWindowForm());

		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			try {
				text = ManifestReader.read(selectedFile);
				text.printContents();
				setLinguistText(text);

				onSelectVerseClick();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	protected void onAboutClick() {
		logger.info("onAboutClick!");
	}

	@Override
	protected void onSelectVerseClick() {
		LinguistTextNavigatorDialog dialog = new LinguistTextNavigatorDialog(this.getWindowForm(), this);
		dialog.setLinguistText(getLinguistText());
		dialog.setVisible(true);
	}

	@Override
	public void onVerseSelected(LinguistText linguistText,
			VerseReference verseRef) {
		verseRef.getVerse();
	}
}
