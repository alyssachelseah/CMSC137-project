package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame {
	private CardLayout cardLayout;
	private Game game;
	private InstructionPanel instructionPanel;
	private MenuPanel menuPanel;
	private JPanel mainPanel;

	private static final String GAME = "GAME";
	private static final String INSTRUCTION = "INSTRUCTION";
	private static final String MENU = "MENU";


	public Window() {
		super("Alien Cows");
		this.setPreferredSize(new Dimension(1280, 720));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(createMainPanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JPanel createMainPanel() {
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		menuPanel = new MenuPanel();
		instructionPanel = new InstructionPanel();

		mainPanel.add(menuPanel, MENU);
		mainPanel.add(instructionPanel, INSTRUCTION);

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

		menuPanel.getInstructionButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cardLayout.show(mainPanel, INSTRUCTION);
			}
		});

		menuPanel.getExitButton().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});	

		instructionPanel.getBackButton().addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cardLayout.show(mainPanel, MENU);
			}
		});

		return mainPanel;
	}
}
