package app;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import calc.Matrix_VektorRechner;
import calc.Projection;
import utils.ApplicationTime;
import utils.FrameUpdate;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Animation {

	private static JFrame frame;
	private static JPanel contentPane;
	public static boolean ausführen = false;
	public static JComboBox<Ort> ort1;
	public static JComboBox<Ort> ort2;
	private static JLabel lblNewLabel;
	
	public static JSlider slider;
	

	public static Ort[] orteListe = { new Ort(48.050144, 8.201419, "Furtwangen"),
			new Ort(-33.867487, 151.206990, "Sidney"), new Ort(40.712784, -74.005941, "New York"),
			new Ort(-33.924869, 18.424055, "Kapstadt"), new Ort(39.039219, 125.762524, "Pjöngjang"),
			new Ort(59.934280, 30.335099, "St.Petersburg") };
	public static boolean wobble = false;

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
		frame.setBounds(100, 100, 1000, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		// Add a JPanel as the new drawing surface

		JPanel panel = new GraphicsContent(thread);
		panel.setBounds(0, 0, 500, 500);
		frame.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(498, 0, 486, 493);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Best\u00E4tigen");

		btnNewButton.setBounds(46, 219, 116, 46);
		panel_1.add(btnNewButton);

		ort1 = new JComboBox<Ort>(orteListe);
		ort1.setBounds(192, 231, 88, 26);

		panel_1.add(ort1);

		ort2 = new JComboBox<Ort>(orteListe);
		ort2.setBounds(310, 231, 88, 26);
		panel_1.add(ort2);

	
		JButton btnNewButton_1 = new JButton("Wobble");// experimenteller Button
		btnNewButton_1.setBounds(396, 456, 80, 26);
		panel_1.add(btnNewButton_1);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wobble) {
					wobble = false;
				} else {
					wobble = true;
				}

			}
		});

		
		
		
		JSlider slider = new JSlider(1,20);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider.setBounds(46, 300, 200, 26);
		panel_1.add(slider);
	    
		
		lblNewLabel = new JLabel("Geschwindigkeit = " + slider.getValue());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(50, 315, 125, 31);
		panel_1.add(lblNewLabel);
		
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel.setText("Geschwindigkeit = " + slider.getValue());
				GraphicsContent.geschwindigkeit = slider.getValue();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ort1.setEnabled(false);
				ort2.setEnabled(false);
				ausführen = true;

			}
		});

		
		
		
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class GraphicsContent extends JPanel {

	// panel has a single time tracking thread associated with it
	private ApplicationTime t;


	public GraphicsContent(ApplicationTime thread) {
		this.t = thread;
	}

	// set this panel's preferred size for auto-sizing the container JFrame
	public Dimension getPreferredSize() {
		return new Dimension(_0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
	}

	int mittelpunktXd = _0_Constants.WINDOW_WIDTH / 2;
	int mittelpunktYd = _0_Constants.WINDOW_HEIGHT / 2;
	
	int diameter = 5;
	
	public static int geschwindigkeit = 10;


	double bewegung = 0.0;

	ArrayList<Punkt> punktListeVorne = new ArrayList<Punkt>();// speichert alle Punkte die vorne gezeichnet werden, damit zuerst hinten gezeichnet werden kann

	public void paintPunktObjekt(Punkt pPunkt, Graphics g) {
		int[] pos = pPunkt.position;
		g.setColor(pPunkt.color);
		g.fillOval(pos[0] - pPunkt.Dicke / 2, pos[1] - pPunkt.Dicke / 2, pPunkt.Dicke, pPunkt.Dicke);
	}

	public void paintVorne(Graphics g) {
		for (Punkt p : punktListeVorne) {
			paintPunktObjekt(p, g);
		}

		punktListeVorne.clear();
	}

	

	public void paintAnimatedGeoD(double bg1, double lg1, double bg2, double lg2, int r, Graphics g, Color c, int dicke,
			double t) {
		double[][] p = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg1, lg1, r), 2);
		double[][] q = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg2, lg2, r), 2);
		double[][] ergebnissPunkt = Matrix_VektorRechner.funktion(p, q, r, t * geschwindigkeit);

		boolean vorn = Matrix_VektorRechner.vorne(ergebnissPunkt);
		Color punktColor = null;

		if (t < (Matrix_VektorRechner.winkel(p, q) / geschwindigkeit)) {

			punktColor = Matrix_VektorRechner.vornHintenColor(ergebnissPunkt, c);
			g.setColor(punktColor);
			try {
				ergebnissPunkt = Projection.projektiere(ergebnissPunkt);
			} catch (Exception e) {

				e.printStackTrace();
			}

			int[] verschobevektor = new int[2];

			verschobevektor[0] = (int) (ergebnissPunkt[0][0]);

			verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

			verschobevektor[1] = (int) (ergebnissPunkt[1][0] );

			verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

			paintGeodaetische(bg1, lg1, bg2, lg2, r, g, c, dicke / 3, bewegung * geschwindigkeit);
			if (!vorn) {
				g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
			} else {

				punktListeVorne.add(new Punkt(verschobevektor, punktColor, dicke));
			}
			bewegung += 0.003;

		} else {
			Animation.ausführen = false;
			Animation.ort1.setEnabled(true);
			Animation.ort2.setEnabled(true);

			
			bewegung = 0.0;
		}

	}

	public void paintGeodaetische(double bg1, double lg1, double bg2, double lg2, int r, Graphics g, Color c, int dicke,
			double max) {
		double[][] p = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg1, lg1, r), 2);
		double[][] q = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg2, lg2, r), 2);

		for (double t = 0; t < max; t = t + 0.006) {
			double[][] ergebnissPunkt = Matrix_VektorRechner.funktion(p, q, r, t);

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
				ergebnissPunkt = Projection.projektiere(ergebnissPunkt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int[] verschobevektor = new int[2];

			verschobevektor[0] = (int) (ergebnissPunkt[0][0]);

			verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

			verschobevektor[1] = (int) (ergebnissPunkt[1][0]);

			verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

			if (!vorn) {
				g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
			} else {

				punktListeVorne.add(new Punkt(verschobevektor, punktColor, dicke));
			}
		}

	}

	public void paintPunktGrad(double bg, double lg, int r, Graphics g, Color c, int dicke) {
		double vektor[][] = Matrix_VektorRechner.roundDoubleVektor(Matrix_VektorRechner.lBGradIn3Dkoord(bg, lg, r), 2);
		double projeVektor[][] = null;
		boolean vorn = Matrix_VektorRechner.vorne(vektor);
		Color punktColor = null;
		try {
			projeVektor = Projection.projektiere(vektor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] verschobevektor = new int[2];

		verschobevektor[0] = (int) (r * projeVektor[0][0]);

		verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

		verschobevektor[1] = (int) (r * projeVektor[1][0]);

		verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;

		punktColor = Matrix_VektorRechner.vornHintenColor(vektor, c);
		g.setColor(punktColor);
		if (!vorn) {
			g.fillOval(verschobevektor[0] - dicke / 2, verschobevektor[1] - dicke / 2, dicke, dicke);
		} else {
			punktListeVorne.add(new Punkt(verschobevektor, punktColor, dicke));
		}

	}

	public void paintUmrissellipse(Graphics g, Color c, int dicke, int r) {

		for (double i = 0; i < 2 * Math.PI; i = i + 0.006) {

			try {
				double point[][] = Matrix_VektorRechner.roundDoubleVektor(Projection.umrissellipse(r, i), 2);

				point = Projection.projektiere(point);
				int[] verschobevektor = new int[2];

				verschobevektor[0] = (int) (r * point[0][0]);

				verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

				verschobevektor[1] = (int) (r * point[1][0]);

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
			projeVektor = Projection.projektiere(vektor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[] verschobevektor = new int[2];

		verschobevektor[0] = (int) (projeVektor[0][0] );

		verschobevektor[0] += _0_Constants.WINDOW_WIDTH / 2;

		verschobevektor[1] = (int) (projeVektor[1][0] );

		verschobevektor[1] += _0_Constants.WINDOW_HEIGHT / 2;
		g.setColor(c);

		g.drawLine(_0_Constants.WINDOW_WIDTH / 2, _0_Constants.WINDOW_HEIGHT / 2, verschobevektor[0],
				verschobevektor[1]);

	}


	static double winkel = 2.0; // für den wobble effekt zuständig

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
		g.clearRect(0, 0, _0_Constants.WINDOW_WIDTH, _0_Constants.WINDOW_HEIGHT);
		// paintVektorProjekted(x1, g, Color.red, diameter);
		// paintVektorProjekted(x2, g, Color.green, diameter);
		// paintVektorProjekted(x3, g, Color.blue, diameter);

		int r = 13; // r ist für den radius zuständig

		double tmax = Math.PI * 2;

		paintGeodaetische(0, 0, 90, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 20, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 40, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 60, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 80, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 100, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 120, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 140, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 160, -89, 0, r, g, Color.black, 3, tmax);
		paintGeodaetische(0, 0, 0, 179, r, g, Color.black, 3, tmax);

		Ort ort1 = (Ort) Animation.ort1.getSelectedItem();
		Ort ort2 = (Ort) Animation.ort2.getSelectedItem();

		
		
		paintPunktGrad(ort1.breitengrad, ort1.längengrad, r, g, Color.green, 20);
		paintPunktGrad(ort2.breitengrad, ort2.längengrad, r, g, Color.green, 20);

		
		
		
		if (Animation.ausführen) {
			paintAnimatedGeoD(ort1.breitengrad, ort1.längengrad, ort2.breitengrad, ort2.längengrad, r, g, Color.red, 20,
					bewegung);
		}

		paintUmrissellipse(g, Color.black, 5, r);
		paintVorne(g);

		if (Animation.wobble) {
			Projection.s1Length = 0.5 * Math.sin(winkel);
			winkel = winkel - 0.3;
		} else {
			Projection.s1Length = 0.45;

		}

	}
}
