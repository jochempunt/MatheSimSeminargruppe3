package calc;

public class Projektion {

	public static final double alphaDegrees =Math.toRadians(150.0) ;
	
	public static final double s1Length =  0.5;
	
	
	
	
	public static double[][] projektiere(double[][] vektor) throws Exception{
		
		double[][] projectionMatrix = {{-s1Length* Math.sin(alphaDegrees),1.0,0.0},{-s1Length*Math.cos(alphaDegrees),0,-1}};
		
		
		double[][]resultVektor = Matrix_VektorRechner.matrixMultiplikationD(projectionMatrix,vektor);
		
		
		
		return Matrix_VektorRechner.roundDoubleVektor(resultVektor, 4);
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		
		
		
		
		try {
			System.out.println("Furtwangen in 3D koord:");
			Matrix_VektorRechner.matrixAusgabeD(projektiere(Matrix_VektorRechner.lBGradIn3Dkoord(48.052,8.216,1)));
			System.out.println("Kapstadt in 3D koord:");
			Matrix_VektorRechner.matrixAusgabeD(projektiere(Matrix_VektorRechner.lBGradIn3Dkoord(-33.928,18.417,1)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
