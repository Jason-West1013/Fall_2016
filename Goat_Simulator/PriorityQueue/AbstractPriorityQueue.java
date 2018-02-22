package PriorityQueue;
import java.util.Comparator;

/*************************************
 * AbstractPriorityQueue Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 *************************************/
public class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {

	//------------------Nested PQEntry class----------------------
	protected static class PQEntry<K,V> implements Entry<K,V> {
		//Instance Variables
		private K _key;
		private V _value;
		
		public PQEntry(K key,V value) {
			_key = key;
			_value = value;
		}
		
		//methods of Entry interface.
		public K getKey() {return _key;}
		public V getValue() {return _value;}
		
		//Other utilities
		protected void setKey(K key) {_key = key;}
		protected void setValue(V value) {_value = value;}
	}//------------------ End of Nested PQEntry class-------------
	
	//Instance Variable
	private Comparator<K> _comp;
	
	//Overridden Constructor that creates an empty priority queue using the given comparator to order keys.
	protected AbstractPriorityQueue(Comparator<K> comp) {_comp = comp;}
	
	//Constructor that creates an empty priority queue based on the natural ordering of its keys.
	protected AbstractPriorityQueue() {this(new DefaultComparator<K>());}
	
	//Method for comparing two entries according to key.
	protected int compare(Entry<K,V> a,Entry<K,V> b) {return _comp.compare(a.getKey(),b.getKey());}
	
	//Determines whether a key is valid. 
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (_comp.compare(key,key) == 0); // see if a key can be compared to itself.
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key.");
		}
	}
	
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {return size() == 0;}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> removeMin() {
		// TODO Auto-generated method stub
		return null;
	}

}
