package api;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import gameClient.util.Point3D;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {

    directed_weighted_graph graph = new DWGraph_DS();
    public DWGraph_Algo (directed_weighted_graph g)
    {
        this.graph=g;
    }
    public DWGraph_Algo()
    {
        graph = new DWGraph_DS();
    }
    public DWGraph_Algo(String json_string) {
        Gson gsonObject = new Gson();
        directed_weighted_graph new_graph = new DWGraph_DS();
        Type type = new TypeToken<JsonObject>() {}.getType();
        JsonObject graph = gsonObject.fromJson(json_string, type);
        JsonArray nodes = graph.get("Nodes").getAsJsonArray();
        JsonArray edges = graph.get("Edges").getAsJsonArray();
        for (JsonElement node : nodes) {
            String[] str_nodes = node.getAsJsonObject().get("pos").getAsString().split(",");
            node_data n = new NodeData(node.getAsJsonObject().get("id").getAsInt());
            n.setLocation(new GeoLocation(Double.parseDouble(str_nodes[0]),
                    Double.parseDouble(str_nodes[1]),
                    Double.parseDouble(str_nodes[2])));
            new_graph.addNode(n);
        }
        for(JsonElement edge : edges) {
            new_graph.connect(edge.getAsJsonObject().get("src").getAsInt(),
                    edge.getAsJsonObject().get("dest").getAsInt(),
                    edge.getAsJsonObject().get("w").getAsDouble());
        }
        init(new_graph);
    }

    @Override
    public void init(directed_weighted_graph g) {

        this.graph = g;
    }

//    public void init(String file_name) {
//        // TODO Auto-generated method stub
//        try {
//
//            FileInputStream fi = new FileInputStream(new File(file_name));
//            ObjectInputStream oi = new ObjectInputStream(fi);
//            DWGraph_Algo t =  (DWGraph_Algo) oi.readObject();
//            this.graph =t.graph;
//
//        } catch (RuntimeException | IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    @Override
    public directed_weighted_graph getGraph() {
        return this.graph;
    }

    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph a = new DWGraph_DS();

        Collection<node_data> now = this.graph.getV();
        for (node_data n2 : now) {
            int key = n2.getKey();
            Point3D Location = new Point3D(n2.getLocation().x(), n2.getLocation().y(), n2.getLocation().z());
            double weight = n2.getWeight();
            int tag = n2.getTag();
            String info;
            if (n2.getInfo() == null) {
                info = new String("");
            } else {
                info = new String(n2.getInfo());
            }
            a.addNode(new NodeData(key, Location, weight, info, tag));
        }

        for (node_data n2 : now) {
            Collection<edge_data> edg = this.graph.getE(n2.getKey());
            for (edge_data ed : edg) {
                //deep copy of Edge
                int src = ed.getSrc();
                int dest = ed.getDest();
                double edge_weight = ed.getWeight();
                String edge_info;
                if (ed.getInfo() == null) {
                    edge_info = new String("");
                } else {
                    edge_info = new String(ed.getInfo());
                }
                int edge_tag = ed.getTag();
                a.connect(src, dest, edge_weight);
                a.getEdge(src, dest).setInfo(edge_info);
                a.getEdge(src, dest).setTag(edge_tag);
            }
        }
        return a;

    }


    @Override
    public boolean isConnected() {
        boolean flag = true;
        int v = graph.nodeSize();
        if (this.graph == null || this.graph.nodeSize() <= 1) return flag;
        Collection<node_data> set = graph.getV();
        for (node_data n : set) {
            n.setTag(0);
        }
        Queue<Integer> queue = new LinkedList<>();
        int key = this.graph.getV().iterator().next().getKey();
        int counter = 0;
        queue.add(key);
        while (!queue.isEmpty()) {
            key = queue.poll();
            Collection<edge_data> get = graph.getE(key);
            if (get != null) {
                for (edge_data b : get) {
                    if (counter == 0) {
                        counter++;
                        queue.add(b.getDest());
                        graph.getNode(b.getSrc()).setTag(1);
                        graph.getNode(b.getDest()).setTag(1);
                    } else {
                        if (graph.getNode(b.getSrc()).getTag() == 1 && graph.getNode(b.getDest()).getTag() == 0) {
                            counter++;
                            queue.add(b.getDest());
                            graph.getNode(b.getSrc()).setTag(1);
                            graph.getNode(b.getDest()).setTag(1);
                        } else {
                            if (counter == v - 1 && graph.getNode(b.getSrc()).getTag() == 1 && graph.getNode(b.getDest()).getTag() == 1) {
                                counter++;
                                queue.add(b.getDest());
                                graph.getNode(b.getSrc()).setTag(1);
                                graph.getNode(b.getDest()).setTag(1);
                            }
                        }

                    }
                }
            }
        }
        if (v == counter) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }


    ///////////////////////////////////////////
    public boolean isConnect(int src, int dest) {
        boolean flag = false;
        int v = graph.nodeSize();
        if (this.graph == null || this.graph.nodeSize() <= 1)
            return true;
        Collection<node_data> set = graph.getV();
        for (node_data n : set) {
            n.setTag(0);
        }
        Queue<Integer> queue = new LinkedList<>();
        //int key = this.graph.getV().iterator().next().getKey();
        int counter = 0;
        queue.add(src);
        while (!queue.isEmpty()) {
            src = queue.poll();
            Collection<edge_data> get = graph.getE(src);
            if (get != null) {
                for (edge_data b : get) {
                    if (counter == 0) {
                        counter++;
                        queue.add(b.getDest());
                        graph.getNode(b.getSrc()).setTag(1);
                        graph.getNode(b.getDest()).setTag(1);
                    } else {
                        if (graph.getNode(b.getSrc()).getTag() == 1 && graph.getNode(b.getDest()).getTag() == 0) {
                            counter++;
                            queue.add(b.getDest());
                            graph.getNode(b.getSrc()).setTag(1);
                            graph.getNode(b.getDest()).setTag(1);
                        } else {
                            if (counter == v - 1 && graph.getNode(b.getSrc()).getTag() == 1 && graph.getNode(b.getDest()).getTag() == 1) {
                                counter++;
                                queue.add(b.getDest());
                                graph.getNode(b.getSrc()).setTag(1);
                                graph.getNode(b.getDest()).setTag(1);
                            }
                        }

                    }
                    if (b.getDest() == dest) {
                        flag = true;
                    }
                }
            }
        }

        return flag;

    }
    @Override
    public double shortestPathDist(int src, int dest) {

        if(graph.getNode(src)==null || graph.getNode(dest)==null)
        {
            return -1;
        }
        for (node_data node : graph.getV())
        {
            node.setWeight(Double.MAX_VALUE);
            node.setInfo("");
            node.setTag(0);
        }
        this.graph.getNode(src).setWeight(0);
        PriorityQueue<node_data> pq = new PriorityQueue<>((o1, o2) -> -Double.compare(o2.getWeight(), o1.getWeight()));

        pq.add(this.graph.getNode(src));


        while(pq.size()!=0)
        {


            node_data first=pq.poll();//0
            if(this.graph.getE(first.getKey())!=null) {
                for (edge_data ed : this.graph.getE(first.getKey()))
                {
                    double sum = ( first.getWeight() + ed.getWeight() );
                    node_data second = this.graph.getNode(ed.getDest());

                    if( sum <= second.getWeight())
                    {
                        second.setInfo(first.getKey()+"");

                        pq.remove(second);
                        pq.add(second);
                        second.setWeight(sum);
                    }

                }
            }
        }
        if(this.graph.getNode(dest).getWeight()!=Double.MIN_VALUE) {
            return this.graph.getNode(dest).getWeight();
        }
        else
        {
            return -1;
        }
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        List<node_data> way = new ArrayList<node_data>();

        double path = shortestPathDist(src, dest);

        if(path == Double.MAX_VALUE) {
            return null;
        }


        way.add(graph.getNode(dest));
        String str = graph.getNode(dest).getInfo();
        while(!str.equals("")) {
            way.add(graph.getNode(Integer.parseInt(str)));
            str = graph.getNode(Integer.parseInt(str)).getInfo();
        }
        Collections.reverse(way);

        return way;
    }


    @Override
    public boolean save(String file) {
        boolean flag = false;
        try {
            JsonWriter t = new JsonCreator().JsonGraph(this.graph, file);

        } catch (IOException e) {
            e.printStackTrace();
            return flag;

        }
        flag = true;
        return flag;
    }

    @Override
    public boolean load(String file) {
        boolean flag = false;
        try {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(DWGraph_DS.class, new LoadGson());
            Gson gson2 = gson.create();
            FileReader reader = new FileReader(file);
            directed_weighted_graph graph = gson2.fromJson(reader, DWGraph_DS.class);
            System.out.println(graph);
            init(graph);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return flag;
        }
        flag = true;
        return flag;
    }


    //////////////////////////Json/////////////////////////////////////
    private static class JsonCreator {
        public JsonWriter JsonGraph(directed_weighted_graph graph, String file) throws IOException {
            FileWriter fw = new FileWriter(file);
            JsonWriter jw = new JsonWriter(fw);
            jw.beginObject();
            jw.name("Nodes");
            jw.beginArray();
            Collection<node_data> nodes = graph.getV();
            for (node_data n : nodes) {
                jw.beginObject();
                jw.name("id = ").value(n.getKey());
                jw.name("location = ").value(n.getLocation().toString());

            }
            jw.name("Edges");
            jw.beginArray();
            Collection<node_data> nodes2 = graph.getV();
            for (node_data n : nodes2) {
                Collection<edge_data> edges = graph.getE(n.getKey());
                for (edge_data b : edges) {
                    jw.beginObject();
                    jw.name("src = ").value(b.getSrc());
                    jw.name("dest = ").value(b.getDest());
                    jw.name("weight = ").value(b.getWeight());
                }
            }
            jw.endObject();
            jw.endArray();
            jw.close();
            return jw;
        }
    }

    private class LoadGson implements JsonDeserializer<directed_weighted_graph> {
        @Override
        public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//              GsonBuilder gson = new GsonBuilder();
//              JsonDeserializer<directed_weighted_graph> graph = new JsonDeserializer<directed_weighted_graph>() {
            directed_weighted_graph gs = new DWGraph_DS();
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray Nodes = jsonObject.get("Nodes").getAsJsonArray();
            for (JsonElement node : Nodes) {
                int key = ((JsonObject) node).get("id").getAsInt();
                String pos = ((JsonObject) node).get("location").getAsString();
                gs.addNode(new NodeData(key));
                gs.getNode(key).setLocation(new GeoLocation(pos));
            }
            JsonArray Edges = jsonObject.get("Edges").getAsJsonArray();
            for (JsonElement edge : Edges) {
                int src = ((JsonObject) edge).get("src").getAsInt();
                int dest = ((JsonObject) edge).get("dest").getAsInt();
                double w = ((JsonObject) edge).get("w").getAsDouble();
                gs.connect(src, dest, w);
            }

            return gs;
        }
    }

    public List<List<node_data>> getLists (){
        DFS dfs = new DFS((DWGraph_DS)this.graph);
        for(node_data node : graph.getV()) {
            ((NodeData) node).setSaw(false);
            node.setTag(-1);
        }
        return dfs.getLists();
    }


    /**
     * helpful private class
     */
    private class DFS {
        DWGraph_DS curr_g;
        Stack<NodeData> s;
        List<List<node_data>> l;

        // constructor by given graph
        public DFS(DWGraph_DS graph) {
            this.curr_g = graph;
            this.s = new Stack<>();
            this.l = new ArrayList<>();
        }

        public List<List<node_data>> dfs() {
            for(node_data nds : this.curr_g.getV()) if(((NodeData) nds).getSaw() == false) dfs((NodeData)nds);
            return this.l;
        }

        public void dfs(NodeData node) {
            node.setSaw(true);
            s.push(node);
            boolean isListRoot = true;
            for(edge_data edge : curr_g.getE(node.getKey())) {
                NodeData neighbor = (NodeData) curr_g.getNode(edge.getDest());
                if(neighbor.getSaw() == false) dfs(neighbor);
                if(node.getTag() > neighbor.getTag()){
                    node.setTag(neighbor.getTag());
                    isListRoot = false;
                }
            }
            if (isListRoot) {
                List<node_data> component = new ArrayList<>();
                while(true) {
                    node_data temp_node = this.s.pop();
                    component.add(temp_node);
                    temp_node.setTag(Integer.MAX_VALUE);
                    if(temp_node == node) break;
                }
                this.l.add(component);
            }
        }

        public List<List<node_data>> getLists() { return this.l; }
    }

}

