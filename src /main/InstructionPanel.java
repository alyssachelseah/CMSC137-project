package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import java.io.IOException;
import java.io.File;

public class InstructionPanel extends JPanel {
	private JLabel header;
	private JButton backButton;
	private JPanel topPanel;
	private JPanel centerPanel;

	private Font font;
	private JLabel objective;

	public InstructionPanel() {
		super(new BorderLayout());

		try {
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/FjallaOne-Regular.otf"));
		  
		  ge.registerFont(font);
		} catch (IOException|FontFormatException e) {
		  e.printStackTrace();
		}

		this.add(createTopPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
	}

	public JPanel createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.BLACK);

		font = font.deriveFont(Font.PLAIN, 18);
		objective = new JLabel("<html><div style=\"text-align: center;\"><br><br><h2 style=\"font-size: 20px;\">GOAL</h2>Earn more points by killing alien cows and defeat the final boss while maintaining stable health points. <br><br><br><h2 style=\"font-size: 20px;\">POINT SYSTEM</h2>Here are the corresponding points for each cow in the game: <br><br> 100 points - blue cow<br>200 points - orange<br>500 points - red cow<br><br><br><h2 style=\"font-size: 20px;\">ADDITIONAL REMINDERS</h2> Getting loots from defeated cows means extra health points for the player <br> Once the three lives are consumed, the player loses the game. On the other hand, if the player defeats the final boss, the player wins.<br><br> <h2 style=\"font-size: 20px;\">PLAYER MOVEMENT<h2><br>The player can use arroy kews for the player movement namely LEFT, RIGHT, UP and DOWN arrow keys. <br>SPACEBAR is used for shooting alien cows. While E for continuous shooting. <br><br> </div></html>"); 
		objective.setFont(font);
		objective.setForeground(Color.WHITE);

		centerPanel.add(objective);

		return centerPanel;
	}

	public JPanel createTopPanel() {
		topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.BLACK);

		font = font.deriveFont(Font.PLAIN, 50);
		header = new JLabel("Instruction");
		header.setFont(font);
		header.setForeground(Color.WHITE);

		backButton = new JButton(" < ");
		backButton.setFont(font);
		backButton.setBorder(null);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);

		topPanel.add(backButton);
		topPanel.add(header);

		return topPanel;
	}
	

	public JButton getBackButton() {
		return this.backButton;
	}
}
