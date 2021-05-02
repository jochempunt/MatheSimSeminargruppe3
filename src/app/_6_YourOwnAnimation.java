package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.ApplicationTime;
import utils.FrameUpdate;
import utils.Matrix_VektorRechner;
import utils.Projektion;

import java.util.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

public class _6_YourOwnAnimation {

	private static JFrame frame;

	public static void main(String[] args) {

		// open new thread for time measurement
		ApplicationTime animThread = new ApplicationTime();
		animThread.start();

		createFrame(animThread);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new FrameUpdate(frame), 100, _0_Constants.TPF);
	}

	// create a JFrame as my container for the simulation content
	private static void createFrame(ApplicationTime thread) {

		// Create a new frame
		frame = new JFrame("Mathematik und Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a JPanel as the new drawing surface
		JPanel panel = new YourGraphicsContent(thread);
		frame.add(panel);
		frame.pack(); // adjusts size of the JFrame to fit the size of it's components
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class YourGraphicsContent extends JPanel {

	// panel has a single time tracking thread associated with it
	private ApplicationTime t;
	private double time;

	public YourGraphicsContent(ApplicationTime thread) {
		this.t = thread;
	}

	// set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
	}

	int startX = 20;
	int startY = 20;
	int vX = 160;
	int vY = 20;
	int diameter = 50;
	int mittelpunktXd = _0_Constants.WINDOW_WIDTH / 2;
	int mittelpunktYd= _0_Constants.WINDOW_HEIGHT / 2;
	int it = 0;
	
	
	int vergrößerung = 100;

	public void paintVektor(double[][] startpoint, double[][] endpoint) {

	}

	public void paintGeodaetische(double bg1, double lg1, double bg2, double lg2, int r, Graphics g, Color c,
			int dicke) {
		double[][] p = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg1, lg1, r), 2);
		double[][] q = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg2, lg2, r), 2);

		for (double t = 0; t < Math.PI * 2; t = t + 0.006) {
			double[][] ergebnissPunkt = Matrix_VektorRechner.funktion(p, q, r, t);
			try {
				ergebnissPunkt = Projektion.projektiere(ergebnissPunkt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int[] verschobevektor = new int[2];

			verschobevektor[0] = (int) (ergebnissPunkt[0][0] * vergrößerung);

			verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

			verschobevektor[1] = (int) (ergebnissPunkt[1][0] * vergrößerung);

			verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

			if (t < Matrix_VektorRechner.winkel(p, q)) {
				g.setColor(c);
			} else {
				g.setColor(Color.black);
			}
			//g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
			g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
		}

	}

	public void paintPunktGrad(double bg, double lg, int r, Graphics g, Color c, int dicke) {
		double vektor[][] = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg, lg, r), 2);
		double projeVektor[][] = null;
		try {
			projeVektor = Projektion.projektiere(vektor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] verschobevektor = new int[2];

		verschobevektor[0] = (int) (projeVektor[0][0] * vergrößerung);

		verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

		verschobevektor[1] = (int) (projeVektor[1][0] * vergrößerung);

		verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

		g.setColor(c);
		g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);

	}

	public void paintVektorProjekted(double[][] vektor, Graphics g, Color c, int dicke) {
		double projeVektor[][] = null;
		try {
			projeVektor = Projektion.projektiere(vektor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] verschobevektor = new int[2];

		verschobevektor[0] = (int) (projeVektor[0][0] * vergrößerung);

		verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

		verschobevektor[1] = (int) (projeVektor[1][0] *vergrößerung);

		verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;
		g.setColor(c);
		g.drawLine(_0_Constants.WINDOW_WIDTH/2,_0_Constants.WINDOW_HEIGHT/2, verschobevektor[0], verschobevektor[1]);
		
	}

	// drawing operations should be done in this method
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		double[][] x1 = {{1},{0},{0}};
		double[][] x2 = {{0},{1},{0}};
		double[][] x3 = {{0},{0},{1}};
		
		
		paintVektorProjekted(x1, g, Color.red, diameter);
		paintVektorProjekted(x2, g, Color.green, diameter);
		paintVektorProjekted(x3, g, Color.blue, diameter);

		int r = 1;

	    

		// paintGeodaetische(-33.928992, 18.417396, -33.8548157,151.2164539, r, g,
		// Color.yellow,5); // kapstadt -- sidney
		

		for (int i = 0; i < 360; i++) {
			paintPunktGrad(i, 0, 1, g, Color.black, 8);
		}
		paintPunktGrad(it, 0, 1, g, Color.red, 10);
		
		it++;
		paintGeodaetische(0, 0, 0, 179, r, g, Color.blue, 8);
		paintPunktGrad(0, it, 1, g, Color.red, 10);
		//paintGeodaetische(-33.928992, 30, 0,0, r, g, Color.red,4 );
		
		paintGeodaetische(48.0529805,8.2163467, -33.928992, 18.417396,r, g, Color.orange,3); // furtwangen - Kapstadt
		/**
		 * Exercises:
		 * 
		 * 1) Use the same initial conditions ( startX, startY, vX, vY ) as above. Let
		 * the circle/ball "bounce off the vertical walls", i.e. provide for correct
		 * changes of velocity whenever the circle/ball "collides elastically with" the
		 * right-hand or the left-hand frame borders.
		 * 
		 * 2) Choose any initial conditions (startX, startY, vX, vY ). Let the
		 * circle/ball "bounce off the walls", i.e. provide for correct changes of
		 * velocity whenever the circle/ball "collides elastically with" any of the four
		 * frame borders.
		 * 
		 * 3) Simulate the motion of the ball/circle under the influence of gravity
		 * Place the circle a some height h above the floor (bottom frame border) with
		 * initial velocity vY = 0. Let the circle/ball undergo accelerated motion
		 * toward the bottom. Once the ball hits the floor, its velocity is reversed
		 * (fully elastic collision), the ensuing upward motion is decelerated until the
		 * circle/ball comes to rest a height h, etc. * 4) As under 3) except that there
		 * is kinetic energy loss at the collision
		 * 
		 * 
		 */

	}
}
