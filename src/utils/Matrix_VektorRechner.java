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
						ergebniss[zeilenAE][spaltenBE] += matrixA[zeilenAE][spaltenA_zeilenB] * matrixB[spaltenA_zeilenB][spaltenBE];
					}
				}
			}
			return ergebniss;
		} else {
			throw new Exception("Anzahl Spalten der ersten Matrix muss gleich der Anzahl Zeilen der zweiten Matrix sein");
		}

	}
	
	public static void matrixAusgabe(int[][] matrix) {
		String ausgabe="";
		for(int i=0; i<matrix.length;i++) {
			for(int j=0; j<matrix[0].length;j++) {
				ausgabe+=matrix[i][j]+" ";
			}
			ausgabe+="\n";
		}
		System.out.println(ausgabe);
		
		
		
	}
	
	public static  int[][] rotationsMatrix() {
		
		return null;
		
		
		
		
		
	}
	
	

}
