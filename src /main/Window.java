package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Window extends Canvas{
	public CardLayout cardLayout;
	public Game gamePanel;
	public MenuPanel menuPanel;
	public JPanel mainPanel;

	public static final String GAME = "GAME";
	public static final String INSTRUCTION = "INSTRUCTION";
	public static final String MENU = "MENU";
	
	
		public Window(int width, int height, String title, Game game) {
			JFrame frame = new JFrame(title);
			
			frame.setPreferredSize(new Dimension(width, height));
			frame.setMaximumSize(new Dimension(width, height));
			frame.setMinimumSize(new Dimension(width, height));
		
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.add(game);
			frame.setVisible(true);
			//game.start();
			frame.setContentPane(createMainPanel(game));
			frame.pack();
		}
		
		private JPanel createMainPanel(Game game) {
			cardLayout = new CardLayout();
			mainPanel = new JPanel(cardLayout);

			menuPanel = new MenuPanel();
			mainPanel.add(menuPanel, MENU);
		

			menuPanel.getStartButton().addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent ae) { 
					game.start();
				}
			});
			
			menuPanel.getField().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					game.start();
				}
			});
			
			menuPanel.getExitButton().addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent ae) {
					System.exit(0);
				}
			});
			
			return menuPanel;
		}

}
