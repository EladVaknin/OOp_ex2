package gameClient;

// imports
import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MyFrame extends JFrame {
	private int _ind;
	private gameClient.util.Range2Range _w2f;
	private Arena arena;
	private MyPanel MyPanel;

	// constructor
	public MyFrame(String a) {
		super(a);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateArena(Arena arena) {
		this.arena = arena;
		//initialize
		this.MyPanel = new MyPanel(arena.getGraph(), this.arena);
		this.add(this.MyPanel);
		this.MyPanel.updatePanel();
	}

	private void drawInfo(Graphics g) {
		List<String> str = arena.get_info();
		String dt = "none";
		for(int i=0;i<str.size();i++) {
			g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
		}

	}

	public void paint(Graphics graph) {
		this.MyPanel.updatePanel();
		this.MyPanel.repaint();
	}
}

