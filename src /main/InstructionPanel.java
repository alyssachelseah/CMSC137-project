package main;
import javax.swing.*;
import java.awt.*;

import java.io.*;

public class InstructionPanel extends JPanel{
	private JLabel header;
	private JButton backButton;
	private JPanel topPanel;
	private JPanel centerPanel;

	private Font font;
	private JLabel objective;

	public InstructionPanel() {
	

		try {
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/FjallaOne-Regular.otf"));
		  
		  ge.registerFont(font);
		} catch (IOException|FontFormatException e) {
		  e.printStackTrace();
		}

		//this.add(createTopPanel(), BorderLayout.NORTH);
		//this.add(createCenterPanel(), BorderLayout.CENTER);
	}

	public JPanel createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.BLACK);

		font = font.deriveFont(Font.PLAIN, 18);
		objective = new JLabel("<html><div style=\"text-align: center;\"><br><br><h2 style=\"font-size: 20px;\">GOAL</h2> Be able to kill the boss while maintaining a considerable amount of health points. You should be able to overcome all three levels while earning loots from the defeated cows. <br><br><br><h2 style=\"font-size: 20px;\">POINT SYSTEM</h2>Here are the corresponding points for different opponent cows: <br><br> 100 points - BLUE COWS <br>300 points - ORANGE COWS <br>500 points - RED COWS<br><br><br><h2 style=\"font-size: 20px;\">ADDITIONAL RULES </h2>You will encounter different cows depending on what level you are currently playing. Defeated cows will produce loots that you can collect to earn more health points.<br><h2 style=\"font-size: 20px;\">PLAYER MOVEMENT</h2>The player may opt to use arrow keys for the movement. UP, DOWN, LEFT, RIGHT keys respectively.<br>Use the SPACEBAR to shoot cows and E for continuous shooting.</div></html>"); 
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
