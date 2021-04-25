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

	public static int[][] Kreuz(int[] a, int[] b) {
		int[] ausgabe = new int[3];
		ausgabe[0] = a[1] * b[2] - a[2] * b[1];
		ausgabe[1] = a[2] * b[0] - a[0] * b[2];
		ausgabe[2] = a[0] * b[1] - a[1] * b[0];

		int matrixausgabe[][] = new int[3][1];

		for (int i = 0; i <ausgabe.length; i++) {
			matrixausgabe[i][0]= ausgabe[i];
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
	
	
	public static double[][]roundDoubleVektor(double[][]vektor, int nachkommastellen){
		double x = Math.pow(10, nachkommastellen);
		for(int i = 0 ; i< vektor[0].length;i++) {
			vektor[0][i]=  Math.round(vektor[0][i] * x) / x;
		}
		
		
		
		return vektor;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		int[] intArray = {3, 1, 2};
		int[] intArray2 = {2, 1, 2};
		
		
		
		double[][]jp = {{0.501,0.50001,0.505}};
		String s1 = ""; 
		
		matrixAusgabeD(roundDoubleVektor(jp, 2));
		/*int[][] F = Kreuz(intArray, intArray2);
		matrixAusgabe(F);
		int[][] ben = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] jochem = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		try {
			matrixAusgabe(matrixMultiplikation(ben,F));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	}
	public static int[][] rotationsMatrix() {

		return null;

	}

}