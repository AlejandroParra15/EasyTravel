package structures;

/**
 * Data structure used to store two values
 * @author Cristian Sanchez
 *
 * @param <V> First value object
 * @param <U> Second value object
 */
public class Duplex<V, U> {
	
	/**
	 * first element
	 */
	private V e1;
	/**
	 * second element
	 */
	private U e2;
	
	/**
	 * creates an object of the class duplex
	 * @param v First element to store
	 * @param u Second element to store
	 */
	public Duplex(V v, U u) {
		e1 = v;
		e2 = u;
	}
	
	/**
	 * Getter for the first element
	 * @return the first element
	 */
	public V getE1() {
		return e1;
	}

	/**
	 * Getter for the second element
	 * @return The second element
	 */
	public U getE2() {
		return e2;
	}
	
}
