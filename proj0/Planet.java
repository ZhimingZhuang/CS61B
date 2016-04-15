
/**
 * Created by zhimingzhuang on 4/14/16.
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;

    /*Planet Constructor*/
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    /*Planet Copy Constructor*/
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p. imgFileName;
    }
    /*Calculate the distance between two planets*/
    public double calcDistance(Planet other) {
        double dx = other.xxPos - xxPos;
        double dy = other.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /*Calculate the force on x-axis between two planets*/
    public double calcForceExertedByX(Planet other) {
        double dx = other.xxPos - xxPos;
        double dy = other.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        double f = (6.67 * 10e-12) * other.mass * mass / (r * r);
        double fx = f * dx / r;
        return fx;

    }
    /*Calculate the force on y-axis between two planets*/
    public double calcForceExertedByY(Planet other) {
        double dx = other.xxPos - xxPos;
        double dy = other.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        double f = (6.67 * 10e-12) * other.mass * mass / (r * r);
        double fy = f * dy / r;
        return fy;
    }

    /*This method takes in a planet, and returns a double describing the force exerted on this planet by the given planet*/
    public double calcForceExertedBy(Planet other) {
        double r = calcDistance(other);
        double f = (6.67 * 10e-12) * other.mass * mass / (r * r);
        return f;
    }

    /*Each take in an array of Planets and calculate the net X and net Y force exerted by all planets in that array upon the current Planet.*/
    public double calcNetForceExertedByX(Planet[] p) {
        double fx = 0;
        for(int i = 0; i < p.length; i++) {
            double dx = p[i].xxPos - xxPos;
            double dy = p[i].yyPos - yyPos;
            if(dx == 0 && dy == 0) continue;
            double r = Math.sqrt(dx * dx + dy * dy);
            double f = (6.67 * 10e-12) * p[i].mass * mass / (r * r);
            fx += f * dx / r;
        }
        return fx;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double fy = 0;
        for(int i = 0; i < p.length; i++) {
            double dx = p[i].xxPos - xxPos;
            double dy = p[i].yyPos - yyPos;
            if(dx == 0 && dy == 0) continue;
            double r = Math.sqrt(dx * dx + dy * dy);
            double f = (6.67 * 10e-12) * p[i].mass * mass / (r * r);
            fy += f * dy / r;
        }
        return fy;
    }

    /*determines how much the forces exerted on the planet will cause that planet to accelerate, and the resulting change
     *in the planet's velocity and position in a small period of time dt*/
    public  void update(double dt, double fx, double fy) {
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public  void draw() {
        String imgToShow = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToShow);
    }
}
