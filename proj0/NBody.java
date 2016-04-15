public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] p = readPlanets(filename);
		double radius = readRadius(filename);

		// Draw the backfround.
		String backGround = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backGround);

		// Draw all the planet.
		for(int i = 0; i < p.length; i++) {
			p[i].draw();
		}
		double time = 0;
		StdAudio.play("./audio/2001.mid");
		for(; time <  T; time += dt) {
			double[] xForces = new double[p.length];
			double[] yForces = new double[p.length];
			for(int i = 0; i < p.length; i++) {
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, backGround);
			for(int i = 0; i < p.length; i++) {
				p[i].update(dt, xForces[i], yForces[i]);
				p[i].draw();
			}

			StdDraw.show(10);
		}

		StdOut.println(p.length);
		StdOut.printf("%.2e\n",radius);
		for(int i = 0; i < p.length; i++) {
			StdOut.printf("% .4e ", p[i].xxPos);
			StdOut.printf("% .4e ", p[i].yyPos);
			StdOut.printf("% .4e ", p[i].xxVel);
			StdOut.printf("% .4e ", p[i].yyVel);
			StdOut.printf(p[i].imgFileName);
			StdOut.println();
		}

	}

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] p = new Planet[numOfPlanets];
		int i = 0;
		while(!in.isEmpty() && i < numOfPlanets){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
			i++;
		}
		return p;
	}
}