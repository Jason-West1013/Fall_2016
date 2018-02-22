package PriorityQueue;
/*************************************
 * PriorityQueue Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 * 
 * Interface for the priority queue ADT
 *************************************/
public interface PriorityQueue<K,V> {
	int size();
	boolean isEmpty();
	Entry<K,V> insert(K key,V value) throws IllegalArgumentException;
	Entry<K,V> min();
	Entry<K,V> removeMin();
}
