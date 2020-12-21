package test;//package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.*;
import api.node_data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Graph_AlgoTest {

    @Test
    void saveload() throws IOException {

        directed_weighted_graph graph1 = new DWGraph_DS();

        graph1.addNode(new NodeData(0));
        graph1.addNode(new NodeData(1));
        graph1.addNode(new NodeData(2));

        graph1.connect(0,1,3000);
        graph1.connect(0,2,30);

        graph1.getNode(0).setLocation(new GeoLocation(1245,12165.5,454.6));
        graph1.getNode(1).setLocation(new GeoLocation(14.67,1.2,6.48));
        graph1.getNode(2).setLocation(new GeoLocation(45.4,777,1.023));


        dw_graph_algorithms first =  new DWGraph_Algo();
        first.init(graph1);
        first.save("test.json");

        ////////

        directed_weighted_graph graph2 = new DWGraph_DS();
        dw_graph_algorithms second = new DWGraph_Algo(graph2);

        second.load("test.json");


        // System.out.println("\n\nfirst.getGraph = \n" +first.getGraph().toString());
        // System.out.println("\nsecond.getGraph = \n"+second.getGraph().toString());

        assertEquals(first.getGraph().toString(), second.getGraph().toString());
    }

	@Test
	void testSave() {
		//saves file
        geo_location l1 = new GeoLocation(0,0,0);
        geo_location l2 = new GeoLocation(0,0,0);

        node_data t1 = new NodeData( 1, l1,1," ",0);
        node_data t2 = new NodeData( 2, l2,1," ",0);

		directed_weighted_graph g = new DWGraph_DS();

		g.addNode(t1);
		g.addNode(t2);

		g.connect(t1.getKey(), t2.getKey(), 5);

		dw_graph_algorithms test = new DWGraph_Algo();

		test.init(g);

		try {
			test.save("test_graph");
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

	@Test
	void testInitGraph() {


        geo_location l1 = new GeoLocation(0,0,0);
        directed_weighted_graph g = new DWGraph_DS();
        for (int i = 1; i <= 1000000 ; i++) {
            node_data t = new NodeData( i, l1,i," ",0);
            g.addNode(t);

        }

		for (int i = 1; i <= 1000000-10 ; i++) {
			g.connect(i, i+1, i*0.5);
			g.connect(i, i+2, i*0.3);
			g.connect(i, i+3, 1);
			g.connect(i, i+4, i*10);
			g.connect(i, i+5, i*3);
			g.connect(i, i+6, i*0.8);
			g.connect(i, i+7, i*0.5);
			g.connect(i, i+8, i*7);
			g.connect(i, i+9, i*3);
			g.connect(i, i+10, i*2.5);
		}

		dw_graph_algorithms test = new DWGraph_Algo();

		try {
			test.init(g);
		} catch (Exception e) {
			// TODO: handle exception
			fail("cant init graph");
		}
	}

	@Test
	void testCopy() {

        geo_location l1 = new GeoLocation(0,0,0);
        geo_location l2 = new GeoLocation(0,0,0);
        geo_location l3 = new GeoLocation(0,0,0);
        geo_location l4 = new GeoLocation(0,0,0);

        directed_weighted_graph g = new DWGraph_DS();

        node_data t1 = new NodeData( 1, l1,1," ",0);
        node_data t2 = new NodeData( 2, l2,1," ",0);
        node_data t3 = new NodeData( 3, l3,1," ",0);
        node_data t4 = new NodeData( 4, l3,1," ",0);


        g.addNode(t1);
        g.addNode(t2);
        g.addNode(t3);
        g.addNode(t4);

		g.connect(1, 2, 3);
		g.connect(2, 3, 3);
		g.connect(3, 4, 3);
		g.connect(4, 1, 3);

		dw_graph_algorithms t = new DWGraph_Algo();
		t.init(g);

		directed_weighted_graph other = t.copy();
		assertEquals(g.edgeSize(), other.edgeSize());

	}

//	@Test
//	void testInitString() {
//		//loads a graph
//		//from file
//		dw_graph_algorithms test = new DWGraph_Algo();
//
//		try {
//			test.init("test_graph");
//		} catch (Exception e) {
//			fail("Not yet implemented");
//		}
//
//	}

	@Test
	void testIsConnected() {


        geo_location l1 = new GeoLocation(0,0,0);
        geo_location l2 = new GeoLocation(0,0,0);
        geo_location l3 = new GeoLocation(0,0,0);
        geo_location l4 = new GeoLocation(0,0,0);
        geo_location l5 = new GeoLocation(0,0,0);
        geo_location l6 = new GeoLocation(0,0,0);
        geo_location l7 = new GeoLocation(0,0,0);

        directed_weighted_graph s = new DWGraph_DS();

        node_data t1 = new NodeData( 1, l1,1," ",0);
        node_data t2 = new NodeData( 2, l2,1," ",0);
        node_data t3 = new NodeData( 3, l3,1," ",0);
        node_data t4 = new NodeData( 4, l3,1," ",0);
        node_data t5 = new NodeData( 5, l5,1," ",0);
        node_data t6 = new NodeData( 6, l6,1," ",0);
        node_data t7 = new NodeData( 7, l7,1," ",0);

        s.addNode(t1);
        s.addNode(t2);
        s.addNode(t3);
        s.addNode(t4);
        s.addNode(t5);
        s.addNode(t6);
        s.addNode(t7);
        s.connect(1, 2, 0);
        s.connect(1, 3, 0);
        s.connect(2, 4, 0);
        s.connect(2, 5, 0);
        s.connect(3, 2, 0);
        s.connect(4, 6, 0);
        s.connect(4, 5, 0);
        s.connect(5, 6, 0);
        s.connect(5, 7, 0);
        s.connect(6, 7, 0);
        s.connect(7, 1, 0);

		DWGraph_Algo e = new DWGraph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());

        directed_weighted_graph g2 = new DWGraph_DS();
        node_data n1 = new NodeData( 1, l1,1," ",0);
        node_data n2 = new NodeData( 2, l2,1," ",0);
        node_data n3 = new NodeData( 3, l3,1," ",0);
        node_data n4 = new NodeData( 4, l3,1," ",0);
        node_data n5 = new NodeData( 5, l4,1," ",0);

        s.addNode(n1);
        s.addNode(n2);
        s.addNode(n3);
        s.addNode(n4);
        s.addNode(n5);
        g2.connect(1, 5, 30);
        g2.connect(5, 1, 30);
        g2.connect(2, 5, 30);
        g2.connect(5, 2, 30);
        g2.connect(3, 5, 30);
        g2.connect(5, 3, 30);
        g2.connect(4, 5, 30);
        g2.connect(5, 4, 30);
        DWGraph_Algo ga2 = new DWGraph_Algo();
        ga2.init(g2);
		assertEquals(true, ga2.isConnected());


        directed_weighted_graph g3 = new DWGraph_DS();
        node_data a1 = new NodeData( 1, l1,1," ",0);
        node_data a2 = new NodeData( 2, l2,1," ",0);
        node_data a3 = new NodeData( 3, l3,1," ",0);
        node_data a4 = new NodeData( 4, l4,1," ",0);
        node_data a5 = new NodeData( 5, l5,1," ",0);

        g3.addNode(a1);
        g3.addNode(a2);
        g3.addNode(a3);
        g3.addNode(a4);
        g3.addNode(a5);
        DWGraph_Algo ga3 = new DWGraph_Algo();
        ga3.init(g3);
        g3.connect(1, 2, 30);
        g3.connect(2, 3, 30);
        g3.connect(3, 4, 30);
        g3.connect(4, 5, 30);

		assertEquals(false, ga3.isConnected());

	}

	@Test
	void testShortestPathDist() {

        geo_location l1 = new GeoLocation(0,0,0);
        geo_location l2 = new GeoLocation(0,0,0);
        geo_location l3 = new GeoLocation(0,0,0);
        geo_location l4 = new GeoLocation(0,0,0);
        geo_location l5 = new GeoLocation(0,0,0);
        geo_location l6 = new GeoLocation(0,0,0);
        geo_location l7 = new GeoLocation(0,0,0);

        directed_weighted_graph s = new DWGraph_DS();

        node_data t1 = new NodeData( 1, l1,1," ",0);
        node_data t2 = new NodeData( 2, l2,1," ",0);
        node_data t3 = new NodeData( 3, l3,1," ",0);
        node_data t4 = new NodeData( 4, l4,1," ",0);
        node_data t5 = new NodeData( 5, l5,1," ",0);
        node_data t6 = new NodeData( 6, l6,1," ",0);
        node_data t7 = new NodeData( 7, l7,1," ",0);

		directed_weighted_graph g = new DWGraph_DS();


		g.addNode(t1);
		g.addNode(t2);
		g.addNode(t3);
		g.addNode(t4);
		g.addNode(t5);
		g.addNode(t6);
		g.addNode(t7);
		

		g.connect(t1.getKey(), t2.getKey(), 2);
		g.connect(t1.getKey(), t3.getKey(), 3);
		g.connect(t2.getKey(), t4.getKey(), 11);
		g.connect(t3.getKey(), t4.getKey(), 7);
		g.connect(t1.getKey(), t5.getKey(), 2);
		g.connect(t4.getKey(), t5.getKey(), 1);
		g.connect(t2.getKey(), t6.getKey(), 9);
		g.connect(t1.getKey(), t6.getKey(), 11);
		//		g.connect(t6.getKey(), t9.getKey(), 5);

		dw_graph_algorithms test = new DWGraph_Algo();
		test.init(g);
		assertEquals(2.0,test.shortestPathDist(1,2));
		//	System.out.println(test.shortestPathDist(1,4));
		assertEquals(10.0,test.shortestPathDist(1,4));
		assertEquals(2.0,test.shortestPathDist(1,5));
		assertEquals(11.0,test.shortestPathDist(1,6));
		//	System.out.println(test.shortestPathDist(8,7));
		//assertEquals(2.0,test.shortestPathDist(1,1));
		//		System.out.println(test.shortestPathDist(1,2));
		//		dw_graph_algorithms test = new DWGraph_Algo();
		//		test.init(g);
		//		assertEquals(2.0,test.shortestPathDist(1,2));
		//		System.out.println(test.shortestPathDist(1,4));
		//		assertEquals(10.0,test.shortestPathDist(1,4));
	}

	@Test
	void testShortestPath() {
        geo_location l1 = new GeoLocation(0,0,0);
        geo_location l2 = new GeoLocation(0,0,0);
        geo_location l3 = new GeoLocation(0,0,0);
        geo_location l4 = new GeoLocation(0,0,0);
        geo_location l5 = new GeoLocation(0,0,0);
        geo_location l6 = new GeoLocation(0,0,0);
        geo_location l7 = new GeoLocation(0,0,0);
        geo_location l8 = new GeoLocation(0,0,0);
        geo_location l9 = new GeoLocation(0,0,0);
        geo_location l10 = new GeoLocation(0,0,0);

        //directed_weighted_graph s = new DWGraph_DS();

        node_data t1 = new NodeData( 1, l1,1," ",0);
        node_data t2 = new NodeData( 2, l2,1," ",0);
        node_data t3 = new NodeData( 3, l3,1," ",0);
        node_data t4 = new NodeData( 4, l4,1," ",0);
        node_data t5 = new NodeData( 5, l5,1," ",0);
        node_data t6 = new NodeData( 6, l6,1," ",0);
        node_data t7 = new NodeData( 7, l7,1," ",0);
        node_data t8 = new NodeData( 8, l8,1," ",0);
        node_data t9 = new NodeData( 9, l9,1," ",0);
        node_data t10 = new NodeData(10, l10,1," ",0);
		directed_weighted_graph g = new DWGraph_DS();


		g.addNode(t1);
		g.addNode(t2);
		g.addNode(t3);
		g.addNode(t4);
		g.addNode(t5);
		g.addNode(t6);
		g.addNode(t7);
		g.addNode(t8);
		g.addNode(t9);
		g.addNode(t10);

		g.connect(t1.getKey(), t2.getKey(), 2);
		g.connect(t1.getKey(), t3.getKey(), 3);
		g.connect(t2.getKey(), t4.getKey(), 11);
		g.connect(t3.getKey(), t4.getKey(), 7);
		g.connect(t1.getKey(), t5.getKey(), 2);
		g.connect(t4.getKey(), t5.getKey(), 1);
		g.connect(t2.getKey(), t6.getKey(), 9);
		g.connect(t1.getKey(), t6.getKey(), 11);
		//			g.connect(t6.getKey(), t9.getKey(), 5);

		dw_graph_algorithms test = new DWGraph_Algo();
		test.init(g);
		List<node_data> arr = (test.shortestPath(1, 4));
		String s = "";
		for (int i = arr.size()-1 ; i >= 0; i--) {
			if (i==0) {
				s=s+arr.get(i).getKey()+"";
				//System.out.print(arr.get(i).getKey());
			}
			else {
				s=s+arr.get(i).getKey()+"->";
				//	System.out.print(arr.get(i).getKey()+"->");
			}
		}
		//System.out.println("\n"+s);
		assertEquals("1->3->4",s);

		List<node_data> arr2 = (test.shortestPath(1, 6));
		String s2 = "";
		for (int i = arr2.size()-1 ; i >= 0; i--) {
			if (i==0) {
				s2=s2+arr2.get(i).getKey()+"";
				//			System.out.print(arr.get(i).getKey());
			}
			else {
				s2=s2+arr2.get(i).getKey()+"->";
				//			System.out.print(arr.get(i).getKey()+"->");
			}
		}
		//	System.out.println("\n"+s2);
		assertEquals("1->2->6",s2);
		//	assertEquals(11.0,test.shortestPathDist(1,6));
		//	System.out.println(test.shortestPathDist(8,7));
	}


}