package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Point;
import structures.*;

class AdjacencyListGraphTest<V> {
	
	private AdjacencyListGraph<Point> AG;
	
	public void setupScenary() {
		AdjacencyListGraph<V> AG = new AdjacencyListGraph<V>(false);
	}
	
	@Test
	void WeightMatrixGraph() {
		setupScenary();
		if(AG == null) {
			fail();
		}
	}
	
	@Test
	void AddVertex() { //this also checks VertexSize method
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		
		if(AG.getVertexSize() != 1) {
			fail();
		}
		
		Point v2 = new Point("cauca", 200, 200);
		AG.addVertex(v2);
		
		if(AG.getVertexSize() != 2) {
			fail();
		}
	}
	
	@Test
	void AddEdge() { //this also checks the areConnected method
		setupScenary();
		
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		
		AG.addEdge(v1, v2, 5);
		
		if(!AG.areConnected(v1, v2)) {
			fail();
		}
		
	}
	
	@Test
	void removeEdge() {
		setupScenary();
		
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		
		AG.addEdge(v1, v2, 5);
		
		AG.removeEdge(v1,  v2);
		
		if(AG.areConnected(v1, v2)) {
			fail();
		}
		
	}
	
	@Test
	void removeVertex() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		if(AG.getVertexSize() != 1) {
			fail();
		}
		Point v2 = new Point("cauca", 200, 200);
		AG.addVertex(v2);
		
		AG.removeVertex(v1);
		AG.removeVertex(v2);
		
		if(AG.getVertexSize() != 0) {
			fail();
		}
	}
	
	@Test
	void isDirected() {
		setupScenary();
		if(AG.isDirected()) {
			fail();
		}
	}

}
