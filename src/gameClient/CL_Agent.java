package gameClient;

import api.*;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.util.List;

public class CL_Agent {
	private int id;
	private node_data curr_Node;
	private directed_weighted_graph graph;
	private geo_location pos;
	private double speed;
	private edge_data curr_edge;
	private CL_Pokemon curr_pokemon;
	private double value;
	private long _sg_dt;
	private List<node_data> path;

	/**
	 * @return list with path
	 */
	public List<node_data> getPath() {
		return path;
	}

	/**
	 * set the path
	 * @param path
	 * @param n - node
	 */
	public void setPath(List<node_data> path, node_data n) {
		this.path = path;
		path.add(n);
	}
// constructor
	public CL_Agent(directed_weighted_graph g, int start_node) {
		this.graph = g;
		setMoney(0);
		this.curr_Node = this.graph.getNode(start_node);
		this.pos = this.curr_Node.getLocation();
		this.id = -1;
		setSpeed(0);
	}

	/**
	 * update by json file
	 * @param json
	 */
	public void update(String json) {
		JSONObject line;
		try {
			// "GameServer":{"graph":"A0","pokemons":3,"agents":1}}
			line = new JSONObject(json);
			JSONObject ttt = line.getJSONObject("Agent");
			int id = ttt.getInt("id");
			if(id==this.getID() || this.getID() == -1) {
				if(this.getID() == -1) this.id = id;
				double speed = ttt.getDouble("speed");
				String p = ttt.getString("pos");
				Point3D pp = new Point3D(p);
				int src = ttt.getInt("src");
				int dest = ttt.getInt("dest");
				double value = ttt.getDouble("value");
				this.pos = pp;
				this.setCurrNode(src);
				this.setSpeed(speed);
				this.setNextNode(dest);
				this.setMoney(value);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int getSrcNode() {return this.curr_Node.getKey();}

	public String toJSON() {
		int d = this.getNextNode();
		String ans = "{\"Agent\":{"
				+ "\"id\":"+this.id+","
				+ "\"value\":"+this.value+","
				+ "\"src\":"+this.curr_Node.getKey()+","
				+ "\"dest\":"+d+","
				+ "\"speed\":"+this.getSpeed()+","
				+ "\"pos\":\""+this.pos.toString()+"\""
				+ "}"
				+ "}";
		return ans;
	}

	/**
	 * set value
	 * @param v - value
	 */
	private void setMoney(double v) {this.value = v;}

	/**
	 * @param dest
	 * @return true or false if the node can acces to dest
	 */
	public boolean setNextNode(int dest) {
		boolean ans = false;
		int src = this.curr_Node.getKey();
		this.curr_edge = this.graph.getEdge(src, dest);
		if(this.curr_edge != null) ans=true;
		return ans;
	}

	/**
	 * set current Node
	 * @param src
	 */
	public void setCurrNode(int src) { this.curr_Node = this.graph.getNode(src); }

	public boolean isMoving() { return this.curr_edge!=null; }

	public String toString() { return toJSON(); }

	public int getID() {
		// TODO Auto-generated method stub
		return this.id; }

	public geo_location getLocation() { return this.pos; }

	public double getValue() { return this.value; }

	/**
	 * @return - the next node
	 */
	public int getNextNode() {
		int ans = -2;
		if(this.curr_edge == null) ans = -1;
		else ans = this.curr_edge.getDest();
		return ans;
	}

	public double getSpeed() { return this.speed; }

	public void setSpeed(double v) { this.speed = v; }

	public CL_Pokemon get_curr_pokemon() { return curr_pokemon; }

	public void set_curr_pokemon(CL_Pokemon curr_pokemon) { this.curr_pokemon = curr_pokemon; }

	public void set_curr_edge(edge_data _curr_edge) { this.curr_edge = _curr_edge; }

	public void set_SDT(long ddtt) {
		long ddt = ddtt;
		if(this.curr_edge!=null) {
			double w = get_curr_edge().getWeight();
			geo_location dest = this.graph.getNode(get_curr_edge().getDest()).getLocation();
			geo_location src = this.graph.getNode(get_curr_edge().getSrc()).getLocation();
			double de = src.distance(dest);
			double dist = this.pos.distance(dest);
			if(this.get_curr_pokemon().get_edge()==this.get_curr_edge()) {
				dist = curr_pokemon.getLocation().distance(this.pos);
			}
			double norm = dist/de;
			double dt = w*norm / this.getSpeed();
			ddt = (long)(1000.0*dt);
		}
		this.set_sg_dt(ddt);
	}

	public edge_data get_curr_edge() { return this.curr_edge; }

	public void set_sg_dt(long _sg_dt) { this._sg_dt = _sg_dt; }
	public void get_sg_dt(long _sg_dt) {this._sg_dt = _sg_dt;}
	public long get_sg_dt() {
		return this._sg_dt;}
}