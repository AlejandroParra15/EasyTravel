package structures;

import java.util.List;
import java.util.Map;

/**
 * This interface has the basic methods every graph must have without depending on the representation used
 * @author Juan Pablo Herrera
 * @author Alejandro parra
 * @author Cristian Sanchez
 * @version 1.0
 * @param <V> represents the object contained in the graph
 */
public interface Graph<V> {
	
	/**
	 * Adds a vertex to the graph
	 * @param v The vertex to add
	 * @return True/false if the vertex was succesfully added or not
	 */
	public boolean addVertex(V v);
	
	/**
	 * Adds an edge between two vertices
	 * @param v initial vertex
	 * @param u final vertex
	 * @return true/false if the edge was succesfully added
	 */
	public boolean addEdge(V v, V u);
	
	/**
	 * Adds a weigthed edge between two vertices 
	 * @param v initial vertex
	 * @param u final vertex
	 * @param w weight of the edge
	 * @return true/false if the edge was succesfully added
	 */
	public boolean addEdge(V v, V u, int w);
	
	/**
	 * Removes am edge from the graph
	 * @param v Edge to be removed
	 * @return The removed edge
	 */
	public void removeVertex(V v);
	
	/**
	 * Removes the edge between two vertices
	 * @param v inital vertex
	 * @param u final vertex
	 * @return true/false if the edge was succesfully removed
	 */
	public void removeEdge(V v, V u);
	
	/**
	 * Tells if two vertices are connected
	 * @param v initial vertex
	 * @param u final vertex
	 * @return true/false if the two vertices are connected or not
	 */
	public boolean areConnected(V v, V u);
	
	/**
	 * Tells if the graph is directed
	 * @return true/false if the graph is directed or not
	 */
	public boolean isDirected();
	
	/**
	 * Calls to the bfs algorithm in order to find paths between vertices
	 * @param v initial vertex
	 * @return List of all the vertices
	 */
	public List<V> bfs(V v);
	
	/**
	 * Calls to the dfs algorithm in order to find paths between vertices
	 * @param v initial vertex
	 * @return List of all the vertices
	 */
	public List<V> dfs(V v);
	
	/**
	 * Calls the dijkstra algorithm in order to get the shortest paths from one vertex to all the others
	 * @param v initial vertex
	 * @return array with the shortest paths from the initial vertex to the others
	 */
	public int[] dijkstra(V v);
	
	/**
	 * calls the floyd-warshall algorithm to get the shortest paths from and to any vertex
	 * @return matrix with the shortest paths
	 */
	public int[][] floydWarshall();
	
	/**
	 * Calls the Kruskal algorithm to create the shortest path possible that connects all the vertices possibles for a possible disconnected graph
	 * @param p Weighted matrix
	 * @return Matrix showing the connections of the MST
	 */
	public int[][] Kruskal(int[][] p);
	
	/**
	 * Calls the Prim algorithm the generate a MST for a graph without disconeccions
	 * @param p Weighted matrix
	 * @return Array of the weight of the MST
	 */
	public int[] Prim(int[][] p);
	
	/**
	 * The method gives the amount of vertices in the graph
	 * @return amount of vertices
	 */
	public int getVertexSize();
	
	/**
	 * The method gives the map of the vertices with their respective position
	 * @return map with vertices and their position
	 */
	public Map<V, Integer> getVertices();
	
	/**
	 * Gives the position of a vertex
	 * @param vertex
	 * @return position of the vertex
	 */
	public int getIndex(V vertex);
	
	/**
	 * Gives all the adjacent vertex of a specified vertex
	 * @param vertex specified vertex
	 * @return List with all the vertices that have direct connection with vertex
	 */
	public List<V> vertexAdjacent(V vertex);
	
}
