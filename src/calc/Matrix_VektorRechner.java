package utils;

public class Matrix_VektorRechner {

	public static int[][] matrixMultiplikation(int[][] matrixA, int[][] matrixB) throws Exception {
		int[][] ergebniss = null;

		if (matrixA[0].length == matrixB.length) {

			int zeilenMatrixA = matrixA.length;
			int spaltenMatrixA = matrixA[0].length;
			int spaltenMatrixB = matrixB[0].length;

			ergebniss = new int[zeilenMatrixA][spaltenMatrixB];

			for (int zeilenAE = 0; zeilenAE < zeilenMatrixA; zeilenAE++) {
				for (int spaltenBE = 0; spaltenBE < spaltenMatrixB; spaltenBE++) {
					ergebniss[zeilenAE][spaltenBE] = 0;
					for (int spaltenA_zeilenB = 0; spaltenA_zeilenB < spaltenMatrixA; spaltenA_zeilenB++) {
						ergebniss[zeilenAE][spaltenBE] += matrixA[zeilenAE][spaltenA_zeilenB]
								* matrixB[spaltenA_zeilenB][spaltenBE];
					}
				}
			}
			return ergebniss;
		} else {
			throw new Exception(
					"Anzahl Spalten der ersten Matrix muss gleich der Anzahl Zeilen der zweiten Matrix sein");
		}

	}

	public static void matrixAusgabe(int[][] matrix) {
		String ausgabe = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				ausgabe += matrix[i][j] + " ";
			}
			ausgabe += "\n";
		}
		System.out.println(ausgabe);

	}

	public static double[][] Kreuz(double[][] a, double[][] b) {
		double[] ausgabe = new double[3];
		ausgabe[0] = a[1][0] * b[2][0] - a[2][0] * b[1][0];
		ausgabe[1] = a[2][0] * b[0][0] - a[0][0] * b[2][0];
		ausgabe[2] = a[0][0] * b[1][0] - a[1][0] * b[0][0];

		double matrixausgabe[][] = new double[3][1];

		for (int i = 0; i < ausgabe.length; i++) {
			matrixausgabe[i][0] = ausgabe[i];
		}

		return matrixausgabe;

	}

	public static double[][] matrixMultiplikationD(double[][] matrixA, double[][] matrixB) throws Exception {
		double[][] ergebniss = null;

		if (matrixA[0].length == matrixB.length) {

			int zeilenMatrixA = matrixA.length;
			int spaltenMatrixA = matrixA[0].length;
			int spaltenMatrixB = matrixB[0].length;

			ergebniss = new double[zeilenMatrixA][spaltenMatrixB];

			for (int zeilenAE = 0; zeilenAE < zeilenMatrixA; zeilenAE++) {
				for (int spaltenBE = 0; spaltenBE < spaltenMatrixB; spaltenBE++) {
					ergebniss[zeilenAE][spaltenBE] = 0;
					for (int spaltenA_zeilenB = 0; spaltenA_zeilenB < spaltenMatrixA; spaltenA_zeilenB++) {
						ergebniss[zeilenAE][spaltenBE] += matrixA[zeilenAE][spaltenA_zeilenB]
								* matrixB[spaltenA_zeilenB][spaltenBE];
					}
				}
			}
			return ergebniss;
		} else {
			throw new Exception(
					"Anzahl Spalten der ersten Matrix muss gleich der Anzahl Zeilen der zweiten Matrix sein");
		}

	}

	public static void matrixAusgabeD(double[][] matrix) {
		String ausgabe = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				ausgabe += matrix[i][j] + " ";
			}
			ausgabe += "\n";
		}
		System.out.println(ausgabe);

	}

	public static double[][] roundDoubleVektor(double[][] vektor, int nachkommastellen) {
		double x = Math.pow(10, nachkommastellen);
		for (int i = 0; i < vektor.length; i++) {
			vektor[i][0] = Math.round(vektor[i][0] * x) / x;
		}

		return vektor;

	}

	public static double[][] lBGradIn3Dkoord(double breitengrad, double l�ngengrad, double r) {
		double[][] vektor = new double[3][1];
		breitengrad = Math.toRadians(breitengrad);
		l�ngengrad = Math.toRadians(l�ngengrad);

		vektor[0][0] = r * Math.cos(breitengrad) * Math.cos(l�ngengrad);
		vektor[1][0] = r * Math.cos(breitengrad) * Math.sin(l�ngengrad);
		vektor[2][0] = r * Math.sin(breitengrad);
		return vektor;
	}

	public static double betrag(double[][] vektor) {
		double betrag = Math.sqrt(Math.pow(Math.abs(vektor[0][0]), 2) + Math.pow(Math.abs(vektor[1][0]), 3) + Math.pow(Math.abs(vektor[2][0]), 2));
		return betrag;
	}

	public static double winkel(double[][] p, double[][] q) {
		double skalarProdukt = (p[0][0] * q[0][0]) +( p[1][0] * q[1][0]) +( p[2][0] * q[2][0]);
		double betragProdukt = betrag(p) * betrag(q);
		return Math.acos(skalarProdukt/ betragProdukt);
	}

	public static void main(String[] args) {

		
		
		
		double[][] p = lBGradIn3Dkoord(48.05 , 8.21, 1);
		double[][] q = lBGradIn3Dkoord(-33.92,18.41 , 1);
		System.out.println("vektor p = ");
		matrixAusgabeD(p);
		System.out.println("vector q = ");
		matrixAusgabeD(q);
		
		System.out.println("betrag von p = " +betrag(p));
		System.out.println("betrag von q = "+ betrag(q));
		System.out.println("winkel zw. p und q = " +Math.toDegrees(winkel(p, q)));
		System.out.println("winkel zw. p und q = " +(winkel(p, q)));
		
		
		//matrixAusgabeD(einheitsVektor(p));
		System.out.println("kreuzprodukt von p und q = ");
		matrixAusgabeD(Kreuz(p, q));
		System.out.println("betrag von p x q = " + betrag(Kreuz(p, q)));
		System.out.println("einheitsvektor von n =  ");
		matrixAusgabeD(einheitsVektor(Kreuz(p, q)));
		System.out.println("einheitsvektor von u = " );
		matrixAusgabeD(einheitsVektor(Kreuz(einheitsVektor(Kreuz(p, q)),p)));
		
		
		
		
		
		
		
		
	}

	public static double[][] einheitsVektor(double[][] vektor) {
		double betrag = betrag(vektor);
		double[][] einheitsVektor = new double[3][1];

		einheitsVektor[0][0] = vektor[0][0] / betrag;
		einheitsVektor[1][0] = vektor[1][0] / betrag;
		einheitsVektor[2][0] = vektor[2][0] / betrag;

		return einheitsVektor;
	}

	public static double[][]funktion(double[][] p,double[][] q,int r,double t){
		double [][]n =  einheitsVektor(Kreuz(p, q));
		double [][]u = einheitsVektor(Kreuz(n, p));
		double [][]pDach = einheitsVektor(p);
		
		
		
		double[][] ersteH�lfte = new double[3][1];
		
		for(int i =0;i<3;i++) {
			ersteH�lfte[i][0]= r* Math.cos(t) * p[i][0];
		}
		double[][] zweiteH�lfte=new double[3][1];
		
		for (int j = 0; j < 3; j++) {
			zweiteH�lfte[j][0] = r* Math.sin(t)*u[j][0];
		}
		
		double[][] ergebnis= new double [3][1];
		
		for (int i = 0; i < 3; i++) {
			ergebnis[i][0]= ersteH�lfte[i][0]+ zweiteH�lfte[i][0];
		}
		
		
		return roundDoubleVektor(ergebnis,2) ;
		
	}

	public static int[][] rotationsMatrix() {

		return null;

	}

}