package PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

/*************************************
 * HeapAdaptablePriorityQueue Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 * 
 * With the 3 bottom methods added. 
 *************************************/
//An implementation of an adaptable priority queue using an array-based heap.
public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V> {

	//--------Nested AdaptablePQEntry Class---------
	protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V> {
		private int index;
		public AdaptablePQEntry(K key,V value,int j) {
			super(key,value);
			index = j;
		}
		public int getIndex() {return index;}
		public void setIndex(int j) {index = j;}
	}//-----End of Nested AdaptablePQEntry Class-----
	
	//Creates an empty adaptable priority queue using natural ordering of keys.
	public HeapAdaptablePriorityQueue() {super();}
	
	//Creates an empty adaptable priority queue using the given comparator.
	public HeapAdaptablePriorityQueue(Comparator<K> comp) {super(comp);}
	
	//protected utilities
	//Validates an entry to ensure it is location-aware.
	protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException {
		if(!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid entry");
		AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry;
		int j = locator.getIndex();
		if(j > heap.size() || heap.get(j) != locator)
			throw new IllegalArgumentException("Invalid entry");
		return locator;
	}
	
	//Exchanges the entries at indices i and j of the array.
	protected void swap(int i,int j) {
		super.swap(i,j);
		((AdaptablePQEntry<K,V>)heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K,V>)heap.get(j)).setIndex(j);
	}
	
	//Restores the heap property by moving the entry at index j upward/downward.
	protected void bubble(int j) {
		if(j > 0 && compare(heap.get(j),heap.get(parent(j))) < 0)
			upheap(j);
		else
			downheap(j); //although, it may not need to move. 
	}
	
	//Inserts a key-value pair and returns the entry created.
	public Entry<K,V> insert(K key,V value) throws IllegalArgumentException {
		checkKey(key); // might throw an exception
		Entry<K,V> newest = new AdaptablePQEntry<>(key,value,heap.size());
		heap.add(newest); // add to the end of the list
		upheap(heap.size() - 1); // upheap newly added entry
		return newest;
	}
	
	//Removes the given entry from the priority queue.
	@Override
	public void remove(Entry<K,V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate(entry);
		int j = locator.getIndex();
		if(j == heap.size() - 1) // entry is at last position
			heap.remove(heap.size() - 1); // so just remove it
		else {
			swap(j,heap.size() - 1); // swap entry to last position
			heap.remove(heap.size() - 1); // them remove it
			bubble(j); // and fix entry displaced by the swap
		}
	}
	
	//Replaces the key of an entry.
	@Override
	public void replaceKey(Entry<K,V> entry,K key) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate(entry);
		checkKey(key); // might throw an exception
 		locator.setKey(key); // method inherited from PQEntry
		bubble(locator.getIndex()); // with new key. may need to move entry
	}
	
	//Replaces the value of an entry
	@Override
	public void replaceValue(Entry<K,V> entry,V value) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate(entry);
		locator.setValue(value); // method inherited from PQEntry
	}	
	
	/******************************************
	 * An added iterator method that cycles 
	 * through the queue and returns an array 
	 * of all the values. 
	 ******************************************/
	public Object[] iterator() {
		Object[] array = new Object[heap.size()];
		int count = 0;
		for(Entry<K,V> v : heap) {
			array[count] = v.getValue();
			count++;
		}
		return array;
	}
	
	/******************************************
	 * Iterates through the queue and compares 
	 * how many have the same key as the one passed. 
	 * @param The key to be compared. 
	 ******************************************/
	public int howManyWithKey(K key) {
		int count = 0;
		for(Entry<K,V> v : heap) {
			if(v.getKey().equals(key))
				count++;
		}
		return count;
	}
	
	/******************************************
	 * Iterates through the heap and returns the 
	 * entry of the passed value.
	 * @param The value to be found. 
	 ******************************************/ 
	public Entry<K,V> getEntry(V value) {
		for(Entry<K,V> v : heap)
			if(value.equals(v.getValue()))
				return v;
		return null;
	}
}
