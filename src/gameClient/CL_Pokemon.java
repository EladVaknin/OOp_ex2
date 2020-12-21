package gameClient;

import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CL_Pokemon {
	private List<CL_Pokemon> pkm;
	private CL_Agent next;
	private edge_data _edge;
	private double _value;
	private int _type;
	private Point3D _pos;
	private double min_dist;
	private int min_ro;
	private String info;

	/**
	 * constructor
	 * @param p - point
	 * @param t - type
	 * @param v - value
	 * @param s - speed
	 * @param e - edge
	 */
	public CL_Pokemon(Point3D p, int t, double v, double s, edge_data e) {
		_type = t;
		//	_speed = s;
		_value = v;
		pkm = new ArrayList<>();
		next = null;
		set_edge(e);
		_pos = p;
		min_dist = Integer.MAX_VALUE;
		min_ro = -1;
	}

	/**
	 * initialize
	 * @param json
	 * @return - pokemon
	 */
	public static CL_Pokemon init_from_json(String json) {
		CL_Pokemon ans = null;
		try {
			JSONObject p = new JSONObject(json);
			int id = p.getInt("id");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	/**
	 * @return - pokemon
	 */
	public List<CL_Pokemon> getClosePokemon() { return pkm; }

	public String toString() { return "F:{v="+this._value+", t="+this._type+"}"; }

	public edge_data get_edge() { return this._edge; }

	public void set_edge(edge_data _edge) { this._edge = _edge; }

	public Point3D getLocation() { return this._pos; }
	public int getType() {return _type;}

	public double getValue() {return this._value;}

	public double getMin_dist() { return this.min_dist; }

	public void setMin_dist(double mid_dist) { this.min_dist = mid_dist; }

	public CL_Agent getNxt() { return this.next; }

	public void setNxt(CL_Agent next) { this.next = next; }

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		CL_Pokemon that = (CL_Pokemon) o;
		return Double.compare(that._value, this._value) == 0 &&
				this._type == that._type &&
				Objects.equals(this._pos, that._pos);
	}

	@Override
	public int hashCode() { return Objects.hash(this._value, this._type, this._pos); }
	public void setInfo(String info) {
		this.info=info;
	}
	public String getInfo() {
		return this.info;
	}
}



