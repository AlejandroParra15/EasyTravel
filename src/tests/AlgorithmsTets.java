package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Point;
import structures.*;

class AlgorithmsTets<V> {
	
	private WeightedMatrixGraph<Point> WG;
	private AdjacencyListGraph<Point> AG;
	
	public void setupScenary() {
		WG = new  WeightedMatrixGraph<Point>(4, false);
		AG = new AdjacencyListGraph<Point>(false);
	}
	
	@Test
	void DFS() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		WG.addVertex(v2);
		Point v3 = new Point("cilo", 400, 400);
		AG.addVertex(v3);
		WG.addVertex(v3);
		
		AG.addEdge(v1, v2, 3);
		AG.addEdge(v2, v3, 4);
		AG.addEdge(v3, v1, 1);
		
		List<Point> x = Algorithms.dfs(WG, v1);
		List<Point> y = Algorithms.dfs(AG, v1);
		if(x.size() != 3 && y.size() != 3) {
			fail();
		}
	}
	
	@Test
	void BFS() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		WG.addVertex(v2);
		Point v3 = new Point("cilo", 400, 400);
		AG.addVertex(v3);
		WG.addVertex(v3);
		
		AG.addEdge(v1, v2, 3);
		AG.addEdge(v2, v3, 4);
		AG.addEdge(v3, v1, 1);
		
		List<Point> x = Algorithms.bfs(WG, v1);
		List<Point> y = Algorithms.bfs(AG, v1);
		if(x.size() != 3 && y.size() != 3) {
			fail();
		}
	}
	
	@Test
	void Dijkstra() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		WG.addVertex(v2);
		Point v3 = new Point("cilo", 400, 400);
		AG.addVertex(v3);
		WG.addVertex(v3);
		
		AG.addEdge(v1, v2, 3);
		AG.addEdge(v2, v3, 4);
		AG.addEdge(v3, v1, 1);
		
		int[] x = Algorithms.dijkstra(AG.getWeight(), 0);
		int[] y = Algorithms.dijkstra(AG.getWeight(), 0);
		if(x[0] != 0 && y[0] != 0) {
			fail();
		}
	}
	
	@Test
	void FloydWarshall() {
		setupScenary();
		Point v1 = new Point("cauca", 200, 200);
		AG.addVertex(v1);
		WG.addVertex(v1);
		Point v2 = new Point("milo", 300, 300);
		AG.addVertex(v2);
		WG.addVertex(v2);
		Point v3 = new Point("cilo", 400, 400);
		AG.addVertex(v3);
		WG.addVertex(v3);
		
		AG.addEdge(v1, v2, 3);
		AG.addEdge(v2, v3, 4);
		AG.addEdge(v3, v1, 1);
		
		int[][] x = Algorithms.floydWarshall(AG.getWeight());
		int[][] y = Algorithms.floydWarshall(AG.getWeight());
		for(int i=0; i<3; i++) {
			if(x[i][i] != 0 && y[i][i] != 0) {
				fail();
			}
		}
	}
	
	@Test
	void Kruskal() {
		setupScenary();
	}
	
	@Test
	void Prim() {
		setupScenary();
	}

}
