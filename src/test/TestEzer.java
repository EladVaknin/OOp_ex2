package test;


import api.*;

public class TestEzer {

    public static void main(String[] args) {
        //    HashMap<Integer,node_info> test =  new HashMap<Integer,node_info> ();
//     NodeInfo a = new NodeInfo ();
        //   test.put(6, a);
        //    NodeInfo b  = new NodeInfo ();
        // a.addNi(b);
        //    System.out.println(a.getNi());
//     WGraph_DS afik = new WGraph_DS ();
////       NodeInfo c = new NodeInfo ();
////       NodeInfo d = new NodeInfo ();
////       NodeInfo e = new NodeInfo ();
//   //   NodeInfo f = new NodeInfo ();
//     afik.addNode(0);
//     afik.addNode(1);
//     afik.addNode(2);
//     afik.addNode(3);
//     afik.addNode(4);
//     System.out.println("num of nodes ="+ afik.nodeSize());
//  //   afik.removeNode(4);
//     System.out.println("num of nodes agter ="+ afik.nodeSize());
//     afik.connect(0, 1, 5);
//     afik.connect(0, 2, 4);
//     afik.connect(1, 2, 5);
//     System.out.println("num of edges ="+afik.edgeSize());
//     afik.removeEdge(0, 1);
//     System.out.println("num of edges after ="+afik.edgeSize());
//


//System.out.println("number " + afik.nodeSize());
//System.out.println(afik.edgeSize());
//System.out.println(afik.getMC());
        //afik.connect(1, 2);
        // System.out.println(afik.edgeSize());
        //   afik.connect(4, 3);
//     System.out.println( "edge" + afik.edgeSize());
//     System.out.println(afik.getMC());
//System.out.println(d.getKey());
//afik.connect(0, 1);
//afik.connect(0, 2);
//afik.connect(2, 3);
//afik.connect(1, 3);
//System.out.println( "edgebefor" + afik.edgeSize());
////afik.removeNode(0);
//afik.removeEdge(0, 2);
//System.out.println("number " + afik.nodeSize());
//System.out.println( "edgeafter" + afik.edgeSize());
////

//     WGraph_DS graph = new WGraph_DS();
//        WGraph_Algo algo = null;
//        algo.init(graph);
//        for (int i = 0; i <= 5; i++) {
//            graph.addNode(i);
//        }
//        graph.connect(0, 1, 4);
//        System.out.println(algo.isConnected());
        directed_weighted_graph graph = new DWGraph_DS();
        dw_graph_algorithms algo = new DWGraph_Algo();
        algo.init(graph);
        node_data a = new NodeData();
        node_data b = new NodeData();
        node_data c = new NodeData();
        node_data d = new NodeData();
        node_data e = new NodeData();
        //     NodeData e = new NodeData();
        //       NodeData f = new NodeData();
//        NodeData g = new NodeData();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
      //  graph.addNode(e);


        graph.connect(0, 1, 4);
        graph.connect(1, 2, 3);
        graph.connect(2, 3, 2);
        //graph.connect(3, 0, 1);
//        graph.connect(0, 3, 1);
//        graph.connect(3,2,6);
//        graph.connect(0, 2, 4);
//        graph.connect(0, 3, 4);
//        graph.connect(3, 1, 4);
//        graph.connect(3, 2, 4);
//        graph.connect(0, 2, 4);

        ///graph.connect(4, 0, 1);

//
//        graph.connect(0, 1, 3);
//        graph.connect(0, 2, 3);
//        graph.connect(1, 3, 3);
//        graph.connect(3, 4, 3);
//        graph.connect(4, 5, 3);
//        graph.connect(5, 3, 3);
        System.out.println("size   " + graph.nodeSize());
        System.out.println("num fo edges   " + graph.edgeSize());
        //  algo.init(graph);
        System.out.println(algo.isConnected());
        System.out.println("5");
        //System.out.printf(algo.shortestPathDist(0,3));
        System.out.println(algo.shortestPathDist(0,1));
        System.out.println("short");
        System.out.println(algo.isConnected());


//     weighted_graph graph = new WGraph_DS();
//     weighted_graph_algorithms algo;
////NodeData.countIndex = 0;
//
//for (int i = 0; i <= 5; i++) {
//  graph.addNode(i);
//}
////System.out.println("create a graph");
////for (node_info n : graph.getV()){
////  System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
////}
//graph.connect(0,2,4);
//graph.connect(0,1,5);
//graph.connect(1,3,7);
//graph.connect(2,3,3);
//graph.connect(3,4,2);
//graph.connect(4,5,3);
//graph.connect(5,0,4);

//
////
////System.out.println("after connent the nodes in the graph");
////for (node_data n : graph.getV()){
////  System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
////}
//////System.out.println(graph.hasEdge(0, 2));
////
//algo = new WGraph_Algo ();
//algo.init(graph);
////System.out.println( "Shortest way 0 -> 2 should be 1 =  " + "the answer is : " + algo.shortestPathDist(0,4));
////System.err.println("MTV");
//System.out.println(algo.isConnected());
////algo.isConnected();
//System.err.println("hello");
//System.out.println((algo.shortestPath(0, 4)));


//System.out.println( "Shortest way 0 -> 3 should be 3 =  " + "the answer is : " +algo.shortestPathDist(0,3));
//System.out.println( "Shortest way 9 -> 6 should be 4 =  " + "the answer is : " +algo.shortestPathDist(9,6));
//System.out.println( "Shortest way 1 -> 0 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,0));
//System.out.println( "Shortest way 0 -> 0 should be 0 =  " + "the answer is : " +algo.shortestPathDist(0,0));
//System.out.println( "Shortest way 4 -> 6 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,6));
//System.out.println( "Shortest way 4 -> 7 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,7));
//System.out.println( "Shortest way 8 -> 1 should be 2 =  " + "the answer is : " +algo.shortestPathDist(8,1));
//System.out.println( "Shortest way 1 -> 5 should be 2 =  " + "the answer is : " +algo.shortestPathDist(1,5));
//System.out.println( "Shortest way 1 -> 20 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,20));

    }
}
//if((node1 == node2)&&(!hasEdge(node1,node2)))return ;
//if((mapNodes.get(node1) ==null) ||mapNodes.get(node2) ==null) return ;
//if (hasEdge (node1, node2)) {
// mapEdges.get(node1).setWeight(node2, w);
// mapEdges.get(node2).setWeight(node1, w);
// counterMC ++ ;
//}
//if (!mapNodes.containsKey(node1)&& !mapNodes.containsKey(node2)) return;
//else {
// mapEdges.get(node1).connectEdge(node2,w);
// mapEdges.get(node2).connectEdge(node1,w);
// counterMC ++ ;
// counterEdge ++;

