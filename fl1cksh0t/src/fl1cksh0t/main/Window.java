package fl1cksh0t.main;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -6572041330096911686L;
	
	public Window(int width, int height, String title, Display game) {

		//removes cursor from screen
		BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank");

		//creating new JFrame to game vision
		JFrame frame = new JFrame(title);

		//setting dimensions and window properties
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//enables windows X button
		frame.setResizable(false);								//uses default size only - may rework later for lower end machines
		frame.setLocationRelativeTo(null);						//Window spawns in middle of screen as opposed to top left
		frame.add(game);
		frame.setVisible(true);
		frame.pack();
		frame.getContentPane().setCursor(blank);

		//Checker for loading window correctly
		System.out.println("Game window created");

		//Starts the game
		game.start();
	}

}
