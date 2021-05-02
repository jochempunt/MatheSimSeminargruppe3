package calc;

public class Projektion {

	public static final double alphaDegrees =Math.toRadians(150.0) ;
	
	public static final double s1Length =  0.5;
	
	
	
	
	public static double[][] projektiere(double[][] vektor) throws Exception{
		
		double[][] projectionMatrix = {{-s1Length* Math.sin(alphaDegrees),1.0,0.0},{-s1Length*Math.cos(alphaDegrees),0,-1}};
		
		
		double[][]resultVektor = Matrix_VektorRechner.matrixMultiplikationD(projectionMatrix,vektor);
		
		
		
		return Matrix_VektorRechner.roundDoubleVektor(resultVektor, 2);
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		double[][] vektor1 = {{1},{0},{0}};
		System.out.println(Math.sqrt(2.0)/3.0);
		try {
			Matrix_VektorRechner.matrixAusgabeD(projektiere(vektor1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
