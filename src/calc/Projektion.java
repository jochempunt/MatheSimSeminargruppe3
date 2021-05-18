package calc;

public class Projektion {

	public static final double alphaDegrees =Math.toRadians(135) ;
	
	public static final double s1Length =  0.5;
	
	
	
	
	public static double[][] projektiere(double[][] vektor) throws Exception{
		
		double[][] projectionMatrix = {{-s1Length* Math.sin(alphaDegrees),1.0,0.0},{-s1Length*Math.cos(alphaDegrees),0,-1}};
		
		
		double[][]resultVektor = Matrix_VektorRechner.matrixMultiplikationD(projectionMatrix,vektor);
		
		
		
		return Matrix_VektorRechner.roundDoubleVektor(resultVektor, 2);
		
	}
	
	
	public static double[][] umrissellipse(int r, double t) throws Exception{
		double phi = Math.atan(s1Length*Math.sin(alphaDegrees));
		double teta =Math.atan(-s1Length*Math.cos(alphaDegrees)*Math.cos(phi));
		
		
		
		double[][] point = {{0},{Math.cos(t)},{Math.sin(t)}};
		
		double[][] drehungX2 = {{Math.cos(teta),0,-Math.sin(teta)},{0,1,0},{Math.sin(teta),0,Math.cos(teta)}};
		
		double[][] drehungX3 = {{Math.cos(phi),-Math.sin(phi),0},{Math.sin(phi),Math.cos(phi),0},{0,0,1}};
		
		
		drehungX3 = Matrix_VektorRechner.matrixMultiplikationD(drehungX3,point);
		point = Matrix_VektorRechner.matrixMultiplikationD(drehungX2,drehungX3);
		
		Matrix_VektorRechner.matrixAusgabeD(point);
		
		
		return point;
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		System.out.println(Math.toRadians(150));
		
		
		
		try {
			System.out.println("Furtwangen in 3D koord:");
			Matrix_VektorRechner.matrixAusgabeD(projektiere(Matrix_VektorRechner.lBGradIn3Dkoord(48.052,8.216,1)));
			System.out.println("Kapstadt in 3D koord:");
			Matrix_VektorRechner.matrixAusgabeD(projektiere(Matrix_VektorRechner.lBGradIn3Dkoord(-33.928,18.417,1)));
			
			umrissellipse(1,1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
