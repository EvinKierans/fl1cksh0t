package fl1cksh0t.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas
{

	private static final long serialVersionUID = -6572041330096911686L;
	
	public Window(int width, int height, String title, Game game) 
	{
		JFrame frame = new JFrame(title);	//inbuilt lib for frame of a window (called "frame" in this case)
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//enables windows X button
		frame.setResizable(false);								//uses default size only - may rework later for lower end machines
		frame.setLocationRelativeTo(null);						//Window spawns in middle of screen as opposed to top left
		frame.add(game);	
		frame.setVisible(true);
		frame.pack();
		
		game.start();
	}

}
