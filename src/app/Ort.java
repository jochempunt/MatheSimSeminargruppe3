package app;

public class Ort {
		public double l�ngengrad;
		public double breitengrad;
		public String name;
		
		public Ort( double breitengrad,double l�ngengrad, String name) {
			super();
			this.l�ngengrad = l�ngengrad;
			this.breitengrad = breitengrad;
			this.name = name;
		}

		@Override
		public String toString() {
			return name ;
		}
		
		
		
		
}
