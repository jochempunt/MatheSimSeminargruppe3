package app;


import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.ApplicationTime;
import utils.FrameUpdate;

import java.util.Timer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class _6_YourOwnAnimation {
	
	private static JFrame frame;

	public static void main(String[] args) {
		
		//open new thread for time measurement
		ApplicationTime animThread = new ApplicationTime();
		animThread.start();

		createFrame(animThread);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new FrameUpdate(frame), 100, _0_Constants.TPF);
	}
	
	//create a JFrame as my container for the simulation content
	private static void createFrame(ApplicationTime thread) {
		
		//Create a new frame
		frame = new JFrame("Mathematik und Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add a JPanel as the new drawing surface
		JPanel panel = new YourGraphicsContent(thread);
		frame.add(panel);
		frame.pack(); //adjusts size of the JFrame to fit the size of it's components
		frame.setVisible(true);
	}
}


@SuppressWarnings("serial") 
class YourGraphicsContent extends JPanel {
	
	//panel has a single time tracking thread associated with it
	private ApplicationTime t;
	private double time;
	
	public YourGraphicsContent(ApplicationTime thread) {
		this.t = thread;
	}
	
	//set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
	}
	
	int startX = 20;
	int startY = 20;
	int vX = 160;
	int vY = 20;
	int diameter = 50;
	
	
	//drawing operations should be done in this method
	@Override protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		time = t.getTimeInSeconds(); 
		 
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, _0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
		
		g.setColor(Color.RED);		
		g.fillOval(startX + (int)( time * vX), startY + (int)(time * vY), diameter , diameter);
		
		/**
		 * Exercises: 
		 * 
		 * 1) Use the same initial conditions ( startX, startY, vX, vY ) as above. 
		 *    Let the circle/ball "bounce off the vertical walls", i.e. provide for correct changes of velocity 
		 *    whenever the circle/ball "collides elastically with" the right-hand or the left-hand frame borders. 
		 * 
		 * 2) Choose any initial conditions (startX, startY, vX, vY ). 
		 *    Let the circle/ball "bounce off the walls", i.e. provide for correct changes of velocity 
		 *    whenever the circle/ball "collides elastically with" any of the four frame borders.
		 *    
		 * 3) Simulate the motion of the ball/circle under the influence of gravity
		 *    Place the circle a some height h above the floor (bottom frame border) with initial velocity vY = 0.
		 *    Let the circle/ball undergo accelerated motion toward the bottom. 
		 *    Once the ball hits the floor, its velocity is reversed (fully elastic collision), 
		 *    the ensuing upward motion is decelerated until the circle/ball comes to rest a height h, etc.
		 *    		 *        
		 * 4) As under 3) except that there is kinetic energy loss at the collision       
		 * 
		 * 
		 */
		
		
		
	}
}
