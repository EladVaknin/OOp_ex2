package api;


import java.io.Serializable;

public class EdgeData implements edge_data  , Serializable {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;
    private Boolean shE;


    // constructors
    public EdgeData ( int src, int dest , double weight ) {
        this.weight=weight;
        this.src=src;
        this.dest=dest;
        this.info = "";
        this.setTag(0);
    }

    public EdgeData ( int src, int dest , double weight , String info , int tag ) {
        this.weight=weight;
        this.src=src;
        this.dest=dest;
        this.info = info;
        this.setTag(tag);

    }


    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {

        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {

        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public boolean getShort() { return this.shE; }

    public void setShort(boolean shortEdge) { this.shE = shortEdge; }
}