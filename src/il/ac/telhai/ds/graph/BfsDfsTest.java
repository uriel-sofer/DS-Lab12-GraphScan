package il.ac.telhai.ds.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.Test;

public class BfsDfsTest {
	
	@Test
	public void testBfsSingleton () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		assertEquals("[A]", g.bfs("A").toString());
	}

	@Test
	public void testBfs2K1 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		assertEquals("[A]", g.bfs("A").toString());
		assertEquals("[B]", g.bfs("B").toString());
	}
	
	@Test
	public void testBfsInvalidSource () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		try {
			g.bfs("B");
			fail("Should throw exception");
		} catch (Exception e) {
		}  
	}

	@Test
	public void testBfsK2 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.putEdge("A", "B", 1.0);
		assertEquals("[A, B]", g.bfs("A").toString());
		assertEquals("[B, A]", g.bfs("B").toString());
	}

	@Test
	public void testBfsP4P1() {
		Graph<String, Double> p1 = new Graph<String, Double>();
		p1.add("A");
		p1.add("B");
		p1.add("C");
		p1.add("D");
		p1.add("E");
		p1.putEdge("A", "B", 1.0);
		p1.putEdge("B", "C", 1.0);
		p1.putEdge("C", "D", 1.0);
		assertEquals("[A, B, C, D]", p1.bfs("A").toString());
		assertEquals("[B, A, C, D]", p1.bfs("B").toString());
		assertEquals("[E]", p1.bfs("E").toString());
	}

	@Test
	public void testBfsLabGraph() {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		g.add("E");
		g.add("F");
		g.add("G");
		g.add("H");
		g.add("I");
		g.putEdge("A", "B", 1.0);
		g.putEdge("A", "C", 1.0);
		g.putEdge("A", "I", 1.0);
		g.putEdge("B", "C", 1.0);
		g.putEdge("B", "D", 1.5);
		g.putEdge("B", "G", 1.0);
		g.putEdge("C", "D", 1.0);
		g.putEdge("C", "H", 1.0);
		g.putEdge("C", "I", 1.0);
		g.putEdge("E", "F", 1.0);
		g.putEdge("G", "H", 1.0);
		g.putEdge("H", "I", 1.0);
		assertEquals("[B, A, C, D, G, I, H]", g.bfs("B").toString());
		assertEquals("[E, F]", g.bfs("E").toString());
	}
	
	@Test
	public void testDfsSingleton () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		assertEquals("[A]", g.dfs().toString());
	}

	@Test
	public void testDfs2K1 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		assertEquals("[A, B]", g.dfs().toString());
	}

	@Test
	public void testDfsK2 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.putEdge("A", "B", 1.0);
		assertEquals("[A, B]", g.dfs().toString());
	}

	@Test
	public void testDfsP4P1() {
		Graph<String, Double> p1 = new Graph<String, Double>();
		p1.add("A");
		p1.add("B");
		p1.add("C");
		p1.add("D");
		p1.add("E");
		p1.putEdge("A", "B", 1.0);
		p1.putEdge("B", "C", 1.0);
		p1.putEdge("C", "D", 1.0);
		assertEquals("[A, B, C, D, E]", p1.dfs().toString());
	}

	@Test
	public void testDfsLabGraph() {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		g.add("E");
		g.add("F");
		g.add("G");
		g.add("H");
		g.add("I");
		g.putEdge("A", "B", 1.0);
		g.putEdge("A", "C", 1.0);
		g.putEdge("A", "I", 1.0);
		g.putEdge("B", "C", 1.0);
		g.putEdge("B", "D", 1.5);
		g.putEdge("B", "G", 1.0);
		g.putEdge("C", "D", 1.0);
		g.putEdge("C", "H", 1.0);
		g.putEdge("C", "I", 1.0);
		g.putEdge("E", "F", 1.0);
		g.putEdge("G", "H", 1.0);
		g.putEdge("H", "I", 1.0);
		assertEquals("[A, B, C, D, H, G, I, E, F]", g.dfs().toString());
	}
}

