package structures;

import java.util.*;

public class DisjointSet<E>{
	
	private HashMap<E,HashMap<E,E>> conjuntos;
	
	public DisjointSet(){		
		conjuntos = new HashMap<E,HashMap<E,E>>();
	}
	
	public boolean makeSet(E element){		
		if(findSet(element) == null) {
			
			HashMap<E,E> map = new HashMap<>();
			map.put(element, element);
			conjuntos.put(element, map);
			return true;
		
		} else {
			return false;
		}

	}

	public E findSet(E element){		
		if(conjuntos.get(element) == null) {			
			E representante = null;
			boolean found = false;	
			for(Map.Entry<E,HashMap<E,E>> entry : conjuntos.entrySet()) {		
				if(!found) {
					if(entry.getValue().containsKey(element)) {
						representante = entry.getKey();
						found = true;
					}
				}else {
					break; 
				}
			}		
			return representante;
		}else {
			return element;
		}
	}
	
	public ArrayList<E> getSet(E elemento) {		
		E representante = findSet(elemento); 	
		if(representante != null) {
			Set<E> conjunto = conjuntos.get(representante).keySet();
          	ArrayList<E> elementos = new ArrayList<>();
          	for(E key  : conjunto) {
          		elementos.add(key);	
          	}
          	return elementos;	
		} else {
			return null;
		}
	}

	public boolean union(E conjunto1, E conjunto2) {		
		E c1 = findSet(conjunto1);
		E c2 = findSet(conjunto2);		
		if(c1 != null && c2 != null) {		
			HashMap <E,E> conjuntoPrincipal = conjuntos.get(c1);
			Set<E> elementos = conjuntos.get(c2).keySet();	
			conjuntos.remove(c2);	
			
			for(E e : elementos) {
				conjuntoPrincipal.put(e, c1);
			}
			
			return true;
			
		} else {
			return false;	
		}
		
	}


	public int size() {
		return conjuntos.size();
	}
	
	
}
