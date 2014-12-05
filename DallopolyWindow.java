import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/*
 * This class will handle the GUI portion of the Dallopoly game application
 */
public class DallopolyWindow extends JFrame {
	/**
	 * Version number for application. Default generated version number used. (1L)
	 */
	private static final long serialVersionUID = 1L;
	private int numPlayers = 0;
	private Dallopoly game;
	private JButton newGame;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton addPlayer;
	private JButton playGame;
	private JScrollPane gameTextPane;
	private JTextArea gameText;
	
	public DallopolyWindow() {
		super("Dallopoly");
		
		//Look and feel settings for the frame
		setSize(600, 400);
		setLocationRelativeTo(null); //Center on startup screen
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		setDefaultEnabled();
		setDefaultDisabled();
		setTextArea();
		addComponents();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit when "x" is clicked
	}
	
	//Default enabled buttons and fields
	private void setDefaultEnabled() {
		newGame = new JButton("New Game");
		newGame.addActionListener(new newGameListener());
		newGame.setEnabled(true);
		nameLabel = new JLabel("Player Name: ");
	}
	
	//Default disabled buttons and fields
	private void setDefaultDisabled() {
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(290,25)); //Size setting for a 600x400 JFrame
		nameField.setEnabled(false);
		addPlayer = new JButton("Add Player");
		addPlayer.addActionListener(new addPlayerListener());
		addPlayer.setEnabled(false);
		playGame = new JButton("Play!");
		playGame.addActionListener(new playGameListener());
		playGame.setEnabled(false);
	}
	
	//Game Text Content
	private void setTextArea() {
		gameText = new JTextArea();
		//Lines will wrap and will wrap only at white spaces for readability
		gameText.setWrapStyleWord(true);
		gameText.setLineWrap(true);
		//------------------------------------------------------------------
		gameTextPane = new JScrollPane(gameText);
		gameTextPane.setPreferredSize(new Dimension(500,300)); //Size setting for a 600x400 JFrame
		gameTextPane.setWheelScrollingEnabled(true);
		gameTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gameTextPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	//Add components to frame in left-right top-bottom order
	private void addComponents() {
		add(newGame);
		add(nameLabel);
		add(nameField);
		add(addPlayer);
		add(gameTextPane);
		add(playGame);		
	}
	
	public void increasePlayerCount() {
		numPlayers++;
	}
	
	public void resetPlayerCount() {
		numPlayers = 0;
	}
	
	public class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			newGame.setEnabled(false);
			gameText.setText("");
			
			//Attempt to create a Dallopoly Game Print in gameText JTextArea if it fails
			try {
				game = new Dallopoly();
			} catch (FileNotFoundException e) {
				gameText.append("squareList.txt FILE NOT FOUND, no board could be created!\nMake sure that the file is located directly in the \\src\\ folder.");
				e.printStackTrace();
			}
			
			gameText.append("You have started a new game of Dallopoly!\n"
					+ "Please add at least two players to begin.\n");
			
			nameField.setEnabled(true);
			addPlayer.setEnabled(true);
		}
	}
	
	public class addPlayerListener implements ActionListener {
		
		public void actionPerformed(ActionEvent actionEvent) {
			if(nameField.getText().length() != 0) {
				//Add player
				game.addPlayer(nameField.getText());
				gameText.append("\nAdded player: " + nameField.getText());
				
				//Clear Field
				nameField.setText("");
				
				//Increase player count for enabling playGame JButton
				increasePlayerCount();
			}
			else {
				gameText.append("\nPlease enter a non-empty name.");
			}
			
			if(numPlayers >= 2)
				playGame.setEnabled(true);
		}
	}
	
	public class playGameListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//Disable buttons for adding and playing
			//Enable newGame button so that a new game can be started
			addPlayer.setEnabled(false);
			nameField.setEnabled(false);
			playGame.setEnabled(false);
			newGame.setEnabled(true);
			
			//------------------------------------------
			//field must be reset or game can be played
			//with only one player
			resetPlayerCount();
			//------------------------------------------
			
			gameText.append("\n\n" + game.playGame());
		}
	}
}
