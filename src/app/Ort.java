package app;

public class Ort {
		public double lšngengrad;
		public double breitengrad;
		public String name;
		
		public Ort( double breitengrad,double lšngengrad, String name) {
			super();
			this.lšngengrad = lšngengrad;
			this.breitengrad = breitengrad;
			this.name = name;
		}

		@Override
		public String toString() {
			return name ;
		}
		
		
		
		
}
