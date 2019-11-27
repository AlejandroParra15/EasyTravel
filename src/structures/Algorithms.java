package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import java.util.Queue;
 /**
  * This class stores the most common algorithms used in graphs
  * @author Cristian Sanchez
  * @author Juan Pablo Herrera
  * @author Alejandro Parra
  * @version 1.0
  *
  */
public class Algorithms {
	
	/**
	 * Breath First Search
	 * @param <V> Generic value of the vertex
	 * @param g Graph in which the algorithm is used
	 * @param v initial vertex
	 * @return List with the vertices of the graph
	 */
	public static <V> List<V> bfs(Graph<V> g, V v){
		return traversal(g, v, new LinkedList<V>());
	}
	
	/**
	 * Depth First Search
	 * @param <V> Generic value of the vertex
	 * @param g Graph in which the algorithm is used
	 * @param v initial vertex
	 * @return List with the vertices of the graph
	 */
	public static <V> List<V> dfs (Graph<V> g, V v){
		return traversal(g, v, new Stack<V>());
	}
	
	/**
	 * Method used by DFS to move through the graph using a stack
	 * @param <V>
	 * @param g Graph used
	 * @param v initial vertex
	 * @param ds Stack
	 * @return List with the vertices of the graph
	 */
	private static <V> List<V> traversal(Graph<V> g, V v, Stack<V> ds){
		List<V> trav = new ArrayList<>();

		V vertex = v;
		ds.push(vertex);
		
		boolean[] visited = new boolean[g.getVertexSize()];
		
		while(!ds.isEmpty()) {
			vertex = ds.pop();
			int indexV = g.getIndex(vertex);
			
			if(!visited[indexV]) {
				trav.add(vertex);
				visited[indexV] = true;
				
				List<V> adjacents = g.vertexAdjacent(vertex);
				ds.addAll(adjacents);
			}
		}
		return trav;
	}
	
	/**
	 * Method used by DFS to move through the graph using a stack
	 * @param <V>
	 * @param g Graph used
	 * @param v initial vertex
	 * @param ds Stack
	 * @return List with the vertices of the graph
	 */
	private static <V> List<V> traversal(Graph<V> g, V v, Queue<V> ds){
		List<V> trav = new ArrayList<>();

		V vertex = v;
		ds.add(vertex);
		
		boolean[] visited = new boolean[g.getVertexSize()];
		
		while(!ds.isEmpty()) {
			vertex = ds.poll();
			int indexV = g.getIndex(vertex);
			
			if(!visited[indexV]) {
				trav.add(vertex);
				visited[indexV] = true;
				
				List<V> adjacents = g.vertexAdjacent(vertex);
				ds.addAll(adjacents);
			}
		}
		return trav;
	}
	
	/**
	 * Dijkstras algorithm used in order to get shortest paths from an initial vertex 
	 * @param <V> generic value of the vertex
	 * @param g Weighted matrix
	 * @param v initial vertex
	 * @return Array with the shortest paths form the initial vertex
	 */
	public static <V> double[] dijkstra(double[][] g, int v){
		double[] dis = new double[g.length];
		boolean[] vis = new boolean[g.length];
		
		
		for (int i=0; i < dis.length; i++) { 
            dis[i] = Integer.MAX_VALUE; 
        }
		dis[v] = 0;
		
		for (int i=0; i<v-1; i++) {  
            int u = minIndex(dis, vis); 
  
            vis[u] = true; 

            for (int j=0; j<v; j++) {
                if (!vis[v] && g[u][v] != 0 && dis[u] != Integer.MAX_VALUE && dis[u] + g[u][v] < dis[v]) 
                   dis[v] = dis[u] + g[u][v]; 
            }
        }
		
		return dis;
		
	}
	
	/**
	 * Method to obtain the index of the minimum, and not visited, value of an array
	 * @param dis Array to search
	 * @param vis array of vertices visited
	 * @return Index of the minimum value
	 */
	private static int minIndex(double[] dis, boolean[] vis) { 
		double min = Integer.MAX_VALUE;
        int indx = -1; 
  
        for (int i=0; i<dis.length; i++) 
            if (vis[i] == false && dis[i] <= min) { 
                min = dis[i]; 
                indx = i; 
            } 
  
        return indx; 
    }
	
	/**
	 * Floyd-Warshalls method to obtain the shortest path form every vertex to each other
	 * @param <V> generic value of the vertices
	 * @param w Weighted matrix
	 * @return Matrix with the shortest path form the vertices to each other
	 */
	public static <V> double[][] floydWarshall(double[][] w){
		int n = w.length;
		double[][] D = w;
		double v = 0;
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(j != k || i != k) {
						if(D[i][k] != Integer.MAX_VALUE && D[k][j] != Integer.MAX_VALUE) {
							
							v = D[i][k] + D[k][j];
							
							if(D[i][j] > v) D[i][j] = v;
							
						}
						
					}
					
				}
			}
		}
		
		return D;
	}
	
	/**
	 * Kruskals algorithm to obtain the MST from a possibly Disconnected graph
	 * @param w Weighted matrix
	 * @return Matrix showing the connections of the MST
	 */
	public static double[][] Kruskal(double[][] w){
		
		DisjointSet<Integer> set = new DisjointSet<>();
		
		double[][] MST = new double[w.length][w.length];

		for(int i = 0; i < w.length; i++) {
			set.makeSet(i);
		}
		
		//-----------------------------------------------------------------
		class obj {
			int A;
			int B;
			double P;			
			obj(int a, int b, double p){
				A = a;
				B = b;
				P = p;
			}
			int getA() {
				return A;
			}	
			int getB() {
				return B;
			}
			double getP() {
				return P;
			}
		}
		//-----------------------------------------------------------------
		
		ArrayList<obj> aristas = new ArrayList<>();		
		for(int i = 0; i < w.length;  i++) {
			for(int j = 0; j < w.length; j++) {
				double peso = w[i][j];
				if(peso > 0 && peso < Integer.MAX_VALUE) {
					obj o = new obj(i, j, peso);
					aristas.add(o);
				}
			}
		}	
		
		Comparator<obj> comparador = new Comparator<obj>() {
			@Override
			public int compare(obj a, obj b) {
				if(a.getP() > b.getP()) {
					return  1;
				} else if (a.getP() < b.getP()) {
					return -1;
				}else {
					return 0;
				}
			}
		};	
		aristas.sort(comparador);	
		
		for(int i = 0; i < aristas.size(); i++) {
			obj arista = aristas.get(i);
			if(set.findSet(arista.getA()) != set.findSet(arista.getB())) {
				set.union(arista.getA(), arista.getB());
				MST[arista.getA()][arista.getB()] = arista.getP();
				MST[arista.getB()][arista.getA()] = arista.getP();
			}
		}		
		return MST;
	}
	
	/**
	 * Prims algorithm to find the MST of a graph without any Disconnections
	 * @param matrix Weighted matrix
	 * @return array with the weight of the MST
	 */
	public static double[] prim(double[][] matrix){
		double[] mst = new double[matrix.length];
		double[] weight = new double[matrix.length];
		boolean[] inMst = new boolean[matrix.length];
		
		for (int i = 0; i < matrix.length; i++) {
			weight[i] = Integer.MAX_VALUE;
		}		
		
		weight[0] = 0;
		mst[0] = -1;			
		
		for (int i = 0; i < matrix.length-1; i++) {
			int u = minIndex(weight, inMst);
			inMst[u] = true;
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[u][j] != 0 && inMst[j] == false && matrix[u][j] < weight[j]){
					mst[j] = u;
					weight[j] = matrix[u][j];
				}
			}
		}
		
		return weight;
	}
	
}
