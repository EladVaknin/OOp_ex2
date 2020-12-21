//package test;
//import api.*;
//import gameClient.CL_Agent;
//import gameClient.CL_Pokemon;
//import gameClient.util.Point3D;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class AgentTest {
//    private directed_weighted_graph graph;
//    @Test
//    public void sdt(){
//        graph = new DWGraph_DS();
//        geo_location g1 = new GeoLocation(3,5,0);
//        geo_location g2 = new GeoLocation(1,2,0);
//        geo_location g3 = new GeoLocation(6,2,0);
//        node_data n1 = new NodeData();
//        node_data n2 = new NodeData();
//        node_data n3 = new NodeData();
//        n1.setLocation(g1);
//        n2.setLocation(g2);
//        n3.setLocation(g3);
//        graph.addNode(n1);
//        graph.addNode(n2);
//        graph.addNode(n3);
//        graph.connect(n1.getKey(),n2.getKey(),2.3);
//        graph.connect(n2.getKey(),n3.getKey(),6.2);
//        graph.connect(n3.getKey(),n1.getKey(),4.3);
//        CL_Agent agent = new CL_Agent(graph, n1.getKey());
//        Point3D point = new Point3D(4,2,0);
//        edge_data e = new EdgeData(1, 2, 6);
//        CL_Pokemon p1 = new CL_Pokemon(point,1,5,1.0,e);
//        p1.set_edge(graph.getEdge(1,2));
//        agent.set_curr_edge(graph.getEdge(n1.getKey(),n2.getKey()));
//        agent.set_curr_fruit(p1);
//        agent.setSpeed(1);
//        agent.set_SDT(10);
//        double de = g1.distance(g2);
//        double dist = point.distance(agent.getLocation());
//        double norm = (dist/de);
//        double dt = (norm*2.3)/agent.getSpeed();
//        long ans = (long) (10*dt);
//        Assertions.assertEquals(ans,agent.get_sg_dt());
//        agent.setSpeed(20);
//        agent.set_SDT(10);
//        ans = agent.get_sg_dt();
//        Assertions.assertEquals(true,ans==1);
//        agent.setSpeed(30);
//        agent.set_SDT(10);
//        ans = agent.get_sg_dt();
//        Assertions.assertEquals(true,ans==0);
//    }
//}