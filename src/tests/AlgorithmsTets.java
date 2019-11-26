package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import structures.*;

class AlgorithmsTets<V> {

	public void setupScenary() {
		WeightedMatrixGraph<V> WG = new  WeightedMatrixGraph<V>(4, false);
		AdjacencyListGraph<V> AG = new AdjacencyListGraph<V>(false);
	}

}
