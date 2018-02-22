package PriorityQueue;
/*************************************
 * Entry Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 * 
 * Interface for a key-value pair.
 *************************************/
public interface Entry<K,V> {
	K getKey(); // returns the key stored in this entry
	V getValue(); // returns the value stored in this entry
}
