package PriorityQueue;
import java.util.Iterator;

/*************************************
 * AdaptablePriorityQueue Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 * 
 * Interface for the priority queue ADT
 *************************************/
public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V> {
	void remove(Entry<K,V> entry) throws IllegalArgumentException;
	void replaceKey(Entry<K,V> entry,K key) throws IllegalArgumentException;
	void replaceValue(Entry<K,V> entry,V value) throws IllegalArgumentException;
}
