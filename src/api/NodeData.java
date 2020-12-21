package api;

public class
NodeData implements node_data {
	private	int key; 
	private String info;
	private int tag;
	private double weight;
	private geo_location local;
	private static int counter;
	private boolean saw;

	
	// Constructors
	public NodeData () {
		this.key = counter ++;
		geo_location local = null;
		info = "";
		tag = 0;
		weight = 0.0;

	}
	public NodeData(int key) {
		this.key = key;
		this.local = new GeoLocation();
		this.weight = 0;
		this.info = "";
		this.tag = 0;
	}

	public NodeData(int key, geo_location local, double weight, String info, int tag)
    {
        this.key = key;
        this.local = local;
        this.weight = weight;
        this.info = info;
        this.tag = tag;
    }
	private NodeData(int k , geo_location local) {
		this.key = k;
		this.setLocation(local);
		this.tag = 0;
		this.info = null;
	}
	@Override
	public int getKey() {

		return this.key;
	}

	@Override
	public geo_location getLocation() {

		return this.local;
	}

	@Override
	public void setLocation(geo_location p) {
		this.local = new GeoLocation( p.x() , p.y(), p.z());
	}

	@Override
	public double getWeight() {

		return this.weight;
	}

	@Override
	public void setWeight(double w) {

		this.weight=w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {

		this.info=s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}
	//////////////////////////
	public boolean getSaw() { return this.saw; }

	public void setSaw(boolean saw) { this.saw = saw; }


}

