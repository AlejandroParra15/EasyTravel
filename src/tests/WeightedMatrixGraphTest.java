package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Point;
import structures.WeightedMatrixGraph;

class WeightedMatrixGraphTest {
	
	private WeightedMatrixGraph<Point> WG;
	
	public void setupScenary() {
		WG = new  WeightedMatrixGraph<Point>(4, false);
	}
	
	@Test
	void WeightMatrixGraph() {
		setupScenary();
		if(WG == null) {
			fail();
		}
	}
	
	@Test
	void AddVertex() { //this also checks VertexSize method
		setupScenary();
		Point v1 = new Point("cauca", 200, 200, 1);
		WG.addVertex(v1);
		
		if(WG.getVertexSize() != 1) {
			fail();
		}
		
		Point v2 = new Point("cauca", 200, 200, 2);
		WG.addVertex(v2);
		
		if(WG.getVertexSize() != 2) {
			fail();
		}
	}
	
	@Test
	void AddEdge() { //this also checks the areConnected method
		setupScenary();
		
		Point v1 = new Point("cauca", 200, 200, 1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300, 2);
		WG.addVertex(v2);
		
		WG.addEdge(v1, v2, 5);
		
		if(!WG.areConnected(v1, v2)) {
			fail();
		}
		
	}
	
	@Test
	void removeEdge() {
		setupScenary();
		
		Point v1 = new Point("cauca", 200, 200, 1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300, 2);
		WG.addVertex(v2);
		
		WG.addEdge(v1, v2, 5);
		
		WG.removeEdge(v1,  v2);
		
		if(WG.areConnected(v1, v2)) {
			fail();
		}
		
	}
	
	@Test
	void removeVertex() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200, 1);
		WG.addVertex(v1);
		if(WG.getVertexSize() != 1) {
			fail();
		}
		Point v2 = new Point("cauca", 200, 200, 2);
		WG.addVertex(v2);
		
		WG.removeVertex(v1);
		WG.removeVertex(v2);
		
		if(WG.getVertexSize() != 0) {
			fail();
		}
	}
	
	@Test
	void isDirected() {
		setupScenary();
		if(WG.isDirected()) {
			fail();
		}
	}

}
