package gameClient;
//
//// imports
//import api.*;
//import gameClient.Arena;
//import gameClient.CL_Agent;
//import gameClient.CL_Pokemon;
//import gameClient.util.Point3D;
//import gameClient.util.Range;
//import gameClient.util.Range2D;
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
///**
// * this class is the game panel control that show everything in the window while the game is running
// */
//public class GamePanel extends JPanel {
//
//    private Graphics2D G2D;
//    private Image agent, pokaball1, pokaball2, background;
//    private Arena arena;
//    private directed_weighted_graph graph;
//    private gameClient.util.Range2Range W2F;
//    private static String FONT = "Arial";
//
//    // constructor
//    public GamePanel(directed_weighted_graph g, Arena a) {
//        super();
//        this.arena = a;
//        this.graph = g;
//        this.agent = new ImageIcon("./images/player.png").getImage();
//        this.pokaball1 = new ImageIcon("./images/pokaball1.png").getImage();
//        this.pokaball2 = new ImageIcon("./images/pokaball2.png").getImage();
//        this.background = new ImageIcon("./images/back2.png").getImage();
//    }
//
//    /**
//     * update panel
//     */
//    public void updatePanel() {
//        double j = ((this.getHeight() * this.getWidth()) / 4000);
//        double k = ((this.getHeight() * this.getWidth()) / 100000);
//        Range rx = new Range(k, this.getWidth() - 10);
//        Range ry = new Range(this.getHeight() - 10, j);
//        Range2D frame = new Range2D(rx, ry);
//        directed_weighted_graph g = this.arena.getGraph();
//        this.W2F = Arena.w2f(g, frame);
//    }
//
//    /**
//     * draw all
//     * @param graphics
//     */
//    public void paint(Graphics graphics) {
//        this.G2D = (Graphics2D) graphics;
//        int w = this.getWidth();
//        int h = this.getHeight();
//        graphics.clearRect(0, 0, w, h);
//        this.G2D.drawImage(this.background, 0, 0, w, h, null);
//        drawGraph(graphics);
//        drawPokemons(graphics);
//        drawAgents(graphics);
//        drawInfo(graphics);
//    }
//
//    /**
//     * draw each node
//     * @param node
//     * @param graphics
//     */
//    public void drawNode(node_data node, Graphics graphics) {
//        geo_location p = node.getLocation();
//        geo_location f = this.W2F.world2frame(p);
//        graphics.fillOval((int) f.x() - 5, (int) f.y() - 5, 2 * 5, 2 * 5);
//        graphics.drawString("" + node.getKey(), (int) f.x(), (int) f.y() - 4 * 5);
//    }
//
//    /**
//     * draw the graph
//     * @param graphics
//     */
//    public void drawGraph(Graphics graphics) {
//        this.G2D = (Graphics2D)graphics;
//        for(node_data node : this.graph.getV()) {
//            Font font = new Font(FONT, Font.BOLD, 12);
//            graphics.setFont(font);
//            graphics.setColor(Color.CYAN);
//            drawNode(node, graphics);
//            for(edge_data e : this.graph.getE(node.getKey())) {
//                this.G2D.setStroke(new BasicStroke(2));
//                this.G2D.setColor(Color.WHITE);
//                drawEdge(e, this.G2D, this.graph);
//            }
//        }
//    }
//
//    /**
//     * draw game info
//     * @param graphics
//     */
//    private void drawInfo(Graphics graphics) {
//
//        Graphics2D g2D = (Graphics2D)graphics;
//
//        g2D.setColor(Color.WHITE);
//        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
//
//        int x0 = this.getWidth() / 70;
//        int y0 = this.getHeight() / 20;
//
//        // print time left
//        g2D.drawString("" + String.valueOf(arena.getTime() / 1000), x0 * 34, y0 + 2);
//        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
//
//        int game_level = this.arena.getLevel();
//        int grade = this.arena.getGrade();
//        int moves = this.arena.getMoves();
//
//        // print game level
//        g2D.drawString("" + game_level, x0 * 22, y0 + 2);
//        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
//
//        // print grade
//        g2D.drawString("" + grade, x0 * 44, y0 + 2);
//        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
//
//        // print moves
//        g2D.drawString("" + moves, x0 * 55, y0 + 2);
//        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
//    }
//
//    /**
//     * draw each edge
//     * @param edge
//     * @param graphics
//     * @param graph
//     */
//    public void drawEdge(edge_data edge, Graphics graphics, directed_weighted_graph graph) {
//        geo_location src = graph.getNode(edge.getSrc()).getLocation();
//        geo_location dest = graph.getNode(edge.getDest()).getLocation();
//        geo_location s0 = this.W2F.world2frame(src);
//        geo_location d0 = this.W2F.world2frame(dest);
//        graphics.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
//    }
//
//    /**
//     * draw each pokemon on the graph
//     * @param graphics
//     */
//    private void drawPokemons(Graphics graphics) {
//        this.G2D = (Graphics2D)graphics;
//        List<CL_Pokemon> pokemons = this.arena.getPokemons();
//        if(pokemons != null) {
//            for(CL_Pokemon pokemon : pokemons) {
//                Point3D point = pokemon.getLocation();
//                int r = 9;
//                if(point != null) {
//                    geo_location fp = this.W2F.world2frame(point);
//                    if(pokemon.getType() < 0) G2D.drawImage(this.pokaball1, (int) fp.x() - 3 * r, (int) fp.y() - r, 3 * r, 3 * r, null);
//                    else this.G2D.drawImage(this.pokaball2, (int) fp.x() - r + 15, (int) fp.y() - r, 3 * r, 3 * r, null);
//                }
//            }
//        }
//    }
//
//    /**
//     * draw each agents on the graph
//     * @param graphics
//     */
//    private void drawAgents(Graphics graphics) {
//        this.G2D = (Graphics2D)graphics;
//        List<CL_Agent> agents = this.arena.getAgents();
//        graphics.setColor(Color.red);
//        int i = 0;
//        while(agents != null && i < agents.size()) {
//            geo_location location = agents.get(i).getLocation();
//            int r = 8;
//            i++;
//            if(location != null) {
//                geo_location fp = this.W2F.world2frame(location);
//                this.G2D.drawImage(this.agent, (int) fp.x() - 2 * r, (int) fp.y() - r, 5 * r, 5 * r, null);
//            }
//        }
//    }
//}


import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;


public class MyPanel extends JPanel {
    private int _ind;
    private Graphics2D g2D;
    private Image agent ;
    private Image pikacho ;
    private Image balbazor ;
    private Image background;
    directed_weighted_graph graph;
    private Arena _ar;
    private static String FONT = "Arial";
    private gameClient.util.Range2Range _w2f;

    // constructor
    public MyPanel(directed_weighted_graph g, Arena a) {
        super();
        agent = new ImageIcon("./Sources/agent.jpeg").getImage();
        pikacho = new ImageIcon("./Sources/pikacho.jpeg").getImage();
        balbazor = new ImageIcon("./Sources/balbazor.jpeg").getImage();
        background = new ImageIcon("./Sources/background.jpeg").getImage();
        _ar = a;
        graph = g;
    }
//    public void update(Arena ar) {
//        this._ar = ar;
//        updatePanel();
//    }

    public void updatePanel() {
        double a = ((this.getHeight() * this.getWidth()) / 4000);
        double b= ((this.getHeight() * this.getWidth()) / 100000);
        Range rx = new Range(a,this.getWidth()-10);
        Range ry = new Range(this.getHeight()-10,b);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g,frame);
    }
    public void paint(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight();
        g2D = (Graphics2D) g;
        g.clearRect(0, 0, w, h);
        g2D.drawImage(background, 0, 0, w, h, null);
        // updateFrame();
        drawPokemons(g);
        drawGraph(g);
        drawAgants(g);
        drawInfo(g);


    }
    private void drawInfo1(Graphics g) {
        java.util.List<String> str = _ar.get_info();
        String dt = "none";
        for(int i=0;i<str.size();i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawGraph(Graphics g) {
        g2D = (Graphics2D) g;
        for(node_data node : graph.getV()) {
            Font font = new Font(FONT, Font.BOLD, 12);
            g.setFont(font);
            g.setColor(Color.black);
            drawNode(node, g);
            for (edge_data e : graph.getE(node.getKey())) {
                g2D.setStroke(new BasicStroke(2));
                g2D.setColor(Color.BLUE);
                drawEdge(e, g2D, graph);
            }
        }
    }
    private void drawPokemons(Graphics g) {
        g2D = (Graphics2D) g;
        java.util.List<CL_Pokemon> fs = _ar.getPokemons();
        if(fs!=null) {
            Iterator<CL_Pokemon> itr = fs.iterator();
            while(itr.hasNext()) {
                CL_Pokemon f = itr.next();
                Point3D c = f.getLocation();
                int r=10;
                if(c!=null) {
                    geo_location fp = this._w2f.world2frame(c);
                    if (f.getType() < 0) {
                        g2D.drawImage(pikacho, (int) fp.x() - 6 * r, (int) fp.y() - r, 6 * r, 6 * r, null);
                    } else {
                        g2D.drawImage(balbazor, (int) fp.x() - r, (int) fp.y() - r, 6 * r, 6 * r, null);
                    }
                }
            }
        }
    }
    private void drawAgants(Graphics g) {
        g2D = (Graphics2D) g;
        List<CL_Agent> rs = _ar.getAgents();
        // Iterator<OOP_Point3D> itr = rs.iterator();
        g.setColor(Color.red);
        int i=0;
        while(rs!=null && i<rs.size()) {
            geo_location c = rs.get(i).getLocation();
            int r=8;
            i++;
            if(c!=null) {

                geo_location fp = this._w2f.world2frame(c);
                g2D.drawImage(agent, (int) fp.x() - 3 * r, (int) fp.y() - r, 8 * r, 8 * r, null);
            }
        }
    }
    private void drawNode(node_data n, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int)fp.x()-5, (int)fp.y()-5, 4*5, 4*5);
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*5);
    }
    private void drawEdge(edge_data e, Graphics g,directed_weighted_graph gra) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());

    }

    private void drawInfo(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        int x = this.getWidth() / 350;
        int y = this.getHeight() / 20;
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
        g2D.drawString("Time left: " + String.valueOf(_ar.getTime() / 1000), x * 30, y - 10);
        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
        int senarion = this._ar.getLevel();
        int moves = this._ar.getMoves();
        int grade = this._ar.getGrade();
        g2D.drawString("Level: " + senarion, x * 30, y + 10);
        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
        g2D.drawString("Moves: " + moves, x * 30, y + 30);
        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
        g2D.drawString("Grade " + grade, x * 30, y + 50);
        g2D.setFont(new Font(FONT, Font.PLAIN, (this.getHeight() * this.getWidth()) / 40000));
    }
//private void drawInfo(Graphics g){
//    long current_time = this._ar.getGame().timeToEnd();
//    String time = "Time to end: "+(current_time/1000)+" seconds";
//    int game_scenario = this._ar.getScenario();
//    String scenario = "game scenario: "+game_scenario;
//    g.setColor(Color.BLACK);
//    Font font= new Font("Forte", Font.CENTER_BASELINE, 30);
//    g.setFont(font);
//    g.drawString(scenario,10,30);
//    g.setFont(font);
//    g.drawString(time,15,80);
//
//}

}
