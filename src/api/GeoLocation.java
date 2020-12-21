package api;

import java.util.StringTokenizer;

public class GeoLocation implements geo_location {
    private double x, y, z;

    public GeoLocation()
    {
        this.x= 0;
        this.y= 0;
        this.z= 0;
    }
    public GeoLocation(String t){
        StringTokenizer pos= new StringTokenizer(t,",",false);
        x=Double.parseDouble(pos.nextToken());
        y=Double.parseDouble(pos.nextToken());
        z=Double.parseDouble(pos.nextToken());
    }


    public GeoLocation(double x, double y, double z)
    {
        this.x= x;
        this.y= y;
        this.z= z;
    }
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(geo_location g) {
        double dx = this.x() - g.x();
        double dy = this.y() - g.y();
        double dz = this.z() - g.z();
        double t = (dx*dx+dy*dy+dz*dz);
        return Math.sqrt(t);
    }

    public String toString(){
        return x+","+y+","+z;
    }
    public boolean equals(Object p){
        if (this == p) return true;
        if(p==null || !(p instanceof GeoLocation)) {return false;}
        GeoLocation p2 = (GeoLocation) p;
        return ( (x==p2.x) && (y==p2.y) && (z==p2.z) );
    }

}
