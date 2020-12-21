//package test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import gameClient.CL_Pokemon;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import api.*;
//import gameClient.util.Point3D;
//
//public class PokemonTest {
//	private Point3D point=new Point3D(2,4,0);
//	private CL_Pokemon pokemon= new CL_Pokemon(point,-1,5.0);
//
//	@Test
//	public void getEdge() {
//		edge_data edge= new EdgeData(4,4, 2.3);
//		this.pokemon.set_edge(edge);
//		edge_data e= new EdgeData(4,4, 2.3);
//		assertEquals(e,pokemon.get_edge());
//	}
//	@Test
//	public void getID() {
//		assertEquals(pokemon.getID(), 4);
//	}
//	@Test
//	public void getType() {
//		assertEquals(pokemon.getType(), -1);
//	}
//	@Test
//	public void getValue() {
//		assertEquals(pokemon.getValue(), 5.0);
//	}
//	@Test
//	public void getInfo() {
//		boolean bol= pokemon.getInfo().equals("f");
//		assertTrue(bol);
//	}
//	@Test
//	public void getLocation() {
//		assertEquals(pokemon.getLocation(), this.point);
//	}
//}
