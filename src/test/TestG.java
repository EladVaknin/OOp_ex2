package test;

import api.*;

public class TestG {
    public class GUI_Test {

        public void main(String[] args) {
            //Window window = new Window();
            //window.setVisible(true);

            geo_location l1 = new GeoLocation(0, 0, 0);
            geo_location l2 = new GeoLocation(0, 0, 0);
            geo_location l3 = new GeoLocation(0, 0, 0);
            geo_location l4 = new GeoLocation(0, 0, 0);
            geo_location l5 = new GeoLocation(0, 0, 0);
            geo_location l6 = new GeoLocation(0, 0, 0);
            geo_location l7 = new GeoLocation(0, 0, 0);

            directed_weighted_graph s = new DWGraph_DS();

            node_data t1 = new NodeData(1, l1, 1, " ", 0);
            node_data t2 = new NodeData(2, l2, 1, " ", 0);
            node_data t3 = new NodeData(3, l3, 1, " ", 0);
            node_data t4 = new NodeData(4, l4, 1, " ", 0);
            node_data t5 = new NodeData(5, l5, 1, " ", 0);
            node_data t6 = new NodeData(6, l6, 1, " ", 0);
            node_data t7 = new NodeData(7, l7, 1, " ", 0);

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


            g.connect(t1.getKey(), t2.getKey(), 6);
            g.connect(t2.getKey(), t3.getKey(), 10);
            g.connect(t3.getKey(), t4.getKey(), 9);
            g.connect(t4.getKey(), t5.getKey(), 3);
            g.connect(t5.getKey(), t1.getKey(), 1);
            g.connect(t1.getKey(), t3.getKey(), 5);
            g.connect(t3.getKey(), t5.getKey(), 6);
            g.connect(t2.getKey(), t4.getKey(), 1);
            g.connect(t2.getKey(), t6.getKey(), 4);
            g.connect(t4.getKey(), t7.getKey(), 3);
            g.connect(t7.getKey(), t6.getKey(), 1);
            g.connect(t7.getKey(), t5.getKey(), 6);
            g.connect(t2.getKey(), t7.getKey(), 1);
            g.connect(t6.getKey(), t5.getKey(), 1);
            g.connect(t4.getKey(), t6.getKey(), 3);
            // Window window = new Window();
//            MyPanel window = new MyPanel(g);
//            MyFrame w = new MyFrame(g);


            //	window.setVisible(true);


        }

    }
}
