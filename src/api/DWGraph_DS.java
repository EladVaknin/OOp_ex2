package api;

import java.io.Serializable;
import java.util.*;


public class DWGraph_DS implements directed_weighted_graph, Serializable {
    private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
    private HashMap<node_data, HashMap<Integer, edge_data>> Edges = new HashMap<node_data, HashMap<Integer, edge_data>>();
    private static int counterEdge = 0;
    private static int mc = 0;

////////////////////////////////////////////////DWGraph_DS///////////////////////////////////////////////////

    // constructor
    public DWGraph_DS() {
        nodes = new HashMap<>();
        Edges = new HashMap<>();
        counterEdge = 0;
        mc = 0;
    }
    public Collection<edge_data> getE() {
        List<edge_data> edgesList = new ArrayList<>();
        for (HashMap<Integer,edge_data> neighbors:this.Edges.values())
            for (edge_data edge : neighbors.values()) edgesList.add(edge);
        return edgesList;
    }


    @Override
    public node_data getNode(int key) {
        if (nodes.get(key) == null) return null;
        return nodes.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        if (src == dest) return null;
        node_data x = null;
        if (nodes.containsKey(src) && Edges.get(nodes.get(src)).containsKey(dest)&&hasEdge(src,dest)) {
            //x = nodes.get(src);
            return Edges.get(getNode(src)).get(dest);
        } else {
            return null;
        }
    }

    @Override
    public void addNode(node_data n) {
        if (nodes.containsKey(n.getKey())) return;
        else {
            nodes.put(n.getKey(), n);
            Edges.put(n, new HashMap<Integer, edge_data>());
            mc++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        //src = dest
        if (src == dest) {
            return;
        }
        if(getNode(src) == null || getNode(dest) == null)
            return;
        if (w < 0) {
            throw new RuntimeException("whight cant be negative value");
        }
        if (hasEdge(src, dest)) {
            if (w == getEdge(src, dest).getWeight())
                return;
        }
        if (nodes.containsKey(src) && nodes.containsKey(dest))
        //else
        {
            mc++;
            counterEdge++;
            node_data k = nodes.get(src);
            Edges.get(k).put(dest, new EdgeData(src, dest, w));
        }
        else return;
    }

//	@Override
//	public void connect(int src, int dest, double w) {
//		if (w < 0)
//			return;
//		if (!nodes.containsKey(src) && !nodes.containsKey(dest))
//			return;
//		if ((src == dest) )return;
//		if (hasEdge(src, dest)) {
//			if (w == getEdge(src, dest).getWeight())
//				return;
//		}
//		if ((nodes.get(src) == null) || (nodes.get(dest) == null))
//			return;
//
//		else {
//			node_data k = nodes.get(src);
//			Edges.get(k).put(dest,  new EdgeData(src, dest, w));
//			counterEdge++;
//			mc++;
//
//		}
//	}

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        node_data n = nodes.get(node_id);
        return Edges.get(n).values();
    }

    public Collection<edge_data> getIn(int node_id) {
        ArrayList<edge_data> arrayE = new ArrayList<>();
        if (Edges.get(getNode(node_id)) == null)
            return arrayE;
        Collection<node_data> temp = getV();
        Iterator<node_data> itr = temp.iterator();
        while (itr.hasNext()) {
            node_data n = itr.next();
                if (hasEdge(n.getKey(), node_id))
                    arrayE.add(getEdge(n.getKey(), itr.next().getKey()));

        }
        return arrayE;
    }


    @Override
    public node_data removeNode(int key) {
        node_data node = this.nodes.get(key);
        if (node == null) return null;
        HashMap<Integer, edge_data> nodeNeighbors = this.Edges.get(getNode(key));
        if (nodeNeighbors == null) {
            this.nodes.remove(key, node);
            mc++;
            return node;
        } else {
            Collection<edge_data> temp = new ArrayList<>(this.getE(key));
            int s = temp.size();
            for (edge_data edge : temp) {
                this.removeEdge(edge.getDest(), edge.getSrc());
                this.removeEdge(edge.getSrc(), edge.getDest());
            }
            Collection<edge_data> temp2 = getIn(key);
            for (edge_data edge2 : temp2) {
                this.removeEdge(edge2.getSrc(), edge2.getDest());
            }

            // remove node from nodes hashmap
            this.nodes.remove(key, node);
            mc++;
            return node;
        }
    }


    //	@Override
//	public node_data removeNode(int key) {
//		mc++;
//		Collection<node_data> col = getV();
//		Iterator<node_data> itr = col.iterator();
//
//		while (itr.hasNext())
//		{
//			node_data temp = itr.next();
//			if(Edges.get(temp).containsKey(key)==true)
//			{
//				Edges.get(temp).remove(key);
//				counterEdge--;
//			}
//		}
//		node_data n = nodes.get(key);
//		counterEdge= counterEdge - Edges.get(n).size();
//		Edges.remove(n);
//
//		return nodes.remove(key);
//	}
    public edge_data removeEdge(int src, int dest) {
        if (this.nodeSize() <= 1 || edgeSize() == 0) return null;
        node_data node1 = this.getNode(src);
        node_data node2 = this.getNode(dest);
        if (node1 == null || node2 == null || src == dest || node1 == node2) return null;
        if (this.hasEdge(src, dest)) {
            edge_data edge = this.Edges.get(getNode(src)).get(dest);
            this.Edges.get(getNode(src)).remove(dest, edge);
            counterEdge--;
            mc++;
            return edge;
        } else return null;
    }
//	@Override
//	public edge_data removeEdge(int src, int dest) {
//		mc++;
//		counterEdge--;
//		node_data n = nodes.get(src);
//		return Edges.get(n).remove(dest);
//	}

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return counterEdge;
    }

    @Override
    public int getMC() {
        return mc;
    }


    ///////////////////////////
    private boolean hasEdge(int src, int dest) {
        if (this.nodeSize() <= 1 || this.edgeSize() == 0) return false;
        node_data node1 = this.getNode(src);
        node_data node2 = this.getNode(dest);
        if (node1 == null || node2 == null || src == dest || node1 == node2) return false;
        HashMap<Integer, edge_data> srcNeighbors = this.Edges.get(getNode(src));
        if (srcNeighbors == null) return false;
        if (srcNeighbors.containsKey(dest)) return true;
        else return false;
    }


}
