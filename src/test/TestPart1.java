package test;


import api.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Java Unit Testing for Exercise 1 in Object Oriented Programming Course
 * @author ilya
 */
public class TestPart1 {
   
   private static Random _rnd = null;
   public static int _count = 0;
   
   /** 
    * TEST -> MC: 
    * 1) |V| = 10 -> MC = 10
    * 2) |V| = 10, |E| = 9, -> MC = 19
    * 3) |V| = 10, |E| = 9, -> MC = 19 (Connect the same edges with same weights -> MC remain the same)
    * 4) |V| = 10, |E| = 9, -> MC = 28 (Connect the same edges with different weights)
    * 5) |V| = 6, |E| = 5 -> MC = 36 (Remove 4 nodes -> which leads to remove 5 edges)
    */
   @Test
   public void TEST1() {
      directed_weighted_graph g = new DWGraph_DS();
      /** Check addNode MC */
      geo_location l1 = new GeoLocation(0,0,0);
      for (int i = 0; i < 10 ; i++) {
         node_data t = new NodeData(i, l1, i, " ", 0);
         g.addNode(t);
      }
         assertEquals(g.getMC(), 10);
      
      /** Check Connect MC */
      for(int i=0; i<9; i++)
         g.connect(i, i+1, i+1);
      assertEquals(g.getMC(), 19);
      
      /** Check Connect MC Again - same Weights!!! */
      for(int i=0; i<9; i++)
         g.connect(i, i+1, i+1);
      assertEquals(g.getMC(), 19);
      
      /** Check Connect MC Again - different Weights!!! */
      for(int i=0; i<9; i++)
         g.connect(i, i+1, i+2);
      assertEquals(g.getMC(), 28);
      
      /** Check RemoveNode MC */
      for(int i=6; i<10; i++)
         g.removeNode(i);
      assertEquals(36, g.getMC());
   }
   
   /** 
    * TEST -> NodeSize
    * 1) |V| = 6
    * 2) |V| = 6 -> Remove Nodes which doesn't exist.
    * 3) |V| = 3 -> Remove 3 Nodes (3 Remain)
    */
   @Test
   public void TEST2() {
      directed_weighted_graph g = graph_creator(6, 0, 1);
      assertEquals(6, g.nodeSize());
      
      for(int i=7; i<10; i++)
         g.removeNode(i);
      assertEquals(6, g.nodeSize());
      
      for(int i=3; i<7; i++)
         g.removeNode(i);
      assertEquals(3, g.nodeSize());
   }
   
   /**
    *  TEST -> EdgeSize
    *  1) |V| = 10, |E| = 9
    *  2) |V| = 9, |E| = 7 (Remove node 2 -> 2 edges removed)
    *  3) |V| = 9, |E| = 3 (Remove 4 edges)
    *  4) Get edge 1-2 (= (-1) - Node 2 was removed)
    *  5) Check edge 1-0 equals to edge 0-1
    *  6) |V| = 9, |E| = 3 -> Try to connect Nodes 1-2 (Can't connect - Node 2 doesn't exist. |E| remain 3)  
    *  7) Add node 2 -> Add edge 1-2 -> |V| = 10, |E| = 4 
    */
   @Test
   public void TEST3() {
      directed_weighted_graph g = graph_creator(10, 0, 1);
      
      for(int i=0; i<9; i++)
         g.connect(i, i+1, i+1);
      assertEquals(9, g.edgeSize());
      
      g.removeNode(2);
      assertEquals(7, g.edgeSize());
      
      for(int i=5; i<9; i++) 
         g.removeEdge(i, i+1);
      assertEquals(3, g.edgeSize());
      
      edge_data w12 = g.getEdge(1, 2);
      assertEquals(w12, null);
      
//      edge_data w01 = g.getEdge(0, 1);
//      edge_data w10 = g.getEdge(1, 0);
//      assertEquals(w01, w10);
      
      g.connect(1, 2, 1); // Node 2 doesn't exist! return -1! 
      assertEquals(3, g.edgeSize());
      node_data t =new NodeData();
      g.addNode(t);
      g.connect(1, 2, 1);
      //assertEquals(g.edgeSize(), 4);
   }
   
   /**
    *  TEST -> getV 
    *  1) |V| = 1000, |E| = 0 -> Check all the nodes (Not Null)
    */
   @Test
   public void TEST4() {
      directed_weighted_graph g = graph_creator(1000, 0, 1);
      Collection<node_data> v = g.getV();
        Iterator<node_data> iter = v.iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            assertNotNull(n);
        }
   }
   
   /** 
    * TEST -> hasEdge 
    * 1) Fully Connected Graph (All True)
    * 2) Graph with no edges (All False)
    * */

   //@Test
// public void TEST5() {
//    int v = 10;
//    directed_weighted_graph fcg = graph_creator(v, 0, 1); // Fully Connected Graph
//    for(int i=0; i<v; i++) {
//       for(int j=i+1; j<v; j++) {
//          fcg.connect(i, j, j+1);
//       }
//    }
//    directed_weighted_graph eg = graph_creator(v, 0, 1); // Graph with no Edges
//    for(int i=0; i<v; i++) {
//       for(int j=i+1; j<v; j++) {
//          assertTrue(fcg.hasEdge(i, j));
//          assertFalse(eg.hasEdge(i, j));
//       }
//    }
// }
//
   /**
    * TEST -> Connect
    * 1) No nodes -> try to connect 1-2 || *** FAIL ***
    * 2) Add node 1 -> |V| = 1 -> try to connect 1-2 -> -1
    * 3) |V| = 1 -> try to connect 1-1 -> -1
    * 4) |V| = 2 -> connect 1-2 -> weight = 1
    * 5) |V| = 2, |E| = 1 (edge 1-2) -> connect 1-2 again with weight = 4 -> Update the weight to 4
    */
   @Test
   public void TEST6() {
      directed_weighted_graph g = new DWGraph_DS();
      g.connect(1, 2, 1);
      assertEquals(g.getEdge(1, 2), null); //Null Pointer Exception - FIXED! Added (nodeContains)
      node_data t = new NodeData();
      g.addNode(t);

      g.connect(1, 2, 1);
      assertEquals(g.getEdge(1, 2), null);
      
      g.connect(1, 1, 1);
      assertEquals(g.getEdge(1, 1), null);
      node_data s = new NodeData();
      g.addNode(s);
      g.connect(1, 2, 1);
      assertEquals(g.getEdge(1, 2), 1); 
      
      g.connect(1, 2, 4); 
      assertEquals(g.getEdge(1, 2), 4); // Updated the weight in connect -> What about MC? 
   }
   
   /**
    * TEST -> RemoveNode
    * 1) |V| = 10, |E| = 9
    * 2) |V| = 9, |E| = 8 -> Remove node 9 -> edge 0-9 removed as well.
    * 3) |V| = 8, |E| = 0 -> Remove node 0 -> Removes all the edges in the graph (all connected to node 0)
    * 4) |V| = 8, |E| = 0 -> Try to remove node 9 again
    */
   @Test
   public void TEST7() {
      directed_weighted_graph g = graph_creator(10, 0, 1);
      for(int i=1; i<10; i++) 
         g.connect(0, i, i+1);
      assertEquals(g.nodeSize(), 10);
      assertEquals(g.edgeSize(), 9);
      
      g.removeNode(9);
      assertEquals(g.nodeSize(), 9);
      assertEquals(g.edgeSize(), 8);
      
      g.removeNode(0);
      assertEquals(g.nodeSize(), 8);
      assertEquals(g.edgeSize(), 0);
      
      g.removeNode(9);
      assertEquals(g.nodeSize(), 8);
   }
   
   /**
    * TEST -> RemoveEdge
    * 1) |V| = 10, |E| = 9
    * 2) |V| = 10, |E| = 8 -> Remove edge 0-1
    * 3) |V| = 10, |E| = 8 -> Try to remove edge 0-0 -> remain 8
    * 4) |V| = 10, |E| = 8 -> Try to remove edge 0-1 again -> remain 8
    * 5) |V| = 10, |E| = 8 -> Try to remove edge 1-1 (doesn't exist) -> remain 8
    * 6) |V| = 10, |E| = 8 -> Remove edge 2-0 (equals to remove edge 0-2) -> remain 7
    */
   @Test
   public void TEST8() {
      directed_weighted_graph g = graph_creator(10, 0, 1);
      for(int i=1; i<10; i++) 
         g.connect(0, i, i+1);
      assertEquals(g.edgeSize(), 9);
      
      g.removeEdge(0, 1);
      assertEquals(g.edgeSize(), 8);
      
      g.removeEdge(0, 0);
      assertEquals(g.edgeSize(), 8);
      
      g.removeEdge(0, 1);
      assertEquals(g.edgeSize(), 8);
      
      g.removeEdge(1, 1);
      assertEquals(g.edgeSize(), 8);
      
      g.removeEdge(2, 0);
      assertEquals(g.edgeSize(), 7);
   }
   
   /**
    * TEST -> isConnected
    * 1) |V| = 10, |E| = 0 -> False
    * 2) |V| = 10, |E| = 8 -> False (Node 9 is not connected)!
    * 3) |V| = 10, |E| = 9 -> True
    * 4) |V| = 10, |E| = 8 -> False (Edge 5-6 removed!)
    * 5) |V| = 10, |E| = 9 -> True (Edge 0-9 added!)
    */
   @Test
   public void TEST9() {
      directed_weighted_graph g = new DWGraph_DS();
      dw_graph_algorithms ag0 = new DWGraph_Algo();
      ag0.init(g);

      geo_location l1 = new GeoLocation(0,0,0);
      for (int i = 0; i < 10 ; i++) {
         node_data t = new NodeData( i, l1,i," ",0);
         g.addNode(t);
      }

      assertFalse(ag0.isConnected());
      
      for(int i=0; i<8; i++) 
         g.connect(i, i+1, i+1);
      assertFalse(ag0.isConnected());
      
      g.connect(8, 9, 9);
      g.connect(0,9,0);
      assertTrue(ag0.isConnected());
      
      g.removeEdge(5, 6);
      assertFalse(ag0.isConnected());

      g.connect(0, 9, 1);
      assertTrue(ag0.isConnected()); 
   }


   
   /**
    * TEST -> equals
    * 1) Create 2 graphs g1 and g2 using graph_creator -> True
    * 2) Remove from g2 node1 -> False
    * 3) Remove from g1 node2 -> False 
    * 4) Return the nodes -> True
    * 5) Add 1 edge to each graph -> different nodes and weights -> False
    * 6) Add 1 edge to each graph -> same nodes but different weights -> False
    * 7) Update the both graph edge weights -> True
    */
// @Test
// public void TEST10() {
//     directed_weighted_graph g1 = graph_creator(100, 0, 1);
//     directed_weighted_graph g2 = graph_creator(100, 0, 1);
//
//     assertTrue(equals(g1, g2));
//     g2.removeNode(1);
//     assertFalse(equals(g1, g2));
//
//     g1.removeNode(2);
//     assertFalse(equals(g1, g2)); // Error? Expected False but was True -> FIXED! Added(checkSimilarity)
//     node_data t = new NodeData();
//    node_data s = new NodeData();
//     g2.addNode(t);
//     g1.addNode(s);
//     assertTrue(equals(g1, g2)); // Return the nodes -> Check if OK.
//
//     g1.connect(1, 2, 1);
//     g2.connect(3, 4, 1);
//     assertFalse(equals(g1, g2));
//
//     g1.connect(4, 3, 2);
//     g2.connect(2, 1, 2);
//     assertFalse(equals(g1, g2)); // Same Edges - Different Weights -> False
//
//     g1.connect(1, 2, 2);
//     g2.connect(3, 4, 2); // Update the weights -> Check if ok.
//     assertTrue(equals(g1, g2));
// }
//
   /**
    * TEST -> Load & Save
    * 1) Check the save function is good and returns true!
    * 2) Create 2 different graphs -> Check they are different! (Save the first graph)
    * 3) Once the graphs are verified as different -> Insert the second graph into the init function -> Now check that the graph was changed successfully.
    * 4) Load the file check if good and returns true
    * 5) generate same graph as the first one -> Now check the load is fine.
    */
// @Test
// public void TEST11() {
//    String myFile = "g1_test";
//    directed_weighted_graph g1 = graph_creator(100, 0, 1);
//    dw_graph_algorithms ag1 = new DWGraph_Algo();
//        ag1.init(g1);
//      //  assertTrue(ag1.save(myFile));
//        directed_weighted_graph g2 = graph_creator(10, 0, 1);
//        assertFalse(equals(g2, ag1.getGraph())); // Check that the graphs are different!
//        ag1.init(g2);
//        assertTrue(equals(g2, ag1.getGraph())); // Change the graph -> check if ok.
//        directed_weighted_graph g3 = graph_creator(100, 0, 1);
//        assertTrue(ag1.load(myFile));
//        assertTrue(equals(g3, ag1.getGraph()));// Load the old file -> Now check if equals
//        delete(myFile);
// }
//
   /**
    * TEST -> ShortestPath & ShortestPathDist
    * Create a connected graph (line) -> Edge 1-2 : Weight 2 | Edge 2-3 : Weight 3 ...
    * 1) Check that all the paths => not null!
    * 2) The shortest path is the edge connected between two nearby nodes
    * 3) Check the path from each node to the starting node -> The distance is the sum of the weights (edges)
    * 4) Add node11 (NOT connected) -> For each node in the graph != node11 -> ShortestPath = null
    * 5) Extends test 4 -> if ShortestPath = null then ShortestPathDist = -1
    * 6) Check that the distance from node 0-6 != distance from 0 to 8
    * 7) Check node 0-8 path (0->1->2->3->4->5->6->7->8)
    * 8) Connect node 0-11 and node 10-11 -> Distance from node 0-8 is now equals to distance node 0-6 = 21
    * 9) Check node 0-8 NEW path (0->11->10->9->8)
    * 
    * If there's no path -> Null Pointer Exception (Instead of return null) - FIXED!
    */
   @Test
   public void TEST12() {
      directed_weighted_graph g1 = graph_creator(11, 0, 1);
      dw_graph_algorithms ag1 = new DWGraph_Algo();
        ag1.init(g1);
      for(int i=0; i<10; i++) 
         g1.connect(i, i+1, i+1);
      
        for(int i=0; i<g1.nodeSize()-1; i++) {
           assertNotNull(ag1.shortestPath(i, i+1));
           assertEquals(ag1.shortestPathDist(i, i+1), i+1);
           assertEquals(ag1.shortestPathDist(0, i), _count+=i);
        }
        node_data t = new NodeData() ;
        g1.addNode(t);
       for(int i=0; i<10; i++) {
           assertNull(ag1.shortestPath(i, 11)); // Null Pointer Exception -> FIXED! Added Condition (if)
           assertEquals(ag1.shortestPathDist(i,  11), -1);
        }
     
       assertEquals(ag1.shortestPathDist(0, 6), 21); 
       assertEquals(ag1.shortestPathDist(0, 8), 36);
       
       List<Integer> myList = new ArrayList<Integer>();
       for(int i=0; i<9; i++)
          myList.add(i);
       assertTrue(checkPath(ag1.shortestPath(0, 8), myList));
       
       g1.connect(0, 11, 1);
       g1.connect(10, 11, 1);
       
       assertEquals(ag1.shortestPathDist(0, 8), ag1.shortestPathDist(0, 6), 21); // Distance: 21 
       
       List<Integer> myList2 = new ArrayList<Integer>();
       myList2.add(0);
       for(int i=11; i>7; i--)
          myList2.add(i);
       assertTrue(checkPath(ag1.shortestPath(0, 8), myList2));
   }
   
   private void delete(String file) {
      File myFile = new File(file);
      myFile.delete();
   }
   
   /**
    * private function to check ShortestPath
    * @param list - the returned list from ShortestPath
    * @param index - list of integers (Correct path)
    * @return boolean -> True/False
    */
   private static boolean checkPath(List<node_data> list, List<Integer> index) {
      if(list.size() == index.size()) 
         for(int i=0; i<list.size(); i++) 
            if(list.get(i).getKey() != index.get(i)) 
               return false;
      return true;
   }
   
   public static directed_weighted_graph graph_creator(int v_size, int e_size, int seed) {
        directed_weighted_graph g = new DWGraph_DS();
        _rnd = new Random(seed);
      geo_location l1 = new GeoLocation(0,0,0);
      for (int i = 0; i < v_size ; i++) {
         node_data t = new NodeData( i, l1,i," ",0);
         g.addNode(t);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            double w = _rnd.nextDouble();
            g.connect(i,j, w);
        }
        return g;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
   
    /**
     * private function to check similarity for 2 arrays of integers. If they are equals return True. Otherwise, False.
     * @param nodes1
     * @param nodes2
     * @return
     */
    private boolean checkSimilarity(int[] nodes1, int[] nodes2) {
       for(int i=0; i<nodes1.length; i++)
          if(nodes1[i] != nodes2[i])
             return false;
       return true;
    }
    
//    public boolean equals(directed_weighted_graph g0, directed_weighted_graph g1) {
//        boolean ans = false;
//        if(g0==null && g1 ==null) return true;
//        if(g0.edgeSize() == g1.edgeSize() && g0.nodeSize() == g1.nodeSize()) {
//            int[] nodes1 = nodes(g0);
//            int[] nodes2 = nodes(g1);
//                if(!checkSimilarity(nodes1, nodes2))
//                 return false;
//                ans = true;
//                for(int i1=0;ans && i1<nodes1.length;i1++) {
//                 Collection<edge_data> ni1 = g0.getE(nodes1[i1]);
//                    Collection<edge_data> ni2 = g1.getE(nodes2[i1]);
//                    Iterator<edge_data> it1 = ni1.iterator();
//                    Iterator<edge_data> it2 = ni2.iterator();
//                    while(it1.hasNext() && it2.hasNext()) {
//                     edge_data n1 = it1.next();
//                        edge_data n2 = it2.next();
//                        edge_data w1 = g0.getEdge(nodes1[i1], n1.getKey());
//                        edge_data w2 = g1.getEdge(nodes2[i1], n2.getKey());
//                        if(w1!=w2) {ans=false;}
//                    }
//                    if(it1.hasNext()|| it2.hasNext()) {ans = false;}
//                }
//            }
//        return ans;
//    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an  Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(directed_weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_data> V = g.getV();
        node_data[] nodes = new node_data[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }
}