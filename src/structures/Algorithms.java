package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import java.util.Queue;

public class Algorithms {
	
	public static <V> List<V> bfs(Graph<V> g, V v){
		return traversal(g, v, new Stack<>());
	}
	
	public static <V> List<V> dfs (Graph<V> g, V v){
		return traversal(g, v, new LinkedList<V>());
	}
	
	private static <V> List<V> traversal(Graph<V> g, V v, Stack<V> ds){
		List<V> trav = new ArrayList<>();
		//Invariant: Each algorithm adds the given element first. 

		V vertex = v;
		ds.push(vertex);
		
		boolean[] visited = new boolean[g.getVertexSize()];
		
		//Invariant: While the traversal occurs, the given DS to be used will have, at least, one element.
		while(!ds.isEmpty()) {
			 //Invariant: Element added is always retired from the DS
			vertex = (V) ds.pop();
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
	
	private static <V> List<V> traversal(Graph<V> g, V v, Queue<V> ds){
		List<V> trav = new ArrayList<>();
		//Invariant: Each algorithm adds the given element first. 

		V vertex = v;
		ds.add(vertex);
		
		boolean[] visited = new boolean[g.getVertexSize()];
		
		//Invariant: While the traversal occurs, the given DS to be used will have, at least, one element.
		while(!ds.isEmpty()) {
			 //Invariant: Element added is always retired from the DS
			vertex = (V) ds.poll();
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
	
	public static <V> int[] dijkstra(int[][] g, int v){
		int[] dis = new int[g.length];
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
	
	private static int minIndex(int[] dis, boolean[] vis) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE;
        int indx = -1; 
  
        for (int i=0; i<dis.length; i++) 
            if (vis[i] == false && dis[i] <= min) { 
                min = dis[i]; 
                indx = i; 
            } 
  
        return indx; 
    }
	
	public static <V> int[][] floydWarshall(int[][] w){
		int n = w.length;
		int[][] D = w;
		int v = 0;
		
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
	
	public static int[][] Kruskal(int[][] pesos){
		
		DisjointSet<Integer> set = new DisjointSet<>();
		
		int[][] MST = new int[pesos.length][pesos.length];

		for(int i = 0; i < pesos.length; i++) {
			set.makeSet(i);
		}
		
		//-----------------------------------------------------------------
		class obj {
			int A;
			int B;
			int P;			
			obj(int a, int b, int p){
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
			int getP() {
				return P;
			}
		}
		//-----------------------------------------------------------------
		
		ArrayList<obj> aristas = new ArrayList<>();		
		for(int i = 0; i < pesos.length;  i++) {
			for(int j = 0; j < pesos.length; j++) {
				int peso = pesos[i][j];
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
	
	public static int minVertex(int[] weight, boolean[] inMst, int vertices){
		int minValue = Integer.MAX_VALUE;
		int minVertex = -1;
		for (int i = 0; i < vertices; i++) {
			if(inMst[i] == false && weight[i] < minValue){
				minValue = weight[i];
				minVertex = i;
			}
		}
		return minVertex;
	}
				
	public static int[] prim(int[][] matrix){
		int[] mst = new int[matrix.length];
		int[] weight = new int[matrix.length];
		boolean[] inMst = new boolean[matrix.length];
		
		for (int i = 0; i < matrix.length; i++) {
			weight[i] = Integer.MAX_VALUE;
		}		
		
		weight[0] = 0;
		mst[0] = -1;			
		
		for (int i = 0; i < matrix.length-1; i++) {
			int u = minVertex(weight, inMst, matrix.length);
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
