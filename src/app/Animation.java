package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import calc.Matrix_VektorRechner;
import calc.Projektion;
import utils.ApplicationTime;
import utils.FrameUpdate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

public class Animation {

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
		JPanel panel = new GraphicsContent(thread);
		frame.add(panel);
		frame.pack(); // adjusts size of the JFrame to fit the size of it's components
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class GraphicsContent extends JPanel {

	// panel has a single time tracking thread associated with it
	private ApplicationTime t;
	private double time;

	public GraphicsContent(ApplicationTime thread) {
		this.t = thread;
	}

	// set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
	}

	int mittelpunktXd = _0_Constants.WINDOW_WIDTH / 2;
	int mittelpunktYd = _0_Constants.WINDOW_HEIGHT / 2;
	int it = 0;
	int diameter = 5;

	int vergrößerung = 125;
	
	
	ArrayList<Punkt> punktListeVorne  = new ArrayList<Punkt>();
	
	
	
	public void paintPunktObjekt(Punkt pPunkt, Graphics g) {
		int[] pos = pPunkt.position;
		g.setColor(pPunkt.color);
		g.fillOval(pos[0] - pPunkt.Dicke/2, pos[1] - pPunkt.Dicke/2,pPunkt.Dicke,pPunkt.Dicke);
	}
	
	
	public void paintVorne(Graphics g) {
		for(Punkt p: punktListeVorne) {
			paintPunktObjekt(p, g);
		}
	}
	
	

	public void paintVektor(double[][] startpoint, double[][] endpoint) {

	}

	public void paintGeodaetische(double bg1, double lg1, double bg2, double lg2, int r, Graphics g, Color c,
			int dicke) {
		double[][] p = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg1, lg1, r), 2);
		double[][] q = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg2, lg2, r), 2);

		for (double t = 0; t < Math.PI * 2; t = t + 0.006) {
			double[][] ergebnissPunkt = Matrix_VektorRechner.funktion(p, q, r, t);
			//Matrix_VektorRechner.matrixAusgabeD(ergebnissPunkt);
			boolean vorn = Matrix_VektorRechner.vorne(ergebnissPunkt);
			Color punktColor = null;
			if (t < Matrix_VektorRechner.winkel(p, q)) {
				
				punktColor = Matrix_VektorRechner.vornHintenColor(ergebnissPunkt, c);
				g.setColor(punktColor);
			} else {
				punktColor = Matrix_VektorRechner.vornHintenColor(ergebnissPunkt, Color.black);
				
				g.setColor(punktColor);
				
			}
			
			
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

			
			// g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2,
			// dicke, dicke);
			
			if(!vorn) {
				g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
			}else {
			
				punktListeVorne.add(new Punkt(verschobevektor,punktColor,dicke));
			}
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

	public void paintUmrissellipse(Graphics g, Color c, int dicke) {

		for (double i = 0; i < 2 * Math.PI; i = i + 0.006) {

			try {
				double point[][] = Matrix_VektorRechner.roundDoubleVektor(Projektion.umrissellipse(1, i), 2);

				point = Projektion.projektiere(point);
				int[] verschobevektor = new int[2];

				verschobevektor[0] = (int) (point[0][0] * vergrößerung);

				verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

				verschobevektor[1] = (int) (point[1][0] * (vergrößerung));

				verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

				g.setColor(c);
				g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void paintVektorProjekted(double[][] vektor, Graphics g, Color c, int dicke) {
		double projeVektor[][] = null;
		try {
			projeVektor = Projektion.projektiere(vektor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[] verschobevektor = new int[2];

		verschobevektor[0] = (int) (projeVektor[0][0] * vergrößerung);

		verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

		verschobevektor[1] = (int) (projeVektor[1][0] * vergrößerung);

		verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;
		g.setColor(c);
		
		g.drawLine(_0_Constants.WINDOW_WIDTH / 2, _0_Constants.WINDOW_HEIGHT / 2, verschobevektor[0],
				verschobevektor[1]);

	}

	// drawing operations should be done in this method
	@Override
	protected void paintComponent(Graphics g) {

		
		
		
		Graphics2D g2d;
		g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		

		double[][] x1 = { { 1 }, { 0 }, { 0 } };
		double[][] x2 = { { 0 }, { 1 }, { 0 } };
		double[][] x3 = { { 0 }, { 0 }, { 1 } };

		paintVektorProjekted(x1, g, Color.red, diameter);
		paintVektorProjekted(x2, g, Color.green, diameter);
		paintVektorProjekted(x3, g, Color.blue, diameter);

		int r = 1;

		// paintGeodaetische(-33.928992, 18.417396, -33.8548157,151.2164539, r, g,
		// Color.yellow,5); // kapstadt -- sidney

		paintGeodaetische(0, 0, 90, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 20, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 40, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 60, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 80, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 100, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 120, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 140, -89, 0, r, g, Color.blue, 3);
		paintGeodaetische(0, 160, -89, 0, r, g, Color.blue, 3);

		// paintPunktGrad(it, 0, 1, g, Color.red, 10);

		paintGeodaetische(0, 0, 0, 179, r, g, Color.blue, 3);

		// paintPunktGrad(0, it, 1, g, Color.red, 10);
		// paintGeodaetische(-33.928992, 30, 0,0, r, g, Color.red,4 );
		it++;
		// paintGeodaetische(48.0529805,8.2163467, -33.928992, 18.417396,r, g,
		// Color.orange,4); // furtwangen - Kapstadt
		 
		
		paintVorne(g);
		
		
		paintUmrissellipse(g,Color.blue,5);
		
		
		/**
		 * To do: Drahtgittermodell + Umrisselipse ; Vorne + Hinten unterscheiden
		 * Ortsauswahl
		 * 
		 */

	}
}
