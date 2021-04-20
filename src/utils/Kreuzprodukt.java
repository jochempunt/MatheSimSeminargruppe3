package utils;

public class Kreuzprodukt {
	
	public static int[] Kreuz(int[] a, int[] b) {
		int[] ausgabe = new int[3];
	ausgabe[0] = a[1] * b[2] - a[2] * b[1];
	ausgabe[1] = a[2] * b[0] - a[0] * b[2];
	ausgabe[2] = a[0] * b[1] - a[1] * b[0];
	
	return ausgabe;
	
	}
public static void main(String[] args) {
	
		int[] intArray = {3, 1, 2};
		int[] intArray2 = {2, 1, 2};
		String s1 = ""; 
		int[] F = Kreuz(intArray, intArray2);
		for (int i = 0; i < intArray2.length; i++) {
			s1 += F[i] + ",";
			
		}
		
 		System.out.println("Kreuzprodukt: " + s1 );
		
	}

}

