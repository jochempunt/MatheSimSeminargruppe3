package app;

public class Ort {
		public double längengrad;
		public double breitengrad;
		public String name;
		
		public Ort( double breitengrad,double längengrad, String name) {
			super();
			this.längengrad = längengrad;
			this.breitengrad = breitengrad;
			this.name = name;
		}

		@Override
		public String toString() {
			return name ;
		}
		
		
		
		
}
